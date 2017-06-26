package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.source;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.TroubleType;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/7 19:49
 * @description:
 */

public interface FormConfigDataSource {
    interface DeviceTypesCallBack {
        void onDeviceTypesLoaded(String string);
        void onDeviceTypesNotAvailable();
    }

    void getDeviceTypes(DeviceTypesCallBack callBack);

    interface TroubleTypesCallBack{
        void onTroubleTypesLoaded(List<TroubleType> data);

        void onTroubleTypesNotAvailable();
    }
    void getTroubleTypes(TroubleTypesCallBack callBack);

}
