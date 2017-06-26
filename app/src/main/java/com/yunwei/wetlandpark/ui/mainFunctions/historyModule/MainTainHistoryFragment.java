package com.yunwei.wetlandpark.ui.mainFunctions.historyModule;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.greedao.TroubleShooterTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.ShootTroubleActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter.MaintainRecyclerAdapter;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history
 * @Description:维护
 * @date 2016/9/12 11:01
 */
public class MainTainHistoryFragment extends BaseHistoryFragment {

    private MaintainRecyclerAdapter adapter;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what){
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                List<TroubleShooterTable> tableList=(List<TroubleShooterTable>)msg.obj;
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
                List<TroubleShooterTable> tableList1 = (List<TroubleShooterTable>)msg.obj;
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
        adapter=new MaintainRecyclerAdapter(getContext());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        setAdapter(adapter);
        refresh();
    }

    private void query(){
        List<TroubleShooterTable> tableList = ZNAPPlication.getDaoSession().getTroubleShooterTableDao().queryBuilder()
                .orderDesc(TroubleShooterTableDao.Properties.Time)
                .list();
        if (page == 0)
            sendHandlerMessage(mHandler, HandlerValue.QUERY_FAC_VALUE, tableList);
        else
            sendHandlerMessage(mHandler, HandlerValue.UPLOAD_FAC_INFO, tableList);
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
        bundle.putSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE, (TroubleShooterTable)data);
        ISkipActivityUtil.startIntent(getContext(), ShootTroubleActivity.class,bundle);
    }

    @Override
    public void longItemClickEvent(Object data, int position) {
        super.longItemClickEvent(data, position);
        ZNAPPlication.getInstance().getDaoSession().getTroubleShooterTableDao().delete((TroubleShooterTable) data);
        refresh();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        query();
    }
}
