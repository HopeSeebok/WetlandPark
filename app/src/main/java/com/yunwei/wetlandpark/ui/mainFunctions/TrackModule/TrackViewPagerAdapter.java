package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.main
 * @Description:
 * @date 2016/8/31 10:46
 */
public class TrackViewPagerAdapter extends FragmentPagerAdapter {

    public TrackViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment ft = null;
        switch (arg0) {
            case 0://采集
                ft = new TrackRecordFragment();
                break;
            case 1://历史
                ft = new TrackHisFragment();
                break;
        }
        return ft;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
