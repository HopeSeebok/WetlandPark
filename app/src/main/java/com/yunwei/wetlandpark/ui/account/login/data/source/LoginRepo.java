package com.yunwei.wetlandpark.ui.account.login.data.source;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.StringBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.utils.INetWorkUtil;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.data.source
 * @Description:登录
 * @date 2016/11/15 11:31
 */

public class LoginRepo implements LoginDataSource, HttpRequestCallBack {

    private LoginCallBack loginCallBack;
    private String username, password;

    @Override
    public void login(String[] loginInfo, LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
        username = loginInfo[0];
        password = loginInfo[1];
        StringBody body = new StringBody("grant_type=password&client_id=wt&client_secret=123456&username=" + username + "&password=" + password);

        if (!INetWorkUtil.isNetworkAvailable(ZNAPPlication.getInstance())) {
            loginCallBack.onLoginFailed(IUtils.getStrToRes(ZNAPPlication.getInstance(), R.string.netword_invalid));
            return;
        }
        LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance(), BuildConfig.DOMAI + BuildConfig.LOGIN_URL, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        loginCallBack.onLoginStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        if (response.getHttpStatus().getCode() == 200){
            loginCallBack.onLoginSuccess(o);
        }else{
            loginCallBack.onLoginFailed(IUtils.getStrToRes(ZNAPPlication.getInstance(), R.string.login_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        loginCallBack.onLoginFailed(IUtils.getStrToRes(ZNAPPlication.getInstance(), R.string.login_error_desc));
    }

    @Override
    public void onEnd(Response response) {
        loginCallBack.onLoginEnd();
    }
}
