package com.helper;

import com.annotation.Inject;
import com.common.ReflectionUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * inject 注解 解析   依赖注入   field
 * Created by wm on 2017/8/4.
 */
public final class IocHelper {
    static {

        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (!MapUtils.isEmpty(beanMap)) {


            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从beanMap 中获取bean类和bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();

                if (ArrayUtils.isNotEmpty(beanFields)) {
                    //循环bean field
                    for (Field beanField : beanFields) {
                        //是否有inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            //在beanMap中获取带有inject注解的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldClass != null) {
                                //通过反射初始化beanFileld 的值

                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }


                        }

                    }

                }

            }


        }

    }
}
