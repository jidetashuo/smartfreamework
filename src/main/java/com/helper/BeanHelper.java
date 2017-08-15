package com.helper;

import com.common.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * bean 容器
 * Created by wm on 2017/8/4.
 */
public class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        System.out.println("BeanHelper static init=======");
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();

        for (Class<?> cls : beanClassSet) {

            Object obj = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, obj);
        }
    }

    /**
     * 获取bean 映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {

        return BEAN_MAP;

    }


    /**
     * 获取bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {

        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean " + cls);
        }

        return (T) BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls, Object obj) {

        BEAN_MAP.put(cls, obj);
    }
    


}
