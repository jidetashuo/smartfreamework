package com.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wm on 2017/8/16.
 */
public class ServletHelper {
    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_THREAD_LOCAL = new ThreadLocal<ServletHelper>();


    private HttpServletRequest request;
    private HttpServletResponse response;


    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static void init(HttpServletRequest request, HttpServletResponse response) {

        SERVLET_HELPER_THREAD_LOCAL.set(new ServletHelper(request, response));
    }

    public static void destory() {

        SERVLET_HELPER_THREAD_LOCAL.remove();
    }

    private static HttpServletRequest getRequest() {


        return SERVLET_HELPER_THREAD_LOCAL.get().request;
    }

    private static HttpServletResponse getResponse() {

        return SERVLET_HELPER_THREAD_LOCAL.get().getResponse();
    }

    private static HttpSession getSession() {

        return getRequest().getSession();
    }

    private static ServletContext getServletContext() {

        return getRequest().getServletContext();
    }


    public static void setRequestAttribute(String key, Object value) {

        getRequest().setAttribute(key, value);

    }


    public static <T> T getRequestAddtribute(String key) {

        return (T) getRequest().getAttribute(key);
    }



}
