package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskLocationFileChildView;
import com.yunwei.wetlandpark.utils.ISpfUtil;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:位置和附件
 * @date 2016/9/27
 * @changeby:
 */

public class TaskLocationFileChildPresenter {

    private final TaskLocationFileChildView mTaskLocationFileChildView;
    private final int mTaskId;
    private final Context mContext;

    public TaskLocationFileChildPresenter(Context context,Fragment fragment, TaskLocationFileChildView taskLocationFileChildView) {
        mContext=context;
        mTaskLocationFileChildView = taskLocationFileChildView;
        mTaskId = fragment.getArguments().getInt(Constants.TASKID);
    }

    public void initUI() {
        String userId = (String) ISpfUtil.getValue(mContext, Constants.ACCOUNT_ID_KEY, "");
        mTaskLocationFileChildView.setView(
                TaskBiz.getInstance().getPoints(mTaskId, userId),
                TaskBiz.getInstance().getImages(mTaskId, userId)
        );
    }
}
