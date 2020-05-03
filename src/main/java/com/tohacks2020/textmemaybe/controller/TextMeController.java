package com.tohacks2020.textmemaybe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller


public class TextMeController {
    @RequestMapping("/")
    public String listGifs() {
        return "home";
    }
}
