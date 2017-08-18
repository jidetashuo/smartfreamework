package com.aop.aspect;

import com.annotation.Transaction;
import com.aop.proxy.Proxy;
import com.aop.proxy.ProxyChain;

import java.lang.reflect.Method;

/**
 * Created by wm on 2017/8/15.
 */
public class TransactionProxy implements Proxy {
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Method method = proxyChain.getTargetMethod();
        if (method.isAnnotationPresent(Transaction.class)) {

            System.out.println("模拟事物切面start================");
        result = proxyChain.doProxyChain();
        System.out.println("模拟事物切面end================");
    }

        return result;
    }
}
