package com.createam.api.service;

import com.createam.api.config.properties.SharedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lukasz@create.am on 04/08/2017.
 */
@Service
@Scope("singleton")
public class HeartbeatService {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatService.class);

    private LocalDateTime lastHeartbeat = LocalDateTime.now();
    private AtomicInteger counter = new AtomicInteger(0);
    private final SharedProperties sharedProperties;

    @Autowired
    public HeartbeatService(SharedProperties sharedProperties) {
        this.sharedProperties = sharedProperties;
    }

    public LocalDateTime updateAndGetLast() {
        LocalDateTime temp = lastHeartbeat;
        lastHeartbeat = LocalDateTime.now();
        counter.incrementAndGet();
        log.info("beat!");
        return temp;
    }

    @Scheduled(fixedRate = 10000)
    public void sendHeartbeat() {
        new RestTemplate().getForEntity(sharedProperties.getBackendUrl() + "/heartbeat", null);
    }

    public Integer getCount() {
        lastHeartbeat = LocalDateTime.now();
        return counter.incrementAndGet();
    }

}
