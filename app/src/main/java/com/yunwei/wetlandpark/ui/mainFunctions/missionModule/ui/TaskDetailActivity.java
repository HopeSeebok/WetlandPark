package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.task.TaskStatus;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.task.FragmentAdapter;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskDetailPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailView;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.view.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:任务详情
 * @date 2016/9/27
 * @changeby:
 */

public class TaskDetailActivity extends BaseActivity implements View.OnClickListener, TaskDetailView {

    private NoScrollViewPager mTaskDetailVp;
    private Button mAcceptBt;
    private Button mProcessingBt;
    private Button mCurrentBt;
    private Button mGiveBackBt;
    private Button mReportBt;
    private TextView mTitleTv;
    private TextView mDescriptionTv;
    private TextView mTaskAcceptTimeTv;
    private TextView mTaskIdTv;
    private ImageView mStatusIv;
    private TaskDetailPresenter mTaskDetailPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_activity);
        initView();
        mTaskDetailPresenter.initUI();
    }

    private void initView() {
        mTaskDetailPresenter = new TaskDetailPresenter(this, this, getSupportFragmentManager());

        mTaskDetailVp = (NoScrollViewPager) findViewById(R.id.task_detail_vp);
        mTaskDetailPresenter.setAdapter();

        SlidingTabLayout mTab = (SlidingTabLayout) findViewById(R.id.id_switch_tab_st);
        if (mTab != null) {
            mTab.setViewPager(mTaskDetailVp);
        }

        mAcceptBt = (Button) findViewById(R.id.accept_bt);
        if (mAcceptBt != null) {
            mAcceptBt.setOnClickListener(this);
        }
        mProcessingBt = (Button) findViewById(R.id.processing_bt);
        if (mProcessingBt != null) {
            mProcessingBt.setOnClickListener(this);
        }
        mCurrentBt = (Button) findViewById(R.id.current_bt);
        if (mCurrentBt != null) {
            mCurrentBt.setOnClickListener(this);
        }
        mGiveBackBt = (Button) findViewById(R.id.giveback_bt);
        if (mGiveBackBt != null) {
            mGiveBackBt.setOnClickListener(this);
        }
        mReportBt = (Button) findViewById(R.id.report_bt);
        if (mReportBt != null) {
            mReportBt.setOnClickListener(this);
        }
        Button mCheckBt = (Button) findViewById(R.id.check_bt);
        if (mCheckBt != null) {
            mCheckBt.setOnClickListener(this);
        }

        mTitleTv = (TextView) findViewById(R.id.mTitleTv);
        mDescriptionTv = (TextView) findViewById(R.id.mDescriptionTv);
        mTaskAcceptTimeTv = (TextView) findViewById(R.id.mTaskAcceptTimeTv);
        mTaskIdTv = (TextView) findViewById(R.id.mTaskIdTv);
        mStatusIv = (ImageView) findViewById(R.id.mStatusIv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.accept_bt:
//                mTaskDetailPresenter.changeStaus(getString(R.string.task_list_accept_tips), Constants.TASK_STATUS.HAVE_ORDER.getValue(), getString(R.string.task_list_accept_failure));
//                break;
//            case R.id.current_bt:
//                mTaskDetailPresenter.changeStaus(getString(R.string.task_list_current_tips), Constants.TASK_STATUS.BEING_PROCESSED.getValue()
//                        , getString(R.string.task_list_current_failure));
//                break;
//            case R.id.giveback_bt:
//                mTaskDetailPresenter.changeStaus(getString(R.string.task_list_back_tips), Constants.TASK_STATUS.HAS_BEEN_RETURNED.getValue(), getString(R.string.task_list_back_failure));
//                break;
            case R.id.report_bt:
                mTaskDetailPresenter.goToActivity();
                break;
        }
    }

    @Override
    public void setView(TaskListAndDetailEntity taskListAndDetailEntity) {
        setToolbarTitle(R.string.task_detail);

        mTitleTv.setText(taskListAndDetailEntity.getTaskTypeString(taskListAndDetailEntity.getTaskType()));
        mDescriptionTv.setText(taskListAndDetailEntity.getTaskNote());
        mTaskAcceptTimeTv.setText(taskListAndDetailEntity.getProcessingTime());
        mTaskIdTv.setText("ID:" + taskListAndDetailEntity.getTaskId());

        if (taskListAndDetailEntity.getTaskLevel().equals(Constants.TASK_LEVEL.NORMAL.getValue())) {
            mStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_ordinary);
        } else if (taskListAndDetailEntity.getTaskLevel().equals(Constants.TASK_LEVEL.IMPORTANT.getValue())) {
            mStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_important);
        } else {
            mStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_urgent);
        }
    }

    @Override
    public void setAdapter(FragmentAdapter fragmentAdapter) {
        mTaskDetailVp.setAdapter(fragmentAdapter);
    }

    @Override
    public void setStatusBt(TaskListAndDetailEntity taskListAndDetailEntity) {
//        //未派发、已派发、处理中、已撤销、已接单、已退回、已完成、已审核
//        switch (taskListAndDetailEntity.getTaskStatus()) {
//            //未派发
//            case 0:
//            //已派发
//            case 1:
//                mAcceptBt.setVisibility(View.VISIBLE);
//                mGiveBackBt.setVisibility(View.VISIBLE);
//                mProcessingBt.setVisibility(View.GONE);
//                mCurrentBt.setVisibility(View.GONE);
//                mReportBt.setVisibility(View.GONE);
//                break;
//            //处理中
//            case 2:
//                mAcceptBt.setVisibility(View.GONE);
//                mProcessingBt.setVisibility(View.VISIBLE);
//                mCurrentBt.setVisibility(View.GONE);
//                mReportBt.setVisibility(View.VISIBLE);
//                break;
//            //已撤销
//            case 3:
//                mAcceptBt.setVisibility(View.GONE);
//                mGiveBackBt.setVisibility(View.GONE);
//                mProcessingBt.setVisibility(View.GONE);
//                mCurrentBt.setVisibility(View.GONE);
//                mReportBt.setVisibility(View.GONE);
//                break;
//            //已接单
//            case 4:
//                mAcceptBt.setVisibility(View.GONE);
//                mGiveBackBt.setVisibility(View.VISIBLE);
//                mProcessingBt.setVisibility(View.GONE);
//                mCurrentBt.setVisibility(View.VISIBLE);
//                mReportBt.setVisibility(View.GONE);
//                break;
//            //已退回
//            case 5:
//                mAcceptBt.setVisibility(View.GONE);
//                mGiveBackBt.setVisibility(View.VISIBLE);
//                mProcessingBt.setVisibility(View.GONE);
//                mCurrentBt.setVisibility(View.GONE);
//                mGiveBackBt.setBackgroundResource(R.drawable.btn_press);
//                mGiveBackBt.setEnabled(false);
//                mGiveBackBt.setText(R.string.task_list_havebeen_back);
//                mReportBt.setVisibility(View.GONE);
//                break;
//            //已完成
//            case 6:
//            //已审核
//            case 7:
//                mAcceptBt.setVisibility(View.GONE);
//                mGiveBackBt.setVisibility(View.GONE);
//                mProcessingBt.setVisibility(View.GONE);
//                mCurrentBt.setVisibility(View.GONE);
//                mReportBt.setVisibility(View.VISIBLE);
//                mReportBt.setText(R.string.task_look);
//                break;
//        }
//        if (taskListAndDetailEntity.getTaskNote().contains(getString(R.string.task_detail_plan))) {
//            mGiveBackBt.setVisibility(View.GONE);
//        }
    }

    @Override
    public void goToActivity(int taskId, int taskType) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TASKID, taskId);
        bundle.putInt(Constants.TASKTYPE, taskType);
        ISkipActivityUtil.startIntent(this, TaskPointListActivity.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTaskStatus(TaskStatus taskStatus) {
        mTaskDetailPresenter.setTaskEntityStatus(6);
        mTaskDetailPresenter.initUI();
    }
}
