package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView;

import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view.task
 * @Description:
 * @date 2016/9/26
 * @changeby:
 */
public interface TaskDetailChildView extends BaseView{
    void setView(TaskListAndDetailEntity taskListAndDetailEntity, String deviceNO);
}
