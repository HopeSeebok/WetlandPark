package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.UserSignEntity;
import com.yunwei.wetlandpark.ui.biz.IUserSignUpload;
import com.yunwei.wetlandpark.ui.biz.interfac.UserSignUploadListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import org.json.JSONObject;


/**
 * @package com.yunwei.camera.ui.biz.impl
 * @description 签到签退
 * @author yangdu
 * @date 16/10/14
 * @time 下午4:50
 * @version V1.0
 **/
public class UserSignUploadBiz implements IUserSignUpload,HttpRequestCallBack {

    private final String TAG = getClass().getSimpleName();

    private UserSignUploadListener listener;
    private Activity activity;
    private int signType;

    @Override
    public void uploadSignInfo(Activity activity, UserSignEntity entity, UserSignUploadListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.signType=!entity.isIsSign()?Constants.UserSignType.SIGN_IN: Constants.UserSignType.SIGN_OUT;
        JsonBody body = new JsonBody(new Gson().toJson(entity));
        LiteHttpManage.Http_Post_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), IConfigValues.USER_SIGN_URL, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.onCheckStart(signType);
    }

    @Override
    public void onSuccess(Object o, Response response) {
        try {
            JSONObject jsonObject=o!=null?new JSONObject(o.toString()):null;//code,status
            int code = jsonObject!=null?jsonObject.getInt("code"):400;
            if (response.getHttpStatus().getCode() == 200&&code==200) {
                int status = jsonObject.getInt("status");
                switch (status) {
                    case Constants.UserWorkStatus.SIGN_IN_SUCCESS_STATE:
                    case Constants.UserWorkStatus.SIGN_OUT_SUCCESS_STATE:
                        listener.onCheckSuccess(signType);
                        break;
                    case Constants.UserWorkStatus.IS_SIGNED_STATE:
                        listener.onCheckFailure(status, jsonObject.getString("msg"));
                        break;
                }
            } else {
                listener.onCheckFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
            }
        }catch (Exception e){
            ILog.e(TAG,"error=="+e.getMessage());
            listener.onCheckFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"error=="+e.getMessage());
        listener.onCheckFailure(401, IUtils.getStrToRes(activity, R.string.sign_failed));
    }

    @Override
    public void onEnd(Response response) {
        listener.onCheckEnd(signType);
    }

}
