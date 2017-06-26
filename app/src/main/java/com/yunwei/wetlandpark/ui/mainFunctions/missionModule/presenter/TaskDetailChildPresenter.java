package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskPointListEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailChildView;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view.task
 * @Description:任务详情子详情
 * @changeby:
 */

public class TaskDetailChildPresenter {

    private final int mTaskId;
    private final Context mContext;
    private TaskDetailChildView mTaskDetailView;

    public TaskDetailChildPresenter(Context context, Fragment fragment, TaskDetailChildView taskDetailView) {
        mContext = context;
        mTaskDetailView = taskDetailView;
        mTaskId = fragment.getArguments().getInt(Constants.TASKID);
    }

    public void initUI() {
        String userId = (String) ISpfUtil.getValue(mContext, Constants.ACCOUNT_ID_KEY, "");
        mTaskDetailView.setView(TaskBiz.getInstance().getTaskListAndDetailEntityByTaskId(mTaskId, userId),
                getDeviceNO());
    }

    private String getDeviceNO() {
        String userId = (String) ISpfUtil.getValue(ZNAPPlication.getInstance(), Constants.ACCOUNT_ID_KEY, "");
        List<TaskPointListEntity> taskPointListEntityList =
                TaskBiz.getInstance().getTaskPointListEntityList(mTaskId, userId);
        String deviceNO = "";
        for (int i = 0; i < taskPointListEntityList.size(); i++) {
            if (i == 0) {
                deviceNO += taskPointListEntityList.get(i).getTaskPointId();
            } else {
                deviceNO += "," + taskPointListEntityList.get(i).getTaskPointId();
            }
        }
        return deviceNO;
    }
}
