package com.yunwei.wetlandpark.ui.presenter;

import android.app.Activity;

import com.yunwei.wetlandpark.entity.RefreshLoactionEntity;
import com.yunwei.wetlandpark.ui.biz.IRefreshLocation;
import com.yunwei.wetlandpark.ui.biz.impl.RefreshLocation;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.presenter
 * @Description:更新当前位置
 * @date 2016/7/28 9:35
 */
public class RefreshLocationPresenter {

    private IRefreshLocation locationBiz;

    public RefreshLocationPresenter() {
        locationBiz = new RefreshLocation();
    }

    public void refreshlocation(Activity activity, RefreshLoactionEntity entity) {
        locationBiz.refreshlocation(activity, entity);
    }
}
