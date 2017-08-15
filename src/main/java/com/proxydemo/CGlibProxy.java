package com.proxydemo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理   任何类都可以  不必像JDK一样必须有接口
 * Created by wm on 2017/8/15.
 */
public class CGlibProxy implements MethodInterceptor {

    private static CGlibProxy instance = new CGlibProxy();

    private CGlibProxy() {


    }

    public <T> T getProxy(Class<T> cls) {

        return (T) Enhancer.create(cls, this);
    }

    public static CGlibProxy getInstance() {

        return instance;
    }


    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("这是CGLIB 代理方法前====");
        Object result = methodProxy.invokeSuper(target, args);
        System.out.println("这是CGLIB 代理方法后====");
        return result;
    }
}
