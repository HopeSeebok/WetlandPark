package com.yunwei.wetlandpark.ui.mainFunctions.homeModule;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yunwei.baidu.BaiduTrack;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.entity.MapSearchEntity;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.AddDeviceActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.CheckPlanActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.MakeTroubleActivity;
import com.yunwei.wetlandpark.ui.view.HomeView;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.yunwei.wetlandpark.R.id.iv_home_check_plan;

/**
 * Describe:主界面
 * Author: hezhiWu
 * Date: 2016-08-28
 * Time: 10:00
 * Version:1.0
 */

public class HomeFragment extends BaseFragment implements HomeView {

    private View mStartBtn;
    private View mStopBtn;
    private BaiduTrack baiduTrack;
    private Track mTrack;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaiduTrack();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        view.findViewById(R.id.iv_home_make_trouble).setOnClickListener(this);
        mStartBtn = view.findViewById(R.id.track_record_start_layout);
        mStartBtn.setOnClickListener(this);
        mStopBtn = view.findViewById(R.id.track_record_complete_layout);
        mStopBtn.setOnClickListener(this);
        initNotificationView(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_home_make_trouble:
                /*Make Trouble*/
                Bundle bundle1 = new Bundle();
                ISkipActivityUtil.startIntent(getActivity(), MakeTroubleActivity.class,bundle1);
                break;
            case R.id.track_record_start_layout:
                /*开始足迹*/
                mStartBtn.setVisibility(View.GONE);
                mStopBtn.setVisibility(View.VISIBLE);
                ToastUtil.showToast(getContext(),"开始巡检记录");
                /*启动鹰眼服务*/
                baiduTrack.startTrace(getContext());
                /*创建足迹实体*/
                mTrack = new Track();
                mTrack.setStartTime(System.currentTimeMillis());
                break;
            case R.id.track_record_complete_layout:
                /*结束足迹*/
                DialogFactory.showMsgDialog(getContext(), "提示", "确定结束当前巡检？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            mStartBtn.setVisibility(View.VISIBLE);
                            mStopBtn.setVisibility(View.GONE);
                            /*停止鹰眼服务*/
                            baiduTrack.stopTrace();
                            /*保存足迹*/
                            mTrack.setEndTime(System.currentTimeMillis());
                            ZNAPPlication.getDaoSession().getTrackDao().insert(mTrack);
                            ToastUtil.showToast(getContext(),"保存足迹成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showToast(getContext(),"保存足迹失败");
                        }
                    }
                });

                break;
        }
    }
    /**
     * 初始化百度鹰眼服务
     */
    private void initBaiduTrack() {
        baiduTrack = BaiduTrack.getInstance();
        baiduTrack.setSERVICE_ID(IStringUtils.toLong(BuildConfig.BAIDU_SERVICE_ID));
        baiduTrack.setENTITY_NAME("wt_park_"+ISpfUtil.getValue(Constants.USER_NAME_KEY,"").toString());
    }


    @Override
    public void search() {

    }

    @Override
    public void goToActivity(ArrayList<MapSearchEntity> dataEntities) {
//        Bundle bu = new Bundle();
//        bu.putSerializable(Constants.SEARCHENTITY, dataEntities);
//        ISkipActivityUtil.startIntent(this.getContext(), SearchListActivity.class, bu);
    }

}
