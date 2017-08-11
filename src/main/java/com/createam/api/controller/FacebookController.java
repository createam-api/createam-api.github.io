package com.createam.api.controller;

import com.createam.api.config.properties.SharedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lukasz@create.am on 10/08/2017.
 */
@RestController
@RequestMapping("/facebook")
public class FacebookController {

    private static final Logger log = LoggerFactory.getLogger(FacebookController.class);

    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private SharedProperties sharedProperties;

    public FacebookController(Facebook facebook,
                              ConnectionRepository connectionRepository,
                              SharedProperties sharedProperties) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.sharedProperties = sharedProperties;
    }

    @GetMapping(value = "/feed", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String connect(HttpServletRequest request, Model model) {
        log.info("Facebook connection requested from: " + request.getRemoteAddr());
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return FrontendUtils.redirectToFrontend("/connect/facebook");
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }
}
