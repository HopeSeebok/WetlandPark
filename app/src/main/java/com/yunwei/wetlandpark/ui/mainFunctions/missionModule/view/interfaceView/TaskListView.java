package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView;

import com.ogaclejapan.rx.binding.RxProperty;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.task.TaskListAdapter;
import com.yunwei.wetlandpark.ui.view.BaseView;

import java.util.List;

/**
 * Created by zls on 2016/9/22.
 *
 * @Description:任务列表的view接口
 */

public interface TaskListView extends BaseView{
    void setTaskListAdapter(TaskListAdapter taskListAdapter);

    void setNodataViewVisiable(boolean visiableView);

    void bindingView(RxProperty<List<TaskListAndDetailEntity>> listRxProperty, TaskListAdapter adapter);
}
