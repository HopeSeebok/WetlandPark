package com.yunwei.wetlandpark.ui.deviceFunctions;

import android.app.Activity;

/**
 * @author CBOK
 * @date 2016/11/15 9:56
 * @description:
 */

public interface BaseViewPro<T> {
    Activity getActivity();
    void setPresenter(T presenter);
}
