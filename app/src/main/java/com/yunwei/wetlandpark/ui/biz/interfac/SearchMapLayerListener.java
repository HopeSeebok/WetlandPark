package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.MapSearchEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz.interfac
 * @Description:
 * @date 2016/7/28 19:42
 */
public interface SearchMapLayerListener {

    public void onSearchMapLayerStart();
    public void onSearchMapLayerEnd();
    public void onSearchMapLayerSuccess(List<MapSearchEntity> entity);
    public void onSearchMapLayerFailure(String errorMsg);
}
