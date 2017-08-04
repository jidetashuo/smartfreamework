package com.common;

import com.annotation.Controller;
import com.annotation.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wm on 2017/8/4.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {

        String basepackage = ConfigHelper.getBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basepackage);
    }

    /**
     * 获取应用包名下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {

        return CLASS_SET;
    }

    /**
     * 获取service注解下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {

            if (cls.isAnnotationPresent(Service.class)) {

                classSet.add(cls);
            }
        }
        return classSet;

    }

    /**
     * 获取controller 注解下的类
     *
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {

            if (cls.isAnnotationPresent(Controller.class)) {

                classSet.add(cls);
            }
        }
        return classSet;

    }


    /**
     * 所有bean类
     *
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;

    }

}
