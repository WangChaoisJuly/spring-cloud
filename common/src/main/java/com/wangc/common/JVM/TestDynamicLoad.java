package com.wangc.common.JVM;

public class TestDynamicLoad {
    // 执行顺序永远都是 静态代码块-》构造代码块-》构造方法
    // 静态代码块仅仅执行一次，若父类已经被加载了，则子类加载时不会再次加载父类
    // 构造代码块、构造方法每次都会被加载
    // 继承相关的顺序是：A静态代码块 B静态代码块 A构造代码块 A构造方法 B构造代码块 B构造方法
    /**
     * 个人理解：把构造代码块和构造方法当成一体，只不过是构造代码块比构造方法先执行
     */
    static {
        System.out.println("*************load TestDynamicLoad************");
    }


    public static void main(String[] args) {
        /**
         * 不注释A 顺序是：loadA A构造代码块 A构造器  loadB A构造代码块 A构造器 B构造代码块 B构造器
         * 注释A 顺序是：loadA loadB A构造代码块 A构造器 B构造代码块 B构造器
         */
        new A();
        System.out.println("*************************");
        B b = new B();  //B不会加载，除非这里执行 new B()
    }
}

class A {
    static {
        System.out.println("*************load A************");
    }
    {//构造代码块
        System.out.println("A的构造代码块");
    }

    public A() {
        System.out.println("*************initial A************");
    }
}

class B extends A{
    static {
        System.out.println("*************load B************");
    }
    {//构造代码块
        System.out.println("B的构造代码块");
    }

    public B() {
        System.out.println("*************initial B************");
    }
}