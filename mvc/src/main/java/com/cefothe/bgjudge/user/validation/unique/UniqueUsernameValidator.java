package com.cefothe.bgjudge.user.validation.unique;

import com.cefothe.bgjudge.user.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            userService.loadUserByUsername(value);
        }catch (UsernameNotFoundException ex){
            return  false;
        }
        return true;
    }
}
