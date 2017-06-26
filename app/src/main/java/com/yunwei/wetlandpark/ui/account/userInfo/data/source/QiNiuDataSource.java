package com.yunwei.wetlandpark.ui.account.userInfo.data.source;

import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.userInfo.data.source
 * @Description:七牛上传图片
 * @date 2016/11/15 17:57
 */

public interface QiNiuDataSource {

    void uploadImage(List<String> list, UploadCallBackListener uploadCallBackListener);
}
