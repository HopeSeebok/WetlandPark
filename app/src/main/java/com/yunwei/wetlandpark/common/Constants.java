package com.yunwei.wetlandpark.common;


import android.util.SparseArray;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.yunwei.zaina.common
 * @Description:公共常量類
 * @author: Aaron
 * @date: 2016-06-02
 * @Time: 15:38
 * @version: V1.0
 */
public class Constants {

    /**
     * 时间
     */
    public static final int ONE_H_MILLSECONDS = 100;// 100ms
    public static final int THREE_H_MILLSECONDES = 300;// 300ms
    public static final int ONE_SECONDE = 1 * 1000;// 1s
    public static final int TWO_SECONDES = 2 * 1000;// 2s
    public static final int THREE_SECONDES = 3 * 1000;// 3s
    public static final int FOUR_SECONDES = 4 * 1000;// 4s
    public static final int FIVE_SECONDES = 5 * 1000;// 5s
    public static final int TEN_SECONDES = 10 * 1000;// 10s
    public static final int TWENTY_SECONDS = 20 * 1000;// 20s
    public static final int THIRTY_SENCONDS = 30 * 1000;// 30s
    public static final int FORTY_SECONDS = 40 * 1000; // 40s
    public static final int FIVTY_SECONDS = 50 * 1000; // 50s
    public static final int ONE_MINUTE = 60 * 1000;// 1minute
    public static final long FIVE_MINUTES_TO_MILLSECONDS = 5 * 60 * 1000;// 5minutes
    public static final long TEN_MINUTES_TO_MILLSECONDS = 10 * 60 * 1000;// 10minutes
    public static final long FIVTEEN_MINUTES_TO_MILLSECONDS = 15 * 60 * 1000;// 15minutes
    public static final long TIMESHIFT_TOTAL_TO_MILLSECONDS = 1 * 60 * 60 * 1000; // 1hour
    public static final long LIVEBACK_TOTAL_TO_MILLSECONDS = 3 * 24 * 60 * 60 * 1000;// 3d

    /*****
     * Key
     *****/
    public static final String USER_NAME_KEY = "user_name";
    public static final String USER_PWD_KEY = "user_pwd";
    public static final String LOGIN_INFO_KEY = "login_info";
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String USER_ICON_KEY = "user_icon";
    public static final String QINIU_TOKEN_KEY = "qiniu_token";
    public static final String TRACK_RECORD_MODE_KEY = "TRACK_RECORD_MODE";
    public static final String TRACK_RECORD_SWITCH_KEY = "track_record_switch";
    public static final String EVENT_VALUE_KEY = "event_key";
    public static final String EVENT_ID_KEY = "event_id";
    public static final String EVENT_NAME_KEY = "event_name";

    public static final String TRACK_RECORD_STRAT_TIME_KEY = "TRACK_RECORD_STRAT_TIME";//记录足迹开始的时间
    public static final String TRACK_RECORD_STATUE_KEY = "TRACK_RECORD_STATUE";//记录足迹状态:1-开始、2-暂停、3-完成
    public static final String TRACK_RECORD_ID_KEY = "TRACK_RECORD_ID";//足迹开始是记录ID

    /**
     * du yang :签到签退KEY
     */
    public static final String USER_SIGN_STATE_KEY = "USER_SIGN_STATE";//用户签到状态(true已签到、false未签到)
    public static final String USER_SIGNED_GROUP_USERS_KEY = "GROUP_USERS";//参与上班的组成员
    public static final String USER_SIGN_OUT_TIME_STAMP = "SIGN_OUT_STAMP";//用户当天签退的时间戳

    public static final int REFRESH_SUCCESS = 200;// 刷新
    public static final int REFRESH_SUCCESS2 = 201;// 刷新2
    public static final int DEVICE_EMPTY_VALUE = 2000;//设施不存在

    public static final String SPF_HIDDEN_DANGER_TYPE_KEY = "SPF_HIDDEN_DANGER_TYPE_KEY";

    /**
     * Seebok添加：FLAGS
     */
    public static final String BUNDLE_ENTITY_FLAG_ONE = "BUNDLE_ENTITY_FLAG_ONE";//数据包 实体类FLAG 1
    public static final String BUNDLE_ENTITY_FLAG_TWO = "BUNDLE_ENTITY_FLAG_TWO";//数据包 实体类FLAG 2
    public static final String BUNDLE_TAG_FLAG = "BUNDLE_TAG_FLAG";//数据包 标记FLAG
    public static final String BUNDLE_TAG_FLAG_TWO = "BUNDLE_TAG_FLAG";//数据包 标记FLAG

    public static final String BUNDLE_FACILITY_FLAG = "BUNDLE_FACILITY_FLAG";//数据包 实体类Facility
    public static final String BUNDLE_WORKTABLE_FLAG = "BUNDLE_WORKTABLE_FLAG";//数据包 实体类WorkRecordTable

    /**
     * Seebok添加：DEVICE KEY  设施相关的KEY
     */
    public static final String KEY_BUNDLE_DEVICE_INFO = "KEY_BUNDLE_DEVICE_INFO";
    public static final String KEY_BUNDLE_CHECK_DEVICE_TABLE = "KEY_BUNDILE_CHECK_DEVICE_TABLE";
    public static final String KEY_BUNDLE_MAINTAIN_DEVICE_TABLE = "KEY_BUNDLE_MAINTAIN_DEVICE_TABLE";
    public static final String KEY_BUNDLE_TROUBLE_TABLE = "KEY_BUNDLE_TROUBLE_TABLE";
    public static final String KEY_BUNDLE_TROUBLE_INFO = "KEY_BUNDLE_TROUBLE_INFO";
    /**
     * Seebok添加：DEVICE  设施相关的常量
     */
    public static final int VALUE_DEVICE_STATUS_NORMAL = 0;
    public static final int VALUE_DEVICE_STATUS_FAULT = 1;
    public static final int VALUE_DEVICE_STATUS_ABANDONED = 2;
    public static final int VALUE_UNSUBMITTED = 0;
    public static final int VALUE_SUBMITTED = 1;
    /**
     * Seebok添加
     */
    public static SparseArray<String> TROUBLE_EMERGENCY_LEVEL = new SparseArray<String>(){{put(0,"一般");put(1,"重要");put(2,"紧急");}};
    public static SparseArray<String> TROUBLE_STATUS = new SparseArray<String>(){{put(0,"未处理");put(1,"处理中");put(2,"已处理");}};
    /**
     * Seebok添加：隐患类型
     */

    public static final List<String> HDTYPES_LIST = Arrays.asList("隐患类型一", "隐患类型二", "隐患类型三", "隐患类型四");

    /**
     * Seebok添加：facilityType  code - string
     */
    public static class Facility {

        public static String FLAG = "Facility";

    }

    /**
     * Seebok添加：本地数据库Local State（提交/未提交...）
     */
    public static class LocalState {
        public final static int UNSUBMITTED = 0;
        public final static int SUBMITTED = 1;
    }

    /**
     * 签到类型（SIGN_IN：签到／SIGN_OUT：签退）
     * du yang
     */
    public static class UserSignType {
        public static final int SIGN_IN = 1;
        public static final int SIGN_OUT = 2;
    }

    /**
     * 用户签到状态（SIGN_IN_SUCCESS_STATE：签到成功／SIGN_OUT_SUCCESS_STATE：签退成功／IS_SIGNED_STATE：今天已签）
     * du yang
     */
    public static class UserWorkStatus {
        public static final int SIGN_IN_SUCCESS_STATE = 1;
        public static final int SIGN_OUT_SUCCESS_STATE = 2;
        public static final int IS_SIGNED_STATE = 3;
    }


    /**
     * 足迹采集方式
     */
    public enum TRACK_RECORD_MODE {
        WALK(1), RIDING(2), DRIVE(3);
        private int value;

        public int getValue() {
            return value;
        }

        TRACK_RECORD_MODE(int value) {
            this.value = value;
        }
    }

    /**
     * 足迹采集状态
     */
    public enum TRACK_RECORD_STATE {
        START(1), STOP(2), COMPLETE(3);

        private int value;

        public int getValue() {
            return value;
        }

        TRACK_RECORD_STATE(int value) {
            this.value = value;
        }
    }

    /**
     * Feature图层
     */
    public enum MAP_FEATURE_STATE {
        HS_STATE(0), BIAOZHU(4), QIANGJI(5), QIUJI(6);

        private int value;

        public int getValue() {
            return value;
        }

        MAP_FEATURE_STATE(int value) {
            this.value = value;
        }
    }

    /**
     * 里程补充
     * no_supplement：不补充，中断两点间距离不记入里程
     * straight：使用直线距离补充
     * driving：使用最短驾车路线距离补充
     * riding：使用最短骑行路线距离补充
     * walking：使用最短步行路线距离补充
     */
    public enum TRACK_DISTANCE_MODE {
        NOSUPPLEMENT("no_supplement"), STRAIGHT("straight"), DRIVING("driving"), RIDING("riding"), WALKING("walking");
        private String value;

        public String getValue() {
            return value;
        }

        TRACK_DISTANCE_MODE(String value) {
            this.value = value;
        }
    }

    /* 数据库实体类标准状态*/
    public static class StandardState {
        public static final int NewCreate = 0x008;//新建的状态，用于进行一些初始化的操作
        public static final int SaveLocal = 0x009;//保存在本地
        public static final int IsUpload = 0x010;//已经提交
        public static final int Network = 0x011;//网络上获取
    }

    //任务
    public static final String JPUSHALIAS = "wt_id_";
    public static final String ACCOUNT_ID_KEY = "account_id_key";
    public static final String TASKENTITY = "taskentity";
    public static final String TASKID = "taskid";
    public static final String TASKTYPE = "tasktype";
    public static final String SEARCHKEY = "searchkey";
    public static final String KEY = "key";
    public static final String TASKPOINTLISTSTATUS= "task_point_list_status";
    //未派发、已派发、处理中、已撤销、已接单、已退回、已完成、已审核
//    public static final int WPF = 0;
//    public static final int YPF = 1;
//    public static final int CLZ = 2;
//    public static final int YCX = 3;
//    public static final int YJD = 4;
//    public static final int YTH = 5;
//    public static final int YWC = 6;
//    public static final int YSH = 7;
//    /**
//     * 任务状态
//     */
//    public enum TASK_STATUS {
//        DIT_NOT_PAY(0), HAVE_BEEN_DISTRIBUTED(1), BEING_PROCESSED(2), HAD_WITHDRAWN(3), HAVE_ORDER(4),
//        HAS_BEEN_RETURNED(5), HAVE_DONE(6), THE_APPROVER(7);
//        private int value;
//        public int getValue() {
//            return value;
//        }
//        TASK_STATUS(int value) {
//            this.value = value;
//        }
//    }
    //电信机房任务状态1、处理中  2、待审核 ,10、已完成,11、已终止,
    public static final int BEING_PROCESSED = 1;
    public static final int CHECK_PENDING = 2;
    public static final int HAVE_DONE = 10;
    public static final int TERMINATED = 11;
    /**
     * 任务等级
     */
    public enum TASK_LEVEL {
        NORMAL(0), IMPORTANT(1), URGENCY(2);
        private int value;
        public int getValue() {
            return value;
        }
        TASK_LEVEL(int value) {
            this.value = value;
        }
    }
    /**
     * 任务模式
     */
    public enum TASK_MOLD {
        TEMPORARY(0), PLAN(1);
        private int value;
        public int getValue() {
            return value;
        }
        TASK_MOLD(int value) {
            this.value = value;
        }
    }
    //    setting
    public static final String TASK_VOICE_TIPS = "task_voice_tips";
    public static final String TASK_NOTICE_TIPS = "task_notice_tips";
    public static final String IS_FIRST_START = "is_first_start";
}
