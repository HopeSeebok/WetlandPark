package com.yunwei.wetlandpark.common.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yunwei.wetlandpark.R;

/**
 * @Package: com.yunwei.zaina.common.dialog
 * @Description:附件添加选择框
 * @author: Aaron
 * @date: 2016-06-14
 * @Time: 19:41
 * @version: V1.0
 */
public class AccessoryPopupUtil {

    public static final String PHOTOGRAPH="photograph";
    public static final String ALBUM="album";

    private static PopupWindow popupWindow;

    private static AccessSeletorListener listener;

    private static View initView(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.accessory_seletor_layout, null);
        view.findViewById(R.id.photograph).setOnClickListener(new onItemClick());
        view.findViewById(R.id.album).setOnClickListener(new onItemClick());
        view.findViewById(R.id.accessory_cancel_btn).setOnClickListener(new onItemClick());

        return view;
    }

    private static PopupWindow initPopupWindow(Activity context) {
        PopupWindow popupWindow = new PopupWindow(initView(context), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x98000000));
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        return popupWindow;
    }

    public static void showAccessorySeletorDialog(Activity activity, View view,AccessSeletorListener listener) {
        popupWindow = initPopupWindow(activity);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        AccessoryPopupUtil.listener=listener;
    }

    private static class onItemClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.photograph:
                    if (listener!=null){
                        listener.onAccessItemClick(PHOTOGRAPH);
                    }
                    popupWindow.dismiss();
                    break;
                case R.id.album:
                    if (listener!=null){
                        listener.onAccessItemClick(ALBUM);
                    }
                    popupWindow.dismiss();
                    break;
                case R.id.accessory_cancel_btn:
                    popupWindow.dismiss();
                    break;
            }
        }
    }

    public interface AccessSeletorListener{
        public void onAccessItemClick(String msg);
    }
}
