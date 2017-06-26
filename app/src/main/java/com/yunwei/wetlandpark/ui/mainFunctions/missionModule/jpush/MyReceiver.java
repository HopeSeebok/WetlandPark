package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskPointListEntity;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.greedao.TroubleShooterTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.NoticeUtil;
import com.yunwei.library.baiduTTS.BaiduTTSUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import rx.Observable;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            try {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                MessageBean messageBean = new Gson().fromJson(message, new TypeToken<MessageBean>() {
                }.getType());

                String userId = (String) ISpfUtil.getValue(mContext, Constants.ACCOUNT_ID_KEY, "");
                switch (messageBean.getCode()) {
                    //新任务
                    case 2001:
                        int receiptTaskSize = messageBean.getData().size();
                        for (int i = 0; i < receiptTaskSize; i++) {
                            int taskId = messageBean.getData().get(i);
                            int currentTasksize = TaskBiz.getInstance().getTaskListAndDetailEntityListBeingProcessed(userId).size();
                            message = "您有" + (currentTasksize + receiptTaskSize) + "个新的任务,请注意查收";

                            checkTaskExist(taskId, userId, message);
                        }
                        return;
                    //撤销 暂时不需要此功能
                    case 2002:
                        message = "您有任务被撤销了";
                        break;
                    //已审核
                    case 2003:
                        message = "您有任务已通过审核";
                        TaskBiz.getInstance().changeTaskStatusSqlite(messageBean.getData().get(0), 3);
                        break;
                    //已终止
                    case 2004:
                        message = "您有任务被终止";
                        TaskBiz.getInstance().changeTaskStatusSqlite(messageBean.getData().get(0), 4);
                        sendMessage(message);
                        break;
                    //任务重做
                    case 2005:
                        for (int i = 0; i < messageBean.getData().size(); i++) {
                            int taskId = messageBean.getData().get(i);
                            message = "您有任务被返回重做";
                            checkTaskExist(taskId,userId,message);
                        }
                        return;
                }
                startTTSAndNotice(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检测本地是否已存在该任务，如果存在则删除本地数据，然后重新网络请求
     * @param taskId
     * @param userId
     * @param message
     */
    private void checkTaskExist(int taskId, String userId, String message) {
        List<TaskListAndDetailEntity> taskListAndDetailEntities =
                TaskBiz.getInstance().getTaskListAndDetailEntityListByTaskId(taskId, userId);
        List<TaskPointListEntity> taskPointListEntities =
                TaskBiz.getInstance().getTaskPointListEntityList(taskId, userId);
        if (taskListAndDetailEntities.size() != 0) {
            List<TroubleShooterTable> troubleShooterTables = ZNAPPlication.getDaoSession().getTroubleShooterTableDao().queryBuilder().where(
                    TroubleShooterTableDao.Properties.MissionDetailID.eq(taskPointListEntities.get(0).getTaskPointId())
            ).list();
            //数据库已经存在该数据，删除
            ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao()
                    .delete(taskListAndDetailEntities.get(0));
            ZNAPPlication.getDaoSession().getTaskPointListEntityDao()
                    .delete(taskPointListEntities.get(0));
            ZNAPPlication.getDaoSession().getTroubleShooterTableDao()
                    .delete(troubleShooterTables.get(0));
        }
        //网络请求获取详情
        Observable<String> taskDetailObservable = TaskBiz.getInstance().getTaskDetailObservable(mContext, taskId);
        TaskBiz.getInstance().changeEntity(userId, taskDetailObservable).subscribe(s -> {
            sendMessage(message);
            startTTSAndNotice(message);
        });//需解决subscribe里不执行，已解决 complete的问题

    }

    private void sendMessage(String message) {
        //向主进程发送消息
        Intent intent = new Intent();
        intent.setAction("com.lp.MyBroadcast");
        intent.putExtra("lp", "跨进程调用Broadcast");
        mContext.sendBroadcast(intent);
    }

    private void startTTSAndNotice(String message) {
        if ((boolean) ISpfUtil.getValue(mContext, Constants.TASK_VOICE_TIPS, false)) {
            BaiduTTSUtils.speak(mContext, message);
        }
        if ((boolean) ISpfUtil.getValue(mContext, Constants.TASK_NOTICE_TIPS, false)) {
            NoticeUtil.startNotice(mContext, message, IUtils.getStrToRes(mContext, R.string.app_name),
                    R.mipmap.ic_logo);
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:").append(key).append(", value: [").append(myKey).append(" - ").append(json.optString(myKey)).append("]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }
            } else {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getString(key));
            }
        }
        return sb.toString();
    }

}
