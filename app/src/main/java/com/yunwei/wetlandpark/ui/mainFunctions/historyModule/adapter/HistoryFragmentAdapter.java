package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.HiddenHistoryFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.SignInHistoryFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.trackHistoryPage.TrailHistoryFragment;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.adapter
 * @Description:历史Fragment适配器
 * @date 2016/9/12 10:54
 */
public class HistoryFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;
    private Context context;

    public HistoryFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    public HistoryFragmentAdapter(FragmentManager manager, String[] tabs) {
        super(manager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SignInHistoryFragment();
                break;
//            case 1:
//                fragment = new DeviceHistoryFragment();
//                break;
            case 1:
                fragment = new HiddenHistoryFragment();
                break;
//            case 3:
//                fragment = new PatrolHistoryFragment();
//                break;
            case 2:
                fragment = new TrailHistoryFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        TextView textView=new TextView(context);
//        return textView.setText(tabs[position]);
//        return tabs[position];
        return null;
    }
}
