package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.common.RegexUtils;
import com.cefothe.bgjudge.io.FileIO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.workers.ProgramLanguages;
import com.cefothe.bgjudge.workers.calculation.ScoreCalculation;
import com.cefothe.bgjudge.workers.checkers.Checker;
import com.cefothe.bgjudge.workers.compilers.CompilationResult;
import com.cefothe.bgjudge.workers.compilers.Compiler;
import com.cefothe.bgjudge.workers.entities.Test;
import com.cefothe.bgjudge.workers.entities.TestResults;
import com.cefothe.bgjudge.workers.executors.Executor;
import com.cefothe.bgjudge.workers.executors.ExecutorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by cefothe on 08.05.17.
 */
@Component
public class StrategyBean implements Strategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyBean.class);

    private final SubmissionRepository submissionRepository;

    private final Checker checker;

    private final ScoreCalculation scoreCalculation;

    private final FileIO fileIO;

    @Autowired
    public StrategyBean(SubmissionRepository submissionRepository, Checker checker, ScoreCalculation scoreCalculation, FileIO fileIO) {
        this.submissionRepository = submissionRepository;
        this.checker = checker;
        this.scoreCalculation = scoreCalculation;
        this.fileIO = fileIO;
    }

    @Async
    @Override
    public Future<Submission> execute(Long submissionId) throws IOException {
        Submission submission = submissionRepository.findOne(submissionId);
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        changeSubmissionStatus(SubmissionStatus.IN_PROGRESS, submission);
        LOGGER.info("Start executing submission with id {}", submissionId);
        String fileName = RegexUtils.findClassName(submission.getCode());
        File file = fileIO.write(submission.getCode(), programLanguages.fileWithExtension(fileName));

        CompilationResult compilationResult = compile(programLanguages, file);

        if (compilationResult.getErrorStream() != null) {
            Test test = new Test(compilationResult.getErrorStream());
            submission.addTest(test);
        } else {
            submission.addTest(executeTests(programLanguages, file, submission));
        }

        checker.check(submission);
        scoreCalculation.calculation(submission);

        changeSubmissionStatus(SubmissionStatus.COMPLETED,submission);
        save(submission);
        fileIO.deleteDirectory(file);
        return new AsyncResult<>(submission);
    }

    private List<Test> executeTests(ProgramLanguages programLanguages, File file, Submission submission) throws IOException {
        Task task = submission.getTask();
        List<Test> tests = new ArrayList<>();
        for (TaskParam taskParam : task.getTaskPrams()) {
            ExecutorResult executorResult = executor(programLanguages, file, new ExecutorResult(taskParam.getInputs()));
            tests.add(executorToTest(executorResult, taskParam));
        }
        return tests;
    }

    private CompilationResult compile(ProgramLanguages programLanguages, File file) throws IOException {
        Compiler compiler = programLanguages.compiler();
        return compiler.compile(file);
    }

    private ExecutorResult executor(ProgramLanguages programLanguages, File file, ExecutorResult executorResult) throws IOException {
        Executor executor = programLanguages.executor();
        return executor.execute(file, executorResult);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void changeSubmissionStatus(SubmissionStatus submissionStatus, Submission submission) {
        submission.setStatus(submissionStatus);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void save(Submission submission){
        submissionRepository.save(submission);
    }

    private Test executorToTest(ExecutorResult executorResult, TaskParam taskParam) {
        String runtimeError = executorResult.getErrorStream();
        String actualOutput = executorResult.getOutput().stream().collect(Collectors.joining(System.lineSeparator()));
        TestResults testResults = new TestResults(taskParam, actualOutput, runtimeError);
        return new Test(testResults);
    }

}
