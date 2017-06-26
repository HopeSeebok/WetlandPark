package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.adapter.task.TaskDetailTimeLineAdapter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskDetailTimeLineView;
import com.yunwei.wetlandpark.utils.ISpfUtil;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:时间轴
 * @date 2016/9/26
 * @changeby:
 */

public class TaskDetailTimeLinePresenter {

    private  TaskDetailTimeLineView mTaskDetailTimeLineView;
    private TaskDetailTimeLineAdapter mAdapter;
    private  int mTaskId;

    public TaskDetailTimeLinePresenter(Fragment fragment, Context context, TaskDetailTimeLineView taskDetailTimeLineView) {
        mTaskId = fragment.getArguments().getInt(Constants.TASKID);
        mTaskDetailTimeLineView=taskDetailTimeLineView;
        mAdapter = new TaskDetailTimeLineAdapter(context);
        setData(context);
    }

    public void setData(Context context) {
        String userId= (String) ISpfUtil.getValue(context, Constants.ACCOUNT_ID_KEY,"");
        mAdapter.setTimeLineEntitys(TaskBiz.getInstance().getTimeLine(mTaskId,userId));
    }

    public void initUI() {
        mTaskDetailTimeLineView.setAdapter(mAdapter);
    }


}
