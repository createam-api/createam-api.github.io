package com.createam.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Heartbeat {
    private String message;
    private Integer heartbeats;
    private String uptime;
}
