package com.cefothe.bgjudge.tasks.services;

import com.cefothe.MvcApplication;
import com.cefothe.bgjudge.exam.entities.ExamSecurity;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.models.binding.CreateTaskModel;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/16/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
@DirtiesContext
public class TaskServiceBeanTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private TaskRepositories taskRepositories;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private ExamSecurity examSecurity;

    @Before
    public void before(){
        this.examSecurity = new ExamSecurity("TestPassword", Collections.emptyList());
    }

    @Test
    public void testAddTask(){
        // Expected
        Role role = new Role("teacher");
        roleRepository.save(role);
        User user = new User("Stefan", "password",new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        userRepository.save(user);

        Examens examens = new Examens("Java Exam", new Timestamp(new Date().getTime()),120, user, this.examSecurity);
        examRepository.save(examens);
        CreateTaskModel createTaskModel = new CreateTaskModel("Test", "This is a test", "Stefan", "Hello, Stefan", "Ivan", "Hello, Ivan");

        // Do actual
        taskService.createTask(createTaskModel, examens.getId());

        // Verify
        Task task = taskRepositories.findById(1L).get();
        assertThat(task.getTaskPrams(), hasSize(2));
        assertThat(task.getTaskPrams(), hasItem(new TaskParam("Stefan", "Hello, Stefan")));
        assertThat(task.getTaskPrams(), hasItem(new TaskParam("Ivan", "Hello, Ivan")));

    }

}
