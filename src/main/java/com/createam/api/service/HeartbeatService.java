package com.createam.api.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lukasz@create.am on 04/08/2017.
 */
@Service
@Scope("singleton")
public class HeartbeatService {

    private LocalDateTime lastHeartbeat = LocalDateTime.now();
    private AtomicInteger counter = new AtomicInteger(0);

    public LocalDateTime updateAndGetLast() {
        LocalDateTime temp = lastHeartbeat;
        lastHeartbeat = LocalDateTime.now();
        counter.incrementAndGet();
        return temp;
    }

    public Integer getCount() {
        lastHeartbeat = LocalDateTime.now();
        return counter.incrementAndGet();
    }
}
