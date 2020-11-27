package com.wangc.productor.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "productor-service", contextId = "TestServicesApis")
public interface TestServicesApis {

    @RequestMapping("/productor/get")
    // 这里注解指向的是生产者中controller的地址
    public String get(String name);

}