package com.createam.api.service;

import com.createam.api.config.properties.SharedProperties;
import com.createam.api.model.Heartbeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lukasz@create.am on 04/08/2017.
 *
 * Service that keeps heroku app alive.
 */
@Service
@Scope("singleton")
public class HeartbeatService {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatService.class);

    private final AtomicInteger counter = new AtomicInteger(0);
    private final SharedProperties sharedProperties;

    @Autowired
    public HeartbeatService(SharedProperties sharedProperties) {
        this.sharedProperties = sharedProperties;
    }

    @Profile("heroku")
    @Scheduled(fixedRate = 10000)
    public void sendHeartbeat() {
        new RestTemplate()
                .getForEntity(sharedProperties.getBackendUrl() + "/heartbeat", Heartbeat.class);
    }

    public Heartbeat generateHeartbeat(HttpServletRequest request) {
        Heartbeat heartbeat =  Heartbeat.builder()
                .message("ahoy! I'am alive")
                .heartbeats(counter.getAndIncrement())
                .uptime(prepareUptime())
                .build();
        log.info(heartbeat.toString());
        return heartbeat;
    }

    private String prepareUptime() {
        Long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        return String.format("%d min %d sec",
                TimeUnit.MILLISECONDS.toMinutes(uptime),
                TimeUnit.MILLISECONDS.toSeconds(uptime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uptime))
        );
    }
}
