package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.TroubleType;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.source.FormConfigDataSource;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/7 16:47
 * @description:
 */

public class FormConfigPresenter implements FormConfigContract.Presenter, FormConfigDataSource.DeviceTypesCallBack,FormConfigDataSource.TroubleTypesCallBack {

    private FormConfigDataSource mFormConfigRepo;
    public static final String DEVICE_TYPES_KEY = "DEVICE_TYPES_KEY";
    public static final String TROUBLE_TYPES_KEY = "TROUBLE_TYPES_KEY";

    public FormConfigPresenter(FormConfigDataSource formConfigRepo) {
        this.mFormConfigRepo = formConfigRepo;
    }

    @Override
    public void saveFormConfig() {
        mFormConfigRepo.getDeviceTypes(this);
        mFormConfigRepo.getTroubleTypes(this);
    }

    @Override
    public void onDeviceTypesLoaded(String string) {
        try {
            ISpfUtil.setValue(DEVICE_TYPES_KEY,string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeviceTypesNotAvailable() {
    }

    @Override
    public void onTroubleTypesLoaded(List<TroubleType> data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TroubleType troubleType : data) {
            stringBuilder.append(troubleType.getName() + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        try {
            ISpfUtil.setValue(TROUBLE_TYPES_KEY,stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTroubleTypesNotAvailable() {

    }
}
