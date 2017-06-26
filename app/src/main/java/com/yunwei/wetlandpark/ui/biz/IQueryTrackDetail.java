package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackDetailListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/18 17:12
 */
public interface IQueryTrackDetail {

    void queryTrackDetail(Activity activity, int id, QueryTrackDetailListener listener);
}
