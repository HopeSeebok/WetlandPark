package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.trackHistoryPage;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.greedao.TrackDao;
import com.yunwei.wetlandpark.greedao.TrackPointsTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.BaseHistoryFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.source.HistoryDataRepo;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/15 10:33
 * @description:
 */

public class TrailHistoryFragment extends BaseHistoryFragment implements TrailHistoryContract.View{

    private TrailHistoryAdapter adapter;
    private TrailHistoryPresenter mTrailHistoryPresenter;
    private Dialog mLoadingDialog;
    private Track mTrack;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what){
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                List<Track> tableList=(List<Track>)msg.obj;
                adapter.clearList();
                if (tableList==null||tableList.size()==0){
                    emptyTextView.setVisibility(View.VISIBLE);
                    adapter.setShowFootFlag(false);
                }else {
                    adapter.setLists(tableList);
                    emptyTextView.setVisibility(View.GONE);
                }
                break;
            case HandlerValue.UPLOAD_FAC_INFO:
                List<Track> tableList1 = (List<Track>)msg.obj;
                if (tableList1==null||tableList1.size()==0){
                    adapter.setShowFootFlag(false);
                }else {
                    adapter.addItems(tableList1);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter=new TrailHistoryAdapter(getContext());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        setAdapter(adapter);
        refresh();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrailHistoryPresenter = new TrailHistoryPresenter(HistoryDataRepo.getInstance(), this);
    }

    private void query(){
        List<Track> tableList= ZNAPPlication.getDaoSession().getTrackDao().queryBuilder()
                .orderDesc(TrackDao.Properties.StartTime).list();
        if (page == 0)
            sendHandlerMessage(mHandler, HandlerValue.QUERY_FAC_VALUE,tableList);
        else
            sendHandlerMessage(mHandler, HandlerValue.UPLOAD_FAC_INFO,tableList);
    }

    @Override
    protected void update() {
        ++page;
        //加载新的数据
        query();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
//        Bundle bundle=new Bundle();
//        bundle.putSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE,(SignInTable)data);
//        ISkipActivityUtil.startIntent(getContext(), MakeTroubleActivity.class,bundle);
        mTrack = (Track) data;
        mTrailHistoryPresenter.searchTrail(mTrack.getStartTime(), mTrack.getEndTime());
    }


    @Override
    public void longItemClickEvent(Object data, int position) {
        ZNAPPlication.getDaoSession().getTrackDao().delete((Track) data);
        refresh();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        query();
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog = DialogFactory.createLoadingDialog(getActivity(), "加载中..");
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showTrailResult(String cacheID) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TrackMapActivity2.TRACK_VALUE, mTrack);
        bundle.putString(TrackMapActivity2.POINT_VALUE, cacheID);
        ISkipActivityUtil.startIntent(getContext(), TrackMapActivity2.class, bundle);
    }

    @Override
    public void showFailMessage() {
        //TODO 失败提示修改
        ToastUtil.showToast(getContext(),"查询轨迹失败");
    }
}
