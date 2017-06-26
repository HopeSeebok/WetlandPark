package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice;

import android.os.Bundle;

import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.source.MaintainDeviceRepo;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice
 * @Description:
 * @date 2016/11/17 14:50
 */

public class MaintainDeviceActivity extends FillBaseActvity{


    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        /*初始化表单、数据*/
        MaintainDeviceFragment fillBaseFragment = new MaintainDeviceFragment();
        fillBaseFragment.setArguments(bundle);
        /*初始化PRESENTER*/
        new MaintainDevicePresenter(MaintainDeviceRepo.getInstance(), fillBaseFragment);
        return fillBaseFragment;
    }

    @Override
    public String getHeadTitle() {
        return "维护设施";
    }


}
