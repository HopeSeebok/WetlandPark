package com.yunwei.wetlandpark.utils.config;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.yunwei.wetlandpark.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Package: com.yunwei.zaina.utils.config
 * @Description: 读取config.xml配制文件
 * @author: Aaron
 * @date: 2016-06-05
 * @Time: 17:35
 * @version: V1.0
 */
public class IConfig {
    private final static String TAG = IConfig.class.getSimpleName();
    /**
     * 记录是否初始化，避免多次初始化
     */
    private static boolean IS_INIT = false;
    /**
     * 配制的参数集合
     */
    private static HashMap<String, String> configMap = new HashMap<String, String>();
    /**
     * 当前配制环境
     */
    private static String currentTAG = "";

    /**
     * 初始化
     *
     * @param context
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static void init(Context context) {
        if (context == null) {
            return;
        }
        if (IS_INIT) {
            return;
        }
        IS_INIT = true;

        boolean flagTag = false;// 标记属于需要配置的TAG
        boolean isConfigInTag = false;// 标记读取的值是在配置的TAG中
        String configTag = "";
        /**
         * 获取pull对象
         */
        XmlResourceParser parser = context.getResources().getXml(R.xml.config);

        int eventType = -1;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                // 文档标签开始
                case XmlPullParser.START_DOCUMENT:
                    break;
                // 标签开始
                case XmlPullParser.START_TAG:
                    String nodeName = parser.getName();// 得到标签名
                    if ("CURRENT_ENV".equals(nodeName)) {// 得到当前的配制环境
                        configTag = parser.getAttributeValue(null, "value");
                        currentTAG = configTag;
                        Log.d(TAG, "当前配制环境为=" + configTag);
                    } else if ("TAG".equals(nodeName)) {// 配制选项
                        isConfigInTag = true;
                        String value = parser.getAttributeValue(null, "name");
                        if (configTag.equals(value)) {// 判断是否当前环境配制的参数
                            flagTag = true;
                        }
                    } else if (flagTag || !isConfigInTag) {
                        String value = parser.getAttributeValue(null, "value");
                        configMap.put(nodeName, value);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    String nodeNameEnd = parser.getName();
                    if ("TAG".equals(nodeNameEnd)) {
                        isConfigInTag = false;
                        flagTag = false;
                    }
                    break;

                default:
                    break;
            }
            try {
                eventType = parser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前配制环境
     *
     * @return
     */
    public static String getCurrentConfigTAG() {
        return currentTAG;
    }

    /**
     * 获取所有的配制信息
     *
     * @return
     */
    public static HashMap<String, String> getConfigInfo() {
        return configMap;
    }

    /**
     * 获取配制的值
     *
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        return configMap.get(key);
    }


}
