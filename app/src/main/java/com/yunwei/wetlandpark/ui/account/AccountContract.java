package com.yunwei.wetlandpark.ui.account;

import com.yunwei.wetlandpark.ui.account.password.data.ChangePwdEntity;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account
 * @Description:账户协议
 * @date 2016/11/15 10:55
 */

public interface AccountContract {

    //////////////////登录
    interface LoginView extends BaseView{
        String[] getUserNameAndPassword();
        void showLoginDialog();
        void dismissLoginDialog();
        void showLoginSuccessMsgAndFinishActivity();
        void showLoginFailedMsg(String msg);
    }

    interface LoginPresenter{
        void login();
    }

    ///////////////////修改个人资料
    interface ModifyHeadPortraitView extends BaseView{
        String getNewHeadPortraitImagePath();
        void showUploadSuccess();
        void showUploadDialog();
        void dismissUploadDialog();
        void showUploadFailedMsg(String msg);
        void setImageView(String path);
    }

    interface ModifyUserPasswordView extends BaseView{
        ChangePwdEntity getNewAndOldPassword();
        void showUploadDialog();
        void dismissUploadDialog();
        void showUploadFailedMsg(String msg);
        void showUpdateSuccessMsgAndFinishAcitvity();
    }

    interface ModifyHeadPortraitPresenter{
        void modifyHeadPortrait();
    }

    interface ModifyPasswordPresenter{
        void modifyPassword();
    }

}
