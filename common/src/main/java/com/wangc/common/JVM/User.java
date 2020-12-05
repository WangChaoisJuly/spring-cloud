package com.wangc.common.JVM;

import java.io.Serializable;

public class User  {

    private String name;
    private String sex;
    private int age;

    public void sout(){
        System.out.println("im sout");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
