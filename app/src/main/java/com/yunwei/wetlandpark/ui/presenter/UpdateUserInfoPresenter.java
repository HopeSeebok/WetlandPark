package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.ui.biz.IUpdateUserInfo;
import com.yunwei.wetlandpark.ui.biz.impl.UpdateUserInfoBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.UpdateUserInfoListener;
import com.yunwei.wetlandpark.ui.view.UpdateUserInfoView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/13 20:49
 */
public class UpdateUserInfoPresenter implements UpdateUserInfoListener {

    private UpdateUserInfoView updateUserInfoView;
    private IUpdateUserInfo userInfo;

    public UpdateUserInfoPresenter(UpdateUserInfoView view) {
        this.updateUserInfoView = view;
        this.userInfo = new UpdateUserInfoBiz();
    }

    public void updateUserInfo(Object o) {
        userInfo.updateUserInfo(updateUserInfoView.getActivity(), o, this);
    }

    public void updateUserPwd(Object o){
        userInfo.updateUserPsd(updateUserInfoView.getActivity(), o, this);
    }

    @Override
    public void onUpdateSuccess() {
        updateUserInfoView.onUpdateSuccess();
    }

    @Override
    public void onUpdateStart() {
        updateUserInfoView.onUpdateStart();
    }

    @Override
    public void onUpdateEnd() {
        updateUserInfoView.onUpdateEnd();
    }

    @Override
    public void onUpdateFailure(int code, String msg) {
        updateUserInfoView.onUpdateFailure(code, msg);
    }
}
