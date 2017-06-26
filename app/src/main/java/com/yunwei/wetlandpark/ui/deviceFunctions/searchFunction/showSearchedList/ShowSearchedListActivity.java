package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList;

import android.os.Bundle;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source.SearchDeviceRemoteRepo;
import com.yunwei.wetlandpark.utils.IUtils;

public class ShowSearchedListActivity extends BaseActivity {

    private ShowSearchedListFragment mContentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);
        setToolbarTitle("查询结果");
        /*管理、初始化Fragment*/
        mContentFragment = new ShowSearchedListFragment();
        mContentFragment.setArguments(getIntent().getExtras());
        /*管理、初始化PRESENTER*/
        new ShowSearchedListPresenter(SearchDeviceRemoteRepo.getInstance(), mContentFragment);

        IUtils.addFragmentToActivity(getSupportFragmentManager(),mContentFragment,R.id.frameLayoutActivity_frameLayout);

    }
}
