package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.esri.core.tasks.identify.IdentifyResult;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.MapEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.source.TapMapSearchDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/5 22:54
 * @description:
 */

public class TapMapSearchPresenter implements TapMapSearchContract.Presenter, TapMapSearchDataSource.TapMapSearchDeviceCallBack{


    private TapMapSearchDataSource mRepo;
    private TapMapSearchContract.View mView;

    public TapMapSearchPresenter(TapMapSearchDataSource repo, TapMapSearchContract.View view) {
        mRepo = repo;
        mView = view;
    }

    @Override
    public void tapSearch() {
        mRepo.tapMapSearch(mView.getActivity(),this);
    }

    @Override
    public void onSearchDataLoaded(IdentifyResult[] results) {
        List<MapEntity> dataList = new ArrayList<>();
        for (IdentifyResult result : results) {
            MapEntity mapEntity = new MapEntity();
            switch (result.getLayerId()) {
                case 0:
                    mapEntity.setLayerID(result.getLayerId());
                    mapEntity.setId(result.getAttributes().get("id").toString());
                    mapEntity.setEmergencyLevel(Integer.valueOf(result.getAttributes().get("missionlevel").toString()));
                    mapEntity.setTroubleStatus(Integer.valueOf(result.getAttributes().get("dangerstatus").toString()));
                    mapEntity.setTroubleType(result.getAttributes().get("dangertype").toString());
                    if (mapEntity.getTroubleStatus() != 2) {
                        dataList.add(mapEntity);
                    }
                    break;
                case 1:
                    mapEntity.setLayerID(result.getLayerId());
                    mapEntity.setId(result.getAttributes().get("id").toString());
                    mapEntity.setCode(result.getAttributes().get("code").toString());
                    dataList.add(mapEntity);
                    break;
            }
        }
        if (dataList.size() > 0) {
            mView.showTapSearchResult(dataList);
        }
    }

    @Override
    public void onSearchDataNotAvailable() {
        mView.showTapSearchError();
    }

    @Override
    public IdentifyParameters getTapSearchParameters() {
        return mView.getTapSearchParameters();
    }
}
