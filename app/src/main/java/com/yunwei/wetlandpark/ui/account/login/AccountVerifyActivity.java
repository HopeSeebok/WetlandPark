package com.yunwei.wetlandpark.ui.account.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.library.utils.IStringUtils;

/**
 * @Package: com.yunwei.zaina.ui.activity.account
 * @Description:密码重置第一步
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 09:27
 * @version: V1.0
 */
public class AccountVerifyActivity extends BaseActivity {

    private EditText accountEdit, verifyCodeEdit;
    private TextView verifyCodeTv;
    private Button nextBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_account_verify);
        setToolbarTitle(R.string.pwd_set_t1);
        setToolbarRightText(R.string.next_str);

    }

    @Override
    public void findViewById() {
        super.findViewById();
        accountEdit = (EditText) findViewById(R.id.account_verify_account_edit);
        verifyCodeEdit = (EditText) findViewById(R.id.account_verify_code_et);

        verifyCodeTv = (TextView) findViewById(R.id.account_verify_code_tv);
        nextBtn = (Button) findViewById(R.id.account_verify_next_btn);

    }

    @Override
    public void setListener() {
        super.setListener();
        nextBtn.setOnClickListener(this);

        verifyCodeEdit.addTextChangedListener(new VeriyCodeEditTextChangeListener());
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        ISkipActivityUtil.startIntent(this, PasswordRestActivity.class);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.account_verify_next_btn:
                ISkipActivityUtil.startIntent(this, PasswordRestActivity.class);
                break;
        }
    }

    class VeriyCodeEditTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (IStringUtils.isEmpty(s.toString().trim())) {
                nextBtn.setBackgroundResource(R.drawable.btn_press);
                nextBtn.setClickable(false);
                nextBtn.setFocusable(false);
            } else {
                nextBtn.setBackgroundResource(R.drawable.common_btn_bule_selector);
                nextBtn.setClickable(true);
                nextBtn.setFocusable(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
