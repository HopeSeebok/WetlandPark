package com.yunwei.wetlandpark.entity;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.esri.core.geometry.Point;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.ILngLatMercator;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.entity
 * @Description:
 * @date 2016/9/18 17:11
 */
public class TrackDetailEntity {

    private int code;

    private Items items;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Items getItems() {
        return this.items;
    }

    public class Points_bd {
        private int coord_type;

        private List<Double> location;

        private int loc_time;

        private String create_time;

        private int direction;

        private int height;

        private int speed;

        private int radius;

        public void setCoord_type(int coord_type) {
            this.coord_type = coord_type;
        }

        public int getCoord_type() {
            return this.coord_type;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }

        public List<Double> getLocation() {
            return this.location;
        }

        public void setLoc_time(int loc_time) {
            this.loc_time = loc_time;
        }

        public int getLoc_time() {
            return this.loc_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCreate_time() {
            return this.create_time;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return this.direction;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return this.speed;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return this.radius;
        }

    }

    public class Points_gg {
        private int coord_type;

        private List<Double> location;

        private int loc_time;

        private String create_time;

        private int direction;

        private int height;

        private int speed;

        private int radius;

        public void setCoord_type(int coord_type) {
            this.coord_type = coord_type;
        }

        public int getCoord_type() {
            return this.coord_type;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }

        public List<Double> getLocation() {
            return this.location;
        }

        public void setLoc_time(int loc_time) {
            this.loc_time = loc_time;
        }

        public int getLoc_time() {
            return this.loc_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCreate_time() {
            return this.create_time;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return this.direction;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return this.speed;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return this.radius;
        }

    }

    public class Items {
        private int status;

        private int total;

        private double distance;

        private List<Points_bd> points_bd;

        private List<Points_gg> points_gg;

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return this.total;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getDistance() {
            return this.distance;
        }

        public void setPoints_bd(List<Points_bd> points_bd) {
            this.points_bd = points_bd;
        }

        public List<Points_bd> getPoints_bd() {
            return this.points_bd;
        }

        public void setPoints_gg(List<Points_gg> points_gg) {
            this.points_gg = points_gg;
        }

        public List<Points_gg> getPoints_gg() {
            return this.points_gg;
        }
    }

    public List<Point> getListPoints() {
        List<Point> list = new ArrayList<Point>();

        if (items.getPoints_bd() == null || items.getPoints_bd().size() == 0) {
            return list;
        }
        Iterator<Points_bd> it = items.getPoints_bd().iterator();

        while (it.hasNext()) {
            Points_bd pois = (Points_bd) it.next();

            List<Double> location = pois.getLocation();
            if (Math.abs(location.get(0) - 0.0) < 0.01 && Math.abs(location.get(1) - 0.0) < 0.01) {
                continue;
            } else {
                DPoint point = new DPoint();
                point.setLatitude(location.get(1));
                point.setLongitude(location.get(0));
                CoordinateConverter converter = new CoordinateConverter(ZNAPPlication.getInstance().getContext());
                converter.from(CoordinateConverter.CoordType.BAIDU);
                try {
                    converter.coord(point);
                    DPoint dPoint = converter.convert();

                    /**
                     * 转成墨卡托坐标
                     */
                    final MPointEntity mercatorPoint = ILngLatMercator.lonLat2WebMercator(dPoint.getLongitude(), dPoint.getLatitude());

                    Point latLng = new Point(mercatorPoint.getX(), mercatorPoint.getY());
                    list.add(latLng);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
