package com.wangc.productor.service.impl;

import org.springframework.stereotype.Service;
import com.wangc.productor.service.TestServices;

@Service
public class TestServicesImpl implements TestServices {

    @Override
    public String get(String name) {
        return "参数name：" + name;
    }
}