package com.yunwei.wetlandpark.ui.mainFunctions;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.tasks.identify.IdentifyParameters;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.map.MapView;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.widget.ArcGisBaseMapView;
import com.yunwei.rfid.RFIDConfig;
import com.yunwei.rfid.RFIDResultListener;
import com.yunwei.rfid.readmode;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntity;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntityDao;
import com.yunwei.wetlandpark.service.LocationService;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.CheckPlanActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.source.DeviceRemoteRepo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice.DisposableLinkActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.FormConfigPresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.signIn.SignInContract;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.signIn.SignInPresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.RFIDSearchContract;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.RFIDSearchPresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.data.source.RFIDSearchRepo;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.ShowSearchedListActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.TapMapSearchContract;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.TapMapSearchPresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.MapEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.source.TapMapSearchRepo;
import com.yunwei.wetlandpark.ui.mainFunctions.data.UnitInfoEntity;
import com.yunwei.wetlandpark.ui.mainFunctions.data.source.MainRemoteRepo;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.HistoryFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.homeModule.HomeFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.CheckAppVersion.CheckAppVersionContract;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.CheckAppVersion.CheckAppVersionPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.source.CheckAppVersionRemoteRepo;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment.MineFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.WindowUtil;
import com.yunwei.wetlandpark.utils.IActivityManage;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.view.MainBottomNavigationBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions
 * @Description:主界面V2版本
 * @date 2016/11/16 10:19
 */

public class MainActivity extends BaseActivity implements OnSingleTapListener, ArcGisBaseMapView.ArcGisMapListener,
        TapMapSearchContract.View, MainBottomNavigationBar.BottomTabSelectedListener, MainContract.View,
        RFIDSearchContract.View, CheckAppVersionContract.View, SignInContract.View {

    private final int TAB_HOME = 0;
    //    private final int TAB_TASK = 1;
    private final int TAB_HISTORY = 1;
    private final int TAB_MINE = 2;

    @BindView(R.id.main_mapView)
    MapView mapView;
    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;

    //Presenter
    private MainPresenter mainPresenter;
    //App版本检测
    private CheckAppVersionPresenter checkAppVersionPresenter;
    //图层查询
    private TapMapSearchPresenter mTapMapSearchPresenter;
    //RFID扫描
    private RFIDSearchPresenter mRFIDSearchPresenter;
    private FormConfigPresenter mFormConfigPresenter;
    //签到
    private SignInPresenter mSignInPresenter;
    //图层参数
    private IdentifyParameters mIdentifyParameters;
    private Dialog mLoadingDialog;
    private String mRFID;
    private DeviceInfo mScanDeviceInfo;


    private Dialog mSignInDialog;
    private SignInDetailsAdapter mSignInDetailsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSwipeEnabled(false);
        setToolbarCenterTitle(R.string.menu_home);
        initUI();
        setAlias();
//        stratTimeCount();
//        setTaskNum();
        RFIDConfig.initUHF(this, false);
    }


    @Override
    public void onResume() {
        RFIDConfig.setListener(rfidResultListener);
        RFIDConfig.initReadLoop();
        mapView.unpause();
        super.onResume();
    }

    @Override
    public void onPause() {
        RFIDConfig.stopRFIDReadLoop();
        mapView.pause();
        super.onPause();
    }

    private RFIDResultListener rfidResultListener = new RFIDResultListener() {
        @Override
        public void RFIDResultCallBack(readmode mode) {
            RFIDConfig.stopRFIDReadLoop();
            mRFID = mode.getEPCNo();
            mRFIDSearchPresenter.searchByRFID();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.recycle();
        RFIDConfig.closeUHF();
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        initMapView();
        initBottomNavigationbar();
        startLocationServer();
        initPresenter();

        currentTabPosition = TAB_HOME;
    }

    /**
     * 初始化地图
     */
    private void initMapView() {
        mapView.setOnSingleTapListener(this);
        mapView.setArcGisListener(this);
        mapView.addFeatureLayer(BuildConfig.DEVICE_LAYER_URL);
        String expression = "dangerstatus in (0,1)";
        mapView.addFeatureLayer(BuildConfig.TROUBLE_LAYER_URL, expression);
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationbar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_content_fl);
        mainBottomNavigationBar.addTabItem(R.mipmap.icon_main_tab_home_pr, R.string.menu_home).addTabItem(R.mipmap.icon_main_tab_record_pr, R.string.menu_history).addTabItem(R.mipmap.icon_main_tab_mine_pr, R.string.menu_mine);
        mainBottomNavigationBar.addFragment(HomeFragment.newInstance()).addFragment(HistoryFragment.newInstance()).addFragment(MineFragment.newInstance());
        mainBottomNavigationBar.setTabSelectedListener(this);
        mainBottomNavigationBar.setDefaultFragment(TAB_HOME);
    }


    /**
     * 启动定位服务
     */
    private void startLocationServer() {
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /**
         * 将这一行注释掉，阻止activity保存fragment的状态
         */
//        super.onSaveInstanceState(outState);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mainPresenter = new MainPresenter(MainRemoteRepo.newInstance(), this);
        mainPresenter.reqQiNiuToken();

        checkAppVersionPresenter = new CheckAppVersionPresenter(CheckAppVersionRemoteRepo.newInstance(), this);
        checkAppVersionPresenter.checkAppVersion();

        mTapMapSearchPresenter = new TapMapSearchPresenter(TapMapSearchRepo.getInstance(), this);
        mRFIDSearchPresenter = new RFIDSearchPresenter(RFIDSearchRepo.getInstance(), this);
//        mFormConfigPresenter = new FormConfigPresenter(FormConfigRemoteRepo.getInstance());
//        mFormConfigPresenter.saveFormConfig();
        mSignInPresenter = new SignInPresenter(DeviceRemoteRepo.getInstance(), this);

    }

    private int currentTabPosition;

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_HOME:
                setToolbarCenterTitle(R.string.menu_home);
                mapView.setVisibility(View.VISIBLE);
                break;
            case TAB_HISTORY:
                setToolbarCenterTitle(R.string.menu_history);
                mapView.setVisibility(View.GONE);
                break;
            case TAB_MINE:
                setToolbarCenterTitle(R.string.menu_mine);
                mapView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void refreshLocation(MPointEntity point) {
        mapView.updateCurrentLocation(new Point(point.getX(), point.getY()));
    }

    @Override
    public void requestUniteListSuccess(UnitInfoEntity entity) {
//        if (entity != null) {
//            /*这边本来是传过来一个 List<UnitInfoEntity> , 赋值给全局变量，所以现在稍加修改*/
//            /*this.unitEntities = entities;*/
//            this.unitEntities.add(entity);
//            MPointEntity mercatorPoint = ILngLatMercator.lonLat2WebMercator(entity.getLng(), entity.getLat());
//            Point point = new Point(mercatorPoint.getX(), mercatorPoint.getY());
//            mapView.setPoint(point, R.mipmap.icon_factory);
//        }
    }


    @Override
    public void showTapSearchResult(List<MapEntity> data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_BUNDLE_DEVICE_INFO, (Serializable) data);
        ISkipActivityUtil.startIntent(this, ShowSearchedListActivity.class, bundle);
    }

    @Override
    public void showTapSearchError() {
        showToast("查询失败");
    }

    @Override
    public IdentifyParameters getTapSearchParameters() {
        return mIdentifyParameters;
    }

    @Override
    public void onClickInsideBtton() {
    }

    @Override
    public void onSingleTap(float v, float v1) {
        Envelope env = new Envelope();
        mapView.getExtent().queryEnvelope(env);
        mIdentifyParameters = mapView.getIdentifyParameters(50, mapView.toMapPoint(v, v1),
                mapView.getSpatialReference(), mapView.getHeight(), mapView.getWidth(), env);
        mTapMapSearchPresenter.tapSearch();
    }

    /**
     * 接收任务后处理
     *
     * @param messageBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainTaskEntity(MessageBean messageBean) {
        if (messageBean.getCode() == 0) {
            setTaskNum();
            return;
        }
    }

    /**
     * 设置任务图标number
     */
    public void setTaskNum() {
//        String userId = (String) ISpfUtil.getValue(this, Constants.ACCOUNT_ID_KEY, "");
//        currentTabPosition = mainBottomNavigationBar.getCurrentSelectedPosition();
//        List<TaskListAndDetailEntity> taskListAndDetailEntities2 =
//                TaskBiz.getInstance().getTaskListAndDetailEntityListBeingProcessed(userId);
//        //  2016/11/24 设置新任务个数
//        int size = taskListAndDetailEntities2.size();
//        if (size == 0) {
//            mainBottomNavigationBar.removeTabSign(TAB_TASK);
//        } else {
//            mainBottomNavigationBar.addTabSign(TAB_TASK, size);
//        }
//        /*优化处理，在任务界面才查询待处理任务个数 hezhiWu*/
//        if (currentTabPosition == TAB_TASK) {
//            List<TaskListAndDetailEntity> taskListAndDetailEntities1 = TaskBiz.getInstance().getTaskListAndDetailEntityList(userId);
//            setToolbarCenterTitle("任务(" + taskListAndDetailEntities1.size() + ")");
//            mainBottomNavigationBar.setFirstSelectedPosition(TAB_TASK).initialise();
//        } else {
//            mainBottomNavigationBar.setDefaultFragment(currentTabPosition);
//        }
    }

    /**
     * 开始网络请求计划规则
     */
    private void stratTimeCount() {
        //先删除之前的数据
//        ZNAPPlication.getDaoSession().getPlanToInspectEntityDao().deleteAll();
//        String userId = (String) ISpfUtil.getValue(this, Constants.ACCOUNT_ID_KEY, "");
//        Observable.create((Observable.OnSubscribe<String>) subscriber ->
//                GetPlanRequest.getInstance().getPlan(subscriber))
//                .map(s -> {
//                    PlanEntity planEntity = new Gson().fromJson(s, new TypeToken<PlanEntity>() {
//                    }.getType());
//                    if (planEntity.getData() != null) {
//                        for (int i = 0; i < planEntity.getData().size(); i++) {
//                            PlanToInspectEntity planToInspectEntity = new PlanToInspectEntity();
//                            PlanEntity.DataBean dataBean = planEntity.getData().get(i);
//                            List<PlanEntity.DataBean.DevicesBean> devicesBean = dataBean.getDevices();
//                            String codes = "";
//                            String rfids = "";
//                            for (int j = 0; j < devicesBean.size(); j++) {
//                                if (j == 0) {
//                                    codes = devicesBean.get(j).getCode();
//                                    rfids = devicesBean.get(j).getRFID();
//                                } else {
//                                    codes += "," + devicesBean.get(j).getCode();
//                                    rfids += "," + devicesBean.get(j).getRFID();
//                                }
//                            }
//                            planToInspectEntity.setPlanId(dataBean.getId())
//                                    .setUserId(userId)
//                                    .setPlanRule(getPlanTime(dataBean.getScheduler()).toString())
//                                    .setEndTime(dataBean.getEndAt())
//                                    .setExecutor(dataBean.getPersonName())
//                                    .setStartTime(dataBean.getStartAt())
//                                    .setPlanName(dataBean.getName())
//                                    .setNote(dataBean.getNote())
//                                    .setCodes(codes)
//                                    .setRfids(rfids);
//                            ZNAPPlication.getDaoSession().getPlanToInspectEntityDao().insert(planToInspectEntity);
//                        }
//                    }
//                    return "";
//                }).subscribe(s -> {
//            startPlanCountTime(userId);
//        });
    }

    /**
     * 获取计划时间段
     *
     * @param scheduler
     * @return
     */
    private List<Integer> getPlanTime(List<Integer> scheduler) {
        List<Integer> timeCount = new ArrayList<>();
        for (int i = 0; i < scheduler.size(); i++) {
            timeCount.add(scheduler.get(i));
        }
        return timeCount;
    }

    /**
     * 获取当前计划
     *
     * @param userId
     * @return
     */
    private PlanToInspectEntity getCurrentPlanEntity(String userId) {
        List<PlanToInspectEntity> planToInspectEntitys = ZNAPPlication.getDaoSession()
                .getPlanToInspectEntityDao().queryBuilder().where(
                        PlanToInspectEntityDao.Properties.UserId.eq(userId)
                ).orderAsc(PlanToInspectEntityDao.Properties.StartTime).list();
        if (planToInspectEntitys.size() != 0) {
            return planToInspectEntitys.get(0);
        }
        return null;
    }

    /**
     * 开始根据时间倒计时
     *
     * @param userId
     */
    private void startPlanCountTime(String userId) {
        final PlanToInspectEntity planToInspectEntity = getCurrentPlanEntity(userId);
        if (planToInspectEntity != null) {
            String[] strings = planToInspectEntity.getPlanRule().replace("[", "").replace("]", "")
                    .replace(" ", "").split(",");
            for (int i = 0; i < strings.length; i++) {
                Integer time = Integer.valueOf(strings[i]);
                if (time == 0) {
                    time = 24 * 60;
                }
                int remainTime = time - IDateTimeUtils.getTimeMinuteNow();
                if (remainTime >= 0) {
                    new CountDownTimer(remainTime * 1000 * 60, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            WindowUtil.showPopupWindow(() -> {
//                                //  2016/12/1  计划巡查入口
                                ISkipActivityUtil.startIntent(MainActivity.this, CheckPlanActivity.class);
                            });
                        }
                    }.start();
                }
            }
        }
    }

    /**
     * 设置jpush别名
     */
    private void setAlias() {
        String userId = (String) ISpfUtil.getValue(this, Constants.ACCOUNT_ID_KEY, "");
        JPushInterface.setAlias(this, Constants.JPUSHALIAS + userId, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出Dialog
     */
    private void showExitDialog() {
        DialogFactory.showMsgDialog(getActivity(), "提示", "确定要退出" + IUtils.getStrToRes(getActivity(), R.string.app_name) + "?",
                v -> IActivityManage.getInstance().exit());
    }

    @Override
    public void showDeviceInfo(DeviceInfo deviceInfo) {
        /*扫描成功，获取信息，展示Dialog*/
        mScanDeviceInfo = deviceInfo;
        List<String> details = new ArrayList<>();
        if (deviceInfo.getExt() != null) {
            details = Arrays.asList(deviceInfo.getExt().split(","));
        }
        mSignInDetailsAdapter = new SignInDetailsAdapter(details);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_signin, null);
        TextView signInPoint = ButterKnife.findById(view, R.id.dialogContent_signInPoint);
        TextView userName = ButterKnife.findById(view, R.id.dialogContent_userName);
        TextView time = ButterKnife.findById(view, R.id.dialogContent_time);
        TextView confirm = ButterKnife.findById(view, R.id.dialogContent_signIn_confirm);
        RecyclerView recyclerView = ButterKnife.findById(view, R.id.dialogContent_signIn_details_recyclerView);

        signInPoint.setText(deviceInfo.getCode());
        userName.setText(ZNAPPlication.getUserInfoEntity().getName());
        time.setText(IDateTimeUtils.formatDateHours(System.currentTimeMillis()));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mSignInDetailsAdapter);
        if (mSignInDialog == null || !mSignInDialog.isShowing()) {
            mSignInDialog = DialogFactory.createDialog(this, "签到确认", view);
            mSignInDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    RFIDConfig.initReadLoop();
                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RFIDConfig.initReadLoop();
                    mSignInDialog.dismiss();
                    mSignInPresenter.signIn(mSignInDetailsAdapter.getCheckedDetails());
                }
            });
        }

    }

    @Override
    public void showRFIDSearchFail() {
        DialogFactory.warningDialog(this, "扫描结果", "网络请求失败", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RFIDConfig.initReadLoop();
            }
        });
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = DialogFactory.createLoadingDialog(this, "处理中");
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showSignInSuccess() {
        ToastUtil.showToast(this, "签到成功");
    }

    @Override
    public void showSignInFail() {
        ToastUtil.showToast(this, "签到失败,请重新签到");
        showDeviceInfo(mScanDeviceInfo);
    }

    @Override
    public DeviceInfo getDeviceInfo() {
        return mScanDeviceInfo;
    }

    @Override
    public void showLinkDeviceDialog() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = DialogFactory.showMsgDialog(this, "扫描结果", "当前牌号未关联，是否关联签到点？", "关联", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(DisposableLinkActivity.KEY_BUNDLE_RFID, mRFID);
                    ISkipActivityUtil.startIntent(MainActivity.this, DisposableLinkActivity.class, bundle);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RFIDConfig.initReadLoop();
                }
            });
        }
    }

    @Override
    public String getRFID() {
        return mRFID;
    }

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showIsLatestVersion() {

    }

    @Override
    public void showCheckingDialog() {

    }

    @Override
    public void dismissCheckingDialog() {

    }
}
