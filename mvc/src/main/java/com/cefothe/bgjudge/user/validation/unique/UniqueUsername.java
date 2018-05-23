package com.cefothe.bgjudge.user.validation.unique;

import com.cefothe.bgjudge.user.validation.password.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "Your username should be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
