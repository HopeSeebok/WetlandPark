package com.yunwei.library.qiniu;

import com.yunwei.library.data.Image;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.library.qiniu
 * @Description:
 * @date 2016/7/27 16:34
 */
public interface UploadCallBackListener {
    void onUploadStart();

    void onUploadEnd();

    void onProgess(double percent);

    void onUploadComplete(List<Image> path);

    void onUploadFailure();
}
