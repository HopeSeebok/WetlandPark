package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.EventPoinitEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.interfac
 * @Description:
 * @date 2016/8/26 15:58
 */
public interface EventPointListener {
    void reqEventPointStart();

    void reqEventPointEnd();

    void reqEventPointSuccess(List<EventPoinitEntity> entities);

    void reqEventPointfailure(int code,String error);
}
