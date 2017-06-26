package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.biz.IUpdateTrack;
import com.yunwei.wetlandpark.ui.biz.impl.UpdateTrackBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.UpdateTrackListener;
import com.yunwei.wetlandpark.ui.view.UpdateTrackView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:修改足迹P层
 * @date 2016/9/20 15:13
 */
public class UpdateTrackPresenter implements UpdateTrackListener {

    private UpdateTrackView updateTrackView;
    private IUpdateTrack updateTrack;

    public UpdateTrackPresenter(UpdateTrackView updateTrackView) {
        this.updateTrackView = updateTrackView;
        this.updateTrack = new UpdateTrackBiz();
    }

    public void updateTrack(Track track) {
        updateTrack.updateTrack(updateTrackView.getActivity(), track, this);
    }

    @Override
    public void onUpdateTrackSuccess() {
        updateTrackView.onUpdateTrackSuccess();
    }

    @Override
    public void onUpdateTrackStart() {
        updateTrackView.onUpdateTrackStart();
    }

    @Override
    public void onUpdateTrackEnd() {
        updateTrackView.onUpdateTrackEnd();
    }

    @Override
    public void onUpdateTrackFailure(int code, String errod) {
        updateTrackView.onUpdateTrackFailure(code, errod);
    }
}
