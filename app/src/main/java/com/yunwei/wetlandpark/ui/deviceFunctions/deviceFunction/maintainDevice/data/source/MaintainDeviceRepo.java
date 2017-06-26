package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.source;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.greedao.MaintainDeviceTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.MaintainDeviceEntity;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice.data.source
 * @Description:
 * @date 2016/11/17 16:19
 */

public class MaintainDeviceRepo implements MaintainDeviceSource{

    private static MaintainDeviceRepo instance;

    private MaintainDeviceRepo(){}

    public static MaintainDeviceRepo getInstance(){
        if (instance == null){
            instance = new MaintainDeviceRepo();
        }
        return instance;
    }

    @Override
    public void upload(MaintainDeviceEntity entity, final UploadCallBack callBack) {
        String MAINTAIN_DEVICE_URL = BuildConfig.DOMAI + BuildConfig.MAINTAIN_DEVICE;
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(entity);
        JsonBody body = new JsonBody(jsonStr);
        LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance(), TOKEN, MAINTAIN_DEVICE_URL, body,
             new HttpRequestCallBack() {
                    @Override
                    public void onStart(AbstractRequest request) {
                    }

                    @Override
                    public void onSuccess(Object o, Response response) {
                        ResponseModel result = new Gson().fromJson(o.toString(), ResponseModel.class);
                        if (result.isSuccess()) {
                            callBack.onUploadSuccess();
                        } else {
                            callBack.onUploadFailed();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, Response response) {
                        callBack.onUploadFailed();
                    }

                    @Override
                    public void onEnd(Response response) {
                    }
             }
        );
    }

    @Override
    public void save(MaintainDeviceTable table, SaveCallBack callBack) {
        try {
            if (table.getId() == null || table.getId() <= 0) {
                //首次添加
                long id = ZNAPPlication.getDaoSession().getMaintainDeviceTableDao().insert(table);
                callBack.onSaveSuccess(id);
            } else {
                //更新
                ZNAPPlication.getDaoSession().getMaintainDeviceTableDao().update(table);
                callBack.onUpdateSuccess();
            }
        }catch (Exception e){
            e.printStackTrace();
            callBack.onSaveFailed();
        }
    }

}
