package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.submissions.entities.Submission;
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
import com.cefothe.bgjudge.workers.strategies.StrategyBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

/**
 * Created by cefothe on 31.05.17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class StrategyBeanTest {

    @Autowired
    public TestResultsRepository testResultsRepository;

    @Autowired
    public TestRepository testRepository;

    @Autowired
    public SubmissionRepository submissionRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ExamRepository examRepository;

    @Autowired
    public Strategy strategy;

    @Autowired
    private TestEntityManager entityManager;

    public User user;


    @Configuration
    @EntityScan(basePackages = "com.cefothe.bgjudge")
    @EnableJpaRepositories(basePackages = "com.cefothe.bgjudge")
    public static class Config{

        @Bean
        public Strategy strategy(TestRepository testRepository, TestResultsRepository testResultsRepository, SubmissionRepository submissionRepository){
            return  new StrategyBean(testRepository,testResultsRepository, submissionRepository);
        }
    }

    @Before
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void before(){
        Role role = new Role("student");
        roleRepository.save(role);
        user = new User("test", "test", new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"),role);
        userRepository.save(user);
        entityManager.flush();


    }

    @After
    public void clearCompilerDirectoryFromClasses(){
        File directory = new File(JavaCompilerTest.class.getResource("/compiler/").getFile());
        Arrays.stream(directory.listFiles((f, p) -> p.endsWith("class"))).forEach(File::delete);
    }


    @Test
    public void strategyTest() throws IOException {
        File file = new File(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getFile());
        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user );
        Task task = new Task("Test", "test");
        TaskParam taskParam = new TaskParam("Hello World", "Hello World");
        task.addTaskParam(taskParam);
        exam.addTask(task);

        examRepository.save(exam);
        String code = new String(Files.readAllBytes(Paths.get(StrategyBeanTest.class.getResource("/compiler/CorrectExecutorTest.java").getPath())));

        Submission submission = new Submission(user, task, exam, code);
        submissionRepository.save(submission);

        strategy.execute(ProgramLanguages.JAVA, submission,file);
    }


}
