package com.createam.api.controller;

import com.createam.api.model.Heartbeat;
import com.createam.api.service.HeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class HeartbeatController {

    private final HeartbeatService heartbeatService;

    @Autowired
    public HeartbeatController(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @GetMapping(value = "/heartbeat", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Heartbeat heartbeat(HttpServletRequest request) {
        return heartbeatService.generateHeartbeat(request);
    }
}
