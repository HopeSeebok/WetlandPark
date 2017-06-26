package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble;

import android.text.TextUtils;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.TroubleShooter;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.source.ShootTroubleDataSource;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.TaskBiz;
import com.yunwei.wetlandpark.utils.IUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/6 21:58
 * @description:
 */

public class ShootTroublePresenter implements ShootTroubleContract.Presenter,ShootTroubleDataSource.SaveCallBack,ShootTroubleDataSource.UploadCallBack {


    private ShootTroubleDataSource mRepo;
    private ShootTroubleContract.View mView;
    private long mTableID = -1;
    private int mLocalTag = Constants.VALUE_UNSUBMITTED;
    
    public ShootTroublePresenter(ShootTroubleDataSource repo, ShootTroubleContract.View view) {
        mRepo = repo;
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void save() {
        TroubleShooterTable troubleData = mView.getUIEntity();
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
        //改变任务状态
        TaskBiz.getInstance().changeTaskStatus(mView.getUIEntity().getMissionID(),
                mView.getUIEntity().getMissionDetailID());
        mView.dismissUploadingDialog();
        mView.showUploadSuccessMsgAndFinishActivity();
    }

    @Override
    public void onUploadFailed() {
        mView.dismissUploadingDialog();
        mView.showUploadFailedMsg();
    }

    @Override
    public TroubleShooter getUploadEntity(List<Image> path) {
        TroubleShooter remote = new TroubleShooter();
        TroubleShooterTable table = mView.getUIEntity();
        remote.setWorkLat(table.getLat());
        remote.setWorkLng(table.getLng());
        remote.setMissionDetailId(table.getMissionDetailID());
        remote.setDangerId(table.getTroubleID());
        remote.setNote(table.getNote());
        if (path != null) {
            remote.setWorkImgs(path);
        }
        return remote;
    }

    @Override
    public List<String> getLocalImageList() {
        String imagesUrl = mView.getUIEntity().getImagesUrl();
        if (!TextUtils.isEmpty(imagesUrl)) {
            return Arrays.asList(imagesUrl.split(","));
        }
        return null;
    }
}
