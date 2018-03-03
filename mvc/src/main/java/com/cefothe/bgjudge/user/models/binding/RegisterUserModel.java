package com.cefothe.bgjudge.user.models.binding;

import com.cefothe.bgjudge.user.validation.password.PasswordMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by cefothe on 02.05.17.
 */

@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch
public class RegisterUserModel implements Serializable {

    @Getter
    @Setter
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @Getter
    @Setter
    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Getter
    @Setter
    private String confirmPassword;

    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;

    @Getter
    @Setter
    @NotNull
    @Size(min = 6, max = 20)
    private String lastName;

    @Getter
    @Setter
    @NotNull
    @Email
    private String email;
}
