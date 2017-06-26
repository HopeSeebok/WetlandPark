package com.yunwei.wetlandpark.ui.mainFunctions.historyModule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntityDao;
import com.yunwei.wetlandpark.ui.adapter.task.TaskHistoryListAdapter;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui.TaskDetailActivity;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history
 * @Description:任务
 * @date 2016/9/12 11:01
 * @changeby:
 */
public class TaskHistoryFragment extends BaseHistoryFragment {
    private TaskHistoryListAdapter mAdapter;
    private List<TaskListAndDetailEntity> mList = new ArrayList<>();
    private String mUserId;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                List<TaskListAndDetailEntity> list = (List<TaskListAndDetailEntity>) msg.obj;
                mAdapter.clearList();
                if (list == null || list.size() == 0) {
                    emptyTextView.setVisibility(View.VISIBLE);
                    mAdapter.setShowFootFlag(false);
                } else {
                    mAdapter.setLists(list);
                    emptyTextView.setVisibility(View.GONE);
                }
                break;
            case HandlerValue.UPLOAD_FAC_INFO:
                List<TaskListAndDetailEntity> list1 = (List<TaskListAndDetailEntity>) msg.obj;
                if (list1 == null || list1.size() == 0) {
                    mAdapter.setShowFootFlag(false);
                } else {
                    mAdapter.addItems(list1);
                }
                break;
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
//        setRefreshLayoutInvisiable();
        mAdapter = new TaskHistoryListAdapter(getActivity());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        setAdapter(mAdapter);
        updateList();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        super.onItemClick(view, data, position);
        Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
        intent.putExtra(Constants.TASKENTITY, mList.get(position));
        getActivity().startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, Object data, int position) {
        super.onLongItemClick(view, data, position);
    }

    @Override
    public void longItemClickEvent(Object data, int position) {
        TaskListAndDetailEntity taskListAndDetailEntity = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().where(
                TaskListAndDetailEntityDao.Properties.UserId.eq(mUserId),
                TaskListAndDetailEntityDao.Properties.TaskId.eq(((TaskListAndDetailEntity) data).getTaskId()))
                .list().get(0);
        ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().delete(taskListAndDetailEntity);
        initData();
        updateList();
    }

    private void updateList() {
        mAdapter.setLists(mList);
        recyclerView.setAdapter(mAdapter);
        if (mList.size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            mAdapter.setShowFootFlag(false);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    private void initData() {
        if (mList != null) {
            mList.clear();
        }
        mUserId = (String) ISpfUtil.getValue(getActivity(), Constants.ACCOUNT_ID_KEY, "");
        mList = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().where(
                TaskListAndDetailEntityDao.Properties.UserId.eq(mUserId),
                TaskListAndDetailEntityDao.Properties.IsHistory.eq(true))
                .orderDesc(TaskListAndDetailEntityDao.Properties.HistoryTime).limit(PAGE_COUNT).list();

        if (page == 0)
            sendHandlerMessage(mHandler, HandlerValue.QUERY_FAC_VALUE, mList);
        else
            sendHandlerMessage(mHandler, HandlerValue.UPLOAD_FAC_INFO, mList);
    }

    public void onMainTaskEntity(MessageBean messageBean) {
        initData();
        updateList();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        initData();
    }

    @Override
    protected void update() {
        ++page;
        //加载新的数据
        initData();
    }
}
