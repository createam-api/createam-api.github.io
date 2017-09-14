package com.createam.api.core;

import com.createam.api.model.Privilege;
import com.createam.api.model.Role;
import com.createam.api.model.User;
import com.createam.api.repository.PrivilegeRepository;
import com.createam.api.repository.RoleRepository;
import com.createam.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by lukasz@create.am on 13/08/2017.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!alreadySetup) {
            Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            userRepository.save(
                    User.builder()
                            .password(passwordEncoder.encode("test"))
                            .email("olga@create.am")
                            .roles(Arrays.asList(adminRole))
                            .enabled(true)
                            .registered(new Date())
                            .build());
            alreadySetup = true;
        }
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = Privilege.builder()
                    .name(name)
                    .build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.builder()
                    .name(name)
                    .build();
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}