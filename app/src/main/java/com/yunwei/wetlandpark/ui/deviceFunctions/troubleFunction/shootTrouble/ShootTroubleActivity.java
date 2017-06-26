package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble;

import android.content.Context;
import android.os.Bundle;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseActvity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data.source.ShootTroubleRepo;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

public class ShootTroubleActivity extends FillBaseActvity {
    public static String TASK_POINT_ID="taskPointId";
    public static String TASK_ID="taskId";

    @Override
    public FillBaseFragment getFillingFragment(Bundle bundle) {
        ShootTroubleFragment shootTroubleFragment = new ShootTroubleFragment();
        shootTroubleFragment.setArguments(bundle);
        new ShootTroublePresenter(ShootTroubleRepo.getInstance(), shootTroubleFragment);
        return shootTroubleFragment;
    }

    @Override
    public String getHeadTitle() {
        return "维修/维护";
    }

    /**
     * 维护任务跳转
     *
     * @param context
     * @param taskPointId
     */
    public static void startActivityMaintainDevice(Context context, int taskId, String taskPointId) {
        Bundle bundle = new Bundle();
        bundle.putInt(TASK_ID, taskId);
        bundle.putString(TASK_POINT_ID, taskPointId);
        bundle.putSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE, taskPointId);
        ISkipActivityUtil.startIntent(context, ShootTroubleActivity.class, bundle);
    }

    /**
     * 维护历史任务跳转
     *
     * @param context
     * @param troubleShooterTable
     */
    public static void startActivityMaintainDevice(Context context, TroubleShooterTable troubleShooterTable) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE, troubleShooterTable);
        ISkipActivityUtil.startIntent(context, ShootTroubleActivity.class, bundle);
    }
}
