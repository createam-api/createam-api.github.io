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
    private String backendUrl;

    public String backendUrl(String path) {
        return backendUrl + path;
    }

}
