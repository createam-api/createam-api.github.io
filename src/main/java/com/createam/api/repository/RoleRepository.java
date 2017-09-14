package com.createam.api.repository;

import com.createam.api.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lukasz@create.am on 13/08/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
