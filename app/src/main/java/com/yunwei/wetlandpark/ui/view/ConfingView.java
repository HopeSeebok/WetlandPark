package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.ConfigEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.view
 * @Description:
 * @date 2016/8/24 16:24
 */
public interface ConfingView extends BaseView {

    void reqConfigStart();

    void reqConfigEnd();

    void reqConfigSuccess(ConfigEntity entity);

    void reqConfigFailure(int code,String errorMsg);
}
