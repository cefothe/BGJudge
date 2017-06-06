package com.cefothe.bgjudge.workers.calculation;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.workers.entities.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/2/17.
 */
@Component
public class ScoreCalculationBean implements ScoreCalculation {

    @Value("${submission.max.score}")
    private int maxScore;

    @Override
    public void calculation(Submission submission) {
        List<Test> tests = submission.getTests();
        int numberOfPassedTest = (int) tests.stream().filter(test -> test.isEquals()).count();
        submission.setResult(new Integer((this.maxScore*numberOfPassedTest/tests.size())));
    }
}
