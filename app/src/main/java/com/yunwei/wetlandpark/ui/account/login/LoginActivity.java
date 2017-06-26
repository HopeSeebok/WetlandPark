package com.yunwei.wetlandpark.ui.account.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.MainActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

/**
 * @Package: com.yunwei.zaina.ui.activity.account
 * @Description:登录界面
 * @author: Aaron
 * @date: 2016-06-07
 * @Time: 15:27
 * @version: V1.0
 */
public class LoginActivity extends BaseActivity implements AccountContract.LoginView {
    private final String TAG = getClass().getSimpleName();

    private EditText accountEdit, pwdEdit;
    private ImageView headIv;
    private ImageButton accountClose, pwdClose;
    private Button loginBtn;

    private AccountContract.LoginPresenter loginPresenter;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        loginPresenter = new LoginPresenter(this);
        initUI();
    }

    private void initUI() {
        String userName = ISpfUtil.getValue(this, Constants.USER_NAME_KEY, "").toString();
        String userPwd = ISpfUtil.getValue(this, Constants.USER_PWD_KEY, "").toString();
        if (!IStringUtils.isEmpty(userName))
            accountEdit.setText(userName);
        if (!IStringUtils.isEmpty(userPwd))
            pwdEdit.setText(userPwd);
    }

    @Override
    public void findViewById() {
        accountEdit = (EditText) findViewById(R.id.account_edit);
        pwdEdit = (EditText) findViewById(R.id.pwd_edit);
        accountClose = (ImageButton) findViewById(R.id.account_close_ib);
        pwdClose = (ImageButton) findViewById(R.id.pwd_close_ib);
        headIv = (ImageView) findViewById(R.id.login_head_iv);
        loginBtn = (Button) findViewById(R.id.login_btn);
    }


    @Override
    public void setListener() {
        accountEdit.addTextChangedListener(new AccountEditChangedListener());
        pwdEdit.addTextChangedListener(new PwdEditChangedListener());

        accountClose.setOnClickListener(this);
        pwdClose.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        findViewById(R.id.login_forget_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_close_ib:
                accountEdit.getText().clear();
                break;
            case R.id.pwd_close_ib:
                pwdEdit.getText().clear();
                break;
            case R.id.login_btn:
                if (TextUtils.isEmpty(accountEdit.getText().toString())) {
                    showToast("账号不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(pwdEdit.getText().toString())) {
                    showToast("密码不能为空!");
                    return;
                }

                loginPresenter.login();
                break;
            case R.id.login_forget_tv:
                ISkipActivityUtil.startIntent(this, AccountVerifyActivity.class);
                break;
        }
    }

    @Override
    public String[] getUserNameAndPassword() {
        String[] info = {accountEdit.getText().toString(),
                            pwdEdit.getText().toString().trim()};
        return info;
    }

    @Override
    public void showLoginDialog() {
        dialog = DialogFactory.createLoadingDialog(this, getResources().getString(R.string.dialog_login_str));
    }

    @Override
    public void dismissLoginDialog() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void showLoginSuccessMsgAndFinishActivity() {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        swipeBackLayout.setSwipeFinished(true);
        finish();
    }

    @Override
    public void showLoginFailedMsg(String msg) {
        showToast(msg);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 账号输入框监听
     */
    class AccountEditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (IStringUtils.isEmpty(s.toString().trim())) {
                accountClose.setVisibility(View.GONE);
            } else {
                accountClose.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 密码输入框监听
     */
    class PwdEditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (IStringUtils.isEmpty(s.toString().trim())) {
                pwdClose.setVisibility(View.GONE);
            } else {
                pwdClose.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
