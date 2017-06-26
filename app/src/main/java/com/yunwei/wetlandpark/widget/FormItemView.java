package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * @Package: com.yunwei.zaina.widget
 * @Description:
 * @author: Aaron
 * @date: 2016-06-15
 * @Time: 19:36
 * @version: V1.0
 */
public class FormItemView extends LinearLayout {

    private TextView textView;

    public FormItemView(Context context){
        super(context);
        initView(context);
    }

    public FormItemView(Context context, AttributeSet attri){
        super(context,attri);
        initView(context);
    }

    private void initView(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.form_item_info,null);
        textView=(TextView)view.findViewById(R.id.form_item_text);
        addView(view);
    }

    public void setItemName(String str){
        textView.setText(str);
    }

    public void setItemName(int res){
        textView.setText(res);
    }
}
