package com;

import com.common.ClassUtil;
import com.helper.*;

/**
 * 加载相应的helper 类
 * Created by wm on 2017/8/4.
 */
public class HelperLoader {
    public static void init() {


        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        try {
            for (Class<?> cls : classList)

            {
                System.out.println("cls.getName():" + cls.getName());
                ClassUtil.loadClass(cls.getName(), true);
            }
            System.out.println("初始化成功。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
