package com.yunwei.wetlandpark.ui.mainFunctions.historyModule;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.SignInTable;
import com.yunwei.wetlandpark.greedao.SignInTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter.SignInHistoryAdapter;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/15 10:31
 * @description:
 */

public class SignInHistoryFragment extends BaseHistoryFragment{

    private SignInHistoryAdapter adapter;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what){
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                List<SignInTable> tableList=(List<SignInTable>)msg.obj;
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
                List<SignInTable> tableList1 = (List<SignInTable>)msg.obj;
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
        adapter=new SignInHistoryAdapter(getContext());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        setAdapter(adapter);
        refresh();
    }

    private void query(){
        List<SignInTable> tableList= ZNAPPlication.getDaoSession().getSignInTableDao().queryBuilder()
                .orderDesc(SignInTableDao.Properties.Time).list();
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
    }


    @Override
    public void longItemClickEvent(Object data, int position) {
        ZNAPPlication.getDaoSession().getSignInTableDao().delete((SignInTable) data);
        refresh();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        query();
    }
}
