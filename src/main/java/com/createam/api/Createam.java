package com.createam.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
public class Createam {

    private static final Logger log = LoggerFactory.getLogger(Createam.class);
    private final Environment env;

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Createam.class);

        Environment env = app.run(args).getEnvironment();

        log.info("\n------------------------------------------\n" +
                "Application '" + env.getProperty("spring.application.name") + "' is running!\n" +
                "Access URL:  " + InetAddress.getLocalHost().getHostAddress() + "\n" +
                "Profile(s):  " + StringUtils.arrayToDelimitedString(env.getActiveProfiles(), " / ")
                + "\n------------------------------------------"
        );
    }
}
