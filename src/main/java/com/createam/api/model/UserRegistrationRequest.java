package com.createam.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lukasz@create.an on 13/08/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest {

    private String email;
    private String password;

}
