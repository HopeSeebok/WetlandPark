package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.CheckAppVersion.CheckAppVersionContract;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.CheckAppVersion.CheckAppVersionPresenter;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.source.CheckAppVersionRemoteRepo;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.dialog.DialogFactory;

/**
 * @Package: com.yunwei.zaina.ui.activity.set.fragment
 * @Description:关于界面
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 11:31
 * @version: V1.0
 */
public class AboutFragment extends BaseFragment implements CheckAppVersionContract.View {

    public static final String ABOUT_FLAG = "about_flag";

    private TextView versionText;

    private CheckAppVersionPresenter checkAppVersionPresenter;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkAppVersionPresenter = new CheckAppVersionPresenter(CheckAppVersionRemoteRepo.newInstance(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, null);
        versionText = (TextView) view.findViewById(R.id.version_text);
        view.findViewById(R.id.check_app_text).setOnClickListener(this);
        versionText.setText(IUtils.getStrToRes(getActivity(), R.string.app_name) + "V" + IUtils.getVersionName(getActivity()));
        return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.check_app_text:
                checkAppVersionPresenter.checkAppVersion();
                break;
        }
    }

    @Override
    public void showRequestFail() {
        ToastUtil.showToast(getContext(),"检测版本请求失败");
    }

    @Override
    public void showIsLatestVersion() {
        ToastUtil.showToast(getContext(),"当前是最新版本");
    }

    @Override
    public void showCheckingDialog() {
        mLoadingDialog = DialogFactory.createLoadingDialog(getActivity(), "检测中..");
    }

    @Override
    public void dismissCheckingDialog() {
        mLoadingDialog.dismiss();
    }
}
