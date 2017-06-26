package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.widget
 * @Description:
 * @date 2016/11/30 09:19
 */

public class FormCheckBoxSelectorView extends LinearLayout{

    @BindView(R.id.formCheckBoxSelector_title_tv)
    TextView formCheckBoxSelectorTitleTv;
    @BindView(R.id.formCheckBoxSelector_title_tv_note)
    TextView formCheckBoxSelectorTitleTvNote;
    @BindView(R.id.formCheckBoxSelector_checkBox)
    SwitchButtonV2 formCheckBoxSelectorCheckBox;

    private String mProperName;
    private String mProperNote;
    private String mCheckBoxName;
    private String textOn;
    private String textOff;
    private boolean mChecked;

    public FormCheckBoxSelectorView(Context context) {
        this(context, null);
    }

    public FormCheckBoxSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.form_checkbox_selector_layout, null);
        ButterKnife.bind(this, view);
        mProperName += getResources().getString(R.string.colon_breaker);
        formCheckBoxSelectorTitleTv.setText(mProperName);
        formCheckBoxSelectorTitleTvNote.setText(mProperNote);
        formCheckBoxSelectorCheckBox.setText(mCheckBoxName);
        formCheckBoxSelectorCheckBox.setChecked(mChecked);
        formCheckBoxSelectorCheckBox.setText(textOn, textOff);
        formCheckBoxSelectorCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChecked = isChecked;
            }
        });


        addView(view);
    }

    private void initAttributeSet(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormCheckBoxSelectorView);
        mProperName = typedArray.getString(R.styleable.FormCheckBoxSelectorView_checkBoxSelectorName);
        mProperNote = typedArray.getString(R.styleable.FormCheckBoxSelectorView_checkBoxSelectorNote);
        mCheckBoxName = typedArray.getString(R.styleable.FormCheckBoxSelectorView_checkBoxName);
        mChecked = typedArray.getBoolean(R.styleable.FormCheckBoxSelectorView_checked, false);
        textOn = typedArray.getString(R.styleable.FormCheckBoxSelectorView_textOn);
        textOff = typedArray.getString(R.styleable.FormCheckBoxSelectorView_textOff);
        typedArray.recycle();
    }

    /**
     * 返回是否打钩
     * @return
     */
    public boolean isChecked(){
        return mChecked;
    }

    /**
     * 返回是否打钩
     * @return
     */
    public String getChecked(){
        if (mChecked){
            return "1";
        }else{
            return "0";
        }
    }
}
