package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.MaintainDeviceTable;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.QiNiuDataSource;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.QiNiuImageUploadRepo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.MaintainDeviceEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.source.MaintainDeviceSource;
import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice
 * @Description:
 * @date 2016/11/17 15:01
 */

public class MaintainDevicePresenter implements MaintainDeviceContract.Presenter, MaintainDeviceSource.SaveCallBack, MaintainDeviceSource.UploadCallBack, UploadCallBackListener {

    private MaintainDeviceSource mSource;
    private QiNiuDataSource qiNiuDataSource;
    private MaintainDeviceContract.View mView;
    private MaintainDeviceTable mTable;
    private boolean isSubmit = false;

    public MaintainDevicePresenter(MaintainDeviceSource source, MaintainDeviceContract.View view){
        this.mSource = source;
        this.mView = view;
        mView.setPresenter(this);
        qiNiuDataSource = new QiNiuImageUploadRepo();
    }

    @Override
    public void save() {
        mTable = mView.getMaintainData();
        mSource.save(mTable, this);
    }

    @Override
    public void submit() {
        mView.showUploadingDialog();
        isSubmit = true;
        //先保存数据在本地
        save();
        //再将图片上传到服务器
        String images = mTable.getImagesUrl();
        //查看有没有图片
        if (images.length() > 0){
            List<String> list = Arrays.asList(images.split(","));
            if (list != null && list.size() > 0) {
                qiNiuDataSource.uploadImage(list, this);
            }
        }else{
            //将'数据库实体'转成'JSON实体'
            MaintainDeviceEntity entity = new MaintainDeviceEntity();
            entity.setDeviceId(mTable.getDeviceID());
            //TODO 设备状态 默认是0
            entity.setDeviceStatus(0);
            entity.setWorkLat(mTable.getLat());
            entity.setWorkLng(mTable.getLng());
            entity.setWorkAddr(mTable.getWorkAddress());
            entity.setNote(mTable.getNote());
            mSource.upload(entity, this);
        }
    }

    ////////////////////////////////////////////
    @Override
    public void onUpdateSuccess() {
        if (!isSubmit) {
            mView.showSaveSuccessMsg();
        }
    }

    @Override
    public void onSaveSuccess(long id) {
        mTable.setId(id);
        if (!isSubmit) {
            mView.showSaveSuccessMsg();
        }
    }

    @Override
    public void onSaveFailed() {
        mView.showSaveFailedMsg("保存失败");
    }

    //////////////////////////////////////////////
    @Override
    public void onUploadStart() {

    }

    @Override
    public void onUploadEnd() {

    }

    @Override
    public void onProgess(double percent) {

    }

    @Override
    public void onUploadComplete(List<Image> path) {
        //将'数据库实体'转成'JSON实体'
        MaintainDeviceEntity entity = new MaintainDeviceEntity();
        entity.setDeviceId(mTable.getDeviceID());
        //TODO 设备状态 默认是0
        entity.setDeviceStatus(0);
        entity.setWorkLat(mTable.getLat());
        entity.setWorkLng(mTable.getLng());
        entity.setWorkAddr(mTable.getWorkAddress());
        entity.setNote(mTable.getNote());
        entity.setWorkImgs(path);
        mSource.upload(entity, this);
    }

    @Override
    public void onUploadFailure() {
        mView.showUploadFailedMsg("上传失败");
        mView.dismissUploadingDialog();
    }

    /////////////////////////////////////////////
    @Override
    public void onUploadSuccess() {
        //上传成功后 修改数据库中维护任务的状态为已上传
        mTable.setLocalTag(Constants.LocalState.SUBMITTED);
        mSource.save(mTable, this);
        mView.dismissUploadingDialog();
        mView.showUploadSuccessMsgAndFinishActivity();
    }

    @Override
    public void onUploadFailed() {
        mView.dismissUploadingDialog();
        mView.showUploadFailedMsg("上传失败");
    }
}
