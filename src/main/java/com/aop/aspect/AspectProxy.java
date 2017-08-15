package com.aop.aspect;

import com.aop.proxy.Proxy;
import com.aop.proxy.ProxyChain;

import java.lang.reflect.Method;

/**
 * Created by wm on 2017/8/15.
 */
public abstract  class AspectProxy implements Proxy {

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result=null;
        
        Class<?> cls=proxyChain.getTargetClass();
        Method  method=proxyChain.getTargetMethod();
        Object[] params=proxyChain.getMethodParams();
        
        begin();

        try {
            if(intercept(cls,method,params)){

                before(cls,method,params);
                result=proxyChain.doProxyChain();
                after(cls,method,params,result);

            }else{
                result=proxyChain.doProxyChain();
            }
        } catch (Throwable throwable) {

            throwable.printStackTrace();
            
        }finally {
            end();
        }


        return result;
    }

    public void end() {
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) {
    }

    public void before(Class<?> cls, Method method, Object[] params) {
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    public void begin() {
    }
}
