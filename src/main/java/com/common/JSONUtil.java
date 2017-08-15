package com.common;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by wm on 2017/8/5.
 */
public final class JSONUtil {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * pojo 对象转json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json 转pojo
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {

        T obj = null;

        try {
            obj = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
