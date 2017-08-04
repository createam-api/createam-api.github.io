package com.createam.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Scheduled(fixedRate = 5000)
    public LocalDateTime updateAndGetLast() {
        LocalDateTime temp = lastHeartbeat;
        lastHeartbeat = LocalDateTime.now();
        counter.incrementAndGet();
        log.info("beat!");
        return temp;
    }


    public Integer getCount() {
        lastHeartbeat = LocalDateTime.now();
        return counter.incrementAndGet();
    }

}
