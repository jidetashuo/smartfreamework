package com.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理  只能代理有接口的类
 * Created by wm on 2017/8/15.
 */
public class Dynamicproxy implements InvocationHandler {

    private Object target;

    public Dynamicproxy(Object target) {
        this.target = target;
    }

    /**
     * 获取动态代理对象
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy() {

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("这是方法前-------");
        Object result = method.invoke(target, args);
        System.out.println("这是方法后-------");
        return result;
    }
}
