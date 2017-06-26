package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yunwei.wetlandpark.R;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.widget
 * @Description:主界面底部Layout
 * @date 2016/11/16 10:24
 */

public class MainBttomNavigationBar extends LinearLayout {

    public MainBttomNavigationBar(Context context) {
        super(context);
        initView();
    }

    public MainBttomNavigationBar(Context context, AttributeSet attri) {
        super(context, attri);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_tab_bttom_layout, null);
//        ButterKnife.bind(view, this);

        addView(view);
    }
}
