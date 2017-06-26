package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view;


import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback.PlanConfirmListener;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.missionModule.view
 * @Description: 弹窗辅助类
 * @date 2016/11/28
 * @changeby:
 */
public class WindowUtil {
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Boolean isShown = false;
    private static PlanConfirmListener mPlanConfirmListener;

    /**
     * 显示弹出框
     * @param planConfirmListener
     */
    public static void showPopupWindow(PlanConfirmListener planConfirmListener) {
        mPlanConfirmListener=planConfirmListener;
        if (isShown) {
            return;
        }
        isShown = true;
        // 获取应用的Context
        Context context = ZNAPPlication.getInstance();
        // 获取WindowManager
        mWindowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mView = setUpView(context);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
    }
    /**
     * 隐藏弹出框
     */
    private static void hidePopupWindow() {
        if (isShown && null != mView) {
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }
    private static View setUpView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow,
                null);
        Button positiveBtn = (Button) view.findViewById(R.id.positiveBtn);
        positiveBtn.setOnClickListener(v -> {
            mPlanConfirmListener.confirm();
            WindowUtil.hidePopupWindow();
        });
        Button negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
        negativeBtn.setOnClickListener(v -> WindowUtil.hidePopupWindow());
        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        final View popupWindowView = view.findViewById(R.id.popup_window);// 非透明的内容区域
        view.setOnTouchListener((v, event) -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Rect rect = new Rect();
            popupWindowView.getGlobalVisibleRect(rect);
            if (!rect.contains(x, y)) {
                WindowUtil.hidePopupWindow();
            }
            return false;
        });
        // 点击back键可消除
        view.setOnKeyListener((v, keyCode, event) -> {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    WindowUtil.hidePopupWindow();
                    return true;
                default:
                    return false;
            }
        });
        return view;
    }
}
