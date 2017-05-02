package com.cefothe.user.service;

import com.cefothe.user.entities.Role;

/**
 * Created by cefothe on 02.05.17.
 */
public interface RoleService {
    Role findByAuthority(String authority);
}
