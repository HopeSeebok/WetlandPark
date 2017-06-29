package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.signIn;

import android.support.annotation.NonNull;

import com.yunwei.wetlandpark.greedao.SignInTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.SignInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.source.DeviceDataSource;
import com.yunwei.wetlandpark.utils.ILog;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/16 14:01
 * @description:
 */

public class SignInPresenter implements SignInContract.Presenter,DeviceDataSource.UploadCallBack{

    private DeviceDataSource mRepo;
    private SignInContract.View mView;
    private SignInfo mSignInfo;
    private List<String> mCheckedDetails;

    public SignInPresenter(DeviceDataSource repo, SignInContract.View view) {
        mRepo = repo;
        mView = view;
    }

    @Override
    public void signIn(List<String> checkedDetails) {
        mCheckedDetails = checkedDetails;
        mView.showLoadingDialog();
        mRepo.signIn(this);
    }

    @Override
    public void onUploadSuccess() {
        mView.dismissLoadingDialog();

        SignInTable signInTable = new SignInTable();
        DeviceInfo deviceInfo = mView.getDeviceInfo();
        signInTable.setCode(deviceInfo.getCode());
        signInTable.setTime(System.currentTimeMillis());
        signInTable.setUserID(ZNAPPlication.getUserInfoEntity().getId());
        signInTable.setUserName(ZNAPPlication.getUserInfoEntity().getName());
        signInTable.setNote(getDetailsString().toString());
        try {
            ZNAPPlication.getDaoSession().getSignInTableDao().insert(signInTable);
            mView.showSignInSuccess();
            ILog.e("SignInPresenter","保存签到记录成功");
        } catch (Exception e) {
            mView.showSignInFail();
            ILog.e("SignInPresenter","保存签到记录失败");
            e.printStackTrace();
        }
    }

    @Override
    public void onUploadFail() {
        mView.dismissLoadingDialog();
        mView.showSignInFail();
    }

    @Override
    public Object getUploadData() {
        mSignInfo = new SignInfo();
        DeviceInfo deviceInfo = mView.getDeviceInfo();
        mSignInfo.setDeviceId(deviceInfo.getId());
        mSignInfo.setAddr(ZNAPPlication.getInstance().getCurrentAddr());
        mSignInfo.setLng(ZNAPPlication.getInstance().getX());
        mSignInfo.setLat(ZNAPPlication.getInstance().getY());
        StringBuilder detail = getDetailsString();
        mSignInfo.setNote(detail.toString());
        return mSignInfo;
    }

    @NonNull
    private StringBuilder getDetailsString() {
        StringBuilder detail = new StringBuilder();
        if (mCheckedDetails.size() > 0) {
            for (int i = 0; i < mCheckedDetails.size(); i++) {
                detail.append(mCheckedDetails.get(i));
                detail.append(",");
            }
            detail.deleteCharAt(detail.length()-1);
        }
        return detail;
    }
}
