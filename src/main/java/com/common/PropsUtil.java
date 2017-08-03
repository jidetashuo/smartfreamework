package com.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wm on 2017/8/3.
 */
public class PropsUtil {


    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);


    /**
     * 加载配置文件
     *
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName) {

        Properties props = null;
        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if (is == null) {
                throw new FileNotFoundException(fileName + " is not found");
            }

            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return props;
    }


    /**
     * 获取属性
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {

        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public static String getString(Properties properties, String key) {

        return getString(properties, key, "");
    }


}
