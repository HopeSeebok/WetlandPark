package com.yunwei.wetlandpark.ui.mainFunctions.mineModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yunwei.wetlandpark.ui.account.userInfo.EditUserInfoActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment.AboutFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment.NotificationsFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment.SuggestionFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment.TrackSetFragment;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.account.AccountInfoFragment;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;

/**
 * @Package: com.yunwei.zaina.ui.activity.set
 * @Description:
 * @author: Aaron
 * @date: 2016-06-26
 * @Time: 11:16
 * @version: V1.0
 */
public class SetingInfoActvity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    public static final String HEAD_TITLE_FLAG = "title_flag";
    public static final String SHOW_FRAGMENT_FLAG = "show_flag";
    private String from_flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_seting_info);
        setToolbarTitle(getIntent().getStringExtra(HEAD_TITLE_FLAG));
        from_flag = getIntent().getStringExtra(SHOW_FRAGMENT_FLAG);
        initUI();
    }

    private void initUI() {
        if (IStringUtils.isEmpty(from_flag)) {
            return;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (from_flag.equals(AccountInfoFragment.ACCOUNT_INFO_FLAG)) {
            setToolbarRightText("编辑");
            transaction.replace(R.id.seting_info_content, new AccountInfoFragment());
        } else if (from_flag.equals(NotificationsFragment.NOTIFICATIONS_INFO_FLAG)) {
            transaction.replace(R.id.seting_info_content, new NotificationsFragment());
        } else if (from_flag.equals(SuggestionFragment.SUGGESTION_INFO_FLAG)) {
            transaction.replace(R.id.seting_info_content, new SuggestionFragment());
        } else if (from_flag.equals(AboutFragment.ABOUT_FLAG)) {
            transaction.replace(R.id.seting_info_content, new AboutFragment());
        } else if (from_flag.equals(TrackSetFragment.SET_TRACK_VALUE)) {
            transaction.replace(R.id.seting_info_content, new TrackSetFragment());
        }
        transaction.commit();
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (from_flag.equals(AccountInfoFragment.ACCOUNT_INFO_FLAG)) {
            ISkipActivityUtil.startIntent(this, EditUserInfoActivity.class);
        }
    }
}
