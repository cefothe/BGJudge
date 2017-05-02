package com.cefothe.user.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by cefothe on 02.05.17.
 */

@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserModel implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String confirmPassword;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;
}
