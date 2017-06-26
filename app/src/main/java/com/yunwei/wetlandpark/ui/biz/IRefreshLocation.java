package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.entity.RefreshLoactionEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz
 * @Description:
 * @date 2016/7/28 9:37
 */
public interface IRefreshLocation {

    public void refreshlocation(Activity activity, RefreshLoactionEntity entity);
}
