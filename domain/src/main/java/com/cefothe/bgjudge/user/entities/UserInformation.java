package com.cefothe.bgjudge.user.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by cefothe on 02.05.17.
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserInformation implements Serializable {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    @Column(unique = true, nullable = false)
    private String email;

    public UserInformation(UserInformation userInformation){
        this.firstName = userInformation.getFirstName();
        this.lastName = userInformation.getFirstName();
        this.email = userInformation.getEmail();
    }
}
