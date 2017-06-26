package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.biz.IQueryTrackByData;
import com.yunwei.wetlandpark.ui.biz.impl.QueryTrackByDataBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackByDataListener;
import com.yunwei.wetlandpark.ui.view.QueryTrackByDataView;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/18 17:18
 */
public class QueryTrackByDataPresenter implements QueryTrackByDataListener {

    private QueryTrackByDataView queryTrackByDataView;
    private IQueryTrackByData queryTrackByData;
    public QueryTrackByDataPresenter(QueryTrackByDataView queryTrackByDataView){
        this.queryTrackByDataView=queryTrackByDataView;
        this.queryTrackByData=new QueryTrackByDataBiz();
    }

    public void queryTrackByData(String data){
        queryTrackByData.queryTrack(queryTrackByDataView.getActivity(),data,this);
    }
    @Override
    public void onQueryTrackByDataStart() {
        queryTrackByDataView.onQueryTrackByDataStart();
    }

    @Override
    public void onQueryTrackByDataEnd() {
        queryTrackByDataView.onQueryTrackByDataEnd();
    }

    @Override
    public void onQueryTrackByDataFailure(int code, String error) {
        queryTrackByDataView.onQueryTrackByDataFailure(code,error);
    }

    @Override
    public void onQueryTrackByDataSuccess(List<TrackEntity> list) {
        queryTrackByDataView.onQueryTrackByDataSuccess(list);
    }
}
