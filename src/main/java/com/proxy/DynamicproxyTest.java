package com.proxy;

/**
 * Created by wm on 2017/8/15.
 */
public class DynamicproxyTest {

    public static void main(String[] args) {
        Dynamicproxy dynamicproxy = new Dynamicproxy(new HelloImpl());
        Hello helloPeoxy = dynamicproxy.getProxy();
        helloPeoxy.say();
    }


}
