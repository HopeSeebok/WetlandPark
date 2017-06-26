package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.biz.ITrackUpload;
import com.yunwei.wetlandpark.ui.biz.impl.TrackUploadBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.TrackUploadListener;
import com.yunwei.wetlandpark.ui.view.TrackUploadView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/9 10:39
 */
public class UploadTrackPresenter implements TrackUploadListener {

    private TrackUploadView trackUploadView;
    private ITrackUpload trackUploadBiz;

    public UploadTrackPresenter(TrackUploadView trackUploadView) {
        this.trackUploadView = trackUploadView;
        this.trackUploadBiz = new TrackUploadBiz();
    }

    public void uplodaTrack(TrackEntity entity) {
        trackUploadBiz.uploadTrack(trackUploadView.getActivity(), entity, this);
    }

    @Override
    public void onStartTrackUpload() {
        trackUploadView.onStartTrackUpload();
    }

    @Override
    public void onEndTrackUpload() {
        trackUploadView.onEndTrackUpload();
    }

    @Override
    public void onFailureTrackUpload(int code, String error) {
        trackUploadView.onFailureTrackUpload(code, error);
    }

    @Override
    public void onSuccessTrackUpload(int code, String msg) {
        trackUploadView.onSuccessTrackUpload(code, msg);
    }
}
