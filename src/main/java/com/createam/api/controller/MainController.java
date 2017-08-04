package com.createam.api.controller;

import com.createam.api.model.HelloMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/")
public class MainController {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HelloMessage sayHello(HttpServletRequest request) {
        return HelloMessage.builder().id(COUNTER.incrementAndGet()).message("hello " + request.getRemoteHost()).build();
    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        return "redirect:/http://createam.herokuapp.com/swagger-ui";
    }

}
