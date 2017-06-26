package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.showTroubleInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.IUtils;

public class ShowTroubleActivity extends BaseActivity {

    private ShowTroubleFragment mContentFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_framelayout);
        setToolbarTitle("隐患点信息");

        mContentFragment = new ShowTroubleFragment();
        mContentFragment.setArguments(getIntent().getExtras());
        IUtils.addFragmentToActivity(getSupportFragmentManager(), mContentFragment,R.id.frameLayoutActivity_frameLayout);
    }


}
