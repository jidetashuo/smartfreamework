package com.helper;

import com.annotation.Action;
import com.common.Handler;
import com.common.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * action 注解解析
 * Created by wm on 2017/8/4.
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {

        //获取所有的controller类

        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (!CollectionUtils.isEmpty(controllerClassSet)) {

            for (Class<?> controllerClass : controllerClassSet) {
                //获取方法
                Method[] methods = controllerClass.getMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    //循环带有action注解的方法
                    for (Method method : methods)
                        if (method.isAnnotationPresent(Action.class)) {

                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            //验证url映射规则
                            if (mapping.matches("\\w+:/\\w*")) {

                                String[] array = mapping.split(":");
                                if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                    //获取请求方法和路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];

                                    Request request = new Request(requestMethod, requestPath);

                                    Handler handler = new Handler(controllerClass, method);
                                    //初始化actionMap
                                    ACTION_MAP.put(request, handler);
                                }


                            }

                        }

                }


            }


        }

    }


    public static Handler getHandler(String requestMethod, String requestpath) {


        Request request = new Request(requestMethod, requestpath);

        return ACTION_MAP.get(request);


    }

}
