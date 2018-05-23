package com.cefothe.bgjudge.exam.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * Created by cefothe on 26.06.17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class LoginIntoExamModel {

    @Getter
    @Setter
    @NotEmpty
    public String examPassword;
}
