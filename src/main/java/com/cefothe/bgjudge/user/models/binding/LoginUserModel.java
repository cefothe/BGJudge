package com.cefothe.bgjudge.user.models.binding;

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
public class LoginUserModel implements Serializable{

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
