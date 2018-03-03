package com.cefothe.bgjudge.user.validation.password;

import com.cefothe.bgjudge.user.models.binding.RegisterUserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterUserModel> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegisterUserModel value, ConstraintValidatorContext context) {
        return  value.getPassword()!= null && value.getConfirmPassword() != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
