package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.TrackDetailEntity;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.adapter.TrackListAdapter;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemClickListener;
import com.yunwei.wetlandpark.ui.presenter.QueryTrackDetailPresenter;
import com.yunwei.wetlandpark.ui.view.QueryTrackDetailView;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.track
 * @Description:
 * @date 2016/9/19 17:22
 */
public class TrackListActivity extends BaseActivity implements OnRecyclerViewItemClickListener, QueryTrackDetailView {

    private RecyclerView mTrackListRecyclerview;

    private List<TrackEntity> mLists;
    private TrackListAdapter adapter;

    private QueryTrackDetailPresenter trackDetailPresenter;

    private TrackEntity entity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_track_list);
        setToolbarTitle("查询结果");
        trackDetailPresenter = new QueryTrackDetailPresenter(this);

        mLists = (List<TrackEntity>) getIntent().getSerializableExtra("entity");
        initRecycler();
        initUI();
    }

    @Override
    public void findViewById() {
        super.findViewById();
        mTrackListRecyclerview = (RecyclerView) findViewById(R.id.track_list_recyclerview);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecycler() {
        //创建默认的线性LayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mTrackListRecyclerview.setLayoutManager(manager);
        //设置布局方向
        manager.setOrientation(OrientationHelper.VERTICAL);
    }

    private void initUI() {
        adapter = new TrackListAdapter(this, TrackListAdapter.FROM_SERVER);
        mTrackListRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.addDataV2(mLists);
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        entity = (TrackEntity) data;
        if (entity != null) {
            trackDetailPresenter.queryTrackDetail(entity.getFootPrintID());
        }
    }

    @Override
    public void onQueryTrackDetailStart() {
        dialog = DialogFactory.createLoadingDialog(this, "查询...");
    }

    @Override
    public void onQueryTrackDetailEnd() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onQueryTrackDetailFailure(int code, String error) {
        showToast(error);
    }

    @Override
    public void onQueryTrackDetailSuccess(TrackDetailEntity entity) {
        if (entity.getListPoints() == null || entity.getListPoints().size() <= 0) {
            showToast("没有足迹点!");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(TrackMapActivity.TRACK_VALUE, this.entity);
        bundle.putSerializable(TrackMapActivity.POINT_VALUE, (Serializable) entity.getListPoints());
        bundle.putString(TrackMapActivity.FROM_FLAG, TrackMapActivity.FROM_SERVER);
        ISkipActivityUtil.startIntent(getActivity(), TrackMapActivity.class, bundle);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
