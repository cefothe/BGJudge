package com.cefothe.bgjudge.user.models.binding;

import com.cefothe.MvcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
public class RegisterUserModelTest {


    @Autowired
    private Validator validator;

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
}
