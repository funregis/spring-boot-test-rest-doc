package com.altari.spring.ws.domain.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SimpleController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}