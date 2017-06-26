package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.DeviceInfoEntity;
import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.greedao.WorkRecordTable;
import com.yunwei.wetlandpark.ui.adapter.task.TaskPointListAdapter;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskPointListFragmentPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskPointListFragmentView;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:已完成任务点列表
 * @date 2016/9/27
 * @changeby:
 */
public class TaskPointListFragment extends BaseFragment implements TaskPointListFragmentView {
    private TextView mTaskpointlistNodataTv;
    private RelativeLayout mTaskpointlistNodataRl;
    private RecyclerView mTaskpointlistRv;
    private TaskPointListFragmentPresenter mTaskPointListPresenter;

    @Override
    public void onResume() {
        super.onResume();
        mTaskPointListPresenter.initUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.task_point_list_fragment, container, false);
        initView(rootView);
        mTaskPointListPresenter = new TaskPointListFragmentPresenter(getActivity(),this,this);
        mTaskPointListPresenter.initUI();
        return rootView;
    }

    private void initRecycleview() {
        mTaskpointlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mTaskpointlistRv.setHasFixedSize(true);
        mTaskpointlistRv.setLayoutAnimation(new LayoutAnimationController(
                AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoomin_anim)));
    }

    private void initView(View rootView) {
        mTaskpointlistNodataTv = (TextView) rootView.findViewById(R.id.taskpointlist_nodata_tv);
        mTaskpointlistNodataRl = (RelativeLayout) rootView.findViewById(R.id.taskpointlist_nodata_rl);
        mTaskpointlistRv = (RecyclerView) rootView.findViewById(R.id.taskpointlist_rv);
        initRecycleview();
    }

    @Override
    public void setVisiableView(boolean visiableView) {
        if (visiableView) {
            mTaskpointlistNodataRl.setVisibility(View.VISIBLE);
            mTaskpointlistRv.setVisibility(View.GONE);
        } else {
            mTaskpointlistNodataRl.setVisibility(View.GONE);
            mTaskpointlistRv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setView(String taskPointStatus) {
        if (taskPointStatus.equals(getActivity().getString(R.string.task_point_list_finished))) {
            mTaskpointlistNodataTv.setText(R.string.task_point_list_nodata_finished);
        }else {
            mTaskpointlistNodataTv.setText(R.string.task_point_list_nodata_unfinished);
        }
    }

    @Override
    public void setAdapter(TaskPointListAdapter taskPointListAdapter) {
        mTaskpointlistRv.setAdapter(taskPointListAdapter);
    }

    @Override
    public void goToHisActivity(String lTaskType, WorkRecordTable workRecordTable) {
        if (lTaskType.equals(getString(R.string.task_check))) {
            //巡查
//            Bundle bundle = new Bundle();
//            bundle.putInt(CheckActivity.FROM_CHECK_FLAG, CheckActivity.SHOW_CHECK_VALUE);
//            ISkipActivityUtil.startIntent(getActivity(), CheckActivity.class, bundle);
//            CheckActivity.onStartActvityShowCheckAction(getActivity(),workRecordTable);
            // TODO: 2016/11/22 跳转设施相关界面
        } else {
            //维护
//            Bundle bundle = new Bundle();
//            bundle.putInt(MaintainActivity.FROM_MAINTAIN_FLAG, MaintainActivity.SHOW_MAINTAIN_VALUE);
//            ISkipActivityUtil.startIntent(getActivity(), MaintainActivity.class, bundle);
//            MaintainActivity.onStartActvityShowMaintainAction(getActivity(),workRecordTable);
            // TODO: 2016/11/22 跳转设施相关界面
        }
    }

    @Override
    public void goToActivity(String lTaskType, Facility facility) {
        if (lTaskType.equals(getString(R.string.task_check))) {
            //巡查
//            Bundle bundle = new Bundle();
//            bundle.putInt(CheckActivity.FROM_CHECK_FLAG, CheckActivity.RECORD_CHECK_VALUE);
            DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
            DeviceInfoEntity.Device device = deviceInfoEntity.new Device();
            device.setCode(facility.getCode());
            device.setAddress(facility.getAddress());
            deviceInfoEntity.setItem(device);
            // TODO: 2016/11/22 跳转设施相关界面
//            CheckActivity.onStartActvityRecordCheckAction(getActivity(),deviceInfoEntity,facility.getTaskId(),facility.getTaskPointId());
//            bundle.putSerializable(CheckRecordFragment.FACILITY_VALUE_KEY, deviceInfoEntity);
//            ISkipActivityUtil.startIntent(getActivity(), CheckActivity.class, bundle);
        } else {
            //维护
//            Bundle bundle = new Bundle();
//            bundle.putInt(MaintainActivity.FROM_MAINTAIN_FLAG, MaintainActivity.RECORD_MAINTAIN_VALUE);
            DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
            DeviceInfoEntity.Device device = deviceInfoEntity.new Device();
            device.setCode(facility.getCode());
            device.setAddress(facility.getAddress());
            deviceInfoEntity.setItem(device);
            // TODO: 2016/11/22 跳转设施相关界面
//            MaintainActivity.onStartActvityRecordMaintainAction(getActivity(),deviceInfoEntity,facility.getTaskId(),facility.getTaskPointId());
//            bundle.putSerializable(MaintainRecordFragment.FACILITY_VALUE_KEY, deviceInfoEntity);
//            ISkipActivityUtil.startIntent(getActivity(), MaintainActivity.class, bundle);
        }
    }
}

