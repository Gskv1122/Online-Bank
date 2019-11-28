package com.userFront.dao;

import com.userFront.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role,Integer> {
    Role findByName(String name);
}
