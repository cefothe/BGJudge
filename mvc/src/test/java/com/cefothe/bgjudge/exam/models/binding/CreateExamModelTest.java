package com.cefothe.bgjudge.exam.models.binding;

import com.cefothe.MvcApplication;
import com.cefothe.bgjudge.utility.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = H2)
@DirtiesContext
public class CreateExamModelTest {

    @Autowired
    private Validator validator;

    @Test
    public void testCorrectExamModel(){
        // Do actual
        Date date = new Date();
        date = DateUtil.addDays(date, 2);
        CreateExamModel createExamModel = new CreateExamModel("Java Basic",DateUtil.toString(date), 120, "Test", null);
        Set<ConstraintViolation<CreateExamModel>> violations = validator.validate(createExamModel);

        //Verify
        assertEquals(0, violations.size());
    }

    @Test
    public  void testWithDateInPast(){
        // Do actual
        Date date = new Date();
        date = DateUtil.addDays(date, -2);
        CreateExamModel createExamModel = new CreateExamModel("Java Basic",DateUtil.toString(date), 120, "Test", null);
        Set<ConstraintViolation<CreateExamModel>> violations = validator.validate(createExamModel);

        //Verify
        assertEquals(1, violations.size());
    }
}
