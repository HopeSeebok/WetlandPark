package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.UnitListListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.biz
 * @Description:
 * @date 2016/11/1 19:31
 */

public interface IUnitListAction {

    void reqUnitList(Activity activity, UnitListListener listListener);
}
