package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.IQueryLocationTrack;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:查询足迹业务类
 * @date 2016/9/20 14:52
 */
public class QueryLocationTrackBiz implements IQueryLocationTrack {

    @Override
    public void queryLocTrack(final Activity activity, final QueryLocationTrackListener listener) {
        listener.onQueryLocTrackStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    final List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.State.eq(Constants.TRACK_RECORD_STATE.COMPLETE.getValue())).where(TrackDao.Properties.Revstr1.eq(ZNAPPlication.getUserInfoEntity(activity).getId())).where(TrackDao.Properties.Distance.gt(0.0)).orderDesc(TrackDao.Properties.EndTime).list();
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            listener.onQueryLocTrackSuccess(list);
//                        }
//                    });
                } catch (Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onQueryLocTrackFailure(401, "查询失败");
                        }
                    });
                } finally {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onQuerylocTrackEnd();
                        }
                    });
                }
            }
        }).start();
    }
}
