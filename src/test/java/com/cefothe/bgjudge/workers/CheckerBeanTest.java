package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
public class CheckerBeanTest {

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
    public void checkWithAllEquals(){

        // Given
        TestResults testResultsFirst = new TestResults(taskParamFirst, "Hello World", null);
        TestResults testResultsSecond = new TestResults(taskParamSecond, "Hello Stefan", null);
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test(testResultsFirst);
        com.cefothe.bgjudge.workers.entities.Test test2 = new com.cefothe.bgjudge.workers.entities.Test(testResultsSecond);
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1,test2));

        // When
        checker.check(submission);
        List<com.cefothe.bgjudge.workers.entities.Test> exceptedResults =submission.getTests();

        // Then
        assertThat(exceptedResults, Matchers.hasSize(2));
        assertThat(true,  Matchers.equalTo(exceptedResults.get(0).isEquals()));
        assertThat(true,  Matchers.equalTo(exceptedResults.get(1).isEquals()));
    }


    @Test
    public void checkWithOneDifferents(){
        // Given
        TaskParam taskParamFirst = new TaskParam("Hello World","Hello World");
        TaskParam taskParamSecond = new TaskParam("Hello Stefan","Hello Stefan");
        TestResults testResultsFirst = new TestResults(taskParamFirst, "Hello World", null);
        TestResults testResultsSecond = new TestResults(taskParamSecond, "Hello Ivan", null);
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test(testResultsFirst);
        com.cefothe.bgjudge.workers.entities.Test test2 = new com.cefothe.bgjudge.workers.entities.Test(testResultsSecond);
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1,test2));

        // When
        checker.check(submission);
        List<com.cefothe.bgjudge.workers.entities.Test> exceptedResults =submission.getTests();

        // Then
        assertThat(exceptedResults, Matchers.hasSize(2));
        assertThat(true,  Matchers.equalTo(exceptedResults.get(0).isEquals()));
        assertThat(false,  Matchers.equalTo(exceptedResults.get(1).isEquals()));

    }

    @Test
    public void checkWithCompillerError(){
        // Given
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test("Compiler error");
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1));

        // When
        checker.check(submission);
        List<com.cefothe.bgjudge.workers.entities.Test> exceptedResults =submission.getTests();

        // Then
        assertThat(exceptedResults, Matchers.hasSize(1));
        assertThat(false,  Matchers.equalTo(exceptedResults.get(0).isEquals()));

    }

    @Test
    public void checkWithRuntimeError(){
        // Given
        TestResults testResultsFirst = new TestResults(taskParamFirst, null, "Runtime error");
        com.cefothe.bgjudge.workers.entities.Test test1 = new com.cefothe.bgjudge.workers.entities.Test(testResultsFirst);
        Submission submission = new Submission(null, null, null , null, SubmissionStatus.IN_PROGRESS);
        submission.addTest(Arrays.asList(test1));

        // When
        checker.check(submission);
        List<com.cefothe.bgjudge.workers.entities.Test> exceptedResults =submission.getTests();

        // Then
        assertThat(exceptedResults, Matchers.hasSize(1));
        assertThat(false,  Matchers.equalTo(exceptedResults.get(0).isEquals()));

    }
}


