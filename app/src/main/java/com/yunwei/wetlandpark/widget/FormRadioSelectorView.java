package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SEEBOK on 2016/10/13.
 */

public class FormRadioSelectorView extends LinearLayout {

    @BindView(R.id.formRadioSelector_title_tv)
    TextView formRadioSelectorTitleTv;
    @BindView(R.id.formRadioSelector_firstButton)
    AppCompatRadioButton formRadioSelectorFirstButton;
    @BindView(R.id.formRadioSelector_secondButton)
    AppCompatRadioButton formRadioSelectorSecondButton;
    @BindView(R.id.formRadioSelector_thirdButton)
    AppCompatRadioButton formRadioSelectorThirdButton;
    @BindView(R.id.formRadioSelector_rdoGroup)
    RadioGroup formRadioSelectorRdoGroup;

    private String mProperName;
    private String mFirstButtonName;
    private String mSecondButtonName;
    private String mThirdButtonName;
    private MyCheckedListener mMyCheckedListner;

    public static final int FIRST_BUTTON = 0;
    public static final int SECOND_BUTTON = 1;
    public static final int THIRD_BUTTON = 2;

    public FormRadioSelectorView(Context context) {
        this(context, null);
    }

    public FormRadioSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.form_radio_selector_layout, null);
        ButterKnife.bind(this, view);
        mProperName += getResources().getString(R.string.colon_breaker);
        formRadioSelectorTitleTv.setText(mProperName);
        formRadioSelectorFirstButton.setText(mFirstButtonName);

        /* 初始化第三个RadioButton时判断，若用户未设置第三个Button，则不显示*/
        formRadioSelectorSecondButton.setText(mSecondButtonName);
        if (!TextUtils.isEmpty(mThirdButtonName)){
            formRadioSelectorThirdButton.setText(mThirdButtonName);
        } else {
            formRadioSelectorThirdButton.setVisibility(GONE);
        }

        formRadioSelectorRdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mMyCheckedListner != null) {
                    switch (checkedId){
                        case R.id.formRadioSelector_firstButton:
                            mMyCheckedListner.onFirstButtonChecked();
                            break;
                        case R.id.formRadioSelector_secondButton:
                            mMyCheckedListner.onSecondButtonChecked();
                            break;
                        case R.id.formRadioSelector_thirdButton:
                            mMyCheckedListner.onThirdButtonChecked();
                            break;
                    }
                }
            }
        });

        addView(view);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormRadioSelectorView);
        mProperName = typedArray.getString(R.styleable.FormRadioSelectorView_radioSelectorName);
        mFirstButtonName = typedArray.getString(R.styleable.FormRadioSelectorView_firstButtonName);
        mSecondButtonName = typedArray.getString(R.styleable.FormRadioSelectorView_secondButtonName);
        mThirdButtonName = typedArray.getString(R.styleable.FormRadioSelectorView_thirdButtonName);
        typedArray.recycle();
    }

    public FormRadioSelectorView setCheckingButton(int number){
        switch (number){
            case FIRST_BUTTON:
                formRadioSelectorFirstButton.setChecked(true);
                break;
            case SECOND_BUTTON:
                formRadioSelectorSecondButton.setChecked(true);
                break;
            case THIRD_BUTTON:
                formRadioSelectorThirdButton.setChecked(true);
                break;
        }
        return this;
    }

    public int getCheckingButton(){
        int checkedRadioButtonId = formRadioSelectorRdoGroup.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.formRadioSelector_secondButton:
                return SECOND_BUTTON;
            case R.id.formRadioSelector_thirdButton:
                return THIRD_BUTTON;
        }
        return FIRST_BUTTON;
    }

    public void setNonEditable(){
        int checkedRadioButtonId = formRadioSelectorRdoGroup.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.formRadioSelector_firstButton:
                formRadioSelectorFirstButton.setTextColor(Color.GRAY);
                formRadioSelectorSecondButton.setVisibility(GONE);
                formRadioSelectorThirdButton.setVisibility(GONE);
                break;
            case R.id.formRadioSelector_secondButton:
                formRadioSelectorFirstButton.setVisibility(GONE);
                formRadioSelectorSecondButton.setTextColor(Color.GRAY);
                formRadioSelectorThirdButton.setVisibility(GONE);
                break;
            case R.id.formRadioSelector_thirdButton:
                formRadioSelectorFirstButton.setVisibility(GONE);
                formRadioSelectorSecondButton.setVisibility(GONE);
                formRadioSelectorThirdButton.setTextColor(Color.GRAY);
                break;
        }
        formRadioSelectorTitleTv.setTextColor(Color.GRAY);
    }

    public void setOnMyCheckedListener(MyCheckedListener myCheckedListener){
        this.mMyCheckedListner = myCheckedListener;
    }

    public interface MyCheckedListener {
        void onFirstButtonChecked();
        void onSecondButtonChecked();
        void onThirdButtonChecked();
    }

    public void setGone(int number){
        switch (number) {
            case 0:
                formRadioSelectorFirstButton.setVisibility(GONE);
                break;
            case 1:
                formRadioSelectorSecondButton.setVisibility(GONE);
                break;
            case 2:
                formRadioSelectorThirdButton.setVisibility(GONE);
                break;
        }
    }


}
