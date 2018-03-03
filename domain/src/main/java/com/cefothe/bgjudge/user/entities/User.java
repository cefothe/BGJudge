package com.cefothe.bgjudge.user.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cefothe on 02.05.17.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class User extends BaseEntity implements UserDetails {

    @Getter
    @Column(unique = true, nullable = false)
    private String username;

    @Getter
    private String password;

    @Getter
    @Embedded
    private UserInformation userInformation;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities = new HashSet<>();

    public User(String username, String password, UserInformation userInformation, Role role) {
        this.username = username;
        this.password = password;
        this.userInformation = userInformation;
        this.authorities.add(role);
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
}
