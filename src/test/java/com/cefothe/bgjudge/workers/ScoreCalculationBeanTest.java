package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.workers.calculation.ScoreCalculation;
import com.cefothe.bgjudge.workers.checkers.Checker;
import com.cefothe.bgjudge.workers.entities.TestResults;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
public class ScoreCalculationBeanTest {

    @Autowired
    private ScoreCalculation scoreCalculation;

    @Autowired
    private Checker checker;

    private TaskParam taskParamFirst;
    private TaskParam taskParamSecond;

    @Before
    public void setUp(){
        this.taskParamFirst = new TaskParam("Hello World","Hello World");
        this.taskParamSecond = new TaskParam("Hello Stefan","Hello Stefan");

    }

    @Test
    public void calculateMaxScore(){
        // Given
        TestResults testResultsFirst = new TestResults(taskParamFirst, "Hello World", null);
        TestResults testResultsSecond = new TestResults(taskParamSecond, "Hello Stefan", null);
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test(testResultsFirst);
        com.cefothe.bgjudge.workers.entities.Test test2 = new com.cefothe.bgjudge.workers.entities.Test(testResultsSecond);
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1,test2));

        // When
        checker.check(submission);
        scoreCalculation.calculation(submission);

        //Then
        assertThat(submission.getResult(), Matchers.equalTo(100));
    }

    @Test
    public void calculateScoreWithTwoPassAndOneFail(){
        // Given
        TestResults testResultsFirst = new TestResults(taskParamFirst, "Hello World", null);
        TestResults testResultsSecond = new TestResults(taskParamSecond, "Hello Stefan", null);
        TestResults testResultsThird = new TestResults(taskParamSecond, "Hello Ivan", null);
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test(testResultsFirst);
        com.cefothe.bgjudge.workers.entities.Test test2 = new com.cefothe.bgjudge.workers.entities.Test(testResultsSecond);
        com.cefothe.bgjudge.workers.entities.Test test3 = new com.cefothe.bgjudge.workers.entities.Test(testResultsThird);
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1,test2,test3));

        // When
        checker.check(submission);
        scoreCalculation.calculation(submission);

        //Then
        assertThat(submission.getResult(), Matchers.equalTo(66));
    }
}
