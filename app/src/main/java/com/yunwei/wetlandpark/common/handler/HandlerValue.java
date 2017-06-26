package com.yunwei.wetlandpark.common.handler;

/**
 * @Package: com.jinggan.dear.common
 * @Description:定义handler数据值
 * @author: Aaron
 * @date: 2015-12-11
 * @Time: 13:22
 * @version: V1.0
 */
public class HandlerValue {

    /*地位相關*/
    public static final int GOOGLE_LOCATION_VALUE=0x1001;

    /*启动页、登录*/
    public static final int WELCOME_DELAYED_VALUE=0x2001;

    /*水表*/
    public static final int WATER_INFO_SUCCESS=0x3001;
    public static final int PLAN_WATER_METER_SUCCESS=0x3002;
    public static final int QUERY_WATER_METER_SUCCESS=0x3003;
    public static final int QUERY_WM=0x3004;
    public static final int WATER_DATA_SUBMIT=0x3005;

    /*足迹*/
    public static final int QUER_HIS_TRACK_SUCCESS=0x4001;
    public static final int START_RECORD_TIME=0x4002;

    public final static int SCAN_RFID_RESULT = 0x456;

    /*设施**/
    public static  final int QUER_HIS_FACILITY_SUCCESS=0x009;//查询成功

    /*历史**/
    public static final int QUERY_FAC_VALUE=0x1001;
    public static final int UPLOAD_FAC_INFO=0x1002;

}
