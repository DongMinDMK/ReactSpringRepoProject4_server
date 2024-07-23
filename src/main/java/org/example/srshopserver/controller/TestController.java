package org.example.srshopserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/tests")
public class TestController {

    @GetMapping("/testGet")
    public HashMap<String, Object> testGet(){
        HashMap<String, Object> hm = new HashMap<>();

        hm.put("test", "스프링부트 서버에서 잘 넘어오니?");

        return hm;
    }
}
