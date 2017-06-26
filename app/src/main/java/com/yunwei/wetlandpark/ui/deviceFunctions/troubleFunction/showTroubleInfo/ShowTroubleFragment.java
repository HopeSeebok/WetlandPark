package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.showTroubleInfo;

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
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.AccessoryView;
import com.yunwei.wetlandpark.widget.FormRadioSelectorView;
import com.yunwei.wetlandpark.widget.FormWriteView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author CBOK
 * @date 2016/12/2 16:54
 * @description:
 */

public class ShowTroubleFragment extends BaseFragment {

    @BindView(R.id.showTroubleFragment_emergency_rsv)
    FormRadioSelectorView showTroubleFragmentEmergencyRsv;
    @BindView(R.id.showTroubleFragment_remark_wv)
    FormWriteView showTroubleFragmentRemarkWv;
    @BindView(R.id.showTroubleFragment_imgPicker_layout)
    FrameLayout showTroubleFragmentImgPickerLayout;
    @BindView(R.id.showTroubleFragment_maintain_btn)
    Button showTroubleFragmentMaintainBtn;
    @BindView(R.id.showTroubleFragment_bottom_layout)
    LinearLayout showTroubleFragmentBottomLayout;
    @BindView(R.id.showTroubleFragment_troubleType_wv)
    FormWriteView showTroubleFragmentTroubleTypeWv;
    private TroubleInfo mTroubleInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTroubleInfo = (TroubleInfo) getArguments().getSerializable(Constants.KEY_BUNDLE_TROUBLE_INFO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_trouble, null);
        ButterKnife.bind(this, view);

        if (mTroubleInfo != null) {
            initializeUI();

        } else {
            /* 若无数据源，则退出Activity，防止app崩溃*/
            ToastUtil.showToast(getActivity().getApplicationContext(), "无法获取设施信息，请联系技术支持人员");
            getActivity().finish();
        }
        return view;
    }

    private void initializeUI() {
        /*若有图片，展示图片*/
        if (mTroubleInfo.getImgs() != null && mTroubleInfo.getImgs().size() > 0) {
            AccessoryView accessoryView = new AccessoryView(getActivity());
            accessoryView.setShowImageList(IUtils.convertImgListToStringList(mTroubleInfo.getImgs()));
            showTroubleFragmentImgPickerLayout.addView(accessoryView);
        }
        showTroubleFragmentTroubleTypeWv.setContentText(mTroubleInfo.getDangerType()).setNonEditable();
        showTroubleFragmentEmergencyRsv.setCheckingButton(mTroubleInfo.getMissionLevel()).setNonEditable();
        showTroubleFragmentRemarkWv.setContentText(mTroubleInfo.getNote()).setNonEditable();
    }


    @OnClick(R.id.showTroubleFragment_maintain_btn)
    public void onClick() {

    }
}
