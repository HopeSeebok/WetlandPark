package com.yunwei.wetlandpark.ui.presenter;

import android.app.Activity;

import com.yunwei.wetlandpark.entity.MapSearchEntity;
import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.ui.biz.impl.SearchBiz;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback.GetDeviceInfoListener;
import com.yunwei.wetlandpark.ui.view.SearchListView;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:
 * @date 2016/9/30
 * @changeby:
 */

public class SearchListPresenter {

    private final Activity mActivity;
    private final SearchListView mSearchListView;
    private final SearchBiz mSearchBiz;

    public SearchListPresenter(Activity activity, SearchListView searchListView) {
        mActivity=activity;
        mSearchListView=searchListView;
        mSearchBiz=new SearchBiz(activity);
    }

    public void requestHDById(MapSearchEntity dataEntity) {
        mSearchBiz.getHDDeviceInfo(dataEntity.getGUID(), new GetDeviceInfoListener() {
            @Override
            public void success(Facility facility) {
                // TODO: 2016/9/29 跳转到隐患界面
//                Intent intent = new Intent(mActivity, CheckWorkActivity.class);
//                intent.putExtra(Constants.BUNDLE_TAG_FLAG,Constants.CheckingFacility.RecordType.MAINTAIN_TYPE);
//                intent.putExtra(Constants.BUNDLE_TAG_FLAG_TWO,1);
//                intent.putExtra(Constants.BUNDLE_ENTITY_FLAG_ONE, facility);
//                mActivity.startActivity(intent);
            }
            @Override
            public void failure() {
            }
        });
    }
}
