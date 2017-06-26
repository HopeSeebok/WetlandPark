package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.IQueryLocationTrackPoint;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackPointListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:查询足迹点
 * @date 2016/9/20 14:32
 */
public class QueryLocationTrackPointBiz implements IQueryLocationTrackPoint {

    @Override
    public void queryTrackPoint(final Activity activity, final long tId, final QueryLocationTrackPointListener listener) {
        listener.onQueryPointStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    final List<Point> points = new ArrayList<>();
//                    List<TrackPoint> lists = ZNAPPlication.getInstance().getDaoSession().getTrackPointDao().queryBuilder().where(TrackPointDao.Properties.PId.eq(tId)).list();
//                    for (TrackPoint entity : lists) {
//                        Point point = new Point();
//                        point.setY(entity.getY());
//                        point.setX(entity.getX());
//                        points.add(point);
//                    }
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            listener.onQueryPointSuccess(points);
//                        }
//                    });
                } catch (Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onQueryPointFailure(401, "查询失败!");
                        }
                    });
                } finally {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onQueryPointEnd();
                        }
                    });
                }
            }
        }).start();
    }
}
