package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.task.TaskListAdapter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskListPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskListView;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

import java.util.List;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:任务列表
 * @date 2016/9/27
 * @changeby:
 */
public class TaskListFragment extends TaskBaseFragment implements TaskListView {
    private TaskListPresenter mTaskListPresenter;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.QUERY_FAC_VALUE:
                coloseRefresh();
                mTaskListPresenter.refreshList(msg);
                break;
            case HandlerValue.UPLOAD_FAC_INFO:
                mTaskListPresenter.uploadList(msg);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTaskListPresenter = new TaskListPresenter(getActivity(), this);
        mTaskListPresenter.setAdapter();
    }

    /**
     * @param messageBean evenbus接收
     */
    public void onMainTaskEntity(MessageBean messageBean) {
        onRefresh();
    }

    @Override
    public void setTaskListAdapter(TaskListAdapter taskListAdapter) {
        taskListAdapter.setOnItemClickListener(this);
        taskListAdapter.setOnItemLongClickListener(this);
        setAdapter(taskListAdapter);
        refresh();
        updateList();
    }

    @Override
    public void setNodataViewVisiable(boolean visiableView) {
        if (visiableView) {
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void bindingView(RxProperty<List<TaskListAndDetailEntity>> listRxProperty, TaskListAdapter adapter) {
        recyclerView.setAdapter(adapter);
        RxView.of(recyclerView).bind(listRxProperty, (target, taskListAndDetailEntities) -> {
            onRefresh();
        });
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        super.onItemClick(view, data, position);
        Bundle bundle = new Bundle();
        TaskListAndDetailEntity taskListAndDetailEntity = (TaskListAndDetailEntity) data;
        bundle.putParcelable(Constants.TASKENTITY, taskListAndDetailEntity);
        ISkipActivityUtil.startIntent(getActivity(), TaskDetailActivity.class, bundle);
    }

    private void updateList() {
        mTaskListPresenter.setAdapterLists();
        mTaskListPresenter.notifyData();

        if (mTaskListPresenter.getLists().size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            mTaskListPresenter.setShowFootFlag(false);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    private void query() {
        mTaskListPresenter.getTaskListData(PAGE_COUNT);
        if (page == 0){
            sendHandlerMessage(mHandler, HandlerValue.QUERY_FAC_VALUE, mTaskListPresenter.getLists());
        }else {
            sendHandlerMessage(mHandler, HandlerValue.UPLOAD_FAC_INFO, mTaskListPresenter.getLists());
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        query();
    }

    @Override
    protected void update() {
        ++page;
        //加载新的数据
        query();
    }
}

