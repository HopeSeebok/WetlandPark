package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackByDataListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/18 17:02
 */
public interface IQueryTrackByData {

    void queryTrack(Activity activity, String data, QueryTrackByDataListener listener);
}
