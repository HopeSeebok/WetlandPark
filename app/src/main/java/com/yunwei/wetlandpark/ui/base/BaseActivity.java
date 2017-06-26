package com.yunwei.wetlandpark.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.common.handler.BAHandler;
import com.yunwei.wetlandpark.common.handler.HadlerUitls;
import com.yunwei.wetlandpark.ui.mainFunctions.homeModule.RFIDScanControl;
import com.yunwei.wetlandpark.utils.IActivityManage;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.SwipeBackLayout;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.ILngLatMercator;
import com.yunwei.rfid.RFIDResultListener;
import com.yunwei.rfid.readmode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Package: com.yunwei.zaina.ui.activity
 * @Description:Activity基类
 * @author: Aaron
 * @date: 2016-05-30
 * @Time: 11:31
 * @version: V1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, RFIDResultListener {
    private final String TAG = this.getClass().getSimpleName();
    /**
     * Toolbar
     */
    private Toolbar mToolbar;
    /**
     * ActionBar
     */
    protected ActionBar mActionBar;

    private TextView mTitleTextView;
    /**
     * ToolBar右边按钮Layout
     */
    private LinearLayout mToolbarMoreLayout;
    /**
     * ToolBar右边TextView
     */
    private TextView mToolbarRightText;
    /**
     * ToolBar右边ImageView
     */
    private ImageView mToolbarRightIV;
    /**
     * 布局实例器
     */
    protected LayoutInflater mLayoutInflater;
    /**
     * 核心内容
     */
    private FrameLayout mLinearLayoutContent;
    /**
     * 软件盘管理类
     */
    protected InputMethodManager mInput;
    /**
     * 侧滑finish
     */
    protected SwipeBackLayout swipeBackLayout;
    /**
     * 消息处理Handler
     */
    protected BAHandler mHandler;

    protected Dialog dialog;
    /**
     * RFID扫描控制器
     */
    protected RFIDScanControl rfidScanControl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        IActivityManage.getInstance().addActivity(this);
        //Activity侧滑Finish()
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.replaceLayer(this);

        EventBus.getDefault().register(this);

        mInput = (InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);

        initView();
        initListener();
        initHandler();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        mLinearLayoutContent = (FrameLayout) findViewById(R.id.base_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_title);
        mToolbarMoreLayout = (LinearLayout) findViewById(R.id.toolbar_more_layout);
        mToolbarRightIV = (ImageView) findViewById(R.id.toolbar_more_add_icon);
        mToolbarRightText = (TextView) findViewById(R.id.toolbar_more_text);
        mTitleTextView = (TextView) findViewById(R.id.toolbar_center_title_tv);

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
    }

    /**
     * 设置监听器
     */
    private void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToolbarLeftButton();
            }
        });
        mToolbarMoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToolbarRightLayout();
            }
        });
    }

    /**
     * 初始化Handler
     */
    private void initHandler() {
        mHandler = new BAHandler(BaseActivity.this) {
            @Override
            public void handleMessage(Message msg) {
                BaseActivity.this.dispatchMessage(msg);
            }
        };
    }

    protected void dispatchMessage(Message msg) {
    }

    public void findViewById() {

    }

    public void setListener() {
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        if (swipeBackLayout.isSwipeFinished()) {
            super.finish();
            overridePendingTransition(0, 0);
        } else {
            swipeBackLayout.cancelPotentialAnimation();
            super.finish();
            overridePendingTransition(0, R.anim.slide_out_right);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = mLayoutInflater.inflate(layoutResID, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLinearLayoutContent.removeAllViews();
        mLinearLayoutContent.addView(view, lp);
        findViewById();
        setListener();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mLinearLayoutContent.removeAllViews();
        mLinearLayoutContent.addView(view, params);
        findViewById();
        setListener();
    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLinearLayoutContent.removeAllViews();
        mLinearLayoutContent.addView(view, lp);
        findViewById();
        setListener();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackGroundUserEvent(NoticeEvent event) {
        switch (event.getFlag()) {
            case EventConstant.NOTICE2://高德地位返回(GC-J02)
                AMapLocation aMapLocation = (AMapLocation) event.getObj();
                if (aMapLocation == null) {
                    return;
                }
                /**
                 * 转成墨卡托坐标
                 */
                final MPointEntity mercatorPoint = ILngLatMercator.lonLat2WebMercator(aMapLocation.getLongitude(), aMapLocation.getLatitude());
                /**
                 * 保存全局数据，设施采集、巡查上报等功能模块使用
                 */
                ZNAPPlication.getInstance().setLat(aMapLocation.getLatitude());
                ZNAPPlication.getInstance().setLng(aMapLocation.getLongitude());
                ZNAPPlication.getInstance().setX(mercatorPoint.getX());
                ZNAPPlication.getInstance().setY(mercatorPoint.getY());
                if (!IStringUtils.isEmpty(aMapLocation.getAddress())) {
                    ZNAPPlication.getInstance().setCurrentAddr(aMapLocation.getAddress());
                }
                if (!IStringUtils.isEmpty(aMapLocation.getStreet())) {
                    ZNAPPlication.getInstance().setRoad(aMapLocation.getStreet());
                }

                refreshLocation(mercatorPoint);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainUserEvent(NoticeEvent event) {
    }

    /**
     * 刷新定位点位置
     *
     * @param point
     */
    public void refreshLocation(MPointEntity point) {

    }

    /**
     * 设置Toolbar返回Btn监听
     */
    public void onClickToolbarLeftButton() {
        this.finish();
    }

    /**
     * 设置Toolbar右边Layout监听
     */
    public void onClickToolbarRightLayout() {
    }

    /**
     * 设置Toolbar标题
     *
     * @param resid
     */
    public void setToolbarTitle(int resid) {
        mToolbar.setTitle(resid);
        mToolbarRightText.setVisibility(View.VISIBLE);
        mToolbarRightIV.setVisibility(View.GONE);
    }

    /**
     * 设置Toolbar标题
     *
     * @param str
     */
    public void setToolbarTitle(String str) {
        mToolbar.setTitle(str);
        mToolbarRightText.setVisibility(View.VISIBLE);
        mToolbarRightIV.setVisibility(View.GONE);
    }

    /**
     * 设置中间标题
     *
     * @param resId
     */
    public void setToolbarCenterTitle(int resId) {
        mToolbar.setTitle("");
        mTitleTextView.setText(resId);
        mTitleTextView.setVisibility(View.VISIBLE);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 设置中间标题
     *
     * @param title
     */
    public void setToolbarCenterTitle(String title) {
        mToolbar.setTitle("");
        mTitleTextView.setText(title);
        mTitleTextView.setVisibility(View.VISIBLE);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 设置Toolbar显示状态
     *
     * @param visibility
     */
    public void setToolbarVisibility(int visibility) {
        mToolbar.setVisibility(visibility);
        mToolbarRightText.setVisibility(View.VISIBLE);
        mToolbarRightIV.setVisibility(View.GONE);
    }

    /**
     * 设置Toolbar右边Layout文字
     *
     * @param resid
     */
    public void setToolbarRightText(int resid) {
        mToolbarRightText.setText(resid);
        mToolbarRightText.setVisibility(View.VISIBLE);
        mToolbarRightIV.setVisibility(View.GONE);
    }

    /**
     * 设置Toolbar右边Layout文字
     *
     * @param str
     */
    public void setToolbarRightText(String str) {
        mToolbarRightText.setText(str);
        mToolbarRightText.setVisibility(View.VISIBLE);
        mToolbarRightIV.setVisibility(View.GONE);
    }

    /**
     * 设置Toolbar右边Layout图片资源
     *
     * @param resid
     */
    public void setToolbarRightImage(int resid) {
        mToolbarRightIV.setImageResource(resid);
        mToolbarRightText.setVisibility(View.GONE);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendEmptyMessage(Handler handler, int what) {
        HadlerUitls.sendEmptyMessage(handler, what);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendEmptyMessageDelayed(Handler handler, int what, long delaymillis) {
        HadlerUitls.sendEmptyMessageDelayed(handler, what, delaymillis);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendEmptyMessageAtTime(Handler handler, int what, long uptimeMillis) {
        HadlerUitls.sendEmptyMessageDelayed(handler, what, uptimeMillis);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessage(Handler handler, int what, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, obj);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessage(Handler handler, int what, int arg1, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, obj);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessage(Handler handler, int what, int arg1, int arg2, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, arg2, obj);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessage(Handler handler, int what, int arg1) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */

    public void sendHandlerMessage(Handler handler, int what, int arg1, int arg2) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, arg2);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessageDelayTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        HadlerUitls.sendHandlerMessageDelayTime(handler, what, arg1, arg2, obj, time);
    }

    /**
     * Handler消息发送
     *
     * @param handler
     * @param what
     */
    public void sendHandlerMessageAtTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        HadlerUitls.sendHandlerMessageAtTime(handler, what, arg1, arg2, obj, time);
    }

    /**
     * 设置Activity是否支持滑动Finsh
     *
     * @param enabled
     */
    public void setSwipeEnabled(boolean enabled) {
        swipeBackLayout.setSwipeEnabled(enabled);
    }

    /**
     * 显示软键盘
     *
     * @param edittext
     */
    public void showSoftInput(final EditText edittext) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                edittext.requestFocus();
                if (mInput != null) mInput.showSoftInput(edittext, 0);
            }
        }, 700);
    }

    /**
     * 收起软键盘
     *
     * @param et
     */
    public void hideSoftInput(EditText et) {
        if (null != mInput && mInput.isActive())
            mInput.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            IActivityManage.getInstance().removeActivity(this);
            mHandler.removeCallbacksAndMessages(null);
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Toast
     *
     * @param resid
     */
    public void showToast(int resid) {
        ToastUtil.showToast(this, resid);
    }

    /**
     * Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    public void RFIDResultCallBack(readmode mode) {
        //唤醒屏幕
        IUtils.wakeAndUnlock(this);
        ILog.d(TAG, "rifd==" + mode.getEPCNo() + "-" + mode.getTIDNo());
        rfidScanControl.checkResult(mode.getEPCNo());
    }

    public void startTaskNotice(){

    }
}
