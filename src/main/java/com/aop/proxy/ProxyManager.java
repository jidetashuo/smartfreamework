package com.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wm on 2017/8/15.
 */
public class ProxyManager {


    public static <T> T createProxy(final Class<T> targetClass, final List<Proxy> proxyList) {


        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method method, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, method, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });

    }

}
