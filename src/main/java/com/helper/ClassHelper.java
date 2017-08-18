package com.helper;

import com.annotation.Component;
import com.annotation.Controller;
import com.annotation.Service;
import com.common.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wm on 2017/8/4.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        System.out.println("ClassHelper static init=======");
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
        classSet.addAll(getClassSetByAnnotation(Component.class,classSet));
        return classSet;

    }


    /**
     * 获取应用包名下的某父类或接口的所有子类或实现
     *
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBysuper(Class<?> superClass) {

        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for (Class<?> cls : CLASS_SET) {

            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {

                classSet.add(cls);
            }
        }

        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends  Annotation> annotationClass ){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);

            }
        }

        return classSet;


    }


    /**
     * 获取某注解的所有类
     *
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<?> annotationClass,Set<Class<?>> classSet) {

        for (Class<?> cls : CLASS_SET) {

            if (cls.isAnnotationPresent((Class<? extends Annotation>) annotationClass)) {

                if(cls.isAnnotation()){
                    getClassSetByAnnotation(cls,classSet);

                }else{
                    classSet.add(cls);
                }
            }
        }

        return classSet;
    }

}
