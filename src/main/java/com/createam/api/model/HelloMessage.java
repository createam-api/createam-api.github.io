package com.createam.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HelloMessage {

    private Integer id;
    private String message;
    private Integer heartbeats;

}
