package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.main.fragment
 * @Description:
 * @date 2016/8/31 11:00
 */
public interface FragmentInteraction {

    void switchTrackRecord();

    void switchTrackHistory();

    void onStartTrack();

    void onStopTrack();

    void onCompleteTrack();

    void onCancelTrack();

    void onResumeTrack();

    void onSignIn();//签到 du yang
    void onSignOut();//签退 du yang
}
