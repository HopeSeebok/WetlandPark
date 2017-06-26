package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble;

import android.text.TextUtils;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TroubleTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.source.MakeTroubleDataSource;
import com.yunwei.wetlandpark.utils.IUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/2 17:07
 * @description:
 */

public class MakeTroublePresenter implements MakeTroubleContract.Presenter, MakeTroubleDataSource.SaveCallBack,MakeTroubleDataSource.UploadCallBack{

    private final MakeTroubleDataSource mRepo;
    private final MakeTroubleContract.View mView;
    private long mTableID = -1;
    private int mLocalTag = Constants.VALUE_UNSUBMITTED;

    public MakeTroublePresenter(MakeTroubleDataSource repo, MakeTroubleContract.View view) {
        mRepo = repo;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void save() {
        TroubleTable troubleData = mView.getTroubleData();
        if (mTableID != -1) {
            troubleData.setId(mTableID);
        }
        troubleData.setLocalTag(mLocalTag);
        mRepo.save(troubleData, this);
    }

    @Override
    public void submit() {
        mView.showUploadingDialog();
        mRepo.upload(this);
    }

    @Override
    public void onUpdateSuccess() {
        if (mLocalTag == Constants.VALUE_UNSUBMITTED) {
            mView.showSaveSuccessMsg("更新成功");
        }
    }

    @Override
    public void onSaveSuccess(long id) {
        mTableID = id;
        if (mLocalTag == Constants.VALUE_UNSUBMITTED) {
            mView.showSaveSuccessMsg("保存成功");
        }
    }

    @Override
    public void onSaveFailed() {
        mView.showSaveFailedMsg();
    }

    @Override
    public void onUploadSuccess() {
        /*成功提交时更新提交状态*/
        mLocalTag = Constants.VALUE_SUBMITTED;
        save();
        mView.dismissUploadingDialog();
        mView.showUploadSuccessMsgAndFinishActivity();
    }

    @Override
    public void onUploadFailed() {
        mView.dismissUploadingDialog();
        mView.showUploadFailedMsg();
    }

    @Override
    public TroubleInfo getUploadEntity(List<Image> path) {
        TroubleInfo remote = new TroubleInfo();
        TroubleTable table = mView.getTroubleData();
        remote.setLat(table.getLat());
        remote.setLng(table.getLng());
        remote.setDangerType(table.getTroubleType());
        remote.setMissionLevel(table.getEmergencyLevel());
        remote.setHandlerId(table.getUserID());
        remote.setHandlerName(table.getUserName());
        remote.setNote(table.getNote());
        if (path != null) {
            remote.setImgs(path);
        }
        return remote;
    }

    @Override
    public List<String> getLocalImageList() {
        String imagesUrl = mView.getTroubleData().getImagesUrl();
        if (!TextUtils.isEmpty(imagesUrl)) {
            return Arrays.asList(imagesUrl.split(","));
        }
        return null;
    }
}
