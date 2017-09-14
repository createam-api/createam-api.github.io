package com.createam.api.controller;

import com.createam.api.model.User;
import com.createam.api.model.UserRegistrationRequest;
import com.createam.api.repository.UserRepository;
import com.createam.api.service.UserService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by lukasz@create.am on 07/08/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService =userService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User single(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User single(@PathVariable @Email String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User create(@RequestBody UserRegistrationRequest request) {
        return userService.register(request);
    }
}
