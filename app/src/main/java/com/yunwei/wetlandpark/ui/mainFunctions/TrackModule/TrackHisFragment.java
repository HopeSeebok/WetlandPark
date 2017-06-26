package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.adapter.TrackListAdapter;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.BaseHistoryFragment;
import com.yunwei.wetlandpark.ui.presenter.QueryLocationTrackPointPresenter;
import com.yunwei.wetlandpark.ui.presenter.QueryLocationTrackPresenter;
import com.yunwei.wetlandpark.ui.view.QueryBaiduTrackHistoryView;
import com.yunwei.wetlandpark.ui.view.QueryLocationTrackPointView;
import com.yunwei.wetlandpark.ui.view.QueryLocationTrackView;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.library.dialog.DialogFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.main.fragment
 * @Description:足迹历史记录
 * @date 2016/8/31 10:48
 */
public class TrackHisFragment extends BaseHistoryFragment implements QueryLocationTrackView, QueryLocationTrackPointView, QueryBaiduTrackHistoryView {

    private TrackListAdapter adapter;
    /**
     * 查询足迹控制层
     */
    private QueryLocationTrackPresenter trackPresenter;
    /**
     * 查询足迹点控制层
     */
    private QueryLocationTrackPointPresenter trackPointPresenter;
    /**
     * 百度鹰眼历史轨迹查询控制层
     */
    //private QueryBaiduTrackHistoryPresenter baiduTrackHistoryPresenter;

    private Track track;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackPresenter = new QueryLocationTrackPresenter(this);
        trackPointPresenter = new QueryLocationTrackPointPresenter(this);
       // baiduTrackHistoryPresenter = new QueryBaiduTrackHistoryPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new TrackListAdapter(getActivity(), TrackListAdapter.FROM_LOCATION);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongListener(this);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);
        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMainUserEvent(NoticeEvent event) {
        super.onMainUserEvent(event);
        switch (event.getFlag()) {
            case EventConstant.NOTICE8:
                refresh();
                break;
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        track = (Track) data;
        if (track == null) {
            showToast("查询失败!");
            return;
        }
        //baiduTrackHistoryPresenter.queryBaiduTrack((int) (track.getStartTime() / 1000), (int) (track.getEndTime() / 1000));
//        trackPointPresenter.queryTrackPoint(track.getTId());
    }

    @Override
    public void onLongItemClick(View view, final Object data, int position) {
//        super.onLongItemClick(view, data, position);
        DialogFactory.showMsgDialog(getActivity(), "删除提示", "确实删除该足迹记录?", "删除", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Track entity = (Track) data;
                if (entity != null) {
//                    ZNAPPlication.getInstance().getDaoSession().getTrackDao().delete(entity);
                    refresh();
                }
            }
        }, null);
    }


    @Override
    public void onRefresh() {
        trackPresenter.queryLocTrack();
    }

    @Override
    public void onQueryLocTrackStart() {

    }

    @Override
    public void onQuerylocTrackEnd() {

    }

    @Override
    public void onQueryLocTrackFailure(int code, String error) {

    }

    @Override
    public void onQueryLocTrackSuccess(List<Track> track) {
        adapter.clearList();
        if (track != null && track.size() > 0) {
            adapter.addData(track);
            emptyTextView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.VISIBLE);
        }
        coloseRefresh();
    }

    @Override
    public void onQueryPointStart() {
        dialog = DialogFactory.createLoadingDialog(getActivity(), "轨迹查询...");
    }

    @Override
    public void onQueryPointEnd() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onQueryPointFailure(int code, String error) {
        showToast(error);
    }

    @Override
    public void onQueryPointSuccess(List<Point> list) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TrackMapActivity.TRACK_VALUE, track);
        bundle.putSerializable(TrackMapActivity.POINT_VALUE, (Serializable) list);
        bundle.putString(TrackMapActivity.FROM_FLAG, TrackMapActivity.FROM_LOCATION);
        ISkipActivityUtil.startIntent(getActivity(), TrackMapActivity.class, bundle);

    }

    @Override
    public void onQueryBaiduTrackFailure(String err) {
        showToast("查询失败!");
    }

    @Override
    public void onQueryBaiduTrackStart() {
        dialog = DialogFactory.createLoadingDialog(getActivity(), "轨迹查询...");
    }

    @Override
    public void onQueryBaiduTrackEnd() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onQueryBaiduTrackSuccess(List<Point> list, double distance) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TrackMapActivity.TRACK_VALUE, track);
        bundle.putSerializable(TrackMapActivity.POINT_VALUE, (Serializable) list);
        bundle.putString(TrackMapActivity.FROM_FLAG, TrackMapActivity.FROM_LOCATION);
        ISkipActivityUtil.startIntent(getActivity(), TrackMapActivity.class, bundle);
    }
}
