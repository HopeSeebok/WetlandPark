package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.source;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.greedao.TroubleTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/2 17:09
 * @description:
 */

public interface MakeTroubleDataSource {

    interface UploadCallBack{
        void onUploadSuccess();
        void onUploadFailed();
        TroubleInfo getUploadEntity(List<Image> path);
        List<String> getLocalImageList();
    }

    interface SaveCallBack{
        void onUpdateSuccess();
        void onSaveSuccess(long id);
        void onSaveFailed();
    }

    void upload(UploadCallBack callBack);

    void save(TroubleTable troubleTable, SaveCallBack callBack);
}
