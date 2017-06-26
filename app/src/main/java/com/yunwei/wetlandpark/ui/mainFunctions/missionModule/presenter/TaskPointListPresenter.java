package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.app.Activity;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskPointListView;
import com.yunwei.wetlandpark.utils.ISpfUtil;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:任务点列表
 * @date 2016/9/27
 * @changeby:
 */
public class TaskPointListPresenter {

    private final TaskPointListView mTaskPointListView;
    private final int mTaskId;
    private final String mUserId;

    public TaskPointListPresenter(final Activity activity, TaskPointListView taskPointListView) {
        mTaskPointListView = taskPointListView;
        mTaskId = activity.getIntent().getExtras().getInt(Constants.TASKID);
        mUserId = (String) ISpfUtil.getValue(activity, Constants.ACCOUNT_ID_KEY, "");
    }

    public void initUI() {
        mTaskPointListView.setView(TaskBiz.getInstance().getTaskPointListEntityList(mTaskId,mUserId,true).size(),
                TaskBiz.getInstance().getTaskPointListEntityList(mTaskId,mUserId,false).size());
    }

    public int getTaskId() {
        return mTaskId;
    }
}
