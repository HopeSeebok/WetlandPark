package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.task.TaskDetailTimeLineAdapter;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskDetailTimeLinePresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailTimeLineView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:时间轴
 * @date 2016/9/27
 * @changeby:
 */

public class TaskTimeLineChildFragment extends BaseFragment implements TaskDetailTimeLineView {
    private RecyclerView mRecyclerView;
    private TaskDetailTimeLinePresenter mTaskDetailTimeLinePresenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            if (mRecyclerView != null) {
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        } else {
            if (mRecyclerView != null) {
                mRecyclerView.setVisibility(View.GONE);
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.task_timeline_child_fragment, container, false);
        initView(rootView);
        initRecycleview();
        initUI();
        return rootView;
    }

    private void initUI() {
        mTaskDetailTimeLinePresenter.initUI();
    }

    private void initView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mainView);
    }

    private void initRecycleview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mTaskDetailTimeLinePresenter = new TaskDetailTimeLinePresenter(this, getActivity(), this);
    }


    @Override
    public void setAdapter(TaskDetailTimeLineAdapter taskDetailTimeLineAdapter) {
        mRecyclerView.setAdapter(taskDetailTimeLineAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainTaskEntity(MessageBean messageBean) {
        mTaskDetailTimeLinePresenter.setData(getActivity());
        mTaskDetailTimeLinePresenter.initUI();
    }
}
