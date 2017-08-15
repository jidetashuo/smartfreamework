package com.helper;

import com.common.ConfigConstant;
import com.common.PropsUtil;

import java.util.Properties;

/**
 * Created by wm on 2017/8/3.
 */
public class ConfigHelper {
    private static final Properties CONFIG_PRO = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);


    public static String getBasePackage() {

        return PropsUtil.getString(CONFIG_PRO, ConfigConstant.BASE_PACKAGE);

    }

    public static String getJspPath() {

        return PropsUtil.getString(CONFIG_PRO, ConfigConstant.JSP_PATH);
    }

    public static String getAppAssetPath() {

        return PropsUtil.getString(CONFIG_PRO, ConfigConstant.ASSET_PATH);
    }


}
