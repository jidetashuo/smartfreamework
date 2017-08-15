package com.helper;

import com.annotation.Aspect;
import com.aop.aspect.AspectProxy;
import com.aop.proxy.Proxy;
import com.aop.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by wm on 2017/8/15.
 */
public class AopHelper {


    static {

        try {
            //代理类及其目标类
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            //目标类和代理对象list的集合
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);



            for (Map.Entry<Class<?>, List<Proxy>> proEntrty : targetMap.entrySet()) {

                Class<?> targetClass = proEntrty.getKey();
                List<Proxy> proxyList = proEntrty.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 获取Aspect 注解中设置的注解类
     *
     * @param aspect
     * @return
     * @throws Exception
     */
    public static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {


        Set<Class<?>> classSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();

        if (annotation != null && !annotation.equals(Aspect.class)) {
            classSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return classSet;
    }

    /**
     * 获取代理类和目标类的对应map
     *
     * @return
     * @throws Exception
     */
    public static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
            Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBysuper(AspectProxy.class);
            for (Class<?> cls : proxyClassSet) {
                if (cls.isAnnotationPresent(Aspect.class)) {
                    Aspect aspect = cls.getAnnotation(Aspect.class);
                    Set<Class<?>> tarClassSet = createTargetClassSet(aspect);
                    proxyMap.put(cls, tarClassSet);
                }
        }
        return proxyMap;
    }

    /**
     * 获取目标类与代理对象集合之间的关系
     *
     * @return
     * @throws Exception
     */
    public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {

        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();


        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {

            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }

        return targetMap;

    }
}
