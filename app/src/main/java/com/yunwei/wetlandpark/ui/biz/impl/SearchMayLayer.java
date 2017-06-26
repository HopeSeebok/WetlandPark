package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.esri.core.tasks.identify.IdentifyResult;
import com.esri.core.tasks.identify.IdentifyTask;
import com.yunwei.wetlandpark.entity.MapSearchEntity;
import com.yunwei.wetlandpark.ui.biz.ISearchMapLayer;
import com.yunwei.wetlandpark.ui.biz.interfac.SearchMapLayerListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.library.utils.IStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz
 * @Description:图层查询业务类
 * @date 2016/7/28 19:44
 */
public class SearchMayLayer implements ISearchMapLayer {
    private final String TAG=this.getClass().getSimpleName();

    private IdentifyTask identifyTask;

    /**
     * @wuqianrui 2016/10/11
     * 修改图层的查询，点击地图查询图层上  枪机(5)、球机(6) 这个两个图层上的设施点
     */
    @Override
    public void search(final Activity activity, String url, final IdentifyParameters parameters, final SearchMapLayerListener layerListener) {
        if (layerListener == null) {
            return;
        }
        layerListener.onSearchMapLayerStart();
        identifyTask = new IdentifyTask(url);
        new Thread() {
            @Override
            public void run() {
                try {
                    IdentifyResult[] results = identifyTask.execute(parameters);
                    if (results == null || results.length <= 0) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layerListener.onSearchMapLayerEnd();
                                layerListener.onSearchMapLayerFailure("查询为空");
                            }
                        });
                        return;
                    }
                    final List<MapSearchEntity> mLists = new ArrayList<MapSearchEntity>();
                    MapSearchEntity entity;
                    for (int i = 0; i < results.length; i++) {
                        entity = new MapSearchEntity();
                        Map<String,Object> attributes = results[i].getAttributes();
                        ILog.d(TAG,"attributes=="+attributes.toString());
                        /* 明明是 id key  为什么能拿到guid*/
                        entity.setGUID(attributes.get("id").toString());
                        entity.setAddr(attributes.get("address").toString());
                        entity.setCode(attributes.get("code").toString());
                        entity.setPointID(IStringUtils.toInt(attributes.get("pointid").toString()));
                        entity.setObjectId(attributes.get("OBJECTID").toString());
                        entity.setCreateTime(attributes.get("createtime").toString());
                        entity.setFacType(results[i].getLayerName());
                        entity.setLayerType(results[i].getLayerName());
                        mLists.add(entity);
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layerListener.onSearchMapLayerSuccess(mLists);
                            layerListener.onSearchMapLayerEnd();
                        }
                    });
                } catch (Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layerListener.onSearchMapLayerEnd();
                            layerListener.onSearchMapLayerFailure("查询失败");
                        }
                    });
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
