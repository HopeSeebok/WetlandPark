package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice;

import android.os.Bundle;
import android.view.KeyEvent;

import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.source.CheckPlanRepo;

/**
 * @author CBOK
 * @date 2016/11/8 11:26
 * @description:
 */

public class CheckPlanActivity extends FillBaseActvity{

    private CheckPlanFragment fillBaseFragment;

    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        /*初始化表单、数据*/
        fillBaseFragment = new CheckPlanFragment();
        fillBaseFragment.setArguments(bundle);
        /*初始化PRESENTER*/
        new CheckPlanPresenter(CheckPlanRepo.getInstance(), fillBaseFragment);
        return fillBaseFragment;
    }

    @Override
    public String getHeadTitle() {
        return "巡查设施";
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //是新增设施时才询问
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mFillFragment instanceof CheckPlanFragment){
                ((CheckPlanFragment)mFillFragment).askIfExit();
            } else{
                this.finish();
            }
        }
        return true;
    }

    @Override
    public void onClickToolbarLeftButton() {
        if (mFillFragment instanceof CheckPlanFragment){
            ((CheckPlanFragment)mFillFragment).askIfExit();
        } else{
            this.finish();
        }
    }
}
