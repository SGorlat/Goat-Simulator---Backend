package com.goat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpBackendController {


    @GetMapping("/health")
    public String upBackend(){
        return "OK";
    }
}
