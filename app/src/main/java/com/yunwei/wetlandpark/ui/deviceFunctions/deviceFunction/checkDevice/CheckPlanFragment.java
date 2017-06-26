package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.greedao.CheckPlanTable;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.widget.BallTextView;
import com.yunwei.wetlandpark.widget.FormCheckBoxSelectorView;
import com.yunwei.wetlandpark.widget.FormWriteView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.rfid.RFIDConfig;
import com.yunwei.rfid.RFIDResultListener;
import com.yunwei.rfid.readmode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/11/8 11:29
 * @description:
 */

public class CheckPlanFragment extends FillBaseFragment implements CheckPlanContract.View{

    private final int PADDING = 20;

    @BindView(R.id.planFragment_plan_name)
    FormWriteView planName;
    @BindView(R.id.planFragment_plan_executor)
    FormWriteView planExecutor;
    @BindView(R.id.planFragment_plan_date)
    FormWriteView planDate;
    @BindView(R.id.planFragment_plan_note)
    FormWriteView planNote;
    @BindView(R.id.planFragment_first_second_room_temp)
    FormWriteView firstSecondRoomTemp;
    @BindView(R.id.planFragment_first_second_room_hum)
    FormWriteView firstSecondRoomHum;
    @BindView(R.id.planFragment_third_room_temp)
    FormWriteView thirdRoomTemp;
    @BindView(R.id.planFragment_third_room_hum)
    FormWriteView thirdRoomHum;
    @BindView(R.id.planFragment_firefighting_system)
    FormCheckBoxSelectorView firefightingSystem;
    @BindView(R.id.planFragment_usb_room)
    FormCheckBoxSelectorView USERoom;
    @BindView(R.id.planFragment_air_conditioner)
    FormCheckBoxSelectorView airConditioner;
    @BindView(R.id.planFragment_compensation_meter)
    FormCheckBoxSelectorView compensationMeter;
    @BindView(R.id.planFragment_distribution_room)
    FormCheckBoxSelectorView distributionRoom;
    @BindView(R.id.planFragment_core_router)
    FormCheckBoxSelectorView coreRouter;
    @BindView(R.id.planFragment_access_system)
    FormCheckBoxSelectorView accessSystem;
    @BindView(R.id.planFragment_check_point_gridlayout)
    GridLayout checkRecordGridLayout;

    private CheckPlanContract.Presenter mPresenter;
    private Dialog mLoadingDialog;
    private Dialog mQueryDialog;

    private PlanToInspectEntity plan;
    private String planId;

    private List<BallTextView> list_ball = new ArrayList<>();//总共巡查点

    @Override
    public void onResume() {
        /*初始化RFID*/
        RFIDConfig.initUHF(getContext(), false);
        RFIDConfig.setListener(rfidResultListener);
        RFIDConfig.initReadLoop();
        super.onResume();
    }

    @Override
    public void onPause() {
        RFIDConfig.closeUHF();
        super.onPause();
    }

    private RFIDResultListener rfidResultListener = new RFIDResultListener() {
        @Override
        public void RFIDResultCallBack(readmode mode) {
            RFIDConfig.stopRFIDReadLoop();
            String TID = mode.getTIDNo();
            mPresenter.searchScannedRFID(TID);
        }
    };

    @Override
    protected View getContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_check_device_new, null);
        ButterKnife.bind(this, view);

        //隐藏保存按钮
        setSaveButtonInvisible();

        plan = mPresenter.getCheckPlan();

        if (plan == null){
            showToast("计划获取失败!");
            getActivity().finish();
        }else {
            planId = plan.getPlanId();
            planName.setContentText(plan.getPlanName());
            planExecutor.setContentText(plan.getExecutor());
            planDate.setContentText(plan.getStartTime().substring(0,10) + "~" + plan.getEndTime().substring(0, 10));
            planNote.setContentText(plan.getNote());
            planName.setNonEditable();
            planExecutor.setNonEditable();
            planDate.setNonEditable();
            planNote.setNonEditable();
            addBall(mPresenter.getCheckPointCount());
        }

        return view;
    }

    private CheckPlanTable catchData() {
        CheckPlanTable table = new CheckPlanTable();
        table.setPlanID(planId);
        table.setFirstSecondRoomTemp(firstSecondRoomTemp.getContentText());
        table.setFirstSecondRoomHum(firstSecondRoomHum.getContentText());
        table.setThirdRoomTemp(thirdRoomTemp.getContentText());
        table.setThirdRoomHum(thirdRoomHum.getContentText());
        table.setFirefightingSystem(firefightingSystem.getChecked());
        table.setUsbRoom(USERoom.getChecked());
        table.setAirConditioner(airConditioner.getChecked());
        table.setCompensationMeter(compensationMeter.getChecked());
        table.setDistributionRoom(distributionRoom.getChecked());
        table.setCoreRouter(coreRouter.getChecked());
        table.setAccessSystem(accessSystem.getChecked());
        table.setCheckedUser(ZNAPPlication.getUserInfoEntity().getName());
        return table;
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected void submitAction() {
        DialogFactory.showMsgDialog(getContext(), "提示", "是否提交表单？", "提交", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upload();
            }
        }, null);
    }

    @Override
    public void showUploadingDialog() {
        mLoadingDialog = DialogFactory.createLoadingDialog(getActivity(), "正在上传..");
    }

    @Override
    public void dismissUploadingDialog() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showUploadSuccessMsgAndFinishActivity() {
        ToastUtil.showToast(getContext(), "上传成功");
        getActivity().finish();
    }

    @Override
    public void showUploadFailedMsg(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }

    @Override
    public CheckPlanTable getPlanData() {
        return catchData();
    }

    @Override
    public void showNoPlanMsg() {
        ToastUtil.showToast(getContext(),"暂时没有巡查计划");
    }

    @Override
    public void showQueryDialog() {
        mQueryDialog = DialogFactory.createLoadingDialog(getActivity(), "正在上传..");
    }

    @Override
    public void dismissQueryDialog() {
        if (mQueryDialog != null){
            mQueryDialog.dismiss();
        }
    }

    @Override
    public void showScannedPoint(int index) {
        list_ball.get(index).setmState(true);
        DialogFactory.warningDialog(this.getActivity(), "巡查成功", "该点巡查成功!",new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RFIDConfig.initReadLoop();
            }
        });
    }

    @Override
    public void showScannedFailMsg(String msg) {
        DialogFactory.warningDialog(this.getActivity(), "扫描失败", msg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RFIDConfig.initReadLoop();
            }
        });
    }

    @Override
    public void setPresenter(CheckPlanContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 询问是否退出填写巡查表的界面
     */
    public void askIfExit(){
        DialogFactory.showMsgDialog(this.getActivity(), "提示", "巡查还未结束,是否退出?", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭界面
                getActivity().finish();
            }
        });
    }

    /**
     * 添加底部打卡记录的小球
     * @param count
     */
    public void addBall(int count){
        //添加打卡记录圆球
        WindowManager wm = (WindowManager) ZNAPPlication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        int srceenWidth = wm.getDefaultDisplay().getWidth() / checkRecordGridLayout.getColumnCount();
        for (int i = 0;i<count;i++){
            BallTextView ball = new BallTextView(getContext());
            ball.setContentText(i+1+"");
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = srceenWidth - 2 * PADDING;
            params.height = srceenWidth - 2 * PADDING;
            params.setMargins(PADDING, PADDING, PADDING, PADDING);
            checkRecordGridLayout.addView(ball, params);
            list_ball.add(ball);
        }
    }

}
