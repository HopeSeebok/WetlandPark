package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.library.dialog.BaseDialog;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.view.SingleSelectorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Package: com.yunwei.zaina.widget
 * @Description:
 * @author: Aaron
 * @date: 2016-06-16
 * @Time: 14:03
 * @version: V1.0
 */
public class FormScrollSelectorView extends LinearLayout {

    @BindView(R.id.formSelector_title_tv)
    TextView formSelectorTitleTv;
    @BindView(R.id.formSelector_content_tv)
    TextView formSelectorContentTv;

    private Context mContext;

    private SingleSelectorView mSingleSelectorView;
    private String properName;

    private String selectTitle;//选择框


    public FormScrollSelectorView(Context context) {
        this(context, null);
    }

    public FormScrollSelectorView(Context context, AttributeSet attr) {
        super(context, attr);
        this.mContext = context;
        initAttributeSet(context,attr);

        View view = LayoutInflater.from(context).inflate(R.layout.form_seletor_layout, null);
        ButterKnife.bind(this, view);
        properName += getResources().getString(R.string.colon_breaker);
        formSelectorTitleTv.setText(properName);
        /* 初始化选择对话框内的选择控件*/
        mSingleSelectorView = new SingleSelectorView(context);
        initView();
        addView(view);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormScrollSelectorView);
        properName = typedArray.getString(R.styleable.FormScrollSelectorView_selectorName);
        typedArray.recycle();
    }

    //初始化控件
    private void initView(){
        //设置标题栏
        formSelectorTitleTv.setTextColor(getResources().getColor(R.color.black));
        //设施选择框 的颜色 -- by wuqianrui
        mSingleSelectorView.setColor(R.color.btn_blue,R.color.black);
    }

    /* 设置弹出框的标题*/
    public void setSelectTitle(String title){
        this.selectTitle=title;
    }

    /* 提供方法设置数据源*/
    public SingleSelectorView setDataSource(String[] strings){
        mSingleSelectorView.registerDataSource(strings);
        return mSingleSelectorView;
    }

    /* 提供方法设置显示值*/
    public FormScrollSelectorView setPropertyContentText(String string){
        formSelectorContentTv.setText(string);
        return this;
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

    @OnClick(R.id.formSelector_content_tv)
    public void onClick() {

        //先设置标题
        if(TextUtils.isEmpty(selectTitle)){
            selectTitle = getResources().getString(R.string.dialog_please_select);
        }
       final BaseDialog dialog=(BaseDialog) DialogFactory.showMsgDialog(mContext,selectTitle , mSingleSelectorView,
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.common_sure), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        formSelectorContentTv.setText(mSingleSelectorView.getSelectValue());
                    }
                }, null);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialog.getContentView().removeAllViews();
            }
        });
        dialog.show();
    }
}
