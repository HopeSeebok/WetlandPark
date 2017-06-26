package com.yunwei.wetlandpark.ui.biz.impl.task;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.ArrayList;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl.task
 * @Description:获取位置和附件
 * @date 2016/9/27
 * @changeby:
 */

public class TaskLocationFileChildBiz {

    private final String mTaskId;
    private final String mUserId;

    public TaskLocationFileChildBiz(Fragment fragment, Context context) {
        mTaskId = fragment.getArguments().getString(Constants.TASKID);
        mUserId = (String) ISpfUtil.getValue(context, Constants.ACCOUNT_ID_KEY, "");
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> lPoints = new ArrayList<>();
//        List<LocationEntity> lLocations = DbUtil.getLocationService(ZNAPPlication.getInstance().getContext()).query("where TASK_ID = ? and USER_ID=?", mTaskId, mUserId);
//        for (int i = 0; i < lLocations.size(); i++) {
//            MPointEntity mPointEntity = ILngLatMercator.lonLat2WebMercator(lLocations.get(i).getLat(), lLocations.get(i).getLng());
//            lPoints.add(new Point(mPointEntity.getX(),mPointEntity.getY()));
//        }
        return lPoints;
    }

//    public List<ImagesEntity> getImages() {
//        return DbUtil.getImagesService(ZNAPPlication.getInstance().getContext()).query("where TASK_ID = ? and USER_ID=?", mTaskId, mUserId);
//    }
}
