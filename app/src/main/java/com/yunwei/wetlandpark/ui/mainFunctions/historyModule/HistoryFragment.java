package com.yunwei.wetlandpark.ui.mainFunctions.historyModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter.HistoryFragmentAdapter;


/**
 * Describe:
 * Author: hezhiWu
 * Date: 2016-08-28
 * Time: 10:00
 * Version:1.0
 * <p/>
 * 不建议多层嵌套Fragment
 */
public class HistoryFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private String[] names;
    private int[] icons;

    private static BaseFragment fragment;
    public static BaseFragment newInstance(){
        if (fragment==null){
            fragment=new HistoryFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names=getResources().getStringArray(R.array.his_tab);
//        icons=new int[]{R.mipmap.icon_task,R.mipmap.icon_fac,R.mipmap.icon_yh,R.mipmap.icon_patrol,R.mipmap.icon_maintain};
        icons=new int[]{R.mipmap.icon_task,R.mipmap.icon_yh,R.mipmap.icon_maintain};
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_his, null);
        findViewById(view);
        initViewPager();
        return view;
    }

    public void findViewById(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.his_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_fragment_history);
        getResources().getStringArray(R.array.his_tab);
        initNotificationView(view);
    }

    private void initViewPager() {
        mViewPager.setAdapter(new HistoryFragmentAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.his_tab)));
        mViewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        initTabView(tabLayout);

    }

    /**
     * 初始化TabView
     * @param layout
     */
    private void initTabView(TabLayout layout) {
        for (int i = 0; i < layout.getTabCount(); i++) {
            TabLayout.Tab tab = layout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_history_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.his_tab_icon);
            TextView textView = (TextView) view.findViewById(R.id.his_tab_name);
            textView.setText(names[i]);
            imageView.setImageResource(icons[i]);
            tab.setCustomView(view);
        }
    }
}
