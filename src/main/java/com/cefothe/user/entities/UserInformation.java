package com.cefothe.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Created by cefothe on 02.05.17.
 */
@Embeddable
@AllArgsConstructor
public class UserInformation {

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
