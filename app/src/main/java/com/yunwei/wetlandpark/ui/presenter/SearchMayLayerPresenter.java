package com.yunwei.wetlandpark.ui.presenter;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.yunwei.wetlandpark.entity.MapSearchEntity;
import com.yunwei.wetlandpark.ui.biz.ISearchMapLayer;
import com.yunwei.wetlandpark.ui.biz.impl.SearchMayLayer;
import com.yunwei.wetlandpark.ui.biz.interfac.SearchMapLayerListener;
import com.yunwei.wetlandpark.ui.view.SearchMapView;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.presenter
 * @Description:
 * @date 2016/7/28 20:33
 */
public class SearchMayLayerPresenter implements SearchMapLayerListener {

    SearchMapView searchMapView;
    ISearchMapLayer mapLayerBiz;

    public SearchMayLayerPresenter(SearchMapView searchMapView) {
        this.mapLayerBiz = new SearchMayLayer();
        this.searchMapView = searchMapView;
    }

    public void search(IdentifyParameters parameters) {
        mapLayerBiz.search(searchMapView.getActivity(),searchMapView.getSearchMapLayerUrl(), parameters, this);
    }

    @Override
    public void onSearchMapLayerStart() {
        searchMapView.onSearchMapLayerStart();
    }

    @Override
    public void onSearchMapLayerEnd() {
        searchMapView.onSearchMapLayerEnd();
    }

    @Override
    public void onSearchMapLayerSuccess(List<MapSearchEntity> entity) {
        searchMapView.onSearchMapLayerSuccess(entity);
    }

    @Override
    public void onSearchMapLayerFailure(String errorMsg) {
        searchMapView.onSearchMapLayerFailure(errorMsg);
    }
}
