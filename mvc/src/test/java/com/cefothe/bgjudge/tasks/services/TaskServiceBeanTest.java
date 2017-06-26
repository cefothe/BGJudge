package com.cefothe.bgjudge.tasks.services;

import com.cefothe.MvcApplication;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.models.binding.CreateTaskModel;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;
/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/16/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
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

    @Test
    public void testAddTask(){
        // Expected
        Role role = new Role("teacher");
        roleRepository.save(role);
        User user = new User("Stefan", "password",null, role);
        userRepository.save(user);

        Examens examens = new Examens("Java Exam", new Timestamp(new Date().getTime()),120, user);
        examRepository.save(examens);
        CreateTaskModel createTaskModel = new CreateTaskModel("Test", "This is a test", "Stefan", "Hello, Stefan", "Ivan", "Hello, Ivan");

        // Do actual
        taskService.createTask(createTaskModel, examens.getId());

        // Verify
        Task task = taskRepositories.findOne(1L);
        assertThat(task.getTaskPrams(), hasSize(2));
        assertThat(task.getTaskPrams(), hasItem(new TaskParam("Stefan", "Hello, Stefan")));
        assertThat(task.getTaskPrams(), hasItem(new TaskParam("Ivan", "Hello, Ivan")));

    }

}
