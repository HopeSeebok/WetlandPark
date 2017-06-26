package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.trackHistoryPage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.map.MapView;
import com.yunwei.map.utils.MLog;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.greedao.TrackPointsTable;
import com.yunwei.wetlandpark.greedao.TrackPointsTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.LineSegmentEntity;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.TrackPointEntity;
import com.yunwei.wetlandpark.ui.presenter.UpdateTrackPresenter;
import com.yunwei.wetlandpark.ui.presenter.UploadTrackPresenter;
import com.yunwei.wetlandpark.ui.view.TrackUploadView;
import com.yunwei.wetlandpark.ui.view.UpdateTrackView;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hezhiWu
 * @package com.yunwei.zaina.ui.activity.track
 * @description 足迹显示
 * @date 16/6/24
 * @time 上午11:50
 **/
public class TrackMapActivity2 extends BaseActivity implements OnStatusChangedListener, UpdateTrackView {
    private final String TAG = this.getClass().getSimpleName();

    public static final String TRACK_VALUE = "track_value";
    public static final String POINT_VALUE = "point_value";

    private MapView mGisMap;
    private TextView startTimeText, endTimeText, distanceText;

    private GraphicsLayer mTrackLineLayer;
    private GraphicsLayer mTrackLineNodeLayer;

    private List<TrackPointEntity> points = new ArrayList<>();

    private UpdateTrackPresenter updateTrackPresenter;

    private Track track;
    private DrawTrackRunnable drawTrackRunnable;
    private boolean isLayerLoaded =false, isDrawOver = false;

    public static final String TRACK_LINE_NAME = "track_line";
    public static final String TRACK_NODE_NAME = "track_node";
    private TrackPointsTable mPointsTable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.track_map_fragment);
//        setHeadTitle("足迹详情");

        initTrackData();
        initMapView();
        initUI();

        updateTrackPresenter = new UpdateTrackPresenter(this);
    }

    private void initTrackData() {
        drawTrackRunnable=new DrawTrackRunnable();
        final Intent intent = getIntent();
        track = (Track) intent.getSerializableExtra(TRACK_VALUE);
        dialog = DialogFactory.createLoadingDialog(this, "正在绘制足迹...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //从数据库查找初始化足迹点数据
                    String cacheTrackId = intent.getStringExtra(POINT_VALUE);
//                    String result = ISpfUtil.getValue(getActivity(), cacheTrackId, "").toString();
                    mPointsTable = ZNAPPlication.getDaoSession().getTrackPointsTableDao().queryBuilder()
                            .where(TrackPointsTableDao.Properties.CacheID.eq(cacheTrackId)).list().get(0);
                    String result =  mPointsTable.getPoints();
                    Type type = new TypeToken<ArrayList<TrackPointEntity>>() {
                    }.getType();
                    points = new Gson().fromJson(result, type);
                    if (!isDrawOver) {
                        mHandler.post(drawTrackRunnable);
                    }
                }catch(Exception e){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showToast("足迹加载失败！");
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void findViewById() {
        mGisMap = (MapView) findViewById(R.id.track_mapView);

        startTimeText = (TextView) findViewById(R.id.track_des_start_time);
        endTimeText = (TextView) findViewById(R.id.track_des_end_time);
        distanceText = (TextView) findViewById(R.id.track_des_distance);

        mGisMap.setLocationBtnVisibility(View.GONE);
    }

    private void initUI() {
        if (track != null) {
            startTimeText.setText("开始时间：" + IDateTimeUtils.formatDate(track.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            endTimeText.setText("结束时间：" + IDateTimeUtils.formatDate(track.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            String d = new DecimalFormat("######0.00").format(0);//转化成KM
            distanceText.setText(d + " KM");
        }
    }

    /**
     * 初始化地图
     */
    private void initMapView() {
        mTrackLineLayer = new GraphicsLayer();
        mTrackLineLayer.setName(TRACK_LINE_NAME);
        mTrackLineNodeLayer = new GraphicsLayer();
        mTrackLineNodeLayer.setName(TRACK_NODE_NAME);
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
        if (mTrackLineLayer.isInitialized() && mTrackLineNodeLayer.isInitialized() && (o instanceof GraphicsLayer && TextUtils.equals(((GraphicsLayer) o).getName(), TRACK_NODE_NAME))) {
            isLayerLoaded =true;
            mHandler.post(drawTrackRunnable);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
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

    /**
     * 绘制轨迹
     * du yang
     */
    class DrawTrackRunnable implements Runnable{
        @Override
        public void run() {
            if (!isLayerLoaded||isDrawOver) {
                //加载数据线程未完成，地图未初始化完成
                return;
            }
            if (points != null && points.size() > 0 && track != null) {
                //分段去绘制轨迹
                isDrawOver = true;
                drawLineSegment(track,points,new int[]{Color.BLUE,Color.GREEN,Color.RED},5);
                String d = new DecimalFormat("######0.00").format(mPointsTable.getDistance()/1000);//转化成KM
                distanceText.setText(d + " KM");
                if (dialog != null) {
                    dialog.dismiss();
                }
            }else{
                showToast("没有足迹点数据");
                if (dialog != null) {
                    dialog.dismiss();
                }
            }//scale:12
        }
    }

    /**
     * 分段绘制足迹
     * @param track
     * @param trackPoints
     * @param colorInts
     * @param radius
     */
    private void drawLineSegment(Track track, List<TrackPointEntity> trackPoints,int [] colorInts,int radius){
        if (track!=null&&trackPoints!=null&&trackPoints.size()>2) {
            List<LineSegmentEntity> lineSegmentList=null;
            List<String> resumeNodeList = new ArrayList<>();// IStringUtils.convertStringToList(track.getResume(), Constants.TRACK_NODE_SPLIT_CHAR, true);
            List<String> pauseNodeList = new ArrayList<>();// IStringUtils.convertStringToList(track.getPause(), Constants.TRACK_NODE_SPLIT_CHAR, true);
            resumeNodeList.add(IDateTimeUtils.formatDate(track.getStartTime()));
            pauseNodeList.add(IDateTimeUtils.formatDate(track.getEndTime()));
            Random random = new Random();
            if (colorInts==null||colorInts.length<=0) {
                colorInts=new int[]{Color.BLUE,Color.GREEN,Color.RED};
            }
            if (resumeNodeList!=null&&resumeNodeList.size()>0&&pauseNodeList!=null&&pauseNodeList.size()>0&&resumeNodeList.size()<=pauseNodeList.size()) {
                lineSegmentList = new ArrayList<>();
                int index=0;
                MLog.i(TAG,"###SIZE"+trackPoints.size()+"###"+trackPoints.get(0).toString()+"###END###"+trackPoints.get(trackPoints.size()-1));
                for (int i = 0; i < pauseNodeList.size(); i++) {
                    long pauseTime=IDateTimeUtils.getTimeStamp(pauseNodeList.get(i), "yyyy-MM-dd HH:mm:ss");//暂停节点
                    MLog.i(TAG,pauseNodeList.get(i)+"#"+pauseTime);
                    List<Point> tempList = new ArrayList<>();
                    for (int j=index;j<trackPoints.size();j++) {
                        index=j;
                        TrackPointEntity entity = trackPoints.get(j);
                        if (entity.getLocTime() <= pauseTime) {
                            MLog.i(TAG,""+entity.toString());
                            tempList.add(new Point(entity.getX(), entity.getY()));
                        }else{
                            break;
                        }
                    }
                    //记录足迹的结束点时间与百度鹰眼的结束点时间不一定相同
                    if (tempList.size()>=2) {
                        Polyline polyline = mGisMap.generatePolyline(tempList);
                        LineSegmentEntity lineSegmentEntity = new LineSegmentEntity(polyline, tempList.get(0), tempList.get(tempList.size() - 1), colorInts[random.nextInt(colorInts.length)],radius<0?5:radius);
                        lineSegmentList.add(lineSegmentEntity);
                        MLog.i(TAG,"index="+index);
                    }
                    tempList.clear();
                }
            }
            Polyline polyLine=mGisMap.generatePolyline(IUtils.extracPoints(trackPoints));
            if (lineSegmentList!=null&&lineSegmentList.size()>0) {
                for (LineSegmentEntity entity: lineSegmentList) {
                    mGisMap.addPictureMarkerSimple1(entity.getStartPoint(), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_st), null);//起点
                    mGisMap.addPolylineToGraphicsLayer(entity.getPolyline(), Color.BLUE, 5, mTrackLineLayer);
                    mGisMap.addPictureMarkerSimple1(entity.getEndPoint(), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_en), null);//终点
                }
            }else{
                mGisMap.addPictureMarkerSimple1(new Point(trackPoints.get(0).getX(),trackPoints.get(0).getY()), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_st), null);//起点
                mGisMap.addPolylineToGraphicsLayer(polyLine, Color.BLUE, 5, mTrackLineLayer);
                mGisMap.addPictureMarkerSimple1(new Point(trackPoints.get(trackPoints.size()-1).getX(),trackPoints.get(trackPoints.size()-1).getY()), mTrackLineNodeLayer, getResources().getDrawable(R.mipmap.line_en), null);//终点
            }
            mGisMap.setExtent(polyLine);

        }
    }
}
