package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.yunwei.wetlandpark.R;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.utils
 * @Description:任务按时通知弹窗
 * @date 2016/11/1
 * @changeby:
 */

public class TaskNoticeDialog {
    private final Context mContext;

    public TaskNoticeDialog(Context context) {
        mContext=context;
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.task_notice_dialog_title));
        final View view = LayoutInflater.from(mContext).inflate(R.layout.task_notice_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(mContext.getString(R.string.dialog_sure), (dialog, which) -> {
        });
        builder.setNegativeButton(mContext.getString(R.string.dialog_cancel_), (dialog, which) -> {
        });
        builder.setCancelable(false);
        builder.show();
    }
}
