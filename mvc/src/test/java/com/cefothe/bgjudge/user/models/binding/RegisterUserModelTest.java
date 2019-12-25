package com.cefothe.bgjudge.user.models.binding;

import com.cefothe.MvcApplication;
import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.repositories.RoleRepository;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
@ActiveProfiles("unittest")
@DirtiesContext
public class RegisterUserModelTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @After
    public void after(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void testCreationOnUserWithNowMatchPassword(){
        // Do actual

        RegisterUserModel registerUserModel = new RegisterUserModel("cefothe","02135822","123123213","Stefan","Angelov","cefothe@gmail.com");
        Set<ConstraintViolation<RegisterUserModel>> violations = validator.validate(registerUserModel);

        // Verify
        assertEquals(1, violations.size());
    }

    @Test
    public void testMatchPassword(){
        // Do actual

        RegisterUserModel registerUserModel = new RegisterUserModel("cefothe","02135822","02135822","Stefan","Angelov","cefothe@gmail.com");
        Set<ConstraintViolation<RegisterUserModel>> violations = validator.validate(registerUserModel);

        // Verify
        assertEquals(0, violations.size());
    }

    @Test
    public void testUniqueUsername(){
        //Expected
        Role role = new Role("teacher");
        roleRepository.save(role);
        User user = new User("cefothe", "password",new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        userRepository.save(user);

        // When
        RegisterUserModel registerUserModel = new RegisterUserModel("cefothe","02135822","02135822","Stefan","Angelov","cefothe1@gmail.com");
        Set<ConstraintViolation<RegisterUserModel>> violations = validator.validate(registerUserModel);

        //Verify
        assertEquals(1, violations.size());

    }

    @Test
    public void testUniqueEmail(){
        //Expected
        Role role = new Role("teacher");
        roleRepository.save(role);
        User user = new User("stefan", "password",new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        userRepository.save(user);

        // When
        RegisterUserModel registerUserModel = new RegisterUserModel("cefothe","02135822","02135822","Stefan","Angelov","cefothe@gmail.com");
        Set<ConstraintViolation<RegisterUserModel>> violations = validator.validate(registerUserModel);

        //Verify
        assertEquals(1, violations.size());

    }
}
