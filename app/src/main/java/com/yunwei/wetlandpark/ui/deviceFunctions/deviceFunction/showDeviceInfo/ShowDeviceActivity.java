package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.showDeviceInfo;

import android.os.Bundle;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.IUtils;

public class ShowDeviceActivity extends BaseActivity {

    private ShowDeviceFragment mContentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_framelayout);
        setToolbarTitle("签到点信息");

        mContentFragment = new ShowDeviceFragment();
        mContentFragment.setArguments(getIntent().getExtras());
        IUtils.addFragmentToActivity(getSupportFragmentManager(), mContentFragment,R.id.frameLayoutActivity_frameLayout);
    }
}
