package com.createam.api.controller;

import com.createam.api.config.properties.SharedProperties;
import com.createam.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by lukasz@create.am on 12/08/2017.
 */
@RestController
@RequestMapping("/email")
public class EmialController {

    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final SharedProperties sharedProperties;

    @Autowired
    public EmialController(EmailService emailService,
                           TemplateEngine templateEngine,
                           SharedProperties sharedProperties) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
        this.sharedProperties = sharedProperties;
    }

    @PostMapping(value = "/send")
    public @ResponseStatus(HttpStatus.ACCEPTED) void sendEmail() {
        Context context = new Context();
        context.setVariable("header", "CZEŚĆ");
        context.setVariable("title", "test");
        context.setVariable("description", "WITAM");
        context.setVariable("api-url-swagger", sharedProperties.backendUrl("/swagger-ui.html"));

        String body = templateEngine.process("email", context);
        emailService.sendEmail("l.wlodarczyk@gazeta.com", "createam newsletter", body);
    }

}
