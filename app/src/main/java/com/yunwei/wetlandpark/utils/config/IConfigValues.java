package com.yunwei.wetlandpark.utils.config;

import com.yunwei.wetlandpark.BuildConfig;

/**
 * @Package: com.yunwei.zaina.utils.config
 * @Description: 获取Confing.xml中的配制项
 * @author: Aaron
 * @date: 2016-06-05
 * @Time: 17:40
 * @version: V1.0
 */
public class IConfigValues {
    /**
     * Http请求域
     */
    public static final String DOMAIN = BuildConfig.DOMAI;
    /**
     * 登录
     */
    public static final String LOGIN_URL = DOMAIN + IConfig.getConfig("LOGIN_URL");
    /**
     * 用户签到
     */
    public static final String USER_SIGN_URL = DOMAIN + IConfig.getConfig("USER_SIGN_URL");
    /**
     * 七牛TOKEN
     */
    public static final String QINIU_TOKEN_URL = DOMAIN + IConfig.getConfig("QINIU_TOKEN_URL");
    /**
     * 逆地理编码
     */
    public static final String REVERSE_GEO_CODIN = IConfig.getConfig("REVERSE_GEO_CODIN");
    /**
     * 修改用户信息
     */
    public static final String UPDATE_USER_INFO_URL = DOMAIN + IConfig.getConfig("UPDATE_USER_INFO_URL");
    /**
     * 修改用户密码
     */
    public static final String UPDATE_USER_PWD_URL = DOMAIN + IConfig.getConfig("UPDATE_USER_PWD_URL");
    /**
     * 图层
     */
    public static final String MAP_FEATURE_URL = IConfig.getConfig("MAP_FEATURE_URL");
    /**
     * 刷新位置
     */
    public static final String REFRESH_LOCATION = DOMAIN + IConfig.getConfig("REFRESH_LOCATION");
    /**
     * 图层查询
     */
    public static final String MAP_LAYER_SEARCH_URL = IConfig.getConfig("MAP_LAYER_SEARCH_URL");
    /**
     * 版本检测
     */
    public static final String UPDATE_VERSION_URL = DOMAIN + IConfig.getConfig("UPDATE_VERSION_URL");
    /**
     * by id 查询设施信息
     */
    public static final String DEVICE_INFO_BY_ID = DOMAIN + IConfig.getConfig("DEVICE_INFO_BY_ID");
    /**
     * by rfid 查询设施信息
     */
    public static final String DEVICE_INFO_BY_RFID = DOMAIN + IConfig.getConfig("DEVICE_INFO_BY_RFID");
    /**
     * 足迹上传
     */
    public static final String TRACK_UPLOAD_URL = DOMAIN + IConfig.getConfig("TRACK_UPLOAD_URL");
    /**
     * 足迹详情
     */
    public static final String TRACK_DETAIL_URL = DOMAIN + IConfig.getConfig("TRACK_DETAIL_URL");
    /**
     * 足迹查询 by 日期
     */
    public static final String TRACK_QUERY_BY_DATA = DOMAIN + IConfig.getConfig("TRACK_QUERY_BY_DATA");

    /**
     * 配制文件
     */
    public static final String CONFIG_URL = DOMAIN + IConfig.getConfig("CONFIG_URL");
    /**
     * 新增设施
     */
    public static final String CREATE_NEW_FAC = DOMAIN + IConfig.getConfig("NEW_FAC");

    /**
     * 新增工作记录
     */
    public static final String NEW_WORK_RECORD = DOMAIN + IConfig.getConfig("NEW_WORK_RECORD");

    /**
     * 获取隐患列表
     */
    public static final String GET_HIDDEN_DANGER_TYPE = DOMAIN + IConfig.getConfig("GET_HIDDEN_DANGER_TYPE");

    /**
     * 修改设施信息
     */
    public static final String UPDATE_FACILITY_INFO = DOMAIN + IConfig.getConfig("UPDATE_FACILITY_INFO");

    /**
     * 通过片区查找 所属派出所
     */
    public static final String GET_OWNER_INFO = DOMAIN + IConfig.getConfig("GET_OWNER");

    /**
     * 获取任务详情信息
     */
    public static final String GET_TASKDETAIL_INFO = DOMAIN + IConfig.getConfig("GET_TASKDETAIL_INFO");

    /**
    /**
     * 获取任务详情信息
     */
    public static final String GET_MAINTAIN_INFO = DOMAIN + BuildConfig.GET_MAINTAIN_INFO;

    /**
     * 撤销任务点
     */
    public static final String REVOKE_TASK_POINT = DOMAIN + IConfig.getConfig("REVOKE_TASK_POINT");

    /**
     * 修改设施信息
     */
    public static final String CHANGE_TASKSTATUS_INFO = DOMAIN + IConfig.getConfig("CHANGE_TASKSTATUS_INFO");

    /**
     * 获取隐患信息
     */
    public static final String GET_HD_INFO = DOMAIN + IConfig.getConfig("GET_HD_INFO");

    /**
     * 获取隐患信息
     */
    public static final String OFF_LINE_MESSAGE = DOMAIN + IConfig.getConfig("OFF_LINE_MESSAGE");

    /**
     * 关键字搜索信息
     */
    public static final String SEARCH_REQUEST = DOMAIN + IConfig.getConfig("SEARCH_REQUEST");
    /**
     * 图层更新检测
     */
    public static final String MAP_LAYER_CHECK = DOMAIN + IConfig.getConfig("MAP_LAYER_CHECK");


}
