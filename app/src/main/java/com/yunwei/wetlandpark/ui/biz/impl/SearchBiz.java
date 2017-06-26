package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.entity.SearchEntity;
import com.yunwei.wetlandpark.entity.search.SearchKey;
import com.yunwei.wetlandpark.entity.task.HDDetail;
import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.ui.biz.interfac.SearchRequestListener;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback.GetDeviceInfoListener;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.SaveObjectUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/28
 * @changeby:
 */

public class SearchBiz {
    private String mSearch;
    private final Context mContext;

    public SearchBiz(Context context,String search) {
        mContext=context;
        mSearch=search;
    }

    public SearchBiz(Context context) {
        mContext=context;
    }

    /**
     * @param searchRequestListener
     * 搜索请求
     */
    public void searchRequest( final SearchRequestListener searchRequestListener) {
        final Dialog progressDialog = DialogFactory.createLoadingDialog((Activity) mContext);
        String url = IConfigValues.SEARCH_REQUEST+mSearch;
        LiteHttpManage.Http_Get_Sync((Activity) mContext, ISpfUtil.getValue(mContext, Constants.ACCESS_TOKEN_KEY, "").toString(), url, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {
            }
            @Override
            public void onSuccess(Object o, Response response) {
                try {
                    JSONObject jsonObject=new JSONObject(o.toString());
                    JSONArray a=jsonObject.getJSONArray("data");
                    if (a.length()==0) {
                        Toast.makeText(mContext, "没有搜到此信息", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SearchEntity searchEntitie = new Gson().fromJson(o.toString(), new TypeToken<SearchEntity>() {
                }.getType());

                searchRequestListener.success(searchEntitie);

            }
            @Override
            public void onFailure(HttpException e, Response response) {
                searchRequestListener.failure();
                Toast.makeText(mContext, "搜索失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onEnd(Response response) {
                DialogFactory.dimissDialog(progressDialog);
            }
        });
    }

    /**
     * 保存搜索关键字
     */
    public void savekey() {
        SaveObjectUtils saveObjectUtils = new SaveObjectUtils(mContext,Constants.SEARCHKEY);
        ArrayList<String> keys = saveObjectUtils.getObject(Constants.KEY, SearchKey.class).getKey();
        if (keys.size()==0) {
            SearchKey searchKey=new SearchKey().setKey(new ArrayList<String>());
            searchKey.getKey().add(mSearch);
            saveObjectUtils.setObject(Constants.KEY,searchKey);
        }else {
            if (keys.size()>=5) {
                ArrayList<String> newkeys= new ArrayList<>();
                newkeys.add("");
                for (int i = 0; i < keys.size()-1; i++) {
                    newkeys.add(keys.get(i));
                }
                newkeys.set(0,mSearch);
                saveObjectUtils.setObject(Constants.KEY,new SearchKey().setKey(newkeys));
            }else {
                keys.add(mSearch);
                saveObjectUtils.setObject(Constants.KEY,new SearchKey().setKey(keys));
            }
        }
    }


    /**
     * @param id
     * @param getDeviceInfoListener
     * 获取隐患信息
     */
    public void getHDDeviceInfo(final String id, final GetDeviceInfoListener getDeviceInfoListener){
        final Dialog dialog= DialogFactory.createLoadingDialog((Activity) mContext);
        String url = IConfigValues.GET_HD_INFO  + id;
        LiteHttpManage.Http_Get_Sync((Activity) mContext, ISpfUtil.getValue(mContext, Constants.ACCESS_TOKEN_KEY, "").toString(), url, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {
            }
            @Override
            public void onSuccess(Object o, Response response) {
                HDDetail hdDetail = new Gson().fromJson(o.toString(), new TypeToken<HDDetail>() {
                }.getType());

                HDDetail.ItemBean itemBean=hdDetail.getItem();
                Facility lFacility = new Facility();
//                lFacility.setTaskId(itemBean.get());
//                lFacility.setTaskPointId(workTaskPointsEntity.getWorkTaskPointId());
                lFacility.setType(hdDetail.getItem().getFacilitytype());
                lFacility.setCode(hdDetail.getItem().getCode());
                lFacility.setAddress(hdDetail.getItem().getHdplace());
                lFacility.setHdType(itemBean.getHdtype());
                lFacility.setHdDesc(itemBean.getHddesc());
                getDeviceInfoListener.success(lFacility);
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                ToastUtil.showToast(mContext, mContext.getString(R.string.taskpoint_invalid));
            }

            @Override
            public void onEnd(Response response) {
                DialogFactory.dimissDialog(dialog);
            }
        });

    }
}
