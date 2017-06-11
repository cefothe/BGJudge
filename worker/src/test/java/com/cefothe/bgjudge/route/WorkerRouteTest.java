package com.cefothe.bgjudge.route;

import com.cefothe.bgjudge.WorkerApplication;
import com.cefothe.bgjudge.exam.entitities.Examens;
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
import com.cefothe.bgjudge.workers.JavaCompilerTest;
import com.cefothe.bgjudge.workers.StrategyBeanTest;
import com.cefothe.bgjudge.workers.repositories.TestRepository;
import com.cefothe.bgjudge.workers.repositories.TestResultsRepository;
import com.cefothe.bgjudge.workers.strategies.Strategy;
import com.cefothe.common.entities.BaseEntity;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.DefaultFluentProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by cefothe on 11.06.17.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-unittest.properties")
@ContextConfiguration(classes = WorkerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "non-async")
@AutoConfigureTestDatabase(connection = H2)
public class WorkerRouteTest {

    @Configuration
    @Profile("non-async")
    public static class Config{

    }

    @Autowired
    private CamelContext camelContext;

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

    @Before
    public void before() {
        Role role = new Role("student");
        saveRepository(roleRepository, role);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void saveRepository(CrudRepository repository, BaseEntity baseEntity) {
        repository.save(baseEntity);
    }

    @After
    public void clearCompilerDirectoryFromClasses() {
        File directory = new File(JavaCompilerTest.class.getResource("/compiler/").getFile());
        Arrays.stream(directory.listFiles((f, p) -> p.endsWith("class"))).forEach(File::delete);
    }

    @Test
    public void strategyTest() throws IOException, InterruptedException {

        NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(1).create();

        User user = createUser(roleRepository.findOne(1L),true);
        File file = new File(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getFile());
        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user);
        Task task = new Task("Test", "test");
        TaskParam taskParam = new TaskParam("Hello World", "Hello World");

        task.addTaskParam(taskParam);
        exam.addTask(task);
        saveRepository(examRepository, exam);
        Submission submission = createSubmision(user, exam, task, true);

        new DefaultFluentProducerTemplate(camelContext).to("direct:start").withBody(submission.getId()).send();
        boolean matches = notify.matches(30, TimeUnit.SECONDS);
        // true means the notifier condition matched (= 1 message is done)
        assertThat(matches, Matchers.is(true));
        Submission expected = submissionRepository.findOne(submission.getId());
        assertThat(expected.getStatus(), Matchers.is(SubmissionStatus.COMPLETED));
    }

    public User createUser(Role role, boolean persisted) {
        User user = new User("test", "test", new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        if (persisted) {
            saveRepository(userRepository, user);
        }
        return user;
    }

    public Submission createSubmision(User user, Examens exam, Task task, boolean needToBePersist) throws IOException {
        String code = new String(Files.readAllBytes(Paths.get(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getPath())));
        Submission submission = new Submission(user, task, exam, code, SubmissionStatus.IN_PROGRESS);
        if (needToBePersist) {
            saveRepository(submissionRepository, submission);
        }
        return submission;
    }

}
