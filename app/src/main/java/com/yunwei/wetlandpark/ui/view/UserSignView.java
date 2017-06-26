package com.yunwei.wetlandpark.ui.view;


import com.yunwei.wetlandpark.entity.UserSignEntity;

/**
 * @package com.yunwei.camera.ui.view
 * @description 签到签退
 * @author yangdu
 * @date 16/10/13
 * @time 下午5:23
 * @version V1.0
 **/
public interface UserSignView extends BaseView {
    void onCheckStart(int signType);//签到、签退
    void onCheckEnd(int signType);
    void onCheckSuccess(int signType);
    void onCheckFailure(int code, String message);
    UserSignEntity getGroupUsers();//获取签到组员信息

}
