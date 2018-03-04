package com.cefothe.bgjudge.tasks.services;

import com.cefothe.MvcApplication;
import com.cefothe.bgjudge.exam.entities.ExamSecurity;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.models.binding.LoginIntoExamModel;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.exam.services.participant.ParticipantServiceBean;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import com.cefothe.common.component.AuthenticationFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
@DirtiesContext
public class ParticipantServiceBeanTest {


    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantServiceBean participantServiceBean;

    @MockBean
    private AuthenticationFacade authenticationFacade;

    private ExamSecurity examSecurity;

    private Examens examens;

    @Before
    public void before(){
        Role role = new Role("teacher");
        roleRepository.save(role);

        User user = new User("Stefan", "password",new UserInformation("Stefan", "Angelov","cefothe@gmail.com"), role);
        userRepository.save(user);

        this.examSecurity = new ExamSecurity("TestPassword", Collections.emptyList());

        examens = new Examens("Java Exam", new Timestamp(new Date().getTime()),120, user, this.examSecurity);
        examens = examRepository.save(examens);

        when(authenticationFacade.getUser()).thenReturn(user);

    }

    @Test
    public void testLogginInToExam(){
        Assert.assertTrue(participantServiceBean.addParticipantIntoExam(examens.getId(), new LoginIntoExamModel("TestPassword")));
    }

    @Test
    public void testLogginIntoExamWithWrongPassword(){
        Assert.assertFalse(participantServiceBean.addParticipantIntoExam(examens.getId(), new LoginIntoExamModel("password")));
    }

}
