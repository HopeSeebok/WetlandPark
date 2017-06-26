package com.yunwei.library.qiniu;

/**
 * 配置信息
 * Created by yangdu on 16/6/8.
 */
public final class Config {

    public static final String DEBUG_MODE = "debug";//
    public static final String RELEASE_MODE = "release";
    public static final String MODE=RELEASE_MODE;//当前模式

    public static final String QINIU_TOCKEN_KEY = "qiniu_tocken";//七牛tocken
    //测试用
    public static final String QINIU_TOCKEN_VALUE = "qXYdwh4EL6uMDvZcHu8VlrmQ0MUN0HEcs4ZKtmqT:UeJ6e3mu8ozAGJw88fYwozUoWRk=:eyJzY29wZSI6ImltZy1kdHhsaW5rIiwiZGVhZGxpbmUiOjE0NjUzOTA5MjMsImluc2VydE9ubHkiOjAsImRldGVjdE1pbWUiOjAsImZzaXplTGltaXQiOjAsImZzaXplTWluIjowLCJjYWxsYmFja0ZldGNoS2V5IjowfQ==";

    public static final String SP_WEBCONFIG_FILENAME = "webconfig";//网络配置相关sp

    public static final String DOMAIN="http://116.7.249.34:1520";
    public static final String LOGIN_DOMAIN = DOMAIN+"/token";//http://192.168.82.12/token
    public static final String GD_URL=DOMAIN+"/v1/device/gd";

    public static final String QINIU_TOCKEN_DOMAIN=DOMAIN+"/v1/qiniu_token";
    public static final String IMAGE_DOMAIN = "http://img.wayto.com.cn/";

    public static final String WATER_DOMIN="http://120.24.152.97:1810/api/damage";
}
