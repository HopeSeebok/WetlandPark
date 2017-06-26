package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList;


import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source.SearchDeviceDataSource;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;

/**
 * @author CBOK
 * @date 2016/11/13 23:48
 * @description:
 */

public class ShowSearchedListPresenter implements ShowSearchedListContract.Presenter,SearchDeviceDataSource.GuIDSearchDeviceCallBack,SearchDeviceDataSource.TroubleSearchCallBack{


    private final SearchDeviceDataSource mRepo;
    private final ShowSearchedListContract.View mView;

    public ShowSearchedListPresenter(SearchDeviceDataSource repo, ShowSearchedListContract.View view) {
        mRepo = repo;
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void searchByID() {
        mView.showLoadingDialog();
        switch (mView.getLayerID()) {
            case 0:
                mRepo.searchTroubleByID(mView.getActivity(),this);
                break;
            case 1:
                mRepo.searchDeviceByGuID(mView.getActivity(), this);
                break;
        }
    }

    @Override
    public void onSearchDataLoaded(DeviceInfo deviceInfo) {
        mView.dismissLoadingDialog();
        mView.showDeviceInfo(deviceInfo);
    }

    @Override
    public void onSearchDataLoaded(TroubleInfo troubleInfo) {
        mView.dismissLoadingDialog();
        mView.showTroubleInfo(troubleInfo);
    }

    @Override
    public void onSearchDataNotAvailable() {
        mView.dismissLoadingDialog();
        mView.showIDSearchFailedMsg();
    }

    @Override
    public String getID() {
        return mView.getID();
    }

    @Override
    public String getGuID() {
        return mView.getID();
    }


}
