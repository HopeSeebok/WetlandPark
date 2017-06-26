package com.yunwei.wetlandpark.ui.account.password.data.source;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.account.password.data.ChangePwdEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.password.data.source
 * @Description: 修改密码
 * @date 2016/11/16 09:25
 */

public class ModifyPasswordRepo implements ModifyPasswordDataSource, HttpRequestCallBack{

    private ModifyPasswordCallBack callBack;

    @Override
    public void modifyPassword(ChangePwdEntity entity, ModifyPasswordCallBack callBack) {
        this.callBack = callBack;
        JsonBody body = new JsonBody(entity);
        LiteHttpManage.Http_Put_Sync(
                ZNAPPlication.getInstance(),
                ISpfUtil.getValue(ZNAPPlication.getInstance(), Constants.ACCESS_TOKEN_KEY, "").toString(),
                BuildConfig.DOMAI + BuildConfig.UPDATE_USER_PWD_URL + ZNAPPlication.getUserInfoEntity().getId(),
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
            callBack.onModifyFailed("修改密码失败");
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        callBack.onModifyFailed("修改密码失败");
    }

    @Override
    public void onEnd(Response response) {
        callBack.onModifyEnd();
    }
}
