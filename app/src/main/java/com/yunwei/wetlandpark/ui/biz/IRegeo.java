package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.RegeoLisenter;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/19 11:26
 */
public interface IRegeo {

    void regeo(Activity activity,double lng, double lat, RegeoLisenter lisenter);
}
