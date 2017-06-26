package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Point;
import com.yunwei.map.tiled.google.GoogleMapLayer;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.map.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTroublePositionActivity extends BaseActivity implements OnStatusChangedListener {

    @BindView(R.id.selectTroublePositionActivity_mapView)
    MapView selectTroublePositionActivityMapView;
    @BindView(R.id.activity_select_trouble_position)
    RelativeLayout activitySelectTroublePosition;
    @BindView(R.id.selectTroublePositionActivity_commit_bt)
    Button selectTroublePositionActivityCommitBt;

    public static final int RESULT_POSITION_SELECTOR = 0x17;
    public static final int REQUEST_POSITION_SELECTOR = 0x18;
    public static final String KEY_SELECTED_POINT = "KEY_SELECTED_POINT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trouble_position);
        ButterKnife.bind(this);
        /*添加当前位置定位点*/
        Point point = new Point(ZNAPPlication.getInstance().getX(), ZNAPPlication.getInstance().getY());
        selectTroublePositionActivityMapView.setPoint(point, R.mipmap.main_icon_follow);
        /*设置地图按钮消失*/
        selectTroublePositionActivityMapView.setSwitchMapLayerButtonVisibility(View.GONE);
        selectTroublePositionActivityMapView.setOnStatusChangedListener(this);

    }

    @Override
    public void onStatusChanged(Object o, STATUS status) {
        if (status == STATUS.LAYER_LOADED) {
            /*设置地图上中心点及Scale*/
            Point point = new Point(ZNAPPlication.getInstance().getX(), ZNAPPlication.getInstance().getY());
            selectTroublePositionActivityMapView.setExtent(point);
            selectTroublePositionActivityMapView.setScale(GoogleMapLayer.scales[18]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectTroublePositionActivityMapView.unpause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectTroublePositionActivityMapView.recycle();
    }

    @Override
    protected void onPause() {
        super.onPause();
        selectTroublePositionActivityMapView.pause();
    }

    @OnClick(R.id.selectTroublePositionActivity_commit_bt)
    public void onClick() {
        Point centerPoint = selectTroublePositionActivityMapView.getCenter();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SELECTED_POINT, centerPoint);
        intent.putExtras(bundle);
        SelectTroublePositionActivity.this.setResult(RESULT_POSITION_SELECTOR, intent);
        SelectTroublePositionActivity.this.finish();
    }
}
