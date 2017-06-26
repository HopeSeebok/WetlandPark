package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author CBOK
 * @date 2016/11/9 15:17
 * @description:
 */

public class SearchDeviceRemoteRepo implements SearchDeviceDataSource {
    private final String TAG = getClass().getSimpleName();

    private SearchDeviceRemoteRepo() {
    }

    private static SearchDeviceRemoteRepo INSTANCE;

    public static SearchDeviceRemoteRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SearchDeviceRemoteRepo();
        }
        return INSTANCE;
    }

    @Override
    public void searchDeviceByGuID(Activity activity, final GuIDSearchDeviceCallBack callBack) {
        final String DEVICE_INFO_BY_GUID_URL = BuildConfig.DOMAI + BuildConfig.DEVICE_INFO_BY_GUID + callBack.getGuID();
        final String TOKEN = ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString();
        LiteHttpManage.Http_Get_Sync(activity, TOKEN, DEVICE_INFO_BY_GUID_URL, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
                    Gson gson = new Gson();
                    ResponseModel<DeviceInfo> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<DeviceInfo>>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        callBack.onSearchDataLoaded(responseModel.getData());
                    } else {
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

    @Override
    public void searchTroubleByID(Activity activity, TroubleSearchCallBack callBack) {
        String TROUBLE_INFO_BY_ID = BuildConfig.DOMAI + BuildConfig.TROUBLE_INFO_BY_ID + callBack.getID();
        final String TOKEN = ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString();
        LiteHttpManage.Http_Get_Sync(activity, TOKEN, TROUBLE_INFO_BY_ID, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
                    Gson gson = new Gson();
                    ResponseModel<TroubleInfo> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<TroubleInfo>>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        callBack.onSearchDataLoaded(responseModel.getData());
                    } else {
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

    @Override
    public void searchByRFID(Activity activity, final RFIDSearchDeviceCallBack callBack) {
        String url = BuildConfig.DOMAI + BuildConfig.DEVICE_INFO_BY_RFID + callBack.getRFID();
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), url, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
                    ILog.d(TAG, "result==" + o.toString());
                    Gson gson = new Gson();
                    ResponseModel<DeviceInfo> responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel<DeviceInfo>>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        callBack.onSearchDataLoaded(responseModel.getData());
                    } else if ("设施不存在".equals(responseModel.getMessage())){
                        callBack.onRFIDNotRelated(callBack.getRFID());
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
