package com.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wm on 2017/8/5.
 */
public final class CodecUtil {

    /**
     * 将url编码
     *
     * @param source
     * @return
     */
    public static String encodeUrl(String source) {
        String target = null;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return target;

    }

    /**
     * 将URL解码
     *
     * @param source
     * @return
     */
    public static String decodeUrl(String source) {

        String target = null;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * md5 加密
     *
     * @param source
     * @return
     */


    public static String md5(String source) {

        return DigestUtils.md5Hex(source);
    }
}
