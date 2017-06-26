package com.yunwei.wetlandpark.ui.biz.interfac;


/**
 * @package com.yunwei.camera.ui.biz
 * @description 签到签退
 * @author yangdu
 * @date 16/10/14
 * @time 下午4:21
 * @version V1.0
 **/
public interface UserSignUploadListener {
    void onCheckStart(int signType);//
    void onCheckEnd(int signType);
    void onCheckSuccess(int signType);
    void onCheckFailure(int code, String message);
}
