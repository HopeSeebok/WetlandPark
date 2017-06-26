package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskPointListEntity;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.greedao.TroubleShooterTableDao;
import com.yunwei.wetlandpark.ui.adapter.task.TaskPointListAdapter;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.ShootTroubleActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskPointListFragmentView;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:任务点列表
 * @date 2016/9/27
 * @changeby:
 */
public class TaskPointListFragmentPresenter {

    private final TaskPointListAdapter mAdapter;
    private final TaskPointListFragmentView mTaskPointListFragmentView;
    private final String mTaskPointStatus;
    private final int mTaskId;
    private final String mUserId;

    public TaskPointListFragmentPresenter(final Activity activity, TaskPointListFragmentView taskPointListFragmentView, Fragment fragment) {
        mTaskPointStatus=fragment.getArguments().getString(Constants.TASKPOINTLISTSTATUS);
        mTaskId=fragment.getArguments().getInt(Constants.TASKID);
        mTaskPointListFragmentView = taskPointListFragmentView;
        mUserId= (String) ISpfUtil.getValue(activity,Constants.ACCOUNT_ID_KEY,"");
        final List<TaskPointListEntity> taskPointListEntities;
        TaskListAndDetailEntity taskListAndDetailEntity = TaskBiz.getInstance().getTaskListAndDetailEntityByTaskId(mTaskId, mUserId);

        mAdapter = new TaskPointListAdapter(activity);
        taskPointListEntities = getWorkTaskPointsEntities(activity);
        mAdapter.setTaskEntitys(taskPointListEntities);
        mAdapter.setOnItemClickListener((view, data, position) -> {
            int taskStatus = taskListAndDetailEntity.getTaskStatus();
            switch (taskStatus) {
                case 1://处理中
                    //  2016/12/6 跳转维护界面
                    ShootTroubleActivity.startActivityMaintainDevice(activity,
                            taskPointListEntities.get(position).getTaskId(),
                            taskPointListEntities.get(position).getTaskPointId());
                    break;
                case 2://审核中
                case 3://已完成
                    List<TroubleShooterTable> troubleShooterTables = ZNAPPlication.getDaoSession().getTroubleShooterTableDao().queryBuilder().where(
                            TroubleShooterTableDao.Properties.MissionDetailID.eq(taskPointListEntities.get(position).getTaskPointId())
                    ).list();
                    if (troubleShooterTables.size()!=0) {
                        //  2016/12/6 跳转维护界面
                        ShootTroubleActivity.startActivityMaintainDevice(activity,troubleShooterTables.get(0));
                    }
                    break;
                case 4://已终止
                    break;
            }
        });
    }

    private List<TaskPointListEntity> getWorkTaskPointsEntities(Activity activity) {
        List<TaskPointListEntity> lWorkTaskPoints;
        if (mTaskPointStatus.equals(activity.getString(R.string.task_point_list_unfinished))) {
            lWorkTaskPoints = TaskBiz.getInstance().getTaskPointListEntityList(mTaskId,mUserId,false);
        }else {
            lWorkTaskPoints = TaskBiz.getInstance().getTaskPointListEntityList(mTaskId,mUserId,true);
        }
        return lWorkTaskPoints;
    }

    public void initUI() {
        mTaskPointListFragmentView.setView(mTaskPointStatus);
        mTaskPointListFragmentView.setAdapter(mAdapter);
    }
}
