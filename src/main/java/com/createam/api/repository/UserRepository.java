package com.createam.api.repository;

import com.createam.api.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lukasz@create.am on 07/08/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
