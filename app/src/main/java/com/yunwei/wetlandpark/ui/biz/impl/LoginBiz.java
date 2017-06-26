package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.StringBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.UserInfoEntity;
import com.yunwei.wetlandpark.ui.biz.ILogin;
import com.yunwei.wetlandpark.ui.biz.interfac.LoginListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.utils.INetWorkUtil;

/**
 * @Package: com.yunwei.zaina.ui.biz.account
 * @Description:登录业务处理
 * @author: hezhiWu
 * @date: 2016-06-08
 * @Time: 15:33
 * @version: V1.0
 */
public class LoginBiz implements ILogin, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();
    private Activity activity;
    private LoginListener listener;

    private String account, pwd;

    @Override
    public void login(Activity activity, String account, String pwd, LoginListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.account = account;
        this.pwd = pwd;

        StringBody body = new StringBody("grant_type=password&client_id=wt&client_secret=123456&username=" + account + "&password=" + pwd);

        if (!INetWorkUtil.isNetworkAvailable(activity)) {
            listener.loginFailed(0, IUtils.getStrToRes(activity, R.string.netword_invalid));
            return;
        }
        LiteHttpManage.Http_Post_Sync(activity, BuildConfig.DOMAI + BuildConfig.LOGIN_URL, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.onLoginStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        if (response.getHttpStatus().getCode() == 200) {
            ILog.d(TAG, "result==" + o.toString());
            ISpfUtil.setValue(activity, Constants.LOGIN_INFO_KEY, o.toString());
            try {
                Gson gson = new Gson();
                UserInfoEntity entity = gson.fromJson(o.toString(), UserInfoEntity.class);

                //数据本地化
                ISpfUtil.setValue(activity, Constants.USER_NAME_KEY, account);
                ISpfUtil.setValue(activity, Constants.USER_PWD_KEY, pwd);
                ISpfUtil.setValue(activity, Constants.USER_ICON_KEY, entity.getIcon());
                ISpfUtil.setValue(activity, Constants.ACCESS_TOKEN_KEY, entity.getAccess_token());
                ISpfUtil.setValue(activity, Constants.ACCOUNT_ID_KEY, entity.getId());

                listener.loginSuccess(entity);
            } catch (Exception e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.loginFailed(-1, activity.getResources().getString(R.string.login_failure));
                }
            }
        } else {
            if (listener != null) {
                listener.loginFailed(-1, activity.getResources().getString(R.string.login_failure));
            }
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        if (response != null && response.getHttpStatus() != null && response.getHttpStatus().getCode() == 400 && listener != null) {
            listener.loginFailed(-1, activity.getResources().getString(R.string.login_error_desc));
        }
    }

    @Override
    public void onEnd(Response response) {
        listener.onLoginEnd();
    }
}
