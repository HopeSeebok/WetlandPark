package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;
import android.content.Context;

import com.yunwei.wetlandpark.entity.ConfigEntity;
import com.yunwei.wetlandpark.ui.biz.interfac.ConfigLinstener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz
 * @Description:
 * @date 2016/8/24 16:27
 */
public interface IConfigSys {

    void reqConfig(Activity activity, ConfigLinstener linstener);

    //获取配置实体
    ConfigEntity getConfigData(Context con);


}
