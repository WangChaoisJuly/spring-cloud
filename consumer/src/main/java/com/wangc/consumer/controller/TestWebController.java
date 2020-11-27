package com.wangc.consumer.controller;

import com.wangc.productor.apis.TestServicesApis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class TestWebController {

    @Autowired
    private TestServicesApis testServicesApis;

    @RequestMapping("/getTest")
    public String getTest() {
        // 这里是直接根据方法来调用的，跟上面的requestMapping没关系
        return testServicesApis.get("通过web调用的RequestMapping");
    }

}