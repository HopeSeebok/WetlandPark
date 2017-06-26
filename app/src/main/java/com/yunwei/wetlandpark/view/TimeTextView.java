package com.yunwei.wetlandpark.view;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.utils
 * @Description: 时间倒计时控件（距离当前的时间）
 * @date 2016/11/23
 * @changeby:
 */

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;

import java.util.Date;

public class TimeTextView extends TextView {
    private long mTimeEnd;
    private long mTime;
    private FinishListener mFinishListener;
    private Context mContext;
    private CountDownTimer mCountDownTimer;

    public TimeTextView(Context context) {
        super(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置结束时间
     *
     * @param timeEnd 结束时间 2016-11-24-17-30-00
     */
    public TimeTextView setTimes(Context context,long timeEnd) {
        mTimeEnd = timeEnd;
        mContext=context;
        setTimeRemain();
        return this;
    }

    public void start() {
        if (mTime > 0) {
            if (mCountDownTimer!=null) {
                return;
            }
            mCountDownTimer=new CountDownTimer(mTime, 1000) {
                public void onTick(long millisUntilFinished) {
                    long hour = millisUntilFinished / 1000 / 60 / 60;
                    if (hour >= 24) {
                        TimeTextView.this.setText("剩余时间: " + hour / 24 + "天");
                    } else {
                        TimeTextView.this.setText("剩余时间: " + IDateTimeUtils.getTimes(millisUntilFinished));
                        TimeTextView.this.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                    }
                }

                public void onFinish() {
//                    TimeTextView.this.setText("任务已过期");
                    if (mFinishListener != null) {
                        mFinishListener.doSomething();
                    }
                }
            }.start();
        } else {
            mFinishListener.doSomething();
        }
    }

    public TimeTextView setFinishListener(FinishListener finishListener) {
        mFinishListener = finishListener;
        return this;
    }

    public interface FinishListener {
        void doSomething();
    }

    /**
     * 设置剩余时间
     */
    private void setTimeRemain() {
        Date date = new Date();
        long mTimeNow = date.getTime();
        mTime = mTimeEnd - mTimeNow;
    }

}