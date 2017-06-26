package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model;

import android.app.Activity;
import android.app.Dialog;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.utils.INetWorkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl.task
 * @Description:任务状态处理
 * @date 2016/9/26
 * @changeby:
 */
public class TaskChangeStatusRequest implements HttpRequestCallBack {
    private Dialog mProgressDialog;
    private Subscriber<? super String> mSub;

    public void changeStatus(final Activity activity, final String taskId, final int status, final String backReason, Subscriber<? super String> sub) {
//        Activity mActivity = activity;
//        int mStatus = status;
//        String mTaskId = taskId;
//        String mBackReason = backReason;
        mSub=sub;
        if (!INetWorkUtil.isNetworkAvailable(activity)) {
            ToastUtil.showToast(activity, "网络断开连接");
            return;
        }

        mProgressDialog = DialogFactory.createLoadingDialog(activity);
        String url = IConfigValues.DOMAIN + "v1/WorkTask";
        JSONObject obj = new JSONObject();
        try {
            obj.put("Id", taskId);
            obj.put("TaskStatus", status);
            obj.put("BackReason", backReason);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBody body = new JsonBody(obj.toString());
        LiteHttpManage.Http_Post_Sync(activity, (String) ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, ""), url, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
    }

    @Override
    public void onSuccess(Object o, Response response) {
        mSub.onNext(o.toString());
    }

    @Override
    public void onFailure(HttpException e, Response response) {
    }

    @Override
    public void onEnd(Response response) {
        DialogFactory.dimissDialog(mProgressDialog);
    }
}
