package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.presenter.UpdateTrackPresenter;
import com.yunwei.wetlandpark.ui.presenter.UploadTrackPresenter;
import com.yunwei.wetlandpark.ui.view.QueryBaiduTrackHistoryView;
import com.yunwei.wetlandpark.ui.view.TrackUploadView;
import com.yunwei.wetlandpark.ui.view.UpdateTrackView;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.baiduTTS.BaiduTTSUtils;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.main.fragment
 * @Description:足迹采集
 * @date 2016/8/31 10:48
 */
public class TrackRecordFragment extends BaseFragment implements TrackUploadView, UpdateTrackView, QueryBaiduTrackHistoryView {
    /**
     * 默认最小距离
     */
    private final double defaultDis = 500.0;

    private LinearLayout startLayout, resumeLayout, pauseLayout, completeLayout;
    private TextView recordTime, recordDistance, recordSpeed;
    private LinearLayout trackLayout;
    /**
     * 计时器
     */
    private Timer timer;
    private Tracktack tracktack;

    private UploadTrackPresenter trackPresenter;
    private UpdateTrackPresenter updateTrackPresenter;
    /**
     * 百度鹰眼历史轨迹查询控制层
     */
    //private QueryBaiduTrackHistoryPresenter baiduTrackHistoryPresenter;

    private Track track;

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.START_RECORD_TIME:
                /**
                 * 计算时间
                 */
                long time = IStringUtils.toLong(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_STRAT_TIME_KEY, "").toString());
                long t = 0;
                if (time > 0) {
                    t = System.currentTimeMillis() - time;
                    recordTime.setText(IDateTimeUtils.showTimeCount(t));
                }

                /**
                 * 计算距离
                 */
                long id = IStringUtils.toLong(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "").toString());
                double distance = 0.0;
//                List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.TId.eq(id)).list();
//                if (list != null && list.size() > 0) {
//                    Track track = list.get(0);
//                    distance = track.getDistance();
//                    String d = new DecimalFormat("######0.00").format(distance / 1000);//转化成KM
//                    recordDistance.setText(d);
//                } else {
//                    recordDistance.setText("0.00");
//                }
                /**
                 * 计算均速
                 */
                double speed = distance / t * 60 * 60;
                if (speed <= 0) {
                    recordSpeed.setText("0.0");
                } else {
                    recordSpeed.setText(new DecimalFormat("######0.0").format(speed));
                }
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackPresenter = new UploadTrackPresenter(this);
        updateTrackPresenter = new UpdateTrackPresenter(this);
       // baiduTrackHistoryPresenter = new QueryBaiduTrackHistoryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_record_fragment, null);
        findViewById(view);
        startRecordTime();
        return view;
    }

    /**
     * 初始控件
     *
     * @param view
     */
    private void findViewById(View view) {
        recordTime = (TextView) view.findViewById(R.id.track_time);
        recordDistance = (TextView) view.findViewById(R.id.track_distans);
        recordSpeed = (TextView) view.findViewById(R.id.track_speed);
        trackLayout = (LinearLayout) view.findViewById(R.id.track_record_layout);
        startLayout = (LinearLayout) view.findViewById(R.id.track_record_start_layout);
        pauseLayout = (LinearLayout) view.findViewById(R.id.track_record_start_pause_layout);
        completeLayout = (LinearLayout) view.findViewById(R.id.track_record_complete_layout);
        resumeLayout = (LinearLayout) view.findViewById(R.id.track_record_resume_layout);

        startLayout.setOnClickListener(this);
        pauseLayout.setOnClickListener(this);
        completeLayout.setOnClickListener(this);
        resumeLayout.setOnClickListener(this);
    }

    /**
     * 记录开始
     */
    private void startRecordTime() {
        int state = IStringUtils.toInt(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, 0).toString());
        if (state == Constants.TRACK_RECORD_STATE.START.getValue()) {//还在记录足迹中
            timer = new Timer();
            timer.schedule(new Tracktack(), 1, Constants.ONE_SECONDE);

            trackLayout.setVisibility(View.VISIBLE);

            startLayout.setVisibility(View.GONE);
            resumeLayout.setVisibility(View.GONE);
            pauseLayout.setVisibility(View.VISIBLE);
            completeLayout.setVisibility(View.GONE);
        } else if (state == Constants.TRACK_RECORD_STATE.STOP.getValue()) {//暂停状态中
            trackLayout.setVisibility(View.VISIBLE);

            startLayout.setVisibility(View.GONE);
            resumeLayout.setVisibility(View.VISIBLE);
            pauseLayout.setVisibility(View.GONE);
            completeLayout.setVisibility(View.VISIBLE);

            listterner.onResumeTrack();
            sendEmptyMessage(mHandler, HandlerValue.START_RECORD_TIME);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.track_record_start_layout://开始
                startLayout.setVisibility(View.GONE);
                resumeLayout.setVisibility(View.GONE);
                pauseLayout.setVisibility(View.VISIBLE);
                completeLayout.setVisibility(View.GONE);

                listterner.onStartTrack();
                startRecord();
                break;
            case R.id.track_record_resume_layout://继续
                startLayout.setVisibility(View.GONE);
                resumeLayout.setVisibility(View.GONE);
                pauseLayout.setVisibility(View.VISIBLE);
                completeLayout.setVisibility(View.GONE);

                listterner.onResumeTrack();
                resumeRecord();
                break;
            case R.id.track_record_start_pause_layout://暂停
                startLayout.setVisibility(View.GONE);
                resumeLayout.setVisibility(View.VISIBLE);
                pauseLayout.setVisibility(View.GONE);
                completeLayout.setVisibility(View.VISIBLE);

                listterner.onStopTrack();
                stopRecord();
                break;
            case R.id.track_record_complete_layout://完成
                startLayout.setVisibility(View.GONE);
                resumeLayout.setVisibility(View.VISIBLE);
                pauseLayout.setVisibility(View.GONE);
                completeLayout.setVisibility(View.GONE);
                completeRecord();
                break;
        }
    }

    /**
     * 开始采集
     */
    private void startRecord() {
        trackLayout.setVisibility(View.VISIBLE);
        long id = System.currentTimeMillis();
        //记录状态
        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, String.valueOf(id));
        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, Constants.TRACK_RECORD_STATE.START.getValue());
        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STRAT_TIME_KEY, String.valueOf(System.currentTimeMillis()));

        //启动计时器
        startTimer();

        final Track entity = new Track();
        entity.setTId(id);
        entity.setStartTime(System.currentTimeMillis());
        entity.setState(Constants.TRACK_RECORD_STATE.START.getValue());
        entity.setRevint1(0);//记录上传状态
        entity.setDistance(0.0);
        entity.setRevstr1(ZNAPPlication.getUserInfoEntity(getActivity()).getId());

//        ZNAPPlication.getInstance().getDaoSession().getTrackDao().insert(entity);

        BaiduTTSUtils.speak(getActivity(), "采集足迹开始");
    }

    /**
     * 暂停采集
     */
    private void stopRecord() {
        long id = IStringUtils.toLong(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "").toString());
        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, Constants.TRACK_RECORD_STATE.STOP.getValue());
//        List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.TId.eq(id)).list();
//        if (list != null && list.size() > 0) {
//            Track track = list.get(0);
//            track.setState(Constants.TRACK_RECORD_STATE.STOP.getValue());
//            ZNAPPlication.getInstance().getDaoSession().getTrackDao().update(track);
//        }
        //停止计时器
        stopTimer();

        BaiduTTSUtils.speak(getActivity(), "采集足迹暂停");
    }

    /**
     * 继续采集
     */
    private void resumeRecord() {
        long id = IStringUtils.toLong(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "").toString());
//        List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.TId.eq(id)).list();
//        if (list != null && list.size() > 0) {
//            Track track = list.get(0);
//            track.setState(Constants.TRACK_RECORD_STATE.START.getValue());
//            ZNAPPlication.getInstance().getDaoSession().getTrackDao().update(track);
//        }
        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, Constants.TRACK_RECORD_STATE.START.getValue());
        //开始计时器
        startTimer();

        BaiduTTSUtils.speak(getActivity(), "采集足迹继续");
    }

    /**
     * 完成采集
     */
    private void completeRecord() {
        final long id = IStringUtils.toLong(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "").toString());
//        List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.TId.eq(id)).list();
        List<Track> list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            final Track track = list.get(0);
            /**
             * 判断距离，小于等于500米舍弃
             */
            if (track.getDistance() <= defaultDis) {
                DialogFactory.showMsgDialog(getActivity(), IUtils.getStrToRes(getActivity(), R.string.dialog_title), "路径太短！是否继续采集?", "继续", "取消", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 足迹太短不做保存处理
                         */
                        //界面更新
                        trackLayout.setVisibility(View.GONE);
                        startLayout.setVisibility(View.VISIBLE);
                        resumeLayout.setVisibility(View.GONE);
                        pauseLayout.setVisibility(View.GONE);
                        completeLayout.setVisibility(View.GONE);

                        listterner.onCancelTrack();

                        //删除记录
//                        ZNAPPlication.getInstance().getDaoSession().getTrackDao().delete(track);

                        //修改状态
                        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, Constants.TRACK_RECORD_STATE.COMPLETE.getValue());
                        ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "");
                    }
                });
            } else {
                //界面更新
                trackLayout.setVisibility(View.GONE);
                startLayout.setVisibility(View.VISIBLE);
                resumeLayout.setVisibility(View.GONE);
                pauseLayout.setVisibility(View.GONE);
                completeLayout.setVisibility(View.GONE);

                listterner.onCompleteTrack();

                //更新结束时间
                track.setState(Constants.TRACK_RECORD_STATE.COMPLETE.getValue());
                track.setEndTime(System.currentTimeMillis());
//                ZNAPPlication.getInstance().getDaoSession().getTrackDao().update(track);

                //修改状态
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_STATUE_KEY, Constants.TRACK_RECORD_STATE.COMPLETE.getValue());
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_ID_KEY, "");

                BaiduTTSUtils.speak(getActivity(), "采集足迹完成");
                uploadTrackDialog(track);
            }
        }
    }

    /**
     * 启动计时器
     */
    private void startTimer() {
        //启动计时器
        timer = new Timer();
        tracktack = new Tracktack();
        timer.schedule(tracktack, 0, Constants.ONE_SECONDE);
    }

    /**
     * 停止计时器
     */
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (tracktack != null) {
            tracktack.cancel();
            timer = null;
        }
    }

    /**
     * 计时器
     */
    private class Tracktack extends TimerTask {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 1;
            sendEmptyMessage(mHandler, HandlerValue.START_RECORD_TIME);
        }
    }

    @Override
    public void onMainUserEvent(NoticeEvent event) {
        super.onMainUserEvent(event);
        switch (event.getFlag()) {
            case EventConstant.TRACK_RECORD_STOP:
                stopRecord();
                break;
        }
    }

    /**
     * 上传提示Dialog
     *
     * @param track
     */
    private void uploadTrackDialog(final Track track) {
        /**
         * 通知足迹历史界面刷新界面
         */
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE8);
        EventBus.getDefault().post(event);

        this.track = track;
        DialogFactory.showMsgDialog(getActivity(), "足迹上传", "足迹完成采集，是否上传该足迹记录?", "上传", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 上传操作
                 */
                TrackEntity entity = new TrackEntity();
                entity.setStartTime(IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd"));
                entity.setEndTime(IDateTimeUtils.formatDate(track.getEndTime(), "yyyy-MM-dd HH:mm:dd"));
                entity.setFootDistance(track.getDistance());
                entity.setDuration(track.getEndTime() - track.getStartTime());
                entity.setFootDesc(ZNAPPlication.getUserInfoEntity(getActivity()).getName() + " " + IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd") + " 足迹");
                trackPresenter.uplodaTrack(entity);
            }
        }, null);
    }

    @Override
    public void onStartTrackUpload() {
        dialog = DialogFactory.createLoadingDialog(getActivity(), "足迹上传...");
    }

    @Override
    public void onEndTrackUpload() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onFailureTrackUpload(int code, String error) {
        showToast(error);
    }

    @Override
    public void onSuccessTrackUpload(int code, String msg) {
        //上传成功更新足迹列表状态
        track.setRevint1(1);
        updateTrackPresenter.updateTrack(track);
        showToast("足迹上传成功!");
    }

    @Override
    public void onUpdateTrackStart() {

    }

    @Override
    public void onUpdateTrackSuccess() {
        /**
         * 通知足迹历史界面刷新界面
         */
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE8);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onUpdateTrackFailure(int code, String errod) {

    }

    @Override
    public void onUpdateTrackEnd() {

    }

    @Override
    public void onQueryBaiduTrackStart() {
        dialog = DialogFactory.createLoadingDialog(getActivity(), "足迹上传...");
    }

    @Override
    public void onQueryBaiduTrackFailure(String err) {
        showToast("上传失败!");
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onQueryBaiduTrackSuccess(List<Point> list, double distance) {
        //纠偏后修改距离
        this.track.setDistance(distance);
        /**
         * 上传操作
         */
        TrackEntity entity = new TrackEntity();
        entity.setStartTime(IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd"));
        entity.setEndTime(IDateTimeUtils.formatDate(track.getEndTime(), "yyyy-MM-dd HH:mm:dd"));
        entity.setFootDistance(track.getDistance());
        entity.setDuration(track.getEndTime() - track.getStartTime());
        entity.setFootDesc(ZNAPPlication.getUserInfoEntity(getActivity()).getName() + " " + IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd") + " 足迹");
        trackPresenter.uplodaTrack(entity);
    }

    @Override
    public void onQueryBaiduTrackEnd() {

    }
}
