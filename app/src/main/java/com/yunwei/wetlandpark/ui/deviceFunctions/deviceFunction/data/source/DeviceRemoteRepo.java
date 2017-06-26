package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.SignInfo;
import com.yunwei.wetlandpark.utils.ISpfUtil;

/**
 * @author CBOK
 * @date 2016/12/16 11:57
 * @description:
 */

public class DeviceRemoteRepo implements DeviceDataSource{

    private DeviceRemoteRepo() {}
    private static DeviceRemoteRepo INSTANCE;
    public static DeviceRemoteRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeviceRemoteRepo();
        }
        return INSTANCE;
    }

    @Override
    public void signIn(UploadCallBack<SignInfo> uploadCallBack) {
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        String SIGN_IN_URL = BuildConfig.DOMAI + BuildConfig.SIGN_IN_URL;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(uploadCallBack.getUploadData());
        JsonBody body = new JsonBody(jsonStr);
        LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance(), TOKEN, SIGN_IN_URL, body, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
                    Gson gson = new Gson();
                    ResponseModel responseModel = gson.fromJson(o.toString(),
                            new TypeToken<ResponseModel>() {
                            }.getType());
                    if (responseModel.isSuccess()) {
                        uploadCallBack.onUploadSuccess();
                    }else {
                        uploadCallBack.onUploadFail();
                    }
                } else {
                    uploadCallBack.onUploadFail();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                uploadCallBack.onUploadFail();
            }

            @Override
            public void onEnd(Response response) {

            }
        });
    }
}
