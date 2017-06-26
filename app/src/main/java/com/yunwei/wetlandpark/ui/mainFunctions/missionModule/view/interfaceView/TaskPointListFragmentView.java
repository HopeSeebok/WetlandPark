package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView;


import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.greedao.WorkRecordTable;
import com.yunwei.wetlandpark.ui.adapter.task.TaskPointListAdapter;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view.task
 * @Description:
 * @date 2016/9/27
 * @changeby:
 */

public interface TaskPointListFragmentView extends BaseView {
    void setView(String mTaskPointStatus);
    void setVisiableView(boolean visiableView);
    void setAdapter(TaskPointListAdapter taskPointListAdapter);

    void goToHisActivity(String lTaskType, WorkRecordTable workRecordTable);
    void goToActivity(String lTaskType, Facility facility);
}
