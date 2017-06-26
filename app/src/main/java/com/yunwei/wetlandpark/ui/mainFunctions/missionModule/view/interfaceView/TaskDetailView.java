package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView;

import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.task.FragmentAdapter;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view.task
 * @Description:
 * @date 2016/9/27
 * @changeby:
 */

public interface TaskDetailView {
    void setView(TaskListAndDetailEntity taskListAndDetailEntity);
    void setAdapter(FragmentAdapter fragmentAdapter);
    void setStatusBt(TaskListAndDetailEntity taskListAndDetailEntity);

    void goToActivity(int taskId, int taskType);
}
