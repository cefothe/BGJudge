package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import com.cefothe.bgjudge.workers.ProgramLanguages;
import com.cefothe.bgjudge.workers.compilers.CompilationResult;
import com.cefothe.bgjudge.workers.compilers.Compiler;
import com.cefothe.bgjudge.workers.executors.Executor;
import com.cefothe.bgjudge.workers.executors.ExecutorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by cefothe on 08.05.17.
 */
@Component
public class StrategyBean implements Strategy {

    @Override
    public void execute(ProgramLanguages programLanguages, Submission submission, File file) throws IOException {
        CompilationResult compilationResult = compile(programLanguages,file);
        if(compilationResult.getErrorStream() != null){
            Task task = submission.getTask();
            for(TaskParam taskParam: task.getTaskPrams()){
                executor(programLanguages, file, new ExecutorResult(taskParam.getInputs()));
            }
        }
    }

    private CompilationResult compile(ProgramLanguages programLanguages, File file) throws IOException {
        Compiler compiler = programLanguages.compiler();
        return compiler.compile(file);
    }

    private ExecutorResult executor(ProgramLanguages programLanguages, File file, ExecutorResult executorResult) throws IOException {
        Executor executor = programLanguages.executor();
        return executor.execute(file, executorResult);
    }

}
