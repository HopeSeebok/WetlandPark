package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback.GiveBackListener;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.utils
 * @Description:退回原因选择弹窗
 * @date 2016/11/1
 * @changeby:
 */

public class GiveBackDialog {

    private final Context mContext;
    private final GiveBackListener mGiveBackListener;

    public GiveBackDialog(Context context, GiveBackListener giveBackListener) {
        mContext=context;
        mGiveBackListener=giveBackListener;
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.task_give_back_tips));
        final View view = LayoutInflater.from(mContext).inflate(R.layout.task_back_reason, null);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.back_rg);
        final EditText editText = (EditText) view.findViewById(R.id.other_et);
        final StringBuilder finalBackReason = new StringBuilder();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (finalBackReason.length()!=0) {
                finalBackReason.delete(0, finalBackReason.length() - 1);
            }
            RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
            if (radioButton.getText().equals(mContext.getString(R.string.task_other))) {
                editText.setVisibility(View.VISIBLE);
            } else {
                editText.setVisibility(View.GONE);
                finalBackReason.append(radioButton.getText()).append(",");
            }
        });
        builder.setView(view);
        builder.setPositiveButton(mContext.getString(R.string.dialog_sure), (dialog, which) -> {
            String bascReason = "";
            if (!TextUtils.isEmpty(finalBackReason)) {
                bascReason = finalBackReason.substring(0, finalBackReason.lastIndexOf(","));
            }
            if (!TextUtils.isEmpty(editText.getText())) {
                bascReason=editText.getText().toString();
            }
            mGiveBackListener.giveBackSuccess(bascReason);
        });
        builder.setNegativeButton(mContext.getString(R.string.dialog_cancel_), (dialog, which) -> {
        });
        builder.setCancelable(false);
        builder.show();
    }
}
