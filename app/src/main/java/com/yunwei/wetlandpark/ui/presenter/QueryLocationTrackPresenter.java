package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.biz.IQueryLocationTrack;
import com.yunwei.wetlandpark.ui.biz.impl.QueryLocationTrackBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackListener;
import com.yunwei.wetlandpark.ui.view.QueryLocationTrackView;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/20 14:56
 */
public class QueryLocationTrackPresenter implements QueryLocationTrackListener {

    private QueryLocationTrackView locationTrackView;
    private IQueryLocationTrack locationTrack;

    public QueryLocationTrackPresenter(QueryLocationTrackView locationTrackView) {
        this.locationTrackView = locationTrackView;
        this.locationTrack = new QueryLocationTrackBiz();
    }

    public void queryLocTrack() {
        locationTrack.queryLocTrack(locationTrackView.getActivity(), this);
    }

    @Override
    public void onQueryLocTrackSuccess(List<Track> track) {
        locationTrackView.onQueryLocTrackSuccess(track);
    }

    @Override
    public void onQueryLocTrackStart() {
        locationTrackView.onQueryLocTrackStart();
    }

    @Override
    public void onQuerylocTrackEnd() {
        locationTrackView.onQuerylocTrackEnd();
    }

    @Override
    public void onQueryLocTrackFailure(int code, String error) {
        locationTrackView.onQueryLocTrackFailure(code, error);
    }
}
