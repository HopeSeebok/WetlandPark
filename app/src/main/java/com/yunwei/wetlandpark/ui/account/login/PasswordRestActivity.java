package com.yunwei.wetlandpark.ui.account.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;

/**
 * @Package: com.yunwei.zaina.ui.activity.account
 * @Description:密码重置第二步
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 10:28
 * @version: V1.0
 */
public class PasswordRestActivity extends BaseActivity {

    private EditText pwdEdit, pwdAffirmEdit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pwd_rest);
        setToolbarTitle(R.string.pwd_set_t2);
        setToolbarRightText(R.string.submit);
    }

    @Override
    public void findViewById() {
        super.findViewById();
        pwdEdit = (EditText) findViewById(R.id.pwd_rest_pwd_edit);
        pwdAffirmEdit = (EditText) findViewById(R.id.pwd_rest_pwd_affirm_edit);
    }

    @Override
    public void setListener() {
        super.setListener();
        findViewById(R.id.pwd_rest_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.pwd_rest_submit:
                break;
        }
    }
}
