package com.yunwei.wetlandpark.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * @package com.yunwei.camera.service
 * @description 检测服务 (启动APP时提醒用户进行签到)
 * @author yang du
 * @date 2016/10/18
 * @time 下午8:18
 * @version V1.0
 **/
public class DetectService extends Service {
    private static final String TAG = "DetectService";

    private Handler mHandler;
    private DetectRunnable mDetectRunnale;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mDetectRunnale=new DetectRunnable();
        ILog.i(TAG,"#detection delayed 10 min");
        mHandler.postDelayed(mDetectRunnale,Constants.TEN_MINUTES_TO_MILLSECONDS);//10*60*1000 10分钟后检测有没有签到
    }

    @Override
    public void onDestroy() {
        mHandler = null;
        mDetectRunnale = null;
        super.onDestroy();
        ILog.i(TAG,"#detect service stop");
    }

    public class DetectRunnable implements Runnable {

        @Override
        public void run() {
            ILog.i(TAG,"#start detection");
            detectSignSate();
            //检测完后结束服务
            stopSelf();
        }
    }

    /**
     * 检测用户当天有没有进行签到
     */
    public void detectSignSate(){
//        String lastSignTimeStamp= (String) ISpfUtil.getValue(getApplication(), Constants.USER_SIGN_OUT_TIME_STAMP, "");
        boolean isSigned= (boolean) ISpfUtil.getValue(getApplication(),Constants.USER_SIGN_STATE_KEY,false);
        if (!isSigned){//&& TextUtils.isEmpty(lastSignTimeStamp)||!IDateTimeUtils.varifyToday(lastSignTimeStamp,null)) {
            //TODO 当天内还没有进行签到，发出消息提醒用户进行签到
            NoticeEvent event=new NoticeEvent();
            event.setFlag(EventConstant.USER_SIGN_REMAINDE);
            EventBus.getDefault().post(event);
        }
    }

}
