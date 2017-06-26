package com.yunwei.wetlandpark.ui.account.userInfo.data.source;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.qiniu.QiNiuImageUploadManager;
import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.userInfo.data.source
 * @Description:七牛上传图片
 * @date 2016/11/15 18:00
 */

public class QiNiuImageUploadRepo implements QiNiuDataSource{

    @Override
    public void uploadImage(List<String> list, UploadCallBackListener uploadCallBackListener) {
        QiNiuImageUploadManager.uploadImage(
                ISpfUtil.getValue(Constants.QINIU_TOKEN_KEY, "").toString(),
                list,
                uploadCallBackListener);
    }

}
