package com.yunwei.wetlandpark.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.CommPopupWindow;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.mainFunctions.TrackModule.TrackListActivity;
import com.yunwei.wetlandpark.ui.presenter.QueryTrackByDataPresenter;
import com.yunwei.wetlandpark.ui.view.QueryTrackByDataView;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.widget
 * @Description: 日历控件
 * @date 2016/9/8 17:29
 */
public class CalendarPopupLayout extends LinearLayout implements View.OnClickListener,QueryTrackByDataView{

    private View view;
    private MaterialCalendarView calendarView;
    private Activity activity;

    private CommPopupWindow popupWindow;

    private QueryTrackByDataPresenter trackByDataPresenter;

    private Dialog dialog;

    public CalendarPopupLayout(Activity context) {
        super(context);
        this.activity=context;
        init();
    }

    public CalendarPopupLayout(Activity context, AttributeSet attri) {
        super(context, attri);
        this.activity=context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.pop_caldeadar_layout, null);
        view.findViewById(R.id.calendar_empty_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        view.findViewById(R.id.track_query_btn).setOnClickListener(this);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        trackByDataPresenter=new QueryTrackByDataPresenter(this);
        addView(view);
    }

    /**
     * show at parnt down
     *
     * @param parnt
     * @param xoff
     * @param yoff
     */
    public void showAsDropDown(View parnt, int xoff, int yoff) {
        calendarView.setCurrentDate(Calendar.getInstance());//设置当前日期
        calendarView.setSelectedDate(Calendar.getInstance());//设置选中日期
        popupWindow = new CommPopupWindow(getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(parnt, xoff, yoff);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.track_query_btn:
                String data=calendarView.getSelectedDate().getYear()+"-"+(calendarView.getSelectedDate().getMonth()+1)+"-"+calendarView.getSelectedDate().getDay();
                trackByDataPresenter.queryTrackByData(data);
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    public void onQueryTrackByDataFailure(int code, String error) {
        ToastUtil.showToast(activity,error);
    }

    @Override
    public void onQueryTrackByDataEnd() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onQueryTrackByDataStart() {
        dialog= DialogFactory.createLoadingDialog(activity,"查询...");
    }

    @Override
    public void onQueryTrackByDataSuccess(List<TrackEntity> list) {
        if (list==null||list.size()==0){
            ToastUtil.showToast(activity,"足迹记录为空!");
        }else {
            Bundle bundle=new Bundle();
            bundle.putSerializable("entity",(Serializable) list);
            ISkipActivityUtil.startIntent(activity, TrackListActivity.class,bundle);
        }
    }

    @Override
    public Activity getActivity() {
        return activity;
    }
}
