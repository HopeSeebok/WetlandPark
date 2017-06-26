package com.yunwei.wetlandpark.ui.account.password;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.account.password.data.ChangePwdEntity;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.library.dialog.DialogFactory;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.mine
 * @Description:密码修改界面
 * @date 2016/9/7 16:23
 */
public class PasswordEditActivity extends BaseActivity implements AccountContract.ModifyUserPasswordView {

    private EditText oldPwdEdit, newPwdEdit;

    private ModifyPasswordPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pwd_edit);
        setToolbarTitle("密码修改");
        setToolbarRightText("提交");
        presenter = new ModifyPasswordPresenter(this);
    }

    @Override
    public void findViewById() {
        super.findViewById();
        oldPwdEdit = (EditText) findViewById(R.id.pwd_edit_old_edit);
        newPwdEdit = (EditText) findViewById(R.id.pwd_edit_new_edit);
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (TextUtils.isEmpty(oldPwdEdit.getText().toString().trim())) {
            showToast("原始密码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(newPwdEdit.getText().toString().trim())) {
            showToast("新密码不能为空!");
            return;
        }
        presenter.modifyPassword();
    }

    @Override
    public ChangePwdEntity getNewAndOldPassword() {
        ChangePwdEntity entity = new ChangePwdEntity();
        entity.setOldPassword(oldPwdEdit.getText().toString().trim());
        entity.setNewPassword(newPwdEdit.getText().toString().trim());
        return entity;
    }

    @Override
    public void showUploadDialog() {
        dialog = DialogFactory.createLoadingDialog(this, "修改...");
    }

    @Override
    public void dismissUploadDialog() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void showUploadFailedMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void showUpdateSuccessMsgAndFinishAcitvity() {
        showToast("密码修改成功!");
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
