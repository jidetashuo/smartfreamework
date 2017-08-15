package com.aop.aspect;

import com.annotation.Aspect;
import com.annotation.Controller;

import java.lang.reflect.Method;

/**
 * Created by wm on 2017/8/15.
 */
@Aspect(Controller.class)
public class ControllerAspect2 extends AspectProxy {

    private long beginTime;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {

        System.out.println("time2：" + String.format("%dms", System.currentTimeMillis() - beginTime));
        System.out.println("-------------end2------------");
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        System.out.println("-----------begin2-----------");
        System.out.println("class2:" + cls.getName());
        System.out.println("meehod2:" + method.getName());
        beginTime = System.currentTimeMillis();
    }


}
