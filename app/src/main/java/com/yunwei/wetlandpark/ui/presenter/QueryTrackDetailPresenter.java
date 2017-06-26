package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.entity.TrackDetailEntity;
import com.yunwei.wetlandpark.ui.biz.IQueryTrackDetail;
import com.yunwei.wetlandpark.ui.biz.impl.QueryTrackDetailBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackDetailListener;
import com.yunwei.wetlandpark.ui.view.QueryTrackDetailView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/18 17:21
 */
public class QueryTrackDetailPresenter implements QueryTrackDetailListener {

    private QueryTrackDetailView queryTrackDetailView;
    private IQueryTrackDetail queryTrackDetail;

    public QueryTrackDetailPresenter(QueryTrackDetailView queryTrackDetailView) {
        this.queryTrackDetailView = queryTrackDetailView;
        this.queryTrackDetail = new QueryTrackDetailBiz();
    }

    public void queryTrackDetail(int id) {
        queryTrackDetail.queryTrackDetail(queryTrackDetailView.getActivity(), id, this);
    }

    @Override
    public void onQueryTrackDetailStart() {
        queryTrackDetailView.onQueryTrackDetailStart();
    }

    @Override
    public void onQueryTrackDetailEnd() {
        queryTrackDetailView.onQueryTrackDetailEnd();
    }

    @Override
    public void onQueryTrackDetailFailure(int code, String error) {
        queryTrackDetailView.onQueryTrackDetailFailure(code, error);
    }

    @Override
    public void onQueryTrackDetailSuccess(TrackDetailEntity entity) {
        queryTrackDetailView.onQueryTrackDetailSuccess(entity);
    }
}
