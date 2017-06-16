package com.cefothe.bgjudge.user.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cefothe on 02.05.17.
 */
@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Role extends BaseEntity implements GrantedAuthority {

    @Getter
    private String authority;
}
