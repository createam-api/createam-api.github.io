package com.createam.api.controller;

import com.createam.api.config.properties.SharedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lukasz@create.am on 10/08/2017.
 */
@RestController("/connect")
public class FacebookController {

    private static final Logger log = LoggerFactory.getLogger(FacebookController.class);

    private final Facebook facebook;
    private final ConnectionRepository connectionRepository;
    private final SharedProperties sharedProperties;

    @Autowired
    public FacebookController(Facebook facebook,
                              ConnectionRepository connectionRepository,
                              SharedProperties sharedProperties) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.sharedProperties = sharedProperties;
    }

    @GetMapping("/facebook") public @ResponseBody
    String connect(HttpServletRequest request) {
        log.info("Facebook connection requested from:" + request.getRemoteAddr());
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return FrontendUtils.redirectToFrontend("facebookConnect.html");
        }

        return facebook.userOperations().getUserProfile().toString();
    }
}
