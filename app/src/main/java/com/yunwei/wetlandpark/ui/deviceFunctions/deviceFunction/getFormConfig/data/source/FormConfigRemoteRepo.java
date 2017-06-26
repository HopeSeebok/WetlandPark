package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.DeviceTypesEntity;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.TroubleType;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/7 20:25
 * @description:
 */

public class FormConfigRemoteRepo implements FormConfigDataSource {

    private FormConfigRemoteRepo() {
    }

    private static FormConfigRemoteRepo INSTANCE;

    public static FormConfigRemoteRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FormConfigRemoteRepo();
        }
        return INSTANCE;
    }


    @Override
    public void getDeviceTypes(final DeviceTypesCallBack callBack) {
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        String GET_DEVICE_TYPES_URL = BuildConfig.DOMAI + BuildConfig.GET_DEVICE_TYPES;
        LiteHttpManage.Http_Get_Sync(ZNAPPlication.getInstance(), TOKEN, GET_DEVICE_TYPES_URL, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {
            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
                    Gson gson = new Gson();
                    ResponseModel<List<DeviceTypesEntity.Item>> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<List<DeviceTypesEntity.Item>>>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        String dataString = gson.toJson(responseModel.getData());
                        callBack.onDeviceTypesLoaded(dataString);
                    } else {
                        callBack.onDeviceTypesNotAvailable();
                    }
                } else {
                    callBack.onDeviceTypesNotAvailable();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                callBack.onDeviceTypesNotAvailable();
            }

            @Override
            public void onEnd(Response response) {
            }
        });
    }

    @Override
    public void getTroubleTypes(TroubleTypesCallBack callBack) {
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        String GET_DEVICE_TYPES_URL = BuildConfig.DOMAI + BuildConfig.GET_TROUBLE_TYPES;
        LiteHttpManage.Http_Get_Sync(ZNAPPlication.getInstance(), TOKEN, GET_DEVICE_TYPES_URL, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {
            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
                    Gson gson = new Gson();
                    ResponseModel<List<TroubleType>> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<List<TroubleType>>>() {
                            }.getType());
                    if (responseModel.isSuccess() && responseModel.getData() != null) {
                        callBack.onTroubleTypesLoaded(responseModel.getData());
                    } else {
                        callBack.onTroubleTypesNotAvailable();
                    }
                } else {
                    callBack.onTroubleTypesNotAvailable();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                callBack.onTroubleTypesNotAvailable();
            }

            @Override
            public void onEnd(Response response) {
            }
        });
    }
}
