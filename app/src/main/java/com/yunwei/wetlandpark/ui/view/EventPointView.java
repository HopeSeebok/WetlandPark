package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.EventPoinitEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.view
 * @Description:
 * @date 2016/8/26 15:56
 */
public interface EventPointView extends BaseView {

    void reqEventPointStart();

    void reqEventPointEnd();

    void reqEventPointSuccess(List<EventPoinitEntity> entities);

    void reqEventPointfailure(int code,String error);
}
