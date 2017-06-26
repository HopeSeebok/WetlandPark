package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.task.FragmentAdapter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui.TaskDetailChildFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui.TaskLocationFileChildFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui.TaskTimeLineChildFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:任务详情
 * @date 2016/9/27
 * @changeby:
 */

public class TaskDetailPresenter {

    private TaskListAndDetailEntity mTaskListAndDetailEntity;
    private final TaskDetailView mTaskDetailView;
    private final Activity mActivity;
    private final FragmentManager mFragmentManager;

    public TaskDetailPresenter(Activity activity, TaskDetailView taskDetailView, FragmentManager supportFragmentManager) {
        mActivity=activity;
        mTaskListAndDetailEntity = activity.getIntent().getExtras().getParcelable(Constants.TASKENTITY);
        mTaskDetailView=taskDetailView;
        mFragmentManager=supportFragmentManager;
    }

    public void changeStaus(String message, final int staus, final String failMessage) {
//        if (staus!=Constants.TASK_STATUS.HAS_BEEN_RETURNED.getValue()) {
//            DialogFactory.showMsgDialog(mActivity, mActivity.getString(R.string.dialog_tips),message ,mActivity.getString(R.string.dialog_sure),
//                    mActivity.getString(R.string.dialog_cancel_), v -> {
//                        changeStatusRequest(staus, "",failMessage);
//                    }, v -> {
//                    });
//        }else {
//            new GiveBackDialog(mActivity, bascReason -> changeStatusRequest( staus, bascReason, failMessage)).show();
//        }

    }

    private void changeStatusRequest(final int staus,String backReason, final String failMessage) {
        try {
//            new TaskChangeStatusRequest().changeStatus(mActivity, String.valueOf(mTaskListAndDetailEntity.getTaskId()),staus,backReason, new TaskStausLinstener() {
//                @Override
//                public void changeStausSuccess() {
//                    mTaskListAndDetailEntity.setTaskStatus(staus);
//                    mTaskDetailView.setStatusBt(mTaskListAndDetailEntity);
//                    if (staus==Constants.TASK_STATUS.HAS_BEEN_RETURNED.getValue()) {
//                        DialogFactory.warningDialog(mActivity, "提示", "退回成功", v -> {
//                        });
//                    }
//                }
//                @Override
//                public void changeStausFailure() {
//                    Toast.makeText(mActivity,failMessage,Toast.LENGTH_SHORT).show();
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initUI() {
        mTaskDetailView.setView(mTaskListAndDetailEntity);
//        mTaskDetailView.setStatusBt(mTaskListAndDetailEntity);
    }

    public void goToActivity() {
        mTaskDetailView.goToActivity(mTaskListAndDetailEntity.getTaskId(), mTaskListAndDetailEntity.getTaskType());
    }

    public void setAdapter() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TaskDetailChildFragment());
        fragments.add(new TaskTimeLineChildFragment());
        fragments.add(new TaskLocationFileChildFragment());
        mTaskDetailView.setAdapter(new FragmentAdapter(mFragmentManager,fragments, mTaskListAndDetailEntity.getTaskId()));
    }

    public void setTaskEntityStatus(int taskEntityStatus) {
        mTaskListAndDetailEntity.setTaskStatus(taskEntityStatus);
    }
}
