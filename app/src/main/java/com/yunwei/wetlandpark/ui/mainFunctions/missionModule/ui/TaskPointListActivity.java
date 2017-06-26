package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.SwitchMultiButton;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskPointListPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskPointListView;

import java.util.Arrays;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:任务点列表
 * @date 2016/9/27
 * @changeby:
 */
public class TaskPointListActivity extends BaseActivity implements TaskPointListView, View.OnClickListener {

    private SwitchMultiButton mTaskpointlistSmbt;
    private FrameLayout mTaskpointlistContainer;
    private TaskPointListPresenter mTaskPointListPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        if (mTaskPointListPresenter!=null) {
            mTaskPointListPresenter.initUI();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_point_list_activity);
        initView();
        mTaskPointListPresenter = new TaskPointListPresenter(this, this);
        mTaskPointListPresenter.initUI();
    }

    private void initView() {
        mTaskpointlistContainer = (FrameLayout) findViewById(R.id.taskpointlist_container);
        mTaskpointlistSmbt = (SwitchMultiButton) findViewById(R.id.taskpointlist_smbt);
        if (mTaskpointlistSmbt!=null) {
            mTaskpointlistSmbt.setOnSwitchListener((position, tabText) -> {
                switch (position) {
                    case 0:
                        TaskPointListFragment lTaskPointFinishedListFragment1 = new TaskPointListFragment();
                        goToFragment(lTaskPointFinishedListFragment1,getString(R.string.task_point_list_unfinished));
                        break;
                    case 1:
                        TaskPointListFragment lTaskPointFinishedListFragment2 = new TaskPointListFragment();
                        goToFragment(lTaskPointFinishedListFragment2,getString(R.string.task_point_list_finished));
                        break;
                }
            });
        }
    }

    @Override
    public void setView(int finishedsize, int unfinishedsize) {
        setToolbarTitle(getString(R.string.taskpoint_list));
        mTaskpointlistSmbt.setText(Arrays.asList(getString(R.string.task_point_list_unfinished)+" ("+unfinishedsize+")",
                getString(R.string.task_point_list_finished)+" ("+finishedsize+")"));
        TaskPointListFragment mTaskPointFinishedListFragment = new TaskPointListFragment();
        goToFragment(mTaskPointFinishedListFragment,mTaskpointlistSmbt.getSelectedTab()==0?
                getString(R.string.task_point_list_unfinished):
                getString(R.string.task_point_list_finished));
    }

    private void goToFragment(TaskPointListFragment taskPointFinishedListFragment, String status) {
        mTaskpointlistContainer.removeAllViews();
        Bundle bundle=new Bundle();
        bundle.putString(Constants.TASKPOINTLISTSTATUS,status);
        bundle.putInt(Constants.TASKID,mTaskPointListPresenter.getTaskId());
        taskPointFinishedListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.taskpointlist_container, taskPointFinishedListFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
