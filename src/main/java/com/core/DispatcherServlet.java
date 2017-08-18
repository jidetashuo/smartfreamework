package com.core;

import com.HelperLoader;
import com.common.*;
import com.helper.BeanHelper;
import com.helper.ConfigHelper;
import com.helper.ControllerHelper;
import com.helper.ServletHelper;
import com.response.Data;
import com.response.View;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wm on 2017/8/4.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("DispatcherServlet init==============");
        //初始化相关 Helper类
        HelperLoader.init();
        //获取servletContext 对象（用于注册servlet）
        ServletContext servletContext = servletConfig.getServletContext();
//        注册处理JSP的servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getJspPath());
        //注册处理静态资源的servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath());


        super.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try {


            //请求方法
            String requestMethod = request.getMethod();
            //请求路径
            String requestPath = request.getPathInfo();
            //获取action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
                //获取controller类及其实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                //创建请求参数对象
                Map<String, Object> paramMap = new HashMap<String, Object>();


                //Enumeration（枚举）接口的作用和Iterator类似，只提供了遍历Vector和HashTable类型集合元素的功能，不支持元素的移除操作
                //boolean hasMoreElements();//是否还有元素，如果有返回true，否则表示至少含有一个元素
                //E nextElement();//如果Enumeration枚举对象还有元素，返回对象只能的下一个元素，否则抛出NoSuchElementException异常。
                Enumeration<String> paramNames = request.getParameterNames();

                while (paramNames.hasMoreElements()) {

                    String paramName = paramNames.nextElement();
                    String paramValue = request.getParameter(paramName);
                    paramMap.put(paramName, paramValue);

                    //从输入流中获取字符串并编码
                    String body = CodecUtil.encodeUrl(StreamUtil.getString(request.getInputStream()));
                    if (body != null && body.length() > 0) {

                        String[] params = body.split("&");

                        for (String param : params) {

                            String[] array = param.split("=");
                            if (ArrayUtils.isNotEmpty(array) && array.length == 2) {

                                String paramName2 = array[0];
                                String paramValue2 = array[1];

                                paramMap.put(paramName2, paramValue2);
                            }
                        }

                    }


                }


                Param param = new Param(paramMap);
                //调用action方法

                Method actionMethod = handler.getActionMethod();


                //调用action method
                Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);

                //处理方法返回结果
                if (result instanceof View) {

                    //视图

                    View view = (View) result;
                    String path = view.getPath();

                    if (path != null && path.length() > 0) {

                        if (path.startsWith("/")) {
                            //TODO  不需要添加model?
                            //重定向
                            response.sendRedirect(request.getContextPath() + path);
                        } else {

                            Map<String, Object> model = view.getModel();
                            for (Map.Entry<String, Object> entry : model.entrySet()) {

                                request.setAttribute(entry.getKey(), entry.getValue());

                            }
                            //转发
                            request.getRequestDispatcher(ConfigHelper.getJspPath()).forward(request, response);

                        }


                    }


                } else if (result instanceof Data) {

                    //返回json数据
                    Data data = (Data) result;
                    Object model = data.getModel();
                    if (model != null) {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter writer = response.getWriter();
                        String json = JSONUtil.toJson(model);
                        writer.write(json);
                        writer.flush();
                        writer.close();
                    }


                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            ServletHelper.destory();
        }


    }


}
