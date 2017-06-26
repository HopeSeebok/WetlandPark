package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.TroubleShooter;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.qiniu.QiNiuImageUploadManager;
import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/6 17:36
 * @description:
 */

public class ShootTroubleRepo implements ShootTroubleDataSource{

    private ShootTroubleRepo() {}
    private static ShootTroubleRepo INSTANCE;
    public static ShootTroubleRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShootTroubleRepo();
        }
        return INSTANCE;
    }


    @Override
    public void upload(final UploadCallBack uploadCallBack) {
        final String SHOOT_TROUBLE_URL = BuildConfig.DOMAI+BuildConfig.SHOOT_TROUBLE;
        final String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        final String QINIU_TOKEN = ISpfUtil.getValue(Constants.QINIU_TOKEN_KEY, "").toString();
        List<String> list = uploadCallBack.getLocalImageList();
        if (list != null && list.size() != 0) {
            QiNiuImageUploadManager.uploadImage(QINIU_TOKEN, list, new UploadCallBackListener() {
                @Override
                public void onUploadStart() {
                }
                @Override
                public void onUploadEnd() {}
                @Override
                public void onProgess(double percent) {}
                @Override
                public void onUploadFailure() {
                    uploadCallBack.onUploadFailed();
                }
                @Override
                public void onUploadComplete(final List<Image> path) {
                    TroubleShooter troubleShooter = uploadCallBack.getUploadEntity(path);
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(troubleShooter);
                    JsonBody body = new JsonBody(jsonStr);
                    LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance().getContext(), TOKEN,
                            SHOOT_TROUBLE_URL, body, new HttpRequestCallBack() {
                                @Override
                                public void onStart(AbstractRequest request) {
                                }
                                @Override
                                public void onSuccess(Object o, Response response) {
                                    if (response.getHttpStatus().getCode() == 200) {

                                        uploadCallBack.onUploadSuccess();
                                    } else {
                                        uploadCallBack.onUploadFailed();
                                    }
                                }
                                @Override
                                public void onFailure(HttpException e, Response response) {
                                    uploadCallBack.onUploadFailed();
                                }
                                @Override
                                public void onEnd(Response response) {
                                }
                            });
                }
            });
        } else {
            TroubleShooter troubleShooter = uploadCallBack.getUploadEntity(null);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(troubleShooter);
            JsonBody body = new JsonBody(jsonStr);

            LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance().getContext(), TOKEN,
                    SHOOT_TROUBLE_URL, body, new HttpRequestCallBack() {
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
                                    uploadCallBack.onUploadSuccess();
                                }else {
                                    uploadCallBack.onUploadFailed();
                                }
                            } else {
                                uploadCallBack.onUploadFailed();
                            }
                        }
                        @Override
                        public void onFailure(HttpException e, Response response) {
                            uploadCallBack.onUploadFailed();
                        }
                        @Override
                        public void onEnd(Response response) {
                        }
                    });
        }
    }

    @Override
    public void save(TroubleShooterTable troubleShooterTable, SaveCallBack saveCallBack) {
        if (troubleShooterTable.getId()==null){
            /* ID为空,既是首次保存，插入数据*/
            try {
                long id = ZNAPPlication.getDaoSession().getTroubleShooterTableDao().insert(troubleShooterTable);
                saveCallBack.onSaveSuccess(id);
            } catch (Exception e) {
                e.printStackTrace();
                saveCallBack.onSaveFailed();
            }
        }else {
            try {
                ZNAPPlication.getDaoSession().getTroubleShooterTableDao().update(troubleShooterTable);
                saveCallBack.onUpdateSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                saveCallBack.onSaveFailed();
            }
        }
    }
}
