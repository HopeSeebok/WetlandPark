package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.data.source.RFIDSearchDataSource;

/**
 * @author CBOK
 * @date 2016/12/7 1:45
 * @description:
 */

public class RFIDSearchPresenter implements RFIDSearchContract.Presenter, RFIDSearchDataSource.RFIDSearchCallBack {

    private final RFIDSearchDataSource mRepo;
    private final RFIDSearchContract.View mView;

    public RFIDSearchPresenter(RFIDSearchDataSource repo, RFIDSearchContract.View view) {
        mRepo = repo;
        mView = view;
    }

    @Override
    public void searchByRFID() {
        mView.showLoadingDialog();
        mRepo.searchByRFID(this);
    }

    @Override
    public void onSearchDataLoaded(DeviceInfo deviceInfo) {
        mView.showDeviceInfo(deviceInfo);
        mView.dismissLoadingDialog();
    }

    @Override
    public void onSearchDataNotAvailable() {
        mView.dismissLoadingDialog();
        mView.showRFIDSearchFail();
    }

    @Override
    public void onRFIDNotLinked() {
        mView.dismissLoadingDialog();
        mView.showLinkDeviceDialog();
    }

    @Override
    public String getRFID() {
        return mView.getRFID();
    }
}
