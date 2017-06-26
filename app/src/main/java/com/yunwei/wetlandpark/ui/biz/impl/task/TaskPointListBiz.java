package com.yunwei.wetlandpark.ui.biz.impl.task;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Toast;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.WorkRecordTable;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback.RevokeTaskPointLinstener;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl.task
 * @Description:任务点的处理
 * @date 2016/9/27
 * @changeby:
 */

public class TaskPointListBiz {

    private final String mUserId;
    private final String mTaskType;
    private final Activity mActivity;
    private String mTaskId;

    public TaskPointListBiz(Activity activity) {
        mActivity=activity;
        mTaskId = activity.getIntent().getStringExtra(Constants.TASKID);
        mUserId = (String) ISpfUtil.getValue(activity, Constants.ACCOUNT_ID_KEY, "");
        mTaskType = activity.getIntent().getStringExtra(Constants.TASKTYPE);
    }

//    public List<WorkTaskPointsEntity> getTaskPoint(){
//        return DbUtil.getWorkTaskPointsService(mActivity).query("where TASK_ID = ? and USER_ID=?", mTaskId, mUserId);
//    }

    public List<WorkRecordTable> getWorkRecordTable(String workTaskPointId){
//        return ZNAPPlication.getDaoSession(mActivity).getWorkRecordTableDao().queryBuilder()
//                .where(WorkRecordTableDao.Properties.TaskID.eq(mTaskId),
//                        WorkRecordTableDao.Properties.TaskPointID.eq(workTaskPointId))
//                .list();
        return null;
    }

//    public List<LocationEntity> getLocations(){
//        return DbUtil.getLocationService(mActivity).query("where TASK_ID = ? and USER_ID=?", mTaskId, mUserId);
//    }

    public String getTaskId(){
        return mTaskId;
    }

    public String getTaskType(){
        return mTaskType;
    }

//    /**
//     * 获取设施信息
//     *
//     */
//    public void getDeviceInfo(final WorkTaskPointsEntity workTaskPointsEntity, final GetDeviceInfoListener getDeviceInfoListener){
//        final Dialog dialog= DialogFactory.createLoadingDialog(mActivity);
//        String url = IConfigValues.DEVICE_INFO_BY_ID  + workTaskPointsEntity.getWorkTaskPointId();
//        String token = (String) ISpfUtil.getValue(mActivity, Constants.ACCESS_TOKEN_KEY, "");
//        LiteHttpManage.Http_Get_Sync(mActivity, token, url, new HttpRequestCallBack() {
//            @Override
//            public void onStart(AbstractRequest request) {
//            }
//            @Override
//            public void onSuccess(Object o, Response response) {
//                DeviceDetail deviceDetail = new Gson().fromJson(o.toString(), new TypeToken<DeviceDetail>() {
//                }.getType());
//
//                Facility lFacility = new Facility();
//                lFacility.setTaskId(mTaskId);
//                lFacility.setTaskPointId(workTaskPointsEntity.getTaskPointId());
//                lFacility.setType(deviceDetail.getItem().getType());
//                lFacility.setCode(deviceDetail.getItem().getCode());
//                lFacility.setAddress(deviceDetail.getItem().getAddress());
//                lFacility.setHdType(workTaskPointsEntity.getHdtype());
//                lFacility.setHdDesc(workTaskPointsEntity.getHddesc());
//                getDeviceInfoListener.success(lFacility);
//            }
//
//            @Override
//            public void onFailure(HttpException e, Response response) {
//                ToastUtil.showToast(mActivity,mActivity.getString(R.string.taskpoint_invalid));
//            }
//
//            @Override
//            public void onEnd(Response response) {
//                DialogFactory.dimissDialog(dialog);
//            }
//        });
//    }
//
//    /**
//     * 获取隐患信息
//     *
//     */
//    public void getHDDeviceInfo(final WorkTaskPointsEntity workTaskPointsEntity, final GetDeviceInfoListener getHDDeviceInfoListener){
//        final Dialog dialog= DialogFactory.createLoadingDialog(mActivity);
//        String url = IConfigValues.GET_HD_INFO  + workTaskPointsEntity.getWorkTaskPointId();
//        String token = (String) ISpfUtil.getValue(mActivity, Constants.ACCESS_TOKEN_KEY, "");
//        LiteHttpManage.Http_Get_Sync(mActivity, token, url, new HttpRequestCallBack() {
//            @Override
//            public void onStart(AbstractRequest request) {
//            }
//            @Override
//            public void onSuccess(Object o, Response response) {
//                HDDetail hdDetail = new Gson().fromJson(o.toString(), new TypeToken<HDDetail>() {
//                }.getType());
//
//                Facility lFacility = new Facility();
//                lFacility.setTaskId(mTaskId);
//                lFacility.setTaskPointId(workTaskPointsEntity.getTaskPointId());
//                lFacility.setType(hdDetail.getItem().getFacilitytype());
//                lFacility.setCode(hdDetail.getItem().getCode());
//                lFacility.setAddress(hdDetail.getItem().getHdplace());
//                lFacility.setHdType(workTaskPointsEntity.getHdtype());
//                lFacility.setHdDesc(workTaskPointsEntity.getHddesc());
//                getHDDeviceInfoListener.success(lFacility);
//            }
//
//            @Override
//            public void onFailure(HttpException e, Response response) {
//                ToastUtil.showToast(mActivity,mActivity.getString(R.string.taskpoint_invalid));
//            }
//            @Override
//            public void onEnd(Response response) {
//                DialogFactory.dimissDialog(dialog);
//            }
//        });
//    }

    /**
     * 撤销任务点
     */
    public void  revokeTaskPoint( final String taskId,final String taskPointId, final RevokeTaskPointLinstener revokeTaskPointLinstener) {
        final Dialog progressDialog = DialogFactory.createLoadingDialog(mActivity);
        String url = IConfigValues.REVOKE_TASK_POINT ;
        JSONObject obj = new JSONObject();
        try {
            obj.put("TaskPointId", taskPointId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBody body = new JsonBody(obj.toString());
        String token = (String) ISpfUtil.getValue(mActivity, Constants.ACCESS_TOKEN_KEY, "");
        LiteHttpManage.Http_Post_Sync(mActivity, token, url, body, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {
            }

            @Override
            public void onSuccess(Object o, Response response) {
                if (response.getHttpStatus().getCode() == 200) {
//                    changeTaskStatus(taskId,taskPointId,true);
                    revokeTaskPointLinstener.revokeSuccess();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                Toast.makeText(mActivity, R.string.revoke_task_point_failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEnd(Response response) {
                DialogFactory.dimissDialog(progressDialog);
            }
        });
    }

    /**
     * @param taskId 任务id
     * @param taskPointId 任务点id
     * @param isGiveback 是否是退回操作
     * 改变任务状态
     */
//    public void changeTaskStatus( String taskId, String taskPointId,boolean isGiveback) {
//        String userId = (String) ISpfUtil.getValue(mActivity, Constants.ACCOUNT_ID_KEY, "");
//        WorkTaskPointsEntity workTaskPointsEntity = DbUtil.getWorkTaskPointsService(mActivity).query(" where TASK_ID = ? and USER_ID = ? " +
//                        "and TASK_POINT_ID = ?",
//                taskId, userId, taskPointId).get(0);
//        workTaskPointsEntity.setIsComplete("1");
//        if (isGiveback) {
//            workTaskPointsEntity.setIsGiveback("1");
//        }
//        DbUtil.getWorkTaskPointsService(mActivity).update(workTaskPointsEntity);
//
//        List<WorkTaskPointsEntity> workTaskPointsEntitys = DbUtil.getWorkTaskPointsService(mActivity).query(" where TASK_ID = ? and USER_ID = ? ",
//                taskId, userId);
//        boolean taskComplete = true;
//        for (int i = 0; i < workTaskPointsEntitys.size(); i++) {
//            if (workTaskPointsEntitys.get(i).getIsComplete().equals("0")) {
//                taskComplete = false;
//            }
//        }
//
//        if (taskComplete) {
//            EventBus.getDefault().post(new TaskStatus());//通知任务详情改变状态
//            TaskEntity taskEntity = DbUtil.getTaskService(mActivity).query(" where TASK_ID = ? and USER_ID = ? ",
//                    taskId, userId).get(0);
//            taskEntity.setIsHistory(1);
//            taskEntity.setHistoryTime(IDateTimeUtils.getDateNow());
//            taskEntity.setTaskStatus("6");
//            DbUtil.getTaskService(mActivity).update(taskEntity);
//
//            ItemsEntity itemsEntity = DbUtil.getItemsService(mActivity).query(" where TASK_ID = ? and USER_ID = ?",
//                    taskId, userId).get(0);
//            itemsEntity.setIsHistory(1);
//            itemsEntity.setHistoryTime(IDateTimeUtils.getDateNow());
//            itemsEntity.setTaskStatus(6);
//            DbUtil.getItemsService(mActivity).update(itemsEntity);
//
//            EventBus.getDefault().post(new MessageBean());
//        }
//    }
}
