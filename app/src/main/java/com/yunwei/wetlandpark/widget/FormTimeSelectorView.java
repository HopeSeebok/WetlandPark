package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.view.GregorianLunarCalendarView;

import java.util.Calendar;

/**
 * @author CBOK
 * @date 2016/11/4 16:00
 * @description:
 */

public class FormTimeSelectorView extends FormClickableView{

    private GregorianLunarCalendarView mCalendarView;

    public FormTimeSelectorView(Context context) {
        super(context,null);
    }

    public FormTimeSelectorView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mCalendarView = new GregorianLunarCalendarView(context, null);
        mCalendarView.init();

        formSelectorContentTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFactory.showMsgDialog(context, "选择日期", mCalendarView, "确定", "取消", null, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GregorianLunarCalendarView.CalendarData calendarData = mCalendarView.getCalendarData();
                        Calendar calendar = calendarData.getCalendar();
                        String date = calendar.get(Calendar.YEAR) + "-" + ((calendar.get(Calendar.MONTH) + 1)<10?"0"+(calendar.get(Calendar.MONTH) + 1):(calendar.get(Calendar.MONTH) + 1))
                                + "-" + (calendar.get(Calendar.DAY_OF_MONTH)<10?"0"+calendar.get(Calendar.DAY_OF_MONTH):calendar.get(Calendar.DAY_OF_MONTH));
                        setContentText(date);
                    }
                });

            }
        });
    }
}
