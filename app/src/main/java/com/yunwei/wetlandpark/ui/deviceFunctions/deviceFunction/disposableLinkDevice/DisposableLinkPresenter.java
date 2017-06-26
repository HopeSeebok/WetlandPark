package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice.data.source.DisposableLinkDataSource;
import com.yunwei.wetlandpark.utils.IUtils;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/7 0:18
 * @description:
 */

public class DisposableLinkPresenter implements DisposableLinkContract.Presenter,DisposableLinkDataSource.UploadCallBack{


    private final DisposableLinkDataSource mRepo;
    private final DisposableLinkContract.View mView;

    public DisposableLinkPresenter(DisposableLinkDataSource repo, DisposableLinkContract.View view) {
        mRepo = repo;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void submit() {
        mView.showUploadingDialog();
        mRepo.upload(this);
    }

    @Override
    public void onUploadSuccess() {
        mView.dismissUploadingDialog();
        mView.showUploadSuccessMsgAndFinishActivity();
    }

    @Override
    public void onUploadFailed() {
        mView.dismissUploadingDialog();
        mView.showUploadFailedMsg();
    }

    @Override
    public DeviceInfo getUploadEntity(List<Image> path) {
        DeviceInfo viewEntity = mView.getViewEntity();
        viewEntity.setImgs(null);
        if (path != null) {
            viewEntity.setImgs(path);
        }
        return viewEntity;
    }

    @Override
    public List<String> getLocalImageList() {
        List<Image> images = mView.getViewEntity().getImgs();
        return IUtils.convertImgListToStringList(images);
    }
}
