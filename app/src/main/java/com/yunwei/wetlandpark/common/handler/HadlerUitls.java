package com.yunwei.wetlandpark.common.handler;

import android.os.Handler;
import android.os.Message;

/**
 * @Package: com.yunwei.zaina.common.handler
 * @Description:Handler工具类
 * @author: Aaron
 * @date: 2016-06-05
 * @Time: 15:16
 * @version: V1.0
 */
public class HadlerUitls {
    /**
     * 发送空省
     *
     * @param handler
     * @param what
     */
    public static void sendEmptyMessage(Handler handler, int what) {
        if (handler == null) {
            return;
        }
        handler.sendEmptyMessage(what);
    }

    /**
     * 延时发送空消息
     *
     * @param handler
     * @param what
     * @param delaymillis
     */
    public static void sendEmptyMessageDelayed(Handler handler, int what, long delaymillis) {
        if (handler == null) {
            return;
        }
        handler.sendEmptyMessageDelayed(what, delaymillis);
    }

    /**
     * @param handler
     * @param what
     * @param uptimeMillis
     */
    public static void sendEmptyMessageAtTime(Handler handler, int what, long uptimeMillis) {
        if (handler == null) {
            return;
        }
        handler.sendEmptyMessageAtTime(what, uptimeMillis);
    }

    /**
     * 发送obj消息
     *
     * @param handler
     * @param what
     * @param obj
     */
    public static void sendHandlerMessage(Handler handler, int what, Object obj) {
        if (handler == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(what, obj));
    }

    /**
     * 发送Obj消息
     *
     * @param handler
     * @param what
     * @param arg1
     * @param obj
     */
    public static void sendHandlerMessage(Handler handler, int what, int arg1, Object obj) {
        if (handler == null) {
            return;
        }
        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.arg1 = arg1;
        msg.obj = obj;
        handler.sendMessage(msg);
    }

    /**
     * 发送Obj消息
     *
     * @param handler
     * @param what
     * @param arg1
     * @param arg2
     * @param obj
     */
    public static void sendHandlerMessage(Handler handler, int what, int arg1, int arg2, Object obj) {
        if (handler == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(what, arg1, arg2, obj));
    }

    /**
     * 发送消息
     *
     * @param handler
     * @param what
     * @param arg1
     */
    public static void sendHandlerMessage(Handler handler, int what, int arg1) {
        if (handler == null) {
            return;
        }
        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.arg1 = arg1;
        handler.sendMessage(msg);
    }

    /**
     * 发送消息
     *
     * @param handler
     * @param what
     * @param arg1
     * @param arg2
     */
    public static void sendHandlerMessage(Handler handler, int what, int arg1, int arg2) {
        if (handler == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(what, arg1, arg2));
    }

    /**
     * 延时发送
     * @param handler
     * @param what
     * @param arg1
     * @param arg2
     * @param obj
     * @param time
     */
    public static void sendHandlerMessageDelayTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        if (handler == null) {
            return;
        }
        handler.sendMessageDelayed(handler.obtainMessage(what, arg1, arg2, obj), time);
    }

    /**
     *
     * @param handler
     * @param what
     * @param arg1
     * @param arg2
     * @param obj
     * @param time
     */
    public static void sendHandlerMessageAtTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        if (handler == null) {
            return;
        }
        handler.sendMessageAtTime(handler.obtainMessage(what, arg1, arg2, obj), time);
    }
}
