package com.cefothe.bgjudge.exam.models.binding;

import com.cefothe.bgjudge.exam.validation.FutureDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by cefothe on 04.05.17.
 */
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamModel implements Serializable {

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    public String name;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @FutureDate
    public String examDate;

    @Getter
    @Setter
    @NotNull
    @Min(1)
    public long examLength;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    public String examPassword;

    @Getter
    @Setter
    public String allowedIP;
}
