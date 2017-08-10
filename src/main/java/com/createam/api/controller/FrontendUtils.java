package com.createam.api.controller;

import com.createam.api.config.properties.SharedProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lukasz@create.am on 10/08/2017.
 */
public class FrontendUtils {

    private static final SharedProperties sharedProperties = new SharedProperties();

    public static String redirectToFrontend(String path) {
        return "redirect:" + sharedProperties.getFrontendUrl() + "/src/main/resources/static/" + path;
    }
}
