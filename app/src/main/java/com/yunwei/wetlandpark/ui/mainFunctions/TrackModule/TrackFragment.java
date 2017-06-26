package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.widget.CalendarPopupLayout;

/**
 * Describe:足迹
 * Author: hezhiWu
 * Date: 2016-08-28
 * Time: 10:00
 * Version:1.0
 */
public class TrackFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private TextView mRecordText, mHisText;
    private ViewPager mViewPager;
    private LinearLayout headTitleLayout;
    private ImageView calendarImg;

    private int currentPage = 0;

    private CalendarPopupLayout popupLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupLayout=new CalendarPopupLayout(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, null);
        findViewById(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void findViewById(View view) {
        mRecordText = (TextView) view.findViewById(R.id.track_record_title);
        mHisText = (TextView) view.findViewById(R.id.track_his_title);
        mViewPager = (ViewPager) view.findViewById(R.id.track_viewPager);
        headTitleLayout = (LinearLayout) view.findViewById(R.id.track_record_title_layout);
        calendarImg = (ImageView) view.findViewById(R.id.track_his_calendar);

        mRecordText.setOnClickListener(this);
        mHisText.setOnClickListener(this);
        calendarImg.setOnClickListener(this);

        mViewPager.setAdapter(new TrackViewPagerAdapter(getChildFragmentManager()));
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.track_record_title:
                setTitleStatus(0);
                calendarImg.setVisibility(View.GONE);
                break;
            case R.id.track_his_title:
                setTitleStatus(1);
                calendarImg.setVisibility(View.VISIBLE);
                break;
            case R.id.track_his_calendar:
                popupLayout.showAsDropDown(headTitleLayout,0,20);
                break;
        }
    }

    /**
     * 切换状态
     *
     * @param position
     */
    private void setTitleStatus(int position) {
        this.currentPage = position;
        switch (position) {
            case 0:
                listterner.switchTrackRecord();
                mViewPager.setCurrentItem(0);
                headTitleLayout.setBackgroundResource(R.mipmap.nav_qiehuan_left);
                mRecordText.setTextColor(getResources().getColor(R.color.colorPrimary));
                mHisText.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                listterner.switchTrackHistory();
                mViewPager.setCurrentItem(1);
                headTitleLayout.setBackgroundResource(R.mipmap.nav_qiehuan_right);
                mRecordText.setTextColor(getResources().getColor(R.color.white));
                mHisText.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitleStatus(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getCurrentPage() {
        return currentPage;
    }
}
