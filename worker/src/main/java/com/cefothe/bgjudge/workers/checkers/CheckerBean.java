package com.cefothe.bgjudge.workers.checkers;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.workers.entities.Test;
import com.cefothe.bgjudge.workers.entities.TestResults;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/1/17.
 */
@Component
public class CheckerBean implements  Checker {

    @Override
    public void check(@NonNull Submission submission){
        if(submission.getStatus() == SubmissionStatus.IN_PROGRESS){
            List<Test> result =  submission.getTests();
            result.stream().filter(currentResult-> currentResult.getCompilerError() == null)
                    .forEach(currentResult -> {
                        TestResults testResults = currentResult.getTestResults();
                        String expectedOutput = testResults.getTaskParam().getOutput();
                        if(testResults.getRuntimeError()==null && testResults.getActualOutput().equals(expectedOutput)){
                            currentResult.setEquals(true);
                        }
                    });
        }
    }
}
