package com.yunwei.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yunwei.library.R;


/**
 * @author WuQianRui
 * @version V1.0
 * @Package cn.carbs.android.gregorianlunarcalendar.library.view
 * @Description 单选选择器
 * @Date 2016/9/29 .
 */
public class SingleSelectorView extends LinearLayout implements NumberPickerView.OnValueChangeListener {

    private static final int DEFAULT_GREGORIAN_COLOR = 0xff3388ff;
    private static final int DEFAULT_LUNAR_COLOR = 0xffee5544;
    private static final int DEFAULT_NORMAL_TEXT_COLOR = 0xFF555555;

    private String[] defaultValue = new String[]{"暂无数据"};

    private OnSingSelectListner mListener;//监听工具
    /**
     * true to use scroll anim when switch picker passively
     */

    boolean mScrollAnim = true;

    private int mIndex=-1;//所选数据的下标,默认是-1，表示没有数据

    private int mThemeColorG = DEFAULT_GREGORIAN_COLOR;
    private int mThemeColorL = DEFAULT_LUNAR_COLOR;
    private int mNormalTextColor = DEFAULT_NORMAL_TEXT_COLOR;

    private NumberPickerView mDataPickerView;//数据集的选择工具

    public SingleSelectorView(Context context) {
        super(context);
        initInternal(context);
    }

    public SingleSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initInternal(context);
    }

    public SingleSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(context, attrs);
        initInternal(context);
    }

    //注册监听器
    public void registerListener(OnSingSelectListner listner){
        this.mListener = listner;
    }


    //只有加注数据才有意义
    public void registerDataSource(String[] dataSource){
        if(dataSource==null||dataSource.length<=0){
            setData(mDataPickerView,0,defaultValue.length-1,0);
            mDataPickerView.setDisplayedValues(defaultValue,true);
            return;
        }
        mDataPickerView.setDisplayedValues(dataSource,true);
        setData(mDataPickerView,0,dataSource.length-1,0);
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
        mDataPickerView.setSelectedTextColor(themeColor);
        mDataPickerView.setHintTextColor(themeColor);
        mDataPickerView.setDividerColor(themeColor);
    }

    public void setNormalColor(int normalColor) {
        mDataPickerView.setNormalTextColor(normalColor);
    }

    public void initInternal(Context con) {
        View v = LayoutInflater.from(con).inflate(R.layout.view_selector_single,this);//加载选择框
        mDataPickerView = (NumberPickerView)v.findViewById(R.id.picker_single_selector_data);
        mDataPickerView.setDisplayedValues(defaultValue);
        setData(mDataPickerView,0,defaultValue.length-1,0);
    }

    private void setData(NumberPickerView picker, int minValue, int maxValue, int index) {
        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue);
        picker.setValue(index);
    }


    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        this.mIndex= newVal;
    }


    //获取已选中的值
    public String getSelectValue() {
        if (mDataPickerView.getContentByCurrValue().equals("暂无数据")) {
            mIndex =-1;
            return "";
        }
        return mDataPickerView.getContentByCurrValue();
    }

    //返回回调
    public void callbackListener(){
        if(mListener!=null){
            this.mListener.selectReturn(getSelectValue(),mIndex);
        }
    }

    public interface OnSingSelectListner{
      public  void selectReturn(String result,int index);
    }


}
