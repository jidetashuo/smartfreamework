package com.proxy;

/**
 * Created by wm on 2017/8/15.
 */
public class CGLIBproxyTest {
    public static void main(String[] args) {

        Hello proxy=CGlibProxy.getInstance().getProxy(HelloImpl.class);
        proxy.say();
        HelloImpl proxy2=CGlibProxy.getInstance().getProxy(HelloImpl.class);
        proxy2.say();
    }
}
