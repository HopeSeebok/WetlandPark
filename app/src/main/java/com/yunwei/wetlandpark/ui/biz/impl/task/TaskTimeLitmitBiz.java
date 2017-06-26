package com.yunwei.wetlandpark.ui.biz.impl.task;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.biz.impl.task
 * @Description:
 * @date 2016/11/3
 * @changeby:
 */

public class TaskTimeLitmitBiz {
    public void compareTime(Context context) {
        boolean lIsOverdue=false;
//        List<ItemsEntity> itemsEntities = DbCore.getDaoSession(context).getItemsEntityDao().queryBuilder().list();
//        for (int i = 0; i < itemsEntities.size(); i++) {
//            ItemsEntity itemsEntity = itemsEntities.get(i);
//            if (IDateTimeUtils.TimeCompareIsDate1Big(IDateTimeUtils.getDateNow(), itemsEntity.getRecycleTime())) {
//                new TaskChangeStatusRequest().changeStatus((Activity) context, itemsEntity.getTaskId(), Constants.YTH, "任务过期",null);
//                lIsOverdue=true;
//            }
//        }

        if (lIsOverdue) {
            Toast.makeText(context, "您有任务过期了", Toast.LENGTH_SHORT).show();
        }
    }
}
