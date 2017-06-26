package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model;

import android.content.Context;

import com.esri.core.geometry.Point;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.task.TaskStatus;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntityDao;
import com.yunwei.wetlandpark.greedao.TaskPointListEntity;
import com.yunwei.wetlandpark.greedao.TaskPointListEntityDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.entity.MaintainEntity;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.biz.impl.task
 * @Description:懒汉式单例 任务业务处理类
 * @date 2016/11/18
 * @changeby:
 */
public class TaskBiz {
    private static TaskBiz instance = null;

    public static synchronized TaskBiz getInstance() {
        if (instance == null) {
            instance = new TaskBiz();
        }
        return instance;
    }

    //--------------------------------------从数据库获取任务详情相关内容----------------------------------------------------------------------
    public List<TaskListAndDetailEntity> getTaskListAndDetailEntityListBeingProcessed(String userId) {
        return ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().
                where(TaskListAndDetailEntityDao.Properties.TaskStatus.eq(Constants.BEING_PROCESSED),
                        TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list();
    }

    public List<TaskListAndDetailEntity> getTaskListAndDetailEntityList(String userId) {
        return ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().
                where(TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list();
    }

    public TaskListAndDetailEntity getTaskListAndDetailEntityByTaskId(int taskId, String userId) {
        return ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().
                where(TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                        TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list().get(0);
    }

    public List<TaskListAndDetailEntity> getTaskListAndDetailEntityListByTaskId(int taskId, String userId) {
        return ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().
                where(TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                        TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list();
    }

    public List<String> getTimeLine(int taskId, String userId) {
        TaskListAndDetailEntity taskListAndDetailEntity = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao()
                .queryBuilder().where(TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                        TaskListAndDetailEntityDao.Properties.UserId.eq(userId)).list().get(0);
        ArrayList<String> lList = new ArrayList<>();
        lList.add(taskListAndDetailEntity.getProcessingTime());
        lList.add(taskListAndDetailEntity.getAuditTime());
        lList.add(taskListAndDetailEntity.getFinishTime());
        return lList;
    }


    //----------------------------------------从数据库获取任务点列表内容---------------------------------------------------------------------
    public List<TaskPointListEntity> getTaskPointListEntityList(int taskId, String userId) {
        return ZNAPPlication.getDaoSession().getTaskPointListEntityDao().queryBuilder().
                where(TaskPointListEntityDao.Properties.TaskId.eq(taskId),
                        TaskPointListEntityDao.Properties.UserId.eq(userId)).list();
    }

    public ArrayList<Point> getPoints(int taskId, String userId) {
        ArrayList<Point> lPoints = new ArrayList<>();
        List<TaskPointListEntity> taskPointListEntities = getTaskPointListEntityList(taskId, userId);
        for (int i = 0; i < taskPointListEntities.size(); i++) {
//            MPointEntity mPointEntity = ILngLatMercator.lonLat2WebMercator(taskPointListEntities.get(i).getLng(),
//                    taskPointListEntities.get(i).getLat());
            lPoints.add(new Point(taskPointListEntities.get(i).getLng(), taskPointListEntities.get(i).getLat()));
        }
        return lPoints;
    }

    public String getImages(int taskId, String userId) {
        String images = "";
        List<TaskPointListEntity> taskPointListEntities = getTaskPointListEntityList(taskId, userId);
        for (int i = 0; i < taskPointListEntities.size(); i++) {
            if (i == 0) {
                images = taskPointListEntities.get(i).getImgs();
            } else {
                images += "," + taskPointListEntities.get(i).getImgs();
            }
        }
        return images;
    }

    public List<TaskPointListEntity> getTaskPointListEntityList(int taskId, String userId, boolean isFinish) {
        return ZNAPPlication.getDaoSession().getTaskPointListEntityDao().queryBuilder()
                .where(TaskPointListEntityDao.Properties.TaskId.eq(taskId),
                        TaskPointListEntityDao.Properties.UserId.eq(userId),
                        TaskPointListEntityDao.Properties.IsCompleted.eq(isFinish)).list();
    }

//    public List<WorkRecordTable> getWorkRecordTable(String workTaskPointId){
//        return ZNAPPlication.getInstance().getDaoSession().get().queryBuilder()
//                .where(WorkRecordTableDao.Properties.TaskID.eq(mTaskId),
//                        WorkRecordTableDao.Properties.TaskPointID.eq(workTaskPointId))
//                .list();
//        return null;
//    }
//---------------------------------------网络请求获取任务相关信息----------------------------------------------------------------------

    public Observable getTaskDetailObservable(final Context context, final int taskId) {
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        new MaintainDetailRequest().detailRequest(context, taskId, sub);
                    }
                });
    }

    public Observable changeEntity(String userId, Observable<String> observable) {
        return observable.map(s -> {
            MaintainEntity missionEntity = new Gson().fromJson(s, new TypeToken<MaintainEntity>() {
            }.getType());
            MaintainEntity.DataBean dataBean = missionEntity.getData();

            int TaskId = dataBean.getId();
            //任务列表和详情数据存储
            TaskListAndDetailEntity taskListAndDetailEntity = new TaskListAndDetailEntity();
            taskListAndDetailEntity
                    .setUserId(userId)
                    .setTaskId(TaskId)
                    .setExcutor(dataBean.getHandlerName())
                    .setOrderPeople(dataBean.getUser())
                    .setTaskLevel(dataBean.getMissionLevel())
                    .setTaskNote(dataBean.getNote())
                    .setTaskRemark(dataBean.getRemark())
                    .setTaskType(dataBean.getMissionType())
                    .setTaskFormType(dataBean.getMissionFromType())
                    .setTaskMold(dataBean.getMissionMold())
                    .setTaskStatus(dataBean.getMissionStatus())
                    .setProcessingTime(dataBean.getCreateTime())
                    .setAuditTime("")
                    .setFinishTime("")
                    .setExpires(dataBean.getExpires())
                    .setHistory(false);
            ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().insert(taskListAndDetailEntity);

            //任务点列表信息
            List<MaintainEntity.DataBean.ItemsBean> itemsBeanList = dataBean.getItems();
            TaskPointListEntity taskPointListEntity = new TaskPointListEntity();
            for (int i = 0; i < itemsBeanList.size(); i++) {
                MaintainEntity.DataBean.ItemsBean itemsBean = itemsBeanList.get(i);
                taskPointListEntity
                        .setTaskId(TaskId)
                        .setUserId(userId)
                        .setTaskPointId(itemsBean.getId())
                        .setTaskPointAddr(itemsBean.getTargetAddr())
                        .setTaskPointKind(itemsBean.getTargetKind())
                        .setTaskPointNo(itemsBean.getTargetCode())
                        .setTaskPointType(itemsBean.getTargetType())
                        .setLng(itemsBean.getTargetLng())
                        .setLat(itemsBean.getTargetLat())
                        .setImgs(getImages(itemsBean.getImgs()))
                        .setCompleted(false);
                ZNAPPlication.getDaoSession().getTaskPointListEntityDao().insert(taskPointListEntity);
            }
            return "";
        });
    }

    private String getImages(List<MaintainEntity.DataBean.ItemsBean.ImgsBean> imgs) {
        if (imgs == null) {
            return "";
        }
        String image = "";
        for (int i = 0; i < imgs.size(); i++) {
            if (i == 0) {
                image = imgs.get(i).getUrl();
            } else {
                image += "," + imgs.get(i).getUrl();
            }
        }
        return image;
    }

    //----------------------------------------网络请求改变任务状态---------------------------------------------------------------------
//    public Observable changeTaskStatusRequestObservable(final Context context, final String taskId, final int status, final String backReason) {
//        return Observable.create(
//                new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> sub) {
//                        new TaskChangeStatusRequest().changeStatus((Activity) context, taskId, status, backReason, sub);
//                        sub.onCompleted();
//                    }
//                }
//        );
//    }

    //----------------------------------------本地数据库改变任务状态---------------------------------------------------------------------
    public void changeTaskStatusSqlite(int taskId, int status) {
        String userId = (String) ISpfUtil.getValue(ZNAPPlication.getInstance().getContext(), Constants.ACCOUNT_ID_KEY, "");
        //处理中、待审核、已完成、已终止

        TaskListAndDetailEntity taskListAndDetailEntity = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().where(
                TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                TaskListAndDetailEntityDao.Properties.UserId.eq(userId)
        ).list().get(0);
        taskListAndDetailEntity.setTaskStatus(status);
        switch (status) {
            //处理中
            case 1:
                taskListAndDetailEntity.setProcessingTime(IDateTimeUtils.getDateNow());
                break;
            //待审核
            case 2:
                taskListAndDetailEntity.setAuditTime(IDateTimeUtils.getDateNow());
                break;
            //已完成
            case 3:
                taskListAndDetailEntity.setHistory(true);
                taskListAndDetailEntity.setHistoryTime(IDateTimeUtils.getDateNow());
                taskListAndDetailEntity.setFinishTime(IDateTimeUtils.getDateNow());
                break;
            //已终止
            case 4:
                taskListAndDetailEntity.setHistory(true);
                taskListAndDetailEntity.setHistoryTime(IDateTimeUtils.getDateNow());
                break;
        }
        ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().update(taskListAndDetailEntity);
        EventBus.getDefault().post(new MessageBean());
    }

    /**
     * 改变任务状态
     * @param taskId      任务id
     * @param taskPointId 任务点id
     */
    public void changeTaskStatus(String taskId, String taskPointId) {
        String userId = (String) ISpfUtil.getValue(ZNAPPlication.getInstance(), Constants.ACCOUNT_ID_KEY, "");
        List<TaskPointListEntity> taskPointListEntities = ZNAPPlication.getDaoSession().getTaskPointListEntityDao()
                .queryBuilder()
                .where(TaskPointListEntityDao.Properties.TaskId.eq(taskId),
                        TaskPointListEntityDao.Properties.TaskPointId.eq(taskPointId),
                        TaskPointListEntityDao.Properties.UserId.eq(userId)
                ).list();

        for (int i = 0; i < taskPointListEntities.size(); i++) {
            TaskPointListEntity workTaskPointsEntity = taskPointListEntities.get(i);
            workTaskPointsEntity.setCompleted(true);
            ZNAPPlication.getDaoSession().getTaskPointListEntityDao().update(workTaskPointsEntity);
            int CurrentTaskId = workTaskPointsEntity.getTaskId();
            setTaskComplete(CurrentTaskId, userId);
        }
    }

    private void setTaskComplete(int taskId, String userId) {
        List<TaskPointListEntity> taskPointListEntities = ZNAPPlication.getDaoSession().getTaskPointListEntityDao()
                .queryBuilder()
                .where(TaskPointListEntityDao.Properties.TaskId.eq(taskId),
                        TaskPointListEntityDao.Properties.UserId.eq(userId)
                ).list();
        boolean taskComplete = true;
        for (int i = 0; i < taskPointListEntities.size(); i++) {
            if (!taskPointListEntities.get(i).getCompleted()) {
                taskComplete = false;
            }
        }
        //如果所有任务点完成，则完成整个任务
        if (taskComplete) {
            EventBus.getDefault().post(new TaskStatus());
            TaskListAndDetailEntity taskListAndDetailEntitie = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao()
                    .queryBuilder()
                    .where(TaskListAndDetailEntityDao.Properties.TaskId.eq(taskId),
                            TaskListAndDetailEntityDao.Properties.UserId.eq(userId)
                    ).list().get(0);
            taskListAndDetailEntitie.setTaskStatus(Constants.CHECK_PENDING);
            taskListAndDetailEntitie.setAuditTime(IDateTimeUtils.getDateNow());
            ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().update(taskListAndDetailEntitie);

            EventBus.getDefault().post(new MessageBean());
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    //回收TaskBiz单例
    public void recycleTaskBiz() {
        instance = null;
    }
}
