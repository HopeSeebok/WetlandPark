package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.RegeoEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/19 11:23
 */
public interface RegeoView extends BaseView {

    void onRegeoStart();

    void onRegeoEnd();

    void onRegeoFailure(int code,String error);

    void onRegeoSuccess(RegeoEntity entity);
}
