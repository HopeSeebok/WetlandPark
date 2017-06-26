package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice;

import android.content.Intent;
import android.os.Bundle;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice.data.source.DisposableLinkRepo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity;

public class DisposableLinkActivity extends FillBaseActvity {

    private DisposableLinkFragment mDisposableLinkFragment;
    public static final String KEY_BUNDLE_RFID = "KEY_BUNDLE_RFID";

    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        mDisposableLinkFragment = new DisposableLinkFragment();
        mDisposableLinkFragment.setArguments(getIntent().getExtras());
        new DisposableLinkPresenter(DisposableLinkRepo.getInstance(),mDisposableLinkFragment);
        return mDisposableLinkFragment;
    }

    @Override
    public String getHeadTitle() {
        return "关联签到点";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectTroublePositionActivity.RESULT_POSITION_SELECTOR) {
            Point point = (Point) data.getSerializableExtra(SelectTroublePositionActivity.KEY_SELECTED_POINT);
            mDisposableLinkFragment.onPointSelected(point);
        }
    }
}
