package com.yunwei.wetlandpark.ui.mainFunctions.data.source;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.mainFunctions.data.UnitInfoEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.data.source
 * @Description:
 * @date 2016/11/16 17:09
 */

public interface MainDataSource {
    /**
     * 七牛Token
     */
    interface RequestQiNiuTokenCallBack {
        void getQiNiuTokenSuccess(String token);
    }

    void reqQiNiuToken(Activity activity, RequestQiNiuTokenCallBack callBack);

    /**
     * 单位列表
     */
    interface RequestUnitListCallBack {
        void getUnitListSuccess(UnitInfoEntity entity);

        void getUnitListFailure();
    }

    void reqUnitList(Activity activity, RequestUnitListCallBack callBack);

}
