package com.yunwei.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.yunwei.library.R;


/**
 * Created by SlientWhale on 2016/9/21.
 */
public class ClockView extends LinearLayout implements NumberPickerView.OnValueChangeListener {

    private static final int DEFAULT_GREGORIAN_COLOR = 0xff3388ff;
    private static final int DEFAULT_LUNAR_COLOR = 0xffee5544;
    private static final int DEFAULT_NORMAL_TEXT_COLOR = 0xFF555555;

    /**
     * true to use scroll anim when switch picker passively
     */
    private boolean mScrollAnim = true;

    private int mThemeColorG = DEFAULT_GREGORIAN_COLOR;
    private int mThemeColorL = DEFAULT_LUNAR_COLOR;
    private int mNormalTextColor = DEFAULT_NORMAL_TEXT_COLOR;

    private NumberPickerView mHourNumberPickerView;//小时的选择工具
    private NumberPickerView mMinuteNumberPickerView;//分钟的选择工具

    public ClockView(Context context) {
        super(context);
        initInternal(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initInternal(context);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(context, attrs);
        initInternal(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GregorianLunarCalendarView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_ScrollAnimation) {
                mScrollAnim = a.getBoolean(attr, true);
            } else if (attr == R.styleable.GregorianLunarCalendarView_glcv_GregorianThemeColor) {
                mThemeColorG = a.getColor(attr, DEFAULT_GREGORIAN_COLOR);
            }
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_LunarThemeColor) {
                mThemeColorL = a.getColor(attr, DEFAULT_LUNAR_COLOR);
            }
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_NormalTextColor) {
                mNormalTextColor = a.getColor(attr, DEFAULT_NORMAL_TEXT_COLOR);
            }
        }
        a.recycle();
    }

    public void init() {
        setColor(mThemeColorG, mNormalTextColor);
    }

    public void setColor(int themeColor, int normalColor) {
        setThemeColor(themeColor);
        setNormalColor(normalColor);
    }

    public void setThemeColor(int themeColor) {
        mHourNumberPickerView.setSelectedTextColor(themeColor);
        mHourNumberPickerView.setHintTextColor(themeColor);
        mHourNumberPickerView.setDividerColor(themeColor);
        mMinuteNumberPickerView.setSelectedTextColor(themeColor);
        mMinuteNumberPickerView.setHintTextColor(themeColor);
        mMinuteNumberPickerView.setDividerColor(themeColor);
    }

    public void setNormalColor(int normalColor) {
        mHourNumberPickerView.setNormalTextColor(normalColor);
        mMinuteNumberPickerView.setNormalTextColor(normalColor);
    }


    public void initInternal(Context con) {
        View v = inflate(con, R.layout.view_lunar_clock_calendar, this);//加载时钟的布局
        mHourNumberPickerView = (NumberPickerView) v.findViewById(R.id.picker_hour);
        mMinuteNumberPickerView = (NumberPickerView) v.findViewById(R.id.picker_minute);
        mHourNumberPickerView.setDisplayedValues(new String[]{"00", "01", "02", "03", "04", "05", "06", "07",
                "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        mMinuteNumberPickerView.setDisplayedValues(new String[]{
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
        });
        setData(mHourNumberPickerView, 0, 23, 12);
        setData(mMinuteNumberPickerView, 0, 59, 0);
        mHourNumberPickerView.setOnValueChangedListener(this);
        mMinuteNumberPickerView.setOnValueChangedListener(this);
    }

    private void setData(NumberPickerView picker, int minValue, int maxValue, int value) {
        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue);
        picker.setValue(value);
    }


    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

    }

    public String getClockFormat() {
        String clockString = "";
        clockString += mHourNumberPickerView.getContentByCurrValue();
        clockString += ":";
        clockString += mMinuteNumberPickerView.getContentByCurrValue();
        return clockString;
    }
}
