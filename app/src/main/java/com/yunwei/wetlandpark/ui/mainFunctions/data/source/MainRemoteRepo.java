package com.yunwei.wetlandpark.ui.mainFunctions.data.source;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.QiNiuTokenEntity;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.data.UnitInfoEntity;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.ParseJson;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.data.source
 * @Description:
 * @date 2016/11/16 17:11
 */

public class MainRemoteRepo implements MainDataSource {

    private static MainRemoteRepo mainRepo;

    public static MainRemoteRepo newInstance() {
        if (mainRepo == null) {
            mainRepo = new MainRemoteRepo();
        }
        return mainRepo;
    }

    /**
     * 七牛Token请求
     *
     * @param activity
     * @param callBack
     */
    @Override
    public void reqQiNiuToken(Activity activity, final RequestQiNiuTokenCallBack callBack) {
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), BuildConfig.DOMAI + BuildConfig.QINIU_TOKEN_URL, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
                    Gson gson=new Gson();
                    ResponseModel<QiNiuTokenEntity> model = gson.fromJson(o.toString(),  new TypeToken<ResponseModel<QiNiuTokenEntity>>() {
                    }.getType());
                    if (model != null) {
                        callBack.getQiNiuTokenSuccess(model.getData().getToken());
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {

            }

            @Override
            public void onEnd(Response response) {

            }
        });
    }

    /**
     * 单位列表请求
     *
     * @param activity
     * @param callBack
     */
    @Override
    public void reqUnitList(Activity activity, final RequestUnitListCallBack callBack) {
//        String UNIT_URL = BuildConfig.DOMAI + BuildConfig.UNIT_LIST_URL+"/"+ ZNAPPlication.getUserInfoEntity().getUnitID();
//        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), UNIT_URL, new HttpRequestCallBack() {
//            @Override
//            public void onStart(AbstractRequest request) {
//
//            }
//
//            @Override
//            public void onSuccess(Object o, Response response) {
//                if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
//                    ResponseModel<UnitInfoEntity> entity = new Gson().fromJson(o.toString(),
//                            new TypeToken<ResponseModel<UnitInfoEntity>>() {
//                            }.getType());
//                    if (entity.isSuccess()) {
//                        callBack.getUnitListSuccess(entity.getData());
//                    } else {
//                        callBack.getUnitListFailure();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, Response response) {
//                callBack.getUnitListFailure();
//            }
//
//            @Override
//            public void onEnd(Response response) {
//
//            }
//        });
    }
}
