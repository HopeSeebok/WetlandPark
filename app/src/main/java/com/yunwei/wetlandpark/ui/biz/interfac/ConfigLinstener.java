package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.ConfigEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.interfac
 * @Description:
 * @date 2016/8/24 16:27
 */
public interface ConfigLinstener {

    void reqConfigStart();

    void reqConfigEnd();

    void reqConfigSuccess(ConfigEntity entity);

    void reqConfigFailure(int code,String errorMsg);
}
