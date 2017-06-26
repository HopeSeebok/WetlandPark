package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble;

import android.content.Intent;
import android.os.Bundle;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.source.MakeTroubleRepo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity;

public class MakeTroubleActivity extends FillBaseActvity {

    private MakeTroubleFragment mMakeTroubleFragment;

    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        mMakeTroubleFragment = new MakeTroubleFragment();
        mMakeTroubleFragment.setArguments(bundle);
        new MakeTroublePresenter(MakeTroubleRepo.getInstance(), mMakeTroubleFragment);
        return mMakeTroubleFragment;
    }

    @Override
    public String getHeadTitle() {
        return "上报隐患";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectTroublePositionActivity.RESULT_POSITION_SELECTOR) {
            Point point = (Point) data.getSerializableExtra(SelectTroublePositionActivity.KEY_SELECTED_POINT);
            mMakeTroubleFragment.onPointSelected(point);
        }
    }
}
