package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.greedao.CheckPlanTable;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntity;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntityDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.CheckPlanEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.source.CheckPlanDataSource;
import com.yunwei.wetlandpark.utils.ILog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/15 9:53
 * @description:
 */

public class CheckPlanPresenter implements CheckPlanContract.Presenter,
                                            CheckPlanDataSource.UploadCallBack{

    private CheckPlanDataSource mRepo;
    private CheckPlanContract.View mView;
    private CheckPlanTable table;
    private PlanToInspectEntity entity;

    private List<String> checked_ball = new ArrayList<>();//已巡查点

    private int checkPoint; //应检个数
    private int checkedPoint; //实检个数

    private String[] rfidArray, codeArray;

    public CheckPlanPresenter(CheckPlanDataSource repo, CheckPlanContract.View view) {
        this.mRepo = repo;
        this.mView = view;
        mView.setPresenter(this);
        entity = getCurrentPlanEntity(ZNAPPlication.getUserInfoEntity().getId());
        if (entity == null) {
            mView.showNoPlanMsg();
            return;
        }
        rfidArray = entity.getRfids().split(",");
        codeArray = entity.getCodes().split(",");
        checkPoint = rfidArray.length;
    }

    @Override
    public void upload() {
        mView.showUploadingDialog();
        mRepo.upload(this);
    }

    @Override
    public void searchScannedRFID(String tid) {
        mView.showQueryDialog();
        //查与rfid对应的code
        for (int i = 0;i<checkPoint;i++){
            if (tid.equals(rfidArray[i])){
                check(codeArray[i]);
                break;
            }else if(i == checkPoint - 1){
                mView.dismissQueryDialog();
                mView.showScannedFailMsg("该巡查点未在当前巡查计划中!");
            }
        }
    }

    @Override
    public PlanToInspectEntity getCheckPlan() {
        return entity;
    }

    @Override
    public int getCheckPointCount() {
        return checkPoint;
    }

    @Override
    public void onUploadSuccess() {
        mView.dismissUploadingDialog();
        mView.showUploadSuccessMsgAndFinishActivity();
    }

    @Override
    public void onUploadFailed(String msg) {
        mView.dismissUploadingDialog();
        mView.showUploadFailedMsg(msg);
    }

    @Override
    public CheckPlanEntity getUploadEntity() {
        table = mView.getPlanData();
        CheckPlanEntity remoteEntity = new CheckPlanEntity();
        remoteEntity.setPlanId(table.getPlanID());
        //摄氏度符号
        String temp = mView.getActivity().getResources().getString(R.string.temp_symbol);
        String[][] value = {
                {table.getFirstSecondRoomTemp() + temp, "1"},
                {table.getFirstSecondRoomHum() + "%", "1"},
                {table.getThirdRoomTemp() + temp, "1"},
                {table.getThirdRoomHum() + "%", "1"},
                ifDangerous(table.getFirefightingSystem()),
                ifDangerous(table.getUsbRoom()),
                ifDangerous(table.getAirConditioner()),
                ifNormal(table.getCompensationMeter()),
                ifNormal(table.getDistributionRoom()),
                ifNormal(table.getCoreRouter()),
                ifNormal(table.getAccessSystem()),
                {checkedPoint+"", checkPoint+""},
                {table.getCheckedUser(), "1"}};
        remoteEntity.setValues(value);
        ILog.d("TAG", value.toString());
        return remoteEntity;
    }

    @Override
    public CheckPlanTable getSaveEntity() {
        return table;
    }

    /**
     * 查找周期计划
     * @param userId
     * @return
     */
    private PlanToInspectEntity getCurrentPlanEntity(String userId) {
        List<PlanToInspectEntity> planToInspectEntitys = ZNAPPlication.getDaoSession()
                .getPlanToInspectEntityDao().queryBuilder()
                .where(PlanToInspectEntityDao.Properties.UserId.eq(userId))
                .orderAsc(PlanToInspectEntityDao.Properties.StartTime)
                .list();
        if (planToInspectEntitys.size()!=0) {
            return planToInspectEntitys.get(0);
        }
        return null;
    }

    /**
     * 巡查
     * @param code
     */
    public void check(String code){
        String numberStr = code.substring(code.length() - 2);
        //判断是否重复巡查
        for (int i = 0;i<checked_ball.size();i++){
            if (numberStr.equals(checked_ball.get(i))){
                mView.dismissQueryDialog();
                mView.showScannedFailMsg("该巡查点已巡查!");
                return;
            }
        }
        //如果不重复,亮起对应的小球
        int number = Integer.parseInt(numberStr);
        mView.dismissQueryDialog();
        if (number > 0 && number <= checkPoint) {
            int index = number - 1;
            mView.showScannedPoint(index);
            checked_ball.add(numberStr);
            ++checkedPoint;
        }else if(number == 0){
            mView.showScannedFailMsg("编号最后两位不能为\"00\"!");
        }else {
            mView.showScannedFailMsg("编号检测异常!");
        }
    }


    /**
     * 判断是否有危险,无危险则为正常
     * @param str
     * @return
     */
    public String[] ifDangerous(String str){
        switch (str){
            case "0":
                return new String[]{"正常", "1"};
            case "1":
                return new String[]{"异常", "0"};
            default:
                return new String[]{"异常", "0"};
        }
    }

    /**
     * 根据字符串判断是否正常
     * @param str
     * @return
     */
    public String[] ifNormal(String str){
        switch (str){
            case "0":
                return new String[]{"异常", "0"};
            case "1":
                return new String[]{"正常", "1"};
            default:
                return new String[]{"异常", "0"};
        }
    }
}