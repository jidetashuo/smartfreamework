package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 * Created by wm on 2017/8/5.
 */
public final class StreamUtil {

    /**
     * 从输入流中读取字符串
     *
     * @param is
     * @return
     */
    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {

                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
