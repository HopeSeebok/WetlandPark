package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.showDeviceInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.CheckPlanActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.MaintainDeviceActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.AccessoryView;
import com.yunwei.wetlandpark.widget.FormWriteView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author CBOK
 * @date 2016/11/3 22:37
 * @description:
 */

public class ShowDeviceFragment extends BaseFragment {

    @BindView(R.id.showDeviceFragment_deviceCode_wv)
    FormWriteView showDeviceFragmentDeviceCodeWv;
    @BindView(R.id.showDeviceFragment_imgPicker_layout)
    FrameLayout showDeviceFragmentImgPickerLayout;
    @BindView(R.id.showDeviceFragment_check_btn)
    Button showDeviceFragmentCheckBtn;
    @BindView(R.id.showDeviceFragment_maintain_btn)
    Button showDeviceFragmentMaintainBtn;
    @BindView(R.id.showDeviceFragment_bottom_layout)
    LinearLayout showDeviceFragmentBottomLayout;
    @BindView(R.id.showDeviceFragment_address_wv)
    FormWriteView showDeviceFragmentAddressWv;
    private DeviceInfo mDeviceInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeviceInfo = (DeviceInfo) getArguments().getSerializable(Constants.KEY_BUNDLE_DEVICE_INFO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_device, null);
        ButterKnife.bind(this, view);
        //隐藏底部按钮
        showDeviceFragmentBottomLayout.setVisibility(View.GONE);

        if (mDeviceInfo != null) {
            initializeUI();

        } else {
            /* 若无数据源，则退出Activity，防止app崩溃*/
            ToastUtil.showToast(getActivity().getApplicationContext(), "无法获取设施信息，请联系技术支持人员");
            getActivity().finish();
        }
        return view;
    }

    private void initializeUI() {
//        switch (mDeviceInfo.getDeviceStatus()) {
//            case Constants.VALUE_DEVICE_STATUS_NORMAL:
//                showDeviceFragmentDeviceStatusWv.setContentText("正常").setNonEditable();
//                break;
//            case Constants.VALUE_DEVICE_STATUS_FAULT:
//                showDeviceFragmentDeviceStatusWv.setContentText("故障").setNonEditable();
//                break;
//            case Constants.VALUE_DEVICE_STATUS_ABANDONED:
//                showDeviceFragmentDeviceStatusWv.setContentText("废弃").setNonEditable();
//                showDeviceFragmentCheckBtn.setVisibility(View.GONE);
//                showDeviceFragmentMaintainBtn.setVisibility(View.GONE);
//                break;
//        }
        showDeviceFragmentDeviceCodeWv.setContentText(mDeviceInfo.getCode()).setNonEditable();
        showDeviceFragmentAddressWv.setContentText(mDeviceInfo.getAddr()).setNonEditable();
        /*若有图片，展示图片*/
        if (mDeviceInfo.getImgs() != null && mDeviceInfo.getImgs().size() > 0) {
            AccessoryView accessoryView = new AccessoryView(getActivity());
            accessoryView.setShowImageList(IUtils.convertImgListToStringList(mDeviceInfo.getImgs()));
            showDeviceFragmentImgPickerLayout.addView(accessoryView);
        }
//        showDeviceFragmentDeviceNameWv.setContentText(mDeviceInfo.getName()).setNonEditable();
//        showDeviceFragmentDeviceTypeWv.setContentText(mDeviceInfo.getDeviceTypeName()).setNonEditable();
//        showDeviceFragmentDeviceAddressWv.setContentText(mDeviceInfo.getAddr()).setNonEditable();
    }


    @OnClick({R.id.showDeviceFragment_check_btn, R.id.showDeviceFragment_maintain_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showDeviceFragment_check_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.KEY_BUNDLE_DEVICE_INFO, mDeviceInfo);
                ISkipActivityUtil.startIntent(getContext(), CheckPlanActivity.class, bundle);
                break;
            case R.id.showDeviceFragment_maintain_btn:
                Bundle bundleMaintain = new Bundle();
                bundleMaintain.putSerializable(Constants.KEY_BUNDLE_DEVICE_INFO, mDeviceInfo);
                ISkipActivityUtil.startIntent(getContext(), MaintainDeviceActivity.class, bundleMaintain);
                break;
        }
    }
}