package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.source;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.greedao.CheckPlanTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.CheckPlanEntity;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author CBOK
 * @date 2016/11/10 20:09
 * @description:
 */

public class CheckPlanRepo implements CheckPlanDataSource {

    private CheckPlanRepo() {}
    private static CheckPlanRepo INSTANCE;
    public static CheckPlanRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CheckPlanRepo();
        }
        return INSTANCE;
    }

    @Override
    public void upload(final UploadCallBack uploadCallBack) {
        //提交
        final String CHECK_PLAN_URL = BuildConfig.DOMAI + BuildConfig.CHECK_PLAN;
        final String TOKEN = ISpfUtil.getValue(ZNAPPlication.getInstance(), Constants.ACCESS_TOKEN_KEY, "").toString();
        CheckPlanEntity checkPlanEntity = uploadCallBack.getUploadEntity();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(checkPlanEntity);
        ILog.d("TAG", jsonStr);
        JsonBody body = new JsonBody(jsonStr);
        LiteHttpManage.Http_Post_Sync(ZNAPPlication.getInstance(), TOKEN,
                CHECK_PLAN_URL, body, new HttpRequestCallBack() {
                    @Override
                    public void onStart(AbstractRequest request) {
                    }

                    @Override
                    public void onSuccess(Object o, Response response) {
                        ResponseModel modle = gson.fromJson(o.toString(), ResponseModel.class);
                        if (modle.isSuccess()){
                            //保存
                            save(uploadCallBack);
                            //回调
                            uploadCallBack.onUploadSuccess();
                        } else {
                            uploadCallBack.onUploadFailed(modle.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, Response response) {
                        uploadCallBack.onUploadFailed("提交失败");
                    }

                    @Override
                    public void onEnd(Response response) {
                    }
                });
    }

    public void save(UploadCallBack callBack) {
        CheckPlanTable checkPlanTable = callBack.getSaveEntity();
        if (checkPlanTable.getId() == null || checkPlanTable.getId() <= 0){
            ZNAPPlication.getInstance().getDaoSession().getCheckPlanTableDao().insert(checkPlanTable);
        }
    }
}
