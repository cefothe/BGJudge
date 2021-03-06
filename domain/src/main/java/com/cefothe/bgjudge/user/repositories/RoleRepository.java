package com.cefothe.bgjudge.user.repositories;

import com.cefothe.bgjudge.user.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 02.05.17.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByAuthority(String authority);
}