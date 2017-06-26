package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskDetailChildPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailChildView;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:子详情
 * @date 2016/9/5
 * @changeby:
 */

public class TaskDetailChildFragment extends BaseFragment implements TaskDetailChildView {

    private TextView mTaskFormTypeTv;
    private TextView mTaskDescTv;
    private TextView mOrderPeopleTv;
    private TextView mDealPeopleTv;
    private TaskDetailChildPresenter mTaskDetailPresenter;
    private TextView mTaskdetailchildfragmentRemarkTv;
    private ExpandableTextView mExpTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.task_detail_child_fragment, container, false);
        initView(rootView);
        initUI();
        return rootView;
    }

    private void initUI() {
        mTaskDetailPresenter.initUI();
    }

    private void initView(View rootView) {
        mTaskFormTypeTv = (TextView) rootView.findViewById(R.id.task_form_type_tv);
        mTaskDescTv = (TextView) rootView.findViewById(R.id.task_desc_tv);
        mOrderPeopleTv = (TextView) rootView.findViewById(R.id.order_people_tv);
        mDealPeopleTv = (TextView) rootView.findViewById(R.id.deal_people_tv);
        mExpTv  = (ExpandableTextView) rootView.findViewById(R.id.expand_text_view);
        mTaskdetailchildfragmentRemarkTv = (TextView) rootView.findViewById(R.id.taskdetailchildfragment_remark_tv);
        mTaskDetailPresenter = new TaskDetailChildPresenter(getActivity(),this, this);
    }

    @Override
    public void setView(TaskListAndDetailEntity taskListAndDetailEntity, String deviceNO) {
        mTaskFormTypeTv.setText(taskListAndDetailEntity.getTaskFormTypeString(taskListAndDetailEntity.getTaskFormType()));
        mTaskDescTv.setText(taskListAndDetailEntity.getTaskNote());
        mOrderPeopleTv.setText(taskListAndDetailEntity.getOrderPeople());
        mDealPeopleTv.setText(taskListAndDetailEntity.getExcutor());
        mTaskdetailchildfragmentRemarkTv.setText(taskListAndDetailEntity.getTaskRemark());
        mExpTv.setText(deviceNO);
    }


}
