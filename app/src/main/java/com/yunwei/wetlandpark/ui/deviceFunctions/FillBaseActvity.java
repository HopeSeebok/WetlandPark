package com.yunwei.wetlandpark.ui.deviceFunctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.activity.fill
 * @Description:填报的BaseActivity类
 * @date 2016/10/9 12:02
 */

public abstract class FillBaseActvity extends BaseActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    protected FillBaseFragment mFillFragment;

    private String savePath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_fill);
        setSwipeEnabled(false);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        /* 获取数据包，并初始化UI */
        Bundle bundle = getIntent().getExtras();
        mFillFragment = getFillingFragment(bundle);
        initUI(bundle);
    }

    /**
     * 初始化UI
     */
    private void initUI(Bundle bundle) {
        setToolbarTitle(getHeadTitle());
        transaction.replace(R.id.fill_content_fl, mFillFragment);
        transaction.commit();
    }

    /**
     * 获取填充的Fragment
     *
     * @return
     */
    public abstract FillBaseFragment getFillingFragment(Bundle bundle);

    /**
     * CBOK:子类设置标题值
     *
     * @return
     */
    public abstract String getHeadTitle();

    /**
     * 图片处理
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFillFragment.photoResult(requestCode, resultCode, data);
    }
}
