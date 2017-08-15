package com.aop.proxy;

/**
 * Created by wm on 2017/8/15.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
