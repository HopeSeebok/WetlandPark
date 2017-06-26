package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.source;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.TroubleShooter;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/6 17:36
 * @description:
 */

public interface ShootTroubleDataSource {
    interface UploadCallBack{
        void onUploadSuccess();
        void onUploadFailed();
        List<String> getLocalImageList();
        TroubleShooter getUploadEntity(List<Image> path);
    }

    interface SaveCallBack{
        void onUpdateSuccess();
        void onSaveSuccess(long id);
        void onSaveFailed();
    }

    void upload(UploadCallBack callBack);

    void save(TroubleShooterTable troubleShooterTable, SaveCallBack callBack);
}
