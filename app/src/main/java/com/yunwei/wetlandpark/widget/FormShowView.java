package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * @Package: com.yunwei.zaina.widget
 * @Description:填写表单
 * @author: Aaron
 * @date: 2016-06-14
 * @Time: 17:15
 * @version: V1.0
 */
public class FormShowView extends LinearLayout {

    private TextView propertyText;
    private TextView propertyContentText;

    private String properName;
    private String properContent;
    private boolean force;

    public FormShowView(Context context) {
        super(context);
        initAttributeset(context, null, 0);
        initView(context);
    }

    public FormShowView(Context context, AttributeSet attri) {
        super(context, attri);
        initAttributeset(context, attri, 0);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.form_show_layout, null);
        propertyText = (TextView) view.findViewById(R.id.form_show_title);
        propertyContentText = (TextView) view.findViewById(R.id.form_show_content);

        propertyText.setText(properName);
        propertyContentText.setText(properContent);
        addView(view);
    }

    private void initAttributeset(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.form_show_item_view, defStyle, 0);
        properName = typedArray.getString(R.styleable.form_show_item_view_pro_name_s);
        properContent = typedArray.getString(R.styleable.form_show_item_view_pro_content_s);
        force = typedArray.getBoolean(R.styleable.form_show_item_view_pro_force_s, force);
        typedArray.recycle();
    }

    public void setPropertyText(String str) {
        propertyText.setText(str);
    }

    public void setPropertyContentText(String str) {
        propertyContentText.setText(str);
    }

    public void setPropertyContentTextColor(int color) {
        propertyContentText.setTextColor(color);
    }

    public void setPropertyText(int resId) {
        propertyText.setText(resId);
    }

    public void setPropertyContentText(int resId) {
        propertyContentText.setText(resId);
    }

    public String getContentText() {
        return propertyContentText.getText().toString();
    }

    public String getContentTextView() {
        return propertyContentText.getText().toString();
    }
}
