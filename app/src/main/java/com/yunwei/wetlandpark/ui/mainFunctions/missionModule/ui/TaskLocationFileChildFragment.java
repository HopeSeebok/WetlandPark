package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.common.ShowImageActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter.TaskLocationFileChildPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskLocationFileChildView;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.map.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.task
 * @Description:位置和附件
 * @date 2016/9/27
 * @changeby:
 */

public class TaskLocationFileChildFragment extends BaseFragment implements TaskLocationFileChildView {
    private MapView mMapView;
    private LinearLayout mCameraLl;
    private FrameLayout mMapViewFl;
    private TaskLocationFileChildPresenter mTaskLocationFileChildPresenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            if (mMapView == null) {
                new Handler().postDelayed(() -> {
                    mMapView = new MapView(getActivity());
                    mMapViewFl.addView(mMapView);
                    mTaskLocationFileChildPresenter.initUI();
                }, 200);
            }
        } else {
            if (mMapView != null) {
                mMapView = null;
                mMapViewFl.removeAllViews();
                mCameraLl.removeAllViews();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.task_location_file_child_fragment, container, false);
        initView(rootView);
        mTaskLocationFileChildPresenter = new TaskLocationFileChildPresenter(getActivity(),this, this);
        return rootView;
    }

    private void initView(View rootView) {
        mMapViewFl = (FrameLayout) rootView.findViewById(R.id.mapview_fl);
//        mMapView=(MapView)rootView.findViewById(R.id.location_Mv);
//        mMapView.setBtmOperaLayoutVisibility(View.GONE);
        mCameraLl = (LinearLayout) rootView.findViewById(R.id.camera_Ll);
    }

    @Override
    public void setView(final List<Point> points, String images) {
        mMapView.addInsideMapLayer(IFileUtils.getMapLayerDir()+"/CMCCLayer.tpk");
        mMapView.setSwitchMapLayerButtonVisibility(View.GONE);
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            mMapView.setPoint(point,R.mipmap.location_red);
        }

        setImages(images);
    }

    private void setImages(String images) {
        if (images.isEmpty()) {
            return;
        }
        String[] imageList = images.split(",");
        if (imageList.length == 0) {
            mCameraLl.setVisibility(View.GONE);
            return;
        }else {
            mCameraLl.setVisibility(View.VISIBLE);
            TextView textView=new TextView(getActivity());
            textView.setText("附件：");
            mCameraLl.addView(textView);
        }
        final ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < imageList.length; i++) {
            imageUrls.add(imageList[i]);
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(180, 180));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(10, 10, 10, 10);
            final int position = i;
            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ShowImageActivity.class);
                intent.putExtra(ShowImageActivity.SHOW_IMG_INDEX, position);
                intent.putExtra(ShowImageActivity.SHOW_IMG_LIST, imageUrls);
                getActivity().startActivity(intent);
            });
            Glide.with(getActivity()).load(imageList[i]).into(imageView);
            mCameraLl.addView(imageView);
        }
    }
}
