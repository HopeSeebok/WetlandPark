package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/11/1 15:08
 * @description:
 */

public class FormClickableView extends LinearLayout {

    @BindView(R.id.formSelector_title_tv)
    TextView formSelectorTitleTv;
    @BindView(R.id.formSelector_content_tv)
    TextView formSelectorContentTv;

    private String mItemName;

    public FormClickableView(Context context) {
        this(context, null);
    }

    public FormClickableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.form_seletor_layout, null);
        ButterKnife.bind(this, view);
        mItemName += getResources().getString(R.string.colon_breaker);
        formSelectorTitleTv.setText(mItemName);
        addView(view);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormClickableView);
        mItemName = typedArray.getString(R.styleable.FormClickableView_itemName);
        typedArray.recycle();
    }

    public TextView getContentView(){
        return formSelectorContentTv;
    }

    /* 提供方法获取显示值*/
    public String getContentText(){
        return formSelectorContentTv.getText().toString();
    }

    /* 提供方法设置不可编辑*/
    public void setNonEditable(){
        formSelectorContentTv.setClickable(false);
        formSelectorTitleTv.setTextColor(Color.GRAY);
        formSelectorContentTv.setTextColor(Color.GRAY);
    }

    /* 提供方法设置显示值*/
    public FormClickableView setContentText(String string){
        formSelectorContentTv.setTextColor(Color.BLACK);
        formSelectorContentTv.setText(string);
        return this;
    }

}
