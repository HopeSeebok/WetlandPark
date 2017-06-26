package com.yunwei.wetlandpark.ui.mainFunctions.TrackModule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.map.MapView;
import com.yunwei.map.tiled.google.GoogleMapLayer;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.presenter.UpdateTrackPresenter;
import com.yunwei.wetlandpark.ui.presenter.UploadTrackPresenter;
import com.yunwei.wetlandpark.ui.view.TrackUploadView;
import com.yunwei.wetlandpark.ui.view.UpdateTrackView;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @package com.yunwei.zaina.ui.activity.track
 * @description 足迹显示
 * @date 16/6/24
 * @time 上午11:50
 **/
public class TrackMapActivity extends BaseActivity implements OnStatusChangedListener, TrackUploadView, UpdateTrackView {
    private final String TAG = this.getClass().getSimpleName();

    public static final String TRACK_VALUE = "track_value";
    public static final String POINT_VALUE = "point_value";
    public static final String FROM_SERVER = "from_server";
    public static final String FROM_LOCATION = "from_location";
    public static final String FROM_FLAG = "from_flag";

    private String from;

    private MapView mGisMap;
    private TextView startTimeText, endTimeText, distanceText;

    private GraphicsLayer mTrackLineLayer;
    private GraphicsLayer mTrackLineNodeLayer;

    private List<Point> points = new ArrayList<>();

    private UploadTrackPresenter trackPresenter;
    private UpdateTrackPresenter updateTrackPresenter;

    private Track track;
    private TrackEntity entity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.track_map_fragment);
        setToolbarTitle("足迹详情");

        Intent intent = getIntent();
        from = intent.getStringExtra(FROM_FLAG);

        if (from.equals(FROM_LOCATION)) {
            track = (Track) intent.getSerializableExtra(TRACK_VALUE);
        } else if (from.equals(FROM_SERVER)) {
            entity = (TrackEntity) intent.getSerializableExtra(TRACK_VALUE);
        }
        points = (List<Point>) intent.getSerializableExtra(POINT_VALUE);

        initMapView();
        initUI();

        trackPresenter = new UploadTrackPresenter(this);
        updateTrackPresenter = new UpdateTrackPresenter(this);
    }

    @Override
    public void findViewById() {
        mGisMap = (MapView) findViewById(R.id.track_mapView);

        startTimeText = (TextView) findViewById(R.id.track_des_start_time);
        endTimeText = (TextView) findViewById(R.id.track_des_end_time);
        distanceText = (TextView) findViewById(R.id.track_des_distance);

    }

    private void initUI() {
        if (from.equals(FROM_LOCATION) && track != null) {
            startTimeText.setText("开始时间：" + IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:dd:ss"));
            endTimeText.setText("结束时间：" + IDateTimeUtils.formatDate(track.getEndTime(), "yyyy-MM-dd HH:dd:ss"));
            String d = new DecimalFormat("######0.00").format(track.getDistance() / 1000);//转化成KM
            distanceText.setText(d + " KM");
            if (track.getRevint1() == 0) {
                setToolbarRightText("提交");
            }
        }

        if (from.equals(FROM_SERVER) && entity != null) {
            startTimeText.setText("开始时间：" + entity.getCreateTime());
            endTimeText.setText("结束时间：" + entity.getEndTime());
            String d = new DecimalFormat("######0.00").format(entity.getFootDistance() / 1000);//转化成KM
            distanceText.setText(d + " KM");
        }
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (track.getRevint1() != 0 || from.equals(FROM_SERVER)) {
            return;
        }
        /**
         * 上传操作
         */
        TrackEntity entity = new TrackEntity();
        entity.setStartTime(IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd"));
        entity.setEndTime(IDateTimeUtils.formatDate(track.getEndTime(), "yyyy-MM-dd HH:mm:dd"));
        entity.setFootDistance(track.getDistance());
        entity.setDuration(track.getEndTime() - track.getStartTime());
        entity.setFootDesc(ZNAPPlication.getUserInfoEntity(this).getName() + " " + IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:dd") + " 足迹");
        trackPresenter.uplodaTrack(entity);
    }

    /**
     * 初始化地图
     */
    private void initMapView() {
        mTrackLineLayer = new GraphicsLayer();
        mTrackLineNodeLayer = new GraphicsLayer();
        mGisMap.addLayer(mTrackLineLayer);
        mGisMap.addLayer(mTrackLineNodeLayer);
        mGisMap.setOnStatusChangedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mGisMap.unpause();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGisMap.pause();
    }

    @Override
    public void onDestroy() {
        mTrackLineNodeLayer = null;
        mTrackLineLayer = null;
        super.onDestroy();
    }

    @Override
    public void onStatusChanged(Object o, STATUS status) {
        if (mTrackLineLayer.isInitialized() && mTrackLineNodeLayer.isInitialized()) {
            if (points != null && points.size() > 0) {
                Polyline polyline = mGisMap.generatePolyline(points);
                mGisMap.addPictureMarkerSimple1(points.get(0), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_st), null);//起点
                mGisMap.addPolylineToGraphicsLayer(polyline, Color.BLUE, 5, mTrackLineLayer);
                mGisMap.addPictureMarkerSimple1(points.get(points.size() - 1), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_en), null);//终点
            }
            mGisMap.setExtent(points.get((int) points.size() / 2));
            mGisMap.setScale(GoogleMapLayer.scales[12]);
        }
    }

    @Override
    public void onStartTrackUpload() {
        dialog = DialogFactory.createLoadingDialog(this, "上传...");
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onSuccessTrackUpload(int code, String msg) {
        //上传成功更新足迹列表状态
        track.setRevint1(1);
        setToolbarRightText("");
        updateTrackPresenter.updateTrack(track);

        /**
         * 通知足迹历史界面刷新界面
         */
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE8);
        EventBus.getDefault().post(event);

        showToast("足迹上传成功!");
    }

    @Override
    public void onFailureTrackUpload(int code, String error) {
        showToast("足迹上传失败!");
    }

    @Override
    public void onEndTrackUpload() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void onUpdateTrackStart() {

    }

    @Override
    public void onUpdateTrackSuccess() {

    }

    @Override
    public void onUpdateTrackFailure(int code, String errod) {

    }

    @Override
    public void onUpdateTrackEnd() {

    }
}
