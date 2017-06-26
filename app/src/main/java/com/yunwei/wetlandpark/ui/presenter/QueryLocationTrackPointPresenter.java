package com.yunwei.wetlandpark.ui.presenter;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.ui.biz.IQueryLocationTrackPoint;
import com.yunwei.wetlandpark.ui.biz.impl.QueryLocationTrackPointBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackPointListener;
import com.yunwei.wetlandpark.ui.view.QueryLocationTrackPointView;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:查询足迹点P层
 * @date 2016/9/20 14:41
 */
public class QueryLocationTrackPointPresenter implements QueryLocationTrackPointListener {

    private QueryLocationTrackPointView locationTrackPointView;
    private IQueryLocationTrackPoint locationTrackPointBiz;

    public QueryLocationTrackPointPresenter(QueryLocationTrackPointView locationTrackPointView) {
        this.locationTrackPointView = locationTrackPointView;
        this.locationTrackPointBiz = new QueryLocationTrackPointBiz();
    }

    public void queryTrackPoint(long tId) {
        locationTrackPointBiz.queryTrackPoint(locationTrackPointView.getActivity(), tId, this);
    }

    @Override
    public void onQueryPointStart() {
        locationTrackPointView.onQueryPointStart();
    }

    @Override
    public void onQueryPointEnd() {
        locationTrackPointView.onQueryPointEnd();
    }

    @Override
    public void onQueryPointFailure(int code, String error) {
        locationTrackPointView.onQueryPointFailure(code, error);
    }

    @Override
    public void onQueryPointSuccess(List<Point> list) {
        locationTrackPointView.onQueryPointSuccess(list);
    }
}
