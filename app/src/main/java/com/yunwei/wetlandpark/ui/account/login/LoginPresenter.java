package com.yunwei.wetlandpark.ui.account.login;

import com.google.gson.Gson;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.UserInfoEntity;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.account.AccountContract.LoginView;
import com.yunwei.wetlandpark.ui.account.login.data.source.LoginDataSource;
import com.yunwei.wetlandpark.ui.account.login.data.source.LoginRepo;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;

/**
 * @Package: com.yunwei.zaina.ui.presenter
 * @Description: 登录
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 15:47
 * @version: V1.0
 */
public class LoginPresenter implements AccountContract.LoginPresenter, LoginDataSource.LoginCallBack{

    private LoginDataSource loginDataSource;
    private LoginView loginView;
    private String[] info;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
        this.loginDataSource = new LoginRepo();
    }

    @Override
    public void login() {
        info = loginView.getUserNameAndPassword();
        loginDataSource.login(info, this);
    }

    @Override
    public void onLoginStart() {
        loginView.showLoginDialog();
    }

    @Override
    public void onLoginSuccess(Object o) {
        ILog.d("LoginPresenter", o.toString());
        ISpfUtil.setValue(Constants.LOGIN_INFO_KEY, o.toString());
        try {
            Gson gson = new Gson();
            UserInfoEntity entity = gson.fromJson(o.toString(), UserInfoEntity.class);

            //数据本地化
            ISpfUtil.setValue(Constants.USER_NAME_KEY, info[0]);
            ISpfUtil.setValue(Constants.USER_PWD_KEY, info[1]);
            ISpfUtil.setValue(Constants.USER_ICON_KEY, entity.getIcon());
            ISpfUtil.setValue(Constants.ACCESS_TOKEN_KEY, entity.getAccess_token());
            ISpfUtil.setValue(Constants.ACCOUNT_ID_KEY, entity.getId());
            loginView.showLoginSuccessMsgAndFinishActivity();
        } catch (Exception e) {
            e.printStackTrace();
            loginView.showLoginFailedMsg(IUtils.getStrToRes(ZNAPPlication.getInstance(), R.string.login_failure));
        }

    }

    @Override
    public void onLoginFailed(String msg) {
        loginView.showLoginFailedMsg(msg);
    }

    @Override
    public void onLoginEnd() {
        loginView.dismissLoginDialog();
    }

}
