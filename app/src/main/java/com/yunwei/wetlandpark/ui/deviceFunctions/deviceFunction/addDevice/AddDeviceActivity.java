package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice;

import android.os.Bundle;

import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;

public class AddDeviceActivity extends FillBaseActvity {


    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        /**
         * 初始化 表单Fragment 并传入Argument
         * Activity唯一管理Fragment和数据
         */
        FillBaseFragment fillBaseFragment = new AddDeviceFragment();
        fillBaseFragment.setArguments(bundle);
        return fillBaseFragment;
    }

    @Override
    public String getHeadTitle() {
        return "新增设施";
    }
}
