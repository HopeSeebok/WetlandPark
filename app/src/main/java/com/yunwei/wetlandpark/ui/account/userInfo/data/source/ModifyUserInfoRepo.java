package com.yunwei.wetlandpark.ui.account.userInfo.data.source;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.data.source
 * @Description: 修改用户信息
 * @date 2016/11/15 16:38
 */

public class ModifyUserInfoRepo implements ModifyUserInfoSource, HttpRequestCallBack {

    private ModifyUserInfoCallBack callBack;

    @Override
    public void uploadUserInfo(Object o, ModifyUserInfoCallBack modifyHeadPortraitCallBack) {
        this.callBack = modifyHeadPortraitCallBack;
        JsonBody body = new JsonBody(o);
        LiteHttpManage.Http_Put_Sync(
                ZNAPPlication.getInstance(),
                ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString(),
                BuildConfig.DOMAI + BuildConfig.EDIT_USER_INFO_URL + ZNAPPlication.getUserInfoEntity().getId(),
                body,
                this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        callBack.onModifyStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ResponseModel entity = new Gson().fromJson(o.toString(), ResponseModel.class);
        if (entity.isSuccess()){
            callBack.onModifySuccess();
        }else {
            callBack.onModifyFailed(entity.getMessage());
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        callBack.onModifyFailed("更新失败");
    }

    @Override
    public void onEnd(Response response) {
        callBack.onModifyEnd();
    }
}
