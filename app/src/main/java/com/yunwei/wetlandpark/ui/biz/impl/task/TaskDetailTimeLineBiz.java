package com.yunwei.wetlandpark.ui.biz.impl.task;

import android.content.Context;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntityDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl.task
 * @Description:获取时间轴信息
 * @date 2016/9/26
 * @changeby:
 */

public class TaskDetailTimeLineBiz {

    public List<String> getTimeLine(Context context, int taskId){
        String userId= (String) ISpfUtil.getValue(context, Constants.ACCOUNT_ID_KEY,"");
        TaskListAndDetailEntity taskListAndDetailEntity= ZNAPPlication.getInstance().getDaoSession().getTaskListAndDetailEntityDao()
                .queryBuilder().where(TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                        TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list().get(0);
        ArrayList<String> lList = new ArrayList<>();
//        lList.add(taskListAndDetailEntity.getOrderTime());
//        lList.add(taskListAndDetailEntity.getReceiptTime());
        lList.add(taskListAndDetailEntity.getProcessingTime());
        lList.add(taskListAndDetailEntity.getFinishTime());
        return lList;
    }
}
