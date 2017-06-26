package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.trackHistoryPage;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.google.gson.Gson;
import com.yunwei.baidu.HistoryTrackData;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.ILngLatMercator;
import com.yunwei.wetlandpark.greedao.TrackPointsTable;
import com.yunwei.wetlandpark.greedao.TrackPointsTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.TrackPointEntity;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.source.HistoryDataSource;
import com.yunwei.wetlandpark.utils.ILog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/21 16:17
 * @description:
 */

public class TrailHistoryPresenter implements TrailHistoryContract.Presenter,
        HistoryDataSource.GetDataCallBack<HistoryTrackData>{

    private HistoryDataSource mRepo;
    private TrailHistoryContract.View mView;
    private int mStartTime;
    private int mEndTime;
    private String mCacheID;
    private TrackPointsTable mTable;

    public TrailHistoryPresenter(HistoryDataSource repo, TrailHistoryContract.View view) {
        mRepo = repo;
        mView = view;
    }

    @Override
    public void searchTrail(long startTime, long endTime) {
        mView.showLoadingDialog();

        mStartTime = (int)(startTime / 1000);
        mEndTime = (int)(endTime / 1000);
        mCacheID = ZNAPPlication.getUserInfoEntity().getId() + "_" + mStartTime;

//        /*判断查询轨迹结束时间*/
//        if (System.currentTimeMillis() / 1000 - mEndTime > 60 * 60 * 6) {
//            /*查询本地*/
//            List<TrackPointsTable> list = ZNAPPlication.getDaoSession().getTrackPointsTableDao().queryBuilder()
//                    .where(TrackPointsTableDao.Properties.CacheID.eq(mCacheID)).list();
//            mTable = list.get(1);
//            if (list != null && list.size() > 0) {
//                //查询到本地数据，回调给mView
//                mView.showTrailResult(list.get(1).getCacheID());
//            } else {
//                /*查询本地为空，查询网络*/
//                mRepo.queryBaiduService(mStartTime,mEndTime,this);
//            }
//        } else {
//            /*查询网络*/
//            mRepo.queryBaiduService(mStartTime,mEndTime,this);
//        }

        List<TrackPointsTable> list = ZNAPPlication.getDaoSession().getTrackPointsTableDao().queryBuilder()
                .where(TrackPointsTableDao.Properties.CacheID.eq(mCacheID)).list();
        if (list != null && list.size() > 0) {
            mTable = list.get(0);
            if (System.currentTimeMillis() - endTime > 1000 * 60 * 60 * 6) {
                //查询到本地数据，回调给mView
                mView.dismissLoadingDialog();
                mView.showTrailResult(mTable.getCacheID());
            } else {
                /*时间在6小时以内，查询网络*/
                mRepo.queryBaiduService(mStartTime,mEndTime,this);
            }
        } else {
            /*查询本地为空，查询网络*/
            mRepo.queryBaiduService(mStartTime,mEndTime,this);
        }

    }

    @Override
    public void onDataLoaded(HistoryTrackData data) {
        List<TrackPointEntity> listPoints = getListPoints(data.points);
        /*缓存请求到的数据*/
        if (mTable == null) {
            mTable= new TrackPointsTable();
            mTable.setCacheID(mCacheID);
        }
        mTable.setDistance(data.getDistance());
        mTable.setPoints(new Gson().toJson(listPoints));
        /*缓存数据*/
        mRepo.saveTrackPoints(mTable);

        mView.dismissLoadingDialog();
        /*请求到网络数据，回调给mView*/
        mView.showTrailResult(mTable.getCacheID());
    }

    @Override
    public void onDataNotAvailable() {
        mView.dismissLoadingDialog();
        mView.showFailMessage();
    }

    /**
     * 坐标系转换(带时间节点的足迹点)
     * du yang
     * @param points
     * @return
     */
    private List<TrackPointEntity> getListPoints(List<HistoryTrackData.Points> points) {
        List<TrackPointEntity> list = new ArrayList<>();
        if (points == null || points.size() == 0) {
            return list;
        }
        Iterator<HistoryTrackData.Points> it = points.iterator();
        while (it.hasNext()) {
            HistoryTrackData.Points pois = it.next();

            List<Double> location = pois.getLocation();
            if (Math.abs(location.get(0) - 0.0) < 0.01 && Math.abs(location.get(1) - 0.0) < 0.01) {
                continue;
            } else {
                DPoint point = new DPoint();
                point.setLatitude(location.get(1));
                point.setLongitude(location.get(0));
                CoordinateConverter converter = new CoordinateConverter(mView.getActivity());
                converter.from(CoordinateConverter.CoordType.BAIDU);
                try {
                    converter.coord(point);
                    DPoint dPoint = converter.convert();
                    /**
                     * 转墨卡托坐标
                     */
                    MPointEntity mPointEntity = ILngLatMercator.lonLat2WebMercator(dPoint.getLongitude(), dPoint.getLatitude());
                    list.add(0, new TrackPointEntity(mPointEntity.getX(), mPointEntity.getY(),Long.valueOf(pois.getLoc_time())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
