package com.yunwei.wetlandpark.ui.adapter.task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yunwei.wetlandpark.common.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.adapter.task
 * @Description:任务详情适配器
 * @date 2016/9/27
 * @changeby:
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private final int mTaskId;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = new String[]{"详情","时间轴","位置&附件"};

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList,int taskId) {
        super(fm);
        this.fragmentList = fragmentList;
        mTaskId = taskId;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=fragmentList.get(position);
        Bundle bundle=new Bundle();
        bundle.putInt(Constants.TASKID,mTaskId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
