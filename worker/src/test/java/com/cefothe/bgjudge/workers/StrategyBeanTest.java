package com.cefothe.bgjudge.workers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.Future;

import com.cefothe.bgjudge.WorkerApplication;
import com.cefothe.bgjudge.exam.entities.ExamSecurity;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import com.cefothe.bgjudge.workers.repositories.TestRepository;
import com.cefothe.bgjudge.workers.repositories.TestResultsRepository;
import com.cefothe.bgjudge.workers.strategies.Strategy;
import com.cefothe.common.entities.BaseEntity;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by cefothe on 31.05.17.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-unittest.properties")
@ContextConfiguration(classes = WorkerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "non-async")
@DirtiesContext
@AutoConfigureTestDatabase(connection = H2)
public class StrategyBeanTest {

    @Autowired
    public TestResultsRepository testResultsRepository;

    @Autowired
    public TestRepository testRepository;

    @Autowired
    public SubmissionRepository submissionRepository;


    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ExamRepository examRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public Strategy strategy;

   	public  User user;

    @Configuration
    @Profile("non-async")
    public static class Config{

    }

    @Before
    public void before() {
        Role role = new Role("student");
        saveRepository(roleRepository, role);
    }

    @After
    public void after(){
        submissionRepository.deleteAll();
        examRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRepository(CrudRepository repository, BaseEntity baseEntity) {
        repository.save(baseEntity);
    }

    @After
    public void clearCompilerDirectoryFromClasses() {
        File directory = new File(JavaCompilerTest.class.getResource("/compiler/").getFile());
        Arrays.stream(directory.listFiles((f, p) -> p.endsWith("class"))).forEach(File::delete);
    }

    @Test
    public void strategyTest() throws IOException, InterruptedException {

     	User user = new User("test1", "test", new UserInformation("Stefan", "Angelov", "cefothe1@gmail.com"), roleRepository.findById(1L).get());
		userRepository.save(user);

        File file = new File(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getFile());
        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, new ExamSecurity("Test", Collections.emptyList()));
        Task task = new Task("Test", "test");
        TaskParam taskParam = new TaskParam("Hello World", "Hello World");

        task.addTaskParam(taskParam);
        exam.addTask(task);
        saveRepository(examRepository, exam);


        Submission submission = createSubmision(user, exam, task, true);

        Future<Submission> submissionFuture = strategy.execute(submission.getId());
        wait(submissionFuture);

        Submission expected = submissionRepository.findById(submission.getId()).get();

        assertThat(expected.getResult(), Matchers.equalTo(100));
        assertThat(expected.getTests(), notNullValue());
        assertThat(expected.getTests(), hasSize(1));
        assertThat(expected.getTests().get(0).getCompilerError(), isEmptyOrNullString());
    }

    @Test
    public void strategyTestWith50Result() throws IOException, InterruptedException {

		User user = new User("test2", "test", new UserInformation("Stefan", "Angelov", "cefothe2@gmail.com"), roleRepository.findById(1L).get());
		userRepository.save(user);

        File file = new File(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getFile());
        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, new ExamSecurity("Test", Collections.emptyList()));
        Task task = new Task("Test", "test");
        TaskParam taskParam = new TaskParam("Hello World", "Hello World");
        TaskParam taskParamSecond = new TaskParam("Hello Stefan", "Hello Ivan");

        task.addTaskParam(taskParam);
        task.addTaskParam(taskParamSecond);
        exam.addTask(task);
        saveRepository(examRepository, exam);


        Submission submission = createSubmision(user, exam, task, true);

        Future<Submission> submissionFuture = strategy.execute(submission.getId());
        wait(submissionFuture);

        Submission expected = submissionRepository.findById(submission.getId()).get();

        assertThat(expected.getResult(), Matchers.equalTo(50));
        assertThat(expected.getTests(), notNullValue());
        assertThat(expected.getTests(), hasSize(2));
        assertThat(expected.getTests().get(0).getCompilerError(), isEmptyOrNullString());
    }


    public Submission createSubmision(User user, Examens exam, Task task, boolean needToBePersist) throws IOException {
        String code = new String(Files.readAllBytes(Paths.get(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getPath())));
        Submission submission = new Submission(user, task, exam, code, SubmissionStatus.IN_PROGRESS);
        if (needToBePersist) {
            saveRepository(submissionRepository, submission);
        }
        return submission;
    }

    private void wait(Future<Submission> submission) throws InterruptedException {
        while (!(submission.isDone())) {
            Thread.sleep(10);
        }
    }

}
