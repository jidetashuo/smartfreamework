package com.response;

import java.util.Map;

/**
 * 视图对象
 * Created by wm on 2017/8/4.
 */
public class View {
    //视图路径
    private String path;


    private Map<String, Object> model;


    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
