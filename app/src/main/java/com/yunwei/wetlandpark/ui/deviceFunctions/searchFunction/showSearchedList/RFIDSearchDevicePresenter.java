package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList;


import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source.SearchDeviceDataSource;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source.SearchDeviceRemoteRepo;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.searchDevice
 * @Description:查询设施信息By RFID
 * @date 2016/11/17 17:12
 */

public class RFIDSearchDevicePresenter implements SearchDeviceDataSource.RFIDSearchDeviceCallBack, ShowSearchedListContract.RFIDSearchPresenter {

    private SearchDeviceRemoteRepo mRemoteRepo;
    private ShowSearchedListContract.RFIDSearchView mView;

    public RFIDSearchDevicePresenter(SearchDeviceRemoteRepo remoteRepo, ShowSearchedListContract.RFIDSearchView view) {
        this.mRemoteRepo = remoteRepo;
        this.mView = view;
    }

    @Override
    public void searchByRFID() {
        mView.showLoadingDialog();
        mRemoteRepo.searchByRFID(mView.getActivity(), this);
    }

    @Override
    public void onSearchDataLoaded(DeviceInfo deviceInfo) {
        mView.showDeviceInfo(deviceInfo);
        mView.dismissLoadingDialog();
    }

    @Override
    public void onSearchDataNotAvailable() {
        mView.dismissLoadingDialog();
        mView.showRFIDSearchFailur("查询失败!");
    }

    @Override
    public String getRFID() {
        return mView.getRFID();
    }

    @Override
    public void onRFIDNotRelated(String rfid) {
        mView.onRFIDNotRelated(rfid);
        mView.dismissLoadingDialog();
    }
}
