package com.createam.api.controller;

import com.createam.api.config.properties.HerokuProperties;
import com.createam.api.model.Heartbeat;
import com.createam.api.service.HeartbeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/")
public class HeartbeatController {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatController.class);

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private final HeartbeatService heartbeatService;
    private final HerokuProperties herokuProperties;

    @Autowired
    public HeartbeatController(HeartbeatService heartbeatService, HerokuProperties herokuProperties) {
        this.heartbeatService = heartbeatService;
        this.herokuProperties = herokuProperties;
    }

    @GetMapping(value = "/heartbeat", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Heartbeat heartbeat(HttpServletRequest request) {
        log.info("Received heartbeat from " + request.getRemoteAddr());
        return heartbeatService.generateHeartbeat(request);
    }
}
