package com.yunwei.wetlandpark.ui.biz;


import android.app.Activity;

import com.yunwei.wetlandpark.entity.UserSignEntity;
import com.yunwei.wetlandpark.ui.biz.interfac.UserSignUploadListener;

/**
 * @package com.yunwei.camera.ui.biz
 * @description 签到签退
 * @author yangdu
 * @date 16/10/14
 * @time 下午4:30
 * @version V1.0
 **/
public interface IUserSignUpload {

    void uploadSignInfo(Activity activity, UserSignEntity entity, UserSignUploadListener listener);

}
