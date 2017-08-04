package com.createam.api.controller;

import com.createam.api.model.HelloMessage;
import com.createam.api.service.HeartbeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private final HeartbeatService heartbeatService;

    @Autowired
    public MainController(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HelloMessage sayHello(HttpServletRequest request) {
        return HelloMessage.builder()
                .id(COUNTER.incrementAndGet())
                .message("hello " + request.getRemoteHost())
                .heartbeats(heartbeatService.getCount())
                .build();
    }

    @GetMapping("/heartbeat")
    public @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    void heartbeat(HttpServletRequest request) {
        LocalDateTime lastHeartbeat = heartbeatService.updateAndGetLast();
        log.info("Heartbeat received from " + request.getRemoteAddr()
                + "! Last heartbeat - " + lastHeartbeat.toString());
    }

}
