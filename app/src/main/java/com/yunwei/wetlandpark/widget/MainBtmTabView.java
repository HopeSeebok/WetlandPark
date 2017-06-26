package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2016-08-28
 * Time: 09:14
 * Version:1.0
 */
public class MainBtmTabView extends LinearLayout {

    private ImageView imageView;
    private TextView nameText;
    private TextView tipText;

    private int iconRes;
    private String name;

    public MainBtmTabView(Context context) {
        super(context);
        initAttributeset(context, null, 0);
        init(context);
    }

    public MainBtmTabView(Context context, AttributeSet attri) {
        super(context, attri);
        initAttributeset(context, attri, 0);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_btn_tab_layout, null);
        imageView = (ImageView) view.findViewById(R.id.main_tab_iv);
        nameText = (TextView) view.findViewById(R.id.main_tab_name);
        tipText = (TextView) view.findViewById(R.id.main_tab_tip);

        imageView.setImageResource(iconRes);
        nameText.setText(name);

        addView(view);
    }

    private void initAttributeset(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.main_bttom_tab, defStyle, 0);
        iconRes = typedArray.getResourceId(R.styleable.main_bttom_tab_main_tab_icon, 0);
        name = typedArray.getString(R.styleable.main_bttom_tab_main_tab_name);
        typedArray.recycle();
    }

    /**
     * 设置图标
     *
     * @param res
     */
    public void setTabImageView(int res) {
        imageView.setImageResource(res);
    }

    /**
     * 设置字体颜色
     *
     * @param color
     */
    public void setTabTextColor(int color) {
        nameText.setTextColor(color);
    }

    /**
     * 设置Tabtip显示隐藏
     *
     * @param visibility
     */
    public void setTabTipVisibility(int visibility) {
        tipText.setVisibility(visibility);
    }

    /**
     * 设置Tabtip未读数量
     *
     * @param count
     */
    public void setTabTipNumber(int count) {
        if (count==0) {
            tipText.setVisibility(View.GONE);
        }else {
            tipText.setVisibility(View.VISIBLE);
        }
        tipText.setText(String.valueOf(count));
    }
}
