package com.yunwei.wetlandpark.ui.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.esri.android.map.Callout;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.event.OnPanListener;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.map.MapView;
import com.yunwei.map.entity.LngLatEntity;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.tiled.google.GoogleMapLayer;
import com.yunwei.map.utils.ILngLatMercator;
import com.yunwei.wetlandpark.entity.MapLocationEntity;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.entity.RegeoEntity;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.presenter.RegeoPresenter;
import com.yunwei.wetlandpark.ui.view.RegeoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SlientWhale
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.map
 * @Description 地图定位  -- 有关地图定位的都在这里进行处理
 * @Date 2016/9/30 .
 */
public class MapLocationActivity extends BaseActivity implements OnStatusChangedListener, OnPanListener, OnSingleTapListener, RegeoView {

    @BindView(R.id.mapActionActivity_mapView)
    MapView mMapView;//ArcGisMapView
    @BindView(R.id.mapActionActivity_comfirm_button)
    Button submitBtn;//确定按钮
    @BindView(R.id.mapActionActivity_marker_icon)
    ImageView markerImageView;//位于地图中心点的图标，类似于Uber中的效果，在查看模式的时候不显示

    public static final String FLAG = "MapLocation";
    public static final String FLAG_DATA = "MapLocationEntity";//数据的Flag

    public static final int MODE_VIEW = 0x002;// 查看模式,地图只显示位置，不修改位置
    public static final int MODE_MODIFY = 0x012;// 修改位置模式，确定之后返回位置类
    public static final int MODE_FOLLOW = 0x003;// 跟随模式，点击图层上的要素点可以移到该位置,并且弹出气泡

    //局部变量
    private int mFlag;
    private MapLocationEntity mInitLocationEntity;//原始的数据
    private MapLocationEntity mNewLocationEntity;//后来的更改的设施的位置
    private boolean isClickPoint;//是否点击了要素点，如果

    //设施图层 按照顺序依次为: 枪机，球机
    private ArcGISFeatureLayer featureLayerGun, featureLayerBall;
    private ArcGISFeatureLayer hiddenDangerLayer;//隐患图层

    //Map 的组件
    private GraphicsLayer mLocationPointLayer;  //定位当前位置的图层

    private GraphicsLayer mMarkerLayer;  //移动图标的Marker图层
    private int mMarkerID = -1;  //MarkID
    private Graphic mGraphic;   //
    private PictureMarkerSymbol mPicSymbol;

    private RegeoPresenter mRegeoPresenter;

    private Dialog mLoadingDialog;
    private Bundle mBundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_map_action);
        ButterKnife.bind(this);
        setToolbarTitle("确定表单位置");
        initData();
        initMapView();
        initSetting();
    }

    private void initData() {
        Intent intent = getIntent();
        mBundle = new Bundle();
        mNewLocationEntity = new MapLocationEntity();//新建的位置实体
        if (!intent.getExtras().containsKey(FLAG_DATA) || !intent.getExtras().containsKey(FLAG)) {
            showToast("获取位置信息失败");
        }
        mFlag = intent.getExtras().getInt(FLAG);
        mInitLocationEntity = (MapLocationEntity) getIntent().getExtras().getSerializable(FLAG_DATA);
    }

    //初始化设置 -- 根据 Flag 进行设置
    private void initSetting() {
        switch (mFlag) {
            case MODE_VIEW:
                setToolbarTitle("查看位置");
                markerImageView.setVisibility(View.GONE);
                submitBtn.setVisibility(View.GONE);
                /** 初始化 MarkerLayer */
                mMarkerLayer = new GraphicsLayer();//
                mMapView.addLayer(mMarkerLayer);
                //设置弹出气泡
                MPointEntity mPointEntity = ILngLatMercator.lonLat2WebMercator(mInitLocationEntity.getLng(), mInitLocationEntity.getLat());
                Point point = new Point(mPointEntity.getX(), mPointEntity.getY());
                showCallout(point, mInitLocationEntity);
                break;

            case MODE_MODIFY:
                setToolbarTitle("更改位置");
                submitBtn.setText("确认位置");
                break;

            case MODE_FOLLOW:
                setToolbarTitle("更改位置");
                submitBtn.setText("确认位置");
                break;
        }

    }

    //初始化地图的设置
    private void initMapView() {
        //添加隐患图层
//        hiddenDangerLayer = mMapView.addFeatureLayer(IConfigValues.MAP_FEATURE_URL + Constants.MAP_FEATURE_STATE.HS_STATE.getValue());
        //添加设施点的图层
        //枪机
//        featureLayerGun = mMapView.addFeatureLayer(IConfigValues.MAP_FEATURE_URL + Constants.MAP_FEATURE_STATE.QIANGJI.getValue());
        //球机
//        featureLayerBall = mMapView.addFeatureLayer(IConfigValues.MAP_FEATURE_URL + Constants.MAP_FEATURE_STATE.QIUJI.getValue());

        //地图的操作
        mMapView.setClickable(true);
        mMapView.setOnStatusChangedListener(this);
        mMapView.setOnPanListener(this);
        //在填报隐患点的时候，希望可以和设施挂钩，也可以不挂钩,所以才设置单点效果
        if (mFlag == MODE_FOLLOW) {
            mMapView.setOnSingleTapListener(this);
        }
        /*初始化 通过坐标获取地址 UploadPresenter*/
        mRegeoPresenter = new RegeoPresenter(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.unpause();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.recycle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFlag != MODE_VIEW) {
            setResult();
        }
    }

    //将数据返回 请求的 Activity
    private void setResult() {
        mBundle.putSerializable(FLAG_DATA, mInitLocationEntity);
        setResult(this.RESULT_CANCELED, new Intent().putExtras(mBundle));
    }

    //如果点击图层附近有设施的话，就移动到图层上面去
    private void moveToTap(float x, float y) {
        int[] ids1 = featureLayerGun.getGraphicIDs(x, y, 5);
        int[] ids2 = featureLayerBall.getGraphicIDs(x, y, 5);
        Graphic graphic = groupIds(ids1, ids2);
        if (graphic != null) {
            Geometry geometry = graphic.getGeometry();
            if (geometry != null && geometry.getType().equals(Geometry.Type.POINT)) {
                Point point = (Point) geometry;
                //移动到查找的设施点上面
                mMapView.centerAt(point, false);
                LngLatEntity mLngLatEntity = ILngLatMercator.WebMercator2lonLat(point.getX(), point.getY());
                mNewLocationEntity.clear();//进行赋值的时候要进行清除操作，去掉之前赋值的数据
                mNewLocationEntity.setX(point.getX());
                mNewLocationEntity.setY(point.getY());
                mNewLocationEntity.setLat(mLngLatEntity.getLAT());
                mNewLocationEntity.setLng(mLngLatEntity.getLNG());
                mNewLocationEntity.setAddress(graphic.getAttributeValue("address").toString());
                String code = graphic.getAttributeValue("code").toString();
                mNewLocationEntity.setCode(code);
                String type = graphic.getAttributeValue("facilitytype").toString();//设施类型
                showCallout(point, mNewLocationEntity);

            }
        }
    }

    //集合收集到的信息点，然后返回对应的图层
    private Graphic groupIds(int[]... ids) {
        Graphic graphic = null;
        List<Integer> idsList = new ArrayList<>();
        for (int[] idss : ids) {
            if (idss != null && idss.length != 0) {
                for (int i = 0; i < idss.length; i++) {
                    idsList.add(idss[i]);
                }
            }
        }
        if (idsList.size() > 0) {
            for (int uid : idsList) {
                try {
                    graphic = featureLayerGun.getGraphic(uid);
                    if (graphic == null) {
                        graphic = featureLayerBall.getGraphic(uid);
                    }
                    //一旦找到有设施图层就返回，不然一直查找下去会累死的
                    if (graphic != null) {
                        return graphic;
                    }
                } catch (Exception e) {
                    showToast("设施查找出现异常");
                }
            }
        } else {
            return null;
        }
        return graphic;
    }

    /**
     * 定位结果回调
     *
     * @param event
     */
    @Override
    public void onBackGroundUserEvent(NoticeEvent event) {
        switch (event.getFlag()) {
            case EventConstant.NOTICE2://高德地位返回(GC-J02)
                AMapLocation aMapLocation = (AMapLocation) event.getObj();
                if (aMapLocation == null) {
                    return;
                }
                /*转成墨卡托坐标*/
                final MPointEntity mercatorPoint = ILngLatMercator.lonLat2WebMercator(aMapLocation.getLongitude(), aMapLocation.getLatitude());
                mLocationPointLayer = mMapView.updateCurrentLocation(new Point(mercatorPoint.getX(), mercatorPoint.getY()));
        }
    }

    @Override
    public void onSingleTap(float x, float y) {
        if (featureLayerBall != null && featureLayerGun != null) {
            moveToTap(x, y);
        }
    }

    @Override
    public void onStatusChanged(Object o, STATUS status) {
        if (status == STATUS.LAYER_LOADED) {
            /*转成墨卡托坐标*/
            final MPointEntity mercatorPoint = ILngLatMercator
                    .lonLat2WebMercator(mInitLocationEntity.getLng(), mInitLocationEntity.getLat());

            /*初始化地图 中心点坐标/比例 */
            Point point = new Point(mercatorPoint.getX(), mercatorPoint.getY());
            mMapView.setExtent(point);
            mMapView.setScale(GoogleMapLayer.scales[18]);
            if (mFlag == MODE_VIEW) {
                /* 只有在查看模式，才初始化Marker，否则报空 */
                mPicSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.mipmap.icon_marker_location));
                mGraphic = new Graphic(point, mPicSymbol, null, 0);
                if (mMarkerID == -1) {
                    mMarkerID = mMarkerLayer.addGraphic(mGraphic);
                }
            }

        }
    }

    @OnClick(R.id.mapActionActivity_comfirm_button)
    public void onClick() {
        DialogFactory.showMsgDialog(this, "提示", "是否确定为该位置？", "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在点选模式下，如果已经点选了点，且没有移动位置，就不要清除之前点选的数据
                if (!(mFlag == MODE_FOLLOW && isClickPoint)) {
                    mNewLocationEntity.clear();//
                }
                Point center = mMapView.getCenter();
                LngLatEntity lngLatEntity = ILngLatMercator.WebMercator2lonLat(center.getX(), center.getY());
                mNewLocationEntity.setLng(lngLatEntity.getLNG());
                mNewLocationEntity.setLat(lngLatEntity.getLAT());
                mNewLocationEntity.setX(center.getX());
                mNewLocationEntity.setY(center.getY());
                mRegeoPresenter.regeo(mNewLocationEntity.getLng(), mNewLocationEntity.getLat());
            }
        }, null);
    }

    //展示气泡
    private void showCallout(Point point, MapLocationEntity locationEntity) {
        if (locationEntity == null) {
            return;
        }
        Callout callout = mMapView.getCallout();
        TextView tv = new TextView(this);
        String calloutString = "";
        calloutString += TextUtils.isEmpty(locationEntity.getAddress()) ? "" : "地址：" + locationEntity.getAddress() + "\n";
        calloutString += TextUtils.isEmpty(locationEntity.getCode()) ? "" : "编号：" + locationEntity.getCode() + "\n";
        calloutString += TextUtils.isEmpty(locationEntity.getRoad()) ? "" : "路段：" + locationEntity.getRoad() + "\n";
        if (TextUtils.isEmpty(calloutString)) {
            calloutString = "目标位置";
        }
        tv.setText(calloutString);
        callout.setOffset(0, 36);//设置偏移量,这个值调的我好辛苦你知道么
        callout.setContent(tv);
        callout.show(point);

    }

    /* OnPanListener 地图平移的回调 x4*/
    @Override
    public void prePointerMove(float v, float v1, float v2, float v3) {
    }

    @Override
    public void postPointerMove(float v, float v1, float v2, float v3) {
        isClickPoint = false;//表示已经移动了

//         /*初始化Marker*/  已经废弃不用
//        mPicSymbol = new PictureMarkerSymbol(getResources().getDrawable(R.mipmap.task_list_location));
//        mGraphic = new Graphic(center, mPicSymbol, null, 0);
//        if (mMarkerID != -1) {
//            mGraphic = new Graphic(center,mPicSymbol,null,0);
//            mMarkerLayer.updateGraphic(mMarkerID,mGraphic);
//        }
        Point center = mMapView.getCenter();
        /** 记录下移动的位置 */
        LngLatEntity lngLatEntity = ILngLatMercator.WebMercator2lonLat(center.getX(), center.getY());
        mNewLocationEntity.clear();//在赋值的时候要进行清除操作
        mNewLocationEntity.setX(center.getX());
        mNewLocationEntity.setY(center.getY());
        mNewLocationEntity.setLng(lngLatEntity.getLNG());
        mNewLocationEntity.setLat(lngLatEntity.getLAT());
        mNewLocationEntity.setAddress("");
    }

    @Override
    public void prePointerUp(float v, float v1, float v2, float v3) {
    }

    @Override
    public void postPointerUp(float v, float v1, float v2, float v3) {
    }

    /**
     * 修改Location回调x5
     */
    @Override
    public void onRegeoStart() {
        mLoadingDialog = DialogFactory.createLoadingDialog(this, "正在确认位置...");
    }

    @Override
    public void onRegeoEnd() {
        DialogFactory.dimissDialog(mLoadingDialog);
    }

    @Override
    public void onRegeoFailure(int code, String error) {
        ToastUtil.showToast(this, "code:" + code + "\n修改失败，请检查网络后重试");
        mLoadingDialog.dismiss();
    }

    //定位成功以后
    @Override
    public void onRegeoSuccess(RegeoEntity entity) {
        if (entity != null) {
            mNewLocationEntity.setAddress(entity.getRegeocode().getFormatted_address());
            mNewLocationEntity.setRoad(entity.getRegeocode().getTownship());
        }
        mInitLocationEntity = mNewLocationEntity;//更替原始数据
        ToastUtil.showToast(this, "更改位置成功");
        setResult();
        this.finish();
    }


    @Override
    public Activity getActivity() {
        return this;
    }
}
