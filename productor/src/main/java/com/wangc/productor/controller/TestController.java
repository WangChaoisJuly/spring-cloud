package com.wangc.productor.controller;

import com.wangc.productor.service.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productor")
public class TestController {

    @Autowired
    private TestServices testServices;

    @RequestMapping("/get")
    public String get(String name) {
        return testServices.get(name);
    }

}