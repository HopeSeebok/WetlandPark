package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.data.source;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author CBOK
 * @date 2016/12/7 1:57
 * @description:
 */

public class RFIDSearchRepo implements RFIDSearchDataSource{

    private RFIDSearchRepo() {
    }
    private static RFIDSearchRepo INSTANCE;
    public static RFIDSearchRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RFIDSearchRepo();
        }
        return INSTANCE;
    }

    @Override
    public void searchByRFID(RFIDSearchCallBack callBack) {
        String url = BuildConfig.DOMAI + BuildConfig.DEVICE_INFO_BY_RFID + callBack.getRFID();
        LiteHttpManage.Http_Get_Sync(ZNAPPlication.getInstance().getApplicationContext(), ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString(), url, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
                    Gson gson = new Gson();
                    ResponseModel<DeviceInfo> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<DeviceInfo>>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        callBack.onSearchDataLoaded(responseModel.getData());
                    } else if ("设施不存在".equals(responseModel.getMessage())){
                        callBack.onRFIDNotLinked();
                    }else {
                        callBack.onSearchDataNotAvailable();
                    }
                } else {
                    callBack.onSearchDataNotAvailable();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                callBack.onSearchDataNotAvailable();
            }

            @Override
            public void onEnd(Response response) {

            }
        });
    }

}
