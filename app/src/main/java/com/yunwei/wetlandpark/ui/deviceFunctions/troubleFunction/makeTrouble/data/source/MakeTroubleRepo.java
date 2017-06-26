package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.source;

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
import com.yunwei.wetlandpark.greedao.TroubleTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.qiniu.QiNiuImageUploadManager;
import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/2 17:09
 * @description:
 */

public class MakeTroubleRepo implements MakeTroubleDataSource{
    private MakeTroubleRepo() {}
    private static MakeTroubleRepo INSTANCE;
    public static MakeTroubleRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MakeTroubleRepo();
        }
        return INSTANCE;
    }

    @Override
    public void upload(final UploadCallBack uploadCallBack) {
        final String MAKE_TROUBLE_URL = BuildConfig.DOMAI+BuildConfig.MAKE_TROUBLE;
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
                    TroubleInfo troubleInfo = uploadCallBack.getUploadEntity(path);
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(troubleInfo);
                    JsonBody body = new JsonBody(jsonStr);
                    LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance().getContext(), TOKEN,
                            MAKE_TROUBLE_URL, body, new HttpRequestCallBack() {
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
            });
        } else {
            TroubleInfo troubleInfo = uploadCallBack.getUploadEntity(null);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(troubleInfo);
            JsonBody body = new JsonBody(jsonStr);

            LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance().getContext(), TOKEN,
                    MAKE_TROUBLE_URL, body, new HttpRequestCallBack() {
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
    public void save(TroubleTable troubleTable, SaveCallBack saveCallBack) {
        if (troubleTable.getId()==null){
            /* ID为空,既是首次保存，插入数据*/
            try {
                long id = ZNAPPlication.getDaoSession().getTroubleTableDao().insert(troubleTable);
                saveCallBack.onSaveSuccess(id);
            } catch (Exception e) {
                e.printStackTrace();
                saveCallBack.onSaveFailed();
            }
        }else {
            try {
                ZNAPPlication.getDaoSession().getTroubleTableDao().update(troubleTable);
                saveCallBack.onUpdateSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                saveCallBack.onSaveFailed();
            }
        }
    }
}
