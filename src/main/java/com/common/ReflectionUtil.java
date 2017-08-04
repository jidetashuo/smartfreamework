package com.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wm on 2017/8/4.
 */
public class ReflectionUtil {
    /**
     * 创建实例
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {

        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;

    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */

    public static Object invokeMethod(Object obj, Method method, Object... args) {

        Object result = null;
        method.setAccessible(true);
        try {
            result = method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 设置成员变量
     *
     * @param obj
     * @param field
     * @param value
     */

    public static void setField(Object obj, Field field, Object value) {


        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
