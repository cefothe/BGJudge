package com.cefothe.bgjudge.tasks.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * Created by cefothe on 05.05.17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateTaskModel {

    @Getter
    @Setter
    @NotEmpty
    @Length(min = 20, max = 255)
    public String title;

    @Getter
    @Setter
    @NotEmpty
    @Length(min = 20, max = 800)
    public String description;

    @Getter
    @Setter
    @NotEmpty
    public String inputOne;

    @Getter
    @Setter
    @NotEmpty
    public String outputOne;

    @Getter
    @Setter
    public String inputTwo;

    @Getter
    @Setter
    public String outputTwo;
}
