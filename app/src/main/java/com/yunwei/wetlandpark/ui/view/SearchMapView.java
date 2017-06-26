package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.MapSearchEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.view
 * @Description:
 * @date 2016/7/28 20:33
 */
public interface SearchMapView extends BaseView{

    public void onSearchMapLayerStart();

    public void onSearchMapLayerEnd();

    public void onSearchMapLayerSuccess(List<MapSearchEntity> entity);

    public void onSearchMapLayerFailure(String errorMsg);

    public String getSearchMapLayerUrl();
}
