package com.createam.api.repository;

import com.createam.api.model.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lukas@create.am on 13/08/2017.
 */
@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{
    Privilege findByName(String name);
}
