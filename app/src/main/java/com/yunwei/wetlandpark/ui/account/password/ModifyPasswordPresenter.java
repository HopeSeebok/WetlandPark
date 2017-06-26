package com.yunwei.wetlandpark.ui.account.password;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.account.AccountContract.ModifyUserPasswordView;
import com.yunwei.wetlandpark.ui.account.password.data.ChangePwdEntity;
import com.yunwei.wetlandpark.ui.account.password.data.source.ModifyPasswordDataSource;
import com.yunwei.wetlandpark.ui.account.password.data.source.ModifyPasswordRepo;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ISpfUtil;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.password
 * @Description: 修改密码
 * @date 2016/11/16 09:30
 */

public class ModifyPasswordPresenter implements AccountContract.ModifyPasswordPresenter, ModifyPasswordDataSource.ModifyPasswordCallBack{

    private ModifyUserPasswordView modifyUserPasswordView;
    private ModifyPasswordRepo modifyPasswordRepo;

    public ModifyPasswordPresenter(ModifyUserPasswordView modifyUserPasswordView){
        this.modifyUserPasswordView = modifyUserPasswordView;
        modifyPasswordRepo = new ModifyPasswordRepo();
    }

    @Override
    public void modifyPassword() {
        ChangePwdEntity entity = modifyUserPasswordView.getNewAndOldPassword();
        if (!entity.getOldPassword().trim().equals(
                ISpfUtil.getValue(
                        ZNAPPlication.getInstance(),
                        Constants.USER_PWD_KEY, "").toString())) {
            modifyUserPasswordView.showUploadFailedMsg("原始密码不正确!");
        }else {
            modifyPasswordRepo.modifyPassword(entity, this);
        }
    }

    @Override
    public void onModifyStart() {
        modifyUserPasswordView.showUploadDialog();
    }

    @Override
    public void onModifySuccess() {
        ISpfUtil.setValue(
                ZNAPPlication.getInstance(),
                Constants.USER_PWD_KEY,
                modifyUserPasswordView.getNewAndOldPassword().getNewPassword());
        modifyUserPasswordView.showUpdateSuccessMsgAndFinishAcitvity();
    }

    @Override
    public void onModifyFailed(String msg) {
        modifyUserPasswordView.showUploadFailedMsg(msg);
    }

    @Override
    public void onModifyEnd() {
        modifyUserPasswordView.dismissUploadDialog();
    }
}
