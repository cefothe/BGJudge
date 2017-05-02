package com.cefothe.user.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cefothe on 02.05.17.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    @Embedded
    private UserInformation userInformation;

    @Getter
    private boolean isAccountNonExpired;

    @Getter
    private boolean isAccountNonLocked;

    @Getter
    private boolean isCredentialsNonExpired;

    @Getter
    private boolean isEnabled;

    @Getter
    @ManyToMany
    private Set<Role> authorities = new HashSet<>();

    public User(String username, String password, UserInformation userInformation) {
        this.username = username;
        this.password = password;
        this.userInformation = userInformation;
    }
}
