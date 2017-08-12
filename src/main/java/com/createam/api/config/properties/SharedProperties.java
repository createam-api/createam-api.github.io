package com.createam.api.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by lukasz@create.am on 05/08/2017.
 */

@Component
@PropertySource("classpath:application-shared.properties")
public class SharedProperties {

    @Value("${backendUrl}")
    private static String backendUrl;

    @Value("${backendUrl}")
    private static String frontendUrl;

    public static String backendUrl(String path) {
        return backendUrl;
    }

    public static String frintendUrl(String path) {
        return frontendUrl;
    }

}
