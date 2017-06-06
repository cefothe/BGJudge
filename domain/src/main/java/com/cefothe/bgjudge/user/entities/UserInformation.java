package com.cefothe.bgjudge.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by cefothe on 02.05.17.
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation implements Serializable {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String email;

    public UserInformation(UserInformation userInformation){
        this.firstName = userInformation.getFirstName();
        this.lastName = userInformation.getFirstName();
        this.email = userInformation.getEmail();
    }
}
