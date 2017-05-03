package com.cefothe.user.service;

import com.cefothe.user.entities.Role;
import com.cefothe.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cefothe on 02.05.17.
 */
@Service
public class RoleServiceBean implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceBean(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByAuthority(String authority) {
        if(authority == null || authority.isEmpty()){
            authority = "STUDENT";
        }
        Role role = this.roleRepository.findByAuthority(authority);
        return role;
    }
}
