package com.cefothe.bgjudge.submissions.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cefothe.bgjudge.exam.entities.ExamSecurity;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import com.cefothe.common.entities.BaseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/6/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
public class SubmissionRepositoryTest {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private TaskRepositories taskRepositories;


    @Autowired
    private RoleRepository roleRepository;

    private ExamSecurity examSecurity;

    @Before
    public void before() {
        Role role = new Role("student");
        saveRepository(roleRepository, role);
        this.examSecurity = new ExamSecurity("TestPassword", Collections.emptyList());
    }

    @Test
    public void test_with_two_submission_and_one_exam_one_user() throws IOException {
        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        ExamSecurity examSecurity = new ExamSecurity("TestPassword", Collections.emptyList());
        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity );
        saveRepository(examRepository, exam);

        Task task = new Task("Test", "test");
        saveRepository(taskRepositories,task);

        //First submission
        createSubmission(user, exam, task, 100, submissionRepository);

        //Second submission
        createSubmission(user, exam, task, 50, submissionRepository);

        List<Submission> foundSubmissions =submissionRepository.findSubmissionForExamMaxScore(exam);

        assertThat(foundSubmissions, hasSize(1));
        assertThat(foundSubmissions.get(0).getResult(), equalTo(100));
    }

    @Test
    public void test_find_all_submission_and_one_exam_one_user() throws IOException {
        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity);
        saveRepository(examRepository, exam);

        Task task = new Task("Test", "test");
        saveRepository(taskRepositories,task);

        //First submission
        createSubmission(user, exam, task, 100, submissionRepository);

        //Second submission
        createSubmission(user, exam, task, 50, submissionRepository);

        List<Submission> foundSubmissions =submissionRepository.findSubmissionByUserTaskExam(exam, task,user);

        assertThat(foundSubmissions, hasSize(2));
    }

    @Test
    public void test_find_all_submission_by_exam_and_users() throws IOException {
        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity );
        saveRepository(examRepository, exam);

        Task task = new Task("Test", "test");
        saveRepository(taskRepositories,task);

        //First submission
        User firstUser = createUser("Stefan", roleRepository.findByAuthority("student"), "stefan@gmail.com");
        saveRepository(userRepository,firstUser);
        Submission firstSubmission = createSubmission(firstUser, exam, task, 100, submissionRepository);

        //Second submission
        User secondUser = createUser("Ivan", roleRepository.findByAuthority("student"), "ivan@gmail.com");
        saveRepository(userRepository,secondUser);
        Submission secondSubmission = createSubmission(secondUser, exam, task, 50, submissionRepository);


        List<Submission> foundSubmissionsForUserOne = submissionRepository.findSubmissionByUserTaskExam(exam, task,firstUser);
        List<Submission> foundSubmissionsForUserTwo = submissionRepository.findSubmissionByUserTaskExam(exam, task,secondUser);

        assertThat(foundSubmissionsForUserOne, hasSize(1));
        assertThat(foundSubmissionsForUserOne.get(0).getResult(), equalTo(100));

        assertThat(foundSubmissionsForUserTwo, hasSize(1));
        assertThat(foundSubmissionsForUserTwo.get(0).getResult(), equalTo(50));

    }


    @Test
    public void test_with_two_submission_and_one_exam_two_user() throws IOException {
        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity );
        saveRepository(examRepository, exam);

        Task task = new Task("Test", "test");
        saveRepository(taskRepositories,task);

        //First submission
        User firstUser = createUser("Stefan", roleRepository.findByAuthority("student"), "stefan@gmail.com");
        saveRepository(userRepository,firstUser);
        createSubmission(firstUser, exam, task, 100, submissionRepository);

        //Second submission
        User secondUser = createUser("Ivan", roleRepository.findByAuthority("student") , "ivan@gmail.com");
        saveRepository(userRepository,secondUser);
        createSubmission(secondUser, exam, task, 50, submissionRepository);

        List<Submission> foundSubmissions =submissionRepository.findSubmissionForExamMaxScore(exam);

        assertThat(foundSubmissions, hasSize(2));
    }

    @Test
    public void test_with_two_submission_and_one_exam_one_user_and_two_tasks() throws IOException {

        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity );
        saveRepository(examRepository, exam);

        Task taskFirst = new Task("Test", "test");
        saveRepository(taskRepositories,taskFirst);

        Task taskSecond = new Task("Test", "test");
        saveRepository(taskRepositories,taskSecond);

        //First submission
        createSubmission(user, exam, taskFirst, 100, submissionRepository);

        //Second submission
        createSubmission(user, exam, taskSecond, 50, submissionRepository);

        List<Submission> foundSubmissions =submissionRepository.findSubmissionForExamMaxScore(exam);

        assertThat(foundSubmissions, hasSize(2));

    }

    @Test
    public void test_with_three_submission_one_exam_one_user_and_two_tasks() throws IOException {
        User user = createUser(roleRepository.findByAuthority("student"),false);
        saveRepository(userRepository,user);

        Examens exam = new Examens("Test", new Timestamp(new Date().getTime()), 120, user, examSecurity );
        saveRepository(examRepository, exam);

        Task taskFirst = new Task("Test", "test");
        saveRepository(taskRepositories,taskFirst);

        Task taskSecond = new Task("Test", "test");
        saveRepository(taskRepositories,taskSecond);

        //First submission
        createSubmission(user, exam, taskFirst, 100, submissionRepository);

        //Second submission
        createSubmission(user, exam, taskFirst, 50, submissionRepository);

        //Third submission
        createSubmission(user, exam, taskSecond, 50, submissionRepository);

        User secondUser = createUser("Ivan", roleRepository.findByAuthority("student"), "ivan@gmail.com");
        saveRepository(userRepository,secondUser);
        //Third submission
        createSubmission(secondUser, exam, taskSecond, 50, submissionRepository);


        List<Submission> foundSubmissions =submissionRepository.findSubmissionForExamMaxScore(exam);

        assertThat(foundSubmissions, hasSize(3));

    }


    private User createUser(String username, Role role){
        return createUser(username,role, "cefothe@gmail.com");
    }

    private User createUser(String username, Role role, String mail) {
        User user = new User(username, "test", new UserInformation("Stefan", "Angelov", mail), role);
        return user;
    }


    private Submission createSubmission(User user, Examens exam, Task task, int result, SubmissionRepository submissionRepository) throws IOException {
        Submission submission = createSubmision(user, exam, task, false);
        submission.setResult(result);
        saveRepository(submissionRepository, submission);
        return submission;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRepository(CrudRepository repository, BaseEntity baseEntity){
        repository.save(baseEntity);
    }

    public User createUser(Role role, boolean persisted) {
        User user = new User("test", "test", new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        if (persisted) {
            saveRepository(userRepository, user);
        }
        return user;
    }

    public Submission createSubmision(User user, Examens exam, Task task, boolean needToBePersist) throws IOException {
        String code = new String(Files.readAllBytes(Paths.get(SubmissionRepositoryTest.class.getResource("/compiler/CorrectExecutorTest.java").getPath())));
        Submission submission = new Submission(user, task, exam, code, SubmissionStatus.IN_PROGRESS);
        if (needToBePersist) {
            saveRepository(submissionRepository, submission);
        }
        return submission;
    }
}
