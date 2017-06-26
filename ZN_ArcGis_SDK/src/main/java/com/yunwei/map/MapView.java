package com.yunwei.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.tasks.identify.IdentifyParameters;
import com.yunwei.map.tiled.google.GoogleMapLayer;
import com.yunwei.map.utils.MSystemUtils;
import com.yunwei.map.widget.ArcGisLocationLayer;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.yunwei.map
 * @Description:
 * @author: Aaron
 * @date: 2016-06-01
 * @Time: 19:40
 * @version: V1.0
 */
public class MapView extends ArcGisLocationLayer {

    public MapView(Context context) {
        super(context);
        setClickListener();
    }

    public MapView(Context context, AttributeSet attri) {
        super(context, attri);
        setClickListener();
    }

    private void setClickListener() {
        switchLayerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickInsideBtton();
                }
                visibilityExternalMapLayer(x, y);
            }
        });
    }


    /**
     * 添加 Feature图层
     *
     * @param url
     */
    public ArcGISFeatureLayer addFeatureLayer(String url) {
        ArcGISFeatureLayer featureLayer = new ArcGISFeatureLayer(url, ArcGISFeatureLayer.MODE.ONDEMAND);
        addLayer(featureLayer);
        return featureLayer;
    }

    /**
     * 添加 Feature图层 过滤条件
     *
     * @param url
     * @param expression
     */
    public ArcGISFeatureLayer addFeatureLayer(String url, String expression) {
        ArcGISFeatureLayer featureLayer = new ArcGISFeatureLayer(url, ArcGISFeatureLayer.MODE.ONDEMAND);
        featureLayer.setDefinitionExpression(expression);
        addLayer(featureLayer);
        return featureLayer;
    }

    /**
     * 底部添加View
     *
     * @param view
     */
    public void addBtmMapView(View view) {
        btmLayout.addView(view);
        btmLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 消除底部View
     */
    public void removeBtmMapView() {
        btmLayout.removeAllViews();
        btmLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置地图图层切换按钮Visibility
     *
     * @param visibility
     */
    public void setSwitchMapLayerButtonVisibility(int visibility) {
        switchLayerBtn.setVisibility(visibility);
    }

    /**
     * 获取空间查询参数  查询图层上的要素
     *
     * @param tolerance
     * @param identifyPoint
     * @param spatialReference
     * @param mapHeight
     * @param mapWidth
     * @param env
     * @return
     * @auth:duyang
     */
    public IdentifyParameters getIdentifyParameters(int tolerance, Point identifyPoint, SpatialReference spatialReference, int mapHeight, int mapWidth, Envelope env) {
        IdentifyParameters params = null;
        int[] layers = {0, 1};
        params = new IdentifyParameters();
        params.setTolerance(tolerance);// 像素值容差
        params.setDPI(98);
        params.setLayers(layers);// 图层ID
        params.setLayerMode(IdentifyParameters.ALL_LAYERS);

        params.setGeometry(identifyPoint);
        params.setSpatialReference(spatialReference);
        params.setMapHeight(mapHeight);
        params.setMapWidth(mapWidth);
        params.setReturnGeometry(true);
        // add the area of extent to identify parameters
        params.setMapExtent(env);
        return params;
    }

    /**
     * 生成ArcGIS Polyline
     *
     * @param points 点集
     * @return
     * @author duyang
     */
    public Polyline generatePolyline(List<Point> points) {
        Polyline polyLine;
        if (points == null || points.size() < 2) {
            polyLine = null;
        } else {
            polyLine = new Polyline();
            polyLine.startPath(points.get(0));
            for (Point point :
                    points) {
                polyLine.lineTo(point);
            }
        }
        return polyLine;
    }

    /**
     * 绘制实线段
     *
     * @param pl
     * @param color
     * @param size
     * @param graphicsLayer
     * @return
     * @author duyang
     */
    public Graphic addPolylineToGraphicsLayer(Polyline pl, int color, int size, GraphicsLayer graphicsLayer) {
        Graphic g = null;
        if (pl != null && pl.getPointCount() > 1) {
            SimpleLineSymbol sls = new SimpleLineSymbol(color, size,
                    com.esri.core.symbol.SimpleLineSymbol.STYLE.SOLID);
            g = new Graphic(pl, sls);
            graphicsLayer.addGraphic(g);
        }
        return g;
    }

    /**
     * 绘制虚线
     *
     * @param pl
     * @param color
     * @param size
     * @param graphicsLayer
     * @return
     * @author duyang
     */
    public Graphic addPolylineDotToGraphicsLayer(Polyline pl, int color, int size, GraphicsLayer graphicsLayer) {
        Graphic g = null;
        if (pl != null && pl.getPointCount() > 1) {
            SimpleLineSymbol sls = new SimpleLineSymbol(color, size,
                    com.esri.core.symbol.SimpleLineSymbol.STYLE.DOT);
            g = new Graphic(pl, sls);
            graphicsLayer.addGraphic(g);
        }
        return g;
    }

    /**
     * 绘制图形覆盖物
     *
     * @param mPoint
     * @param graphicsLayer
     * @param drawable
     * @param attributes    附带属性
     * @return
     * @author duyang
     */
    public Graphic addPictureMarkerSimple1(Point mPoint, GraphicsLayer graphicsLayer, Drawable drawable, Map<String, Object> attributes) {
        PictureMarkerSymbol picSys = new PictureMarkerSymbol(drawable);
        Graphic graphic = null;
        if (graphicsLayer != null && mPoint != null) {
            graphic = new Graphic(mPoint, picSys, attributes, 0);
            graphicsLayer.addGraphic(graphic);
        }
        return graphic;
    }

    /**
     * 设置底部控制View visibility
     *
     * @param visibility
     */
    public void setBtmOperaLayoutVisibility(int visibility) {
        proLayout.setVisibility(visibility);
    }

    /**
     * 设置定位Btn visibility
     *
     * @param visibility
     */
    public void setLocationBtnVisibility(int visibility) {
        locationIv.setVisibility(visibility);
    }


    /**
     * 显示室外图层
     */
    public void visibilityExternalMapLayer(double x, double y) {
        terrainLayer.setVisible(true);
        imageLayer.setVisible(true);
        annotationLayer.setVisible(true);
        locationIv.setVisibility(VISIBLE);
        layerIV.setVisibility(VISIBLE);

        setScale(scale);
        setExtent(new Point(x, y));
        isExternalMapLayer = true;
        switchLayerBtn.setVisibility(GONE);
    }

    double scale;

    /**
     * 显示室内图层
     */
    public void visibilityInsideMapLayer() {
        terrainLayer.setVisible(false);
        imageLayer.setVisible(false);
        annotationLayer.setVisible(false);
        locationIv.setVisibility(GONE);
        layerIV.setVisibility(GONE);

        scale = getScale();

        isExternalMapLayer = false;
        switchLayerBtn.setVisibility(VISIBLE);
    }

    /**
     * 添加室内图层
     *
     * @param filePath
     * @return
     */
    public ArcGISLocalTiledLayer addInsideMapLayer(String filePath) {
        localTiledLayer = new ArcGISLocalTiledLayer("file://" + filePath);
        addLayer(localTiledLayer);

        visibilityInsideMapLayer();
        return localTiledLayer;
    }

    /**
     * 设置地图上某个点
     *
     * @param point 点信息
     * @param res   图标资源
     */
    public GraphicsLayer setPoint(Point point, int res) {
        Bitmap bmpStart = BitmapFactory.decodeResource(getResources(), res);
//        bmpStart= MSystemUtils.getScaleBitmap(bmpStart,0);
        Drawable drawable = new BitmapDrawable(getResources(), bmpStart);
        PictureMarkerSymbol picSys = new PictureMarkerSymbol(drawable);
        Graphic graphic = new Graphic(point, picSys, null, 0);
        GraphicsLayer graphicsLayer = new GraphicsLayer();
        graphicsLayer.addGraphic(graphic);
        addLayer(graphicsLayer);
        return graphicsLayer;
    }

    public void setFlow(boolean flow) {
        this.isFlow = flow;
    }

    public void setArcGisListener(ArcGisMapListener listener) {
        this.listener = listener;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * 设置默认的Scale
     *
     * @param scale
     */
    public void setDefualtScale(double scale) {
        this.defaultScale = scale;
    }
}
