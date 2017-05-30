package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.workers.ProgramLanguages;
import com.cefothe.bgjudge.workers.compilers.CompilationResult;
import com.cefothe.bgjudge.workers.compilers.Compiler;
import com.cefothe.bgjudge.workers.entities.Test;
import com.cefothe.bgjudge.workers.entities.TestResults;
import com.cefothe.bgjudge.workers.executors.Executor;
import com.cefothe.bgjudge.workers.executors.ExecutorResult;
import com.cefothe.bgjudge.workers.repositories.TestRepository;
import com.cefothe.bgjudge.workers.repositories.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cefothe on 08.05.17.
 */
@Component
public class StrategyBean implements Strategy {

    private final TestRepository testRepository;

    private final TestResultsRepository testResultsRepository;

    private final SubmissionRepository submissionRepository;

    @Autowired
    public StrategyBean(TestRepository testRepository, TestResultsRepository testResultsRepository, SubmissionRepository submissionRepository) {
        this.testRepository = testRepository;
        this.testResultsRepository = testResultsRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(ProgramLanguages programLanguages, Submission submission, File file) throws IOException {
        CompilationResult compilationResult = compile(programLanguages, file);
        if (compilationResult.getErrorStream() != null) {
            Test test = new Test(compilationResult.getErrorStream());
            submission.addTest(test);
            return;
        }

        Task task = submission.getTask();
        List<Test> tests = new ArrayList<>();
        for (TaskParam taskParam : task.getTaskPrams()) {
            ExecutorResult executorResult = executor(programLanguages, file, new ExecutorResult(taskParam.getInputs()));
            tests.add(executorToTest(executorResult, taskParam));
        }
        submission.addTest(tests);
        submissionRepository.save(submission);
    }

    private CompilationResult compile(ProgramLanguages programLanguages, File file) throws IOException {
        Compiler compiler = programLanguages.compiler();
        return compiler.compile(file);
    }

    private ExecutorResult executor(ProgramLanguages programLanguages, File file, ExecutorResult executorResult) throws IOException {
        Executor executor = programLanguages.executor();
        return executor.execute(file, executorResult);
    }

    private Test executorToTest(ExecutorResult executorResult, TaskParam taskParam){
       String runtimeError = executorResult.getErrorStream();
       String actualOutput = executorResult.getOutput().stream().collect(Collectors.joining(System.lineSeparator()));
       TestResults testResults = new TestResults(taskParam, actualOutput, runtimeError);
       return  new Test(testResults);
    }

}
