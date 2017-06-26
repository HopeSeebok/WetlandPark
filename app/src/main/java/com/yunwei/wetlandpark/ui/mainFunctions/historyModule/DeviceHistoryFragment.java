package com.yunwei.wetlandpark.ui.mainFunctions.historyModule;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter.DeviceRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history
 * @Description:设施
 * @date 2016/9/12 11:01
 */
public class DeviceHistoryFragment extends BaseHistoryFragment {

    private DeviceRecycleAdapter adapter;


    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what){
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                List<Facility> facilities=(List<Facility>)msg.obj;
                adapter.clearList();
                if (facilities==null||facilities.size()==0){
                    emptyTextView.setVisibility(View.VISIBLE);
                    adapter.setShowFootFlag(false);
                }else {
                    adapter.setLists(facilities);
                    emptyTextView.setVisibility(View.GONE);
                }
                break;
            case HandlerValue.UPLOAD_FAC_INFO:
                List<Facility> facilities1 = (List<Facility>)msg.obj;
                if (facilities1==null||facilities1.size()==0){
                    adapter.setShowFootFlag(false);
                }else {
                    adapter.addItems(facilities1);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter=new DeviceRecycleAdapter(getContext());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        setAdapter(adapter);
        refresh();
    }

    private void query(){
//        List<Facility> facilities= ZNAPPlication.getDaoSession(getActivity()).getFacilityDao().queryBuilder()
//                .orderDesc(FacilityDao.Properties.Id).list();
        //Demo
        List<Facility> facilities = new ArrayList<Facility>();
        for (int i = 0;i<10;i++){
            Facility facility = new Facility();
            facility.setType("aaa");
            facility.setCreateTime("bbb");
            facility.setAddress("ccc");
            facility.setState(Constants.StandardState.SaveLocal);
            facilities.add(facility);
        }

        if (page == 0)
            sendHandlerMessage(mHandler, HandlerValue.QUERY_FAC_VALUE,facilities);
        else
            sendHandlerMessage(mHandler, HandlerValue.UPLOAD_FAC_INFO,facilities);
    }

    @Override
    protected void update() {
        ++page;
        //加载新的数据
        query();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        super.onItemClick(view, data, position);
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.Facility.FLAG,(Facility)data);
//        ISkipActivityUtil.startIntent(getContext(), FacilityHistoryActivity.class,bundle);
    }

    @Override
    public void longItemClickEvent(Object data, int position) {
        super.longItemClickEvent(data, position);
        Facility facility=(Facility)data;
//        ZNAPPlication.getInstance().getDaoSession(this.getContext()).getFacilityDao().delete(facility);
        refresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        query();
    }
}
