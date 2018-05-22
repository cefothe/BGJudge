package com.cefothe.bgjudge.exam.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.cefothe.bgjudge.exam.services.ExamServiceBean.DATE_PATTERN;

/**
 * Validator if the date is correct and in future
 */
public class FutureDateValidator implements ConstraintValidator<FutureDate, String> {
    @Override
    public void initialize(FutureDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        Date date;
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
           return false;
        }
        return date.getTime() >= new Date().getTime();
    }
}
