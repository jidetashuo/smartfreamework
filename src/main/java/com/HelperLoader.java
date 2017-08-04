package com;

import com.common.ClassUtil;
import com.helper.BeanHelper;
import com.helper.ClassHelper;
import com.helper.ControllerHelper;
import com.helper.IocHelper;

/**
 * 加载相应的helper 类
 * Created by wm on 2017/8/4.
 */
public class HelperLoader {
    public static void init() {

        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList)

        {
            ClassUtil.loadClass(cls.getName(), false);
        }

    }
}
