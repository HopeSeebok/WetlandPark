package com.yunwei.wetlandpark.ui.mainFunctions;

import com.yunwei.wetlandpark.ui.mainFunctions.data.UnitInfoEntity;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions
 * @Description:
 * @date 2016/11/16 16:58
 */

public interface MainContract {

    interface View extends BaseView {
        void requestUniteListSuccess(UnitInfoEntity entity);
    }

    interface presenter {
        /*获取七牛Token*/
        void reqQiNiuToken();

        /*获取单位列表*/
        void reqUniteList();
    }
}
