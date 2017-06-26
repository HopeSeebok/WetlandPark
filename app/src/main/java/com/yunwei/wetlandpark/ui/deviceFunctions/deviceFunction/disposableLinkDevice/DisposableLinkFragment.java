package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.esri.core.geometry.Point;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.FormClickableView;
import com.yunwei.wetlandpark.widget.FormWriteView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity.REQUEST_POSITION_SELECTOR;

/**
 * @author CBOK
 * @date 2016/12/7 0:21
 * @description:
 */

public class DisposableLinkFragment extends FillBaseFragment implements DisposableLinkContract.View {

    @BindView(R.id.disposableLinkFragment_rfid)
    FormWriteView disposableLinkFragmentRfid;
    @BindView(R.id.disposableLinkFragment_position_cv)
    FormClickableView disposableLinkFragmentPositionCv;
    @BindView(R.id.disposableLinkFragment_code_wv)
    FormWriteView disposableLinkFragmentCodeWv;
    @BindView(R.id.disposableLinkFragment_imgPicker_layout)
    FrameLayout disposableLinkFragmentImgPickerLayout;
    @BindView(R.id.disposableLinkFragment_address_wv)
    FormWriteView disposableLinkFragmentAddressWv;
    private TextView mAddressView;
    private double mLng;
    private double mLat;
    private String mRFID;
    DisposableLinkContract.Presenter mPresenter;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRFID = getArguments().getString(DisposableLinkActivity.KEY_BUNDLE_RFID);
    }

    @Override
    protected View getContentView(LayoutInflater inflater) {
        setSaveButtonInvisible();
        View view = inflater.inflate(R.layout.fragment_disposable_link, null);
        ButterKnife.bind(this, view);
        initializeUIWidget();
        return view;
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected void submitAction() {
        if (TextUtils.isEmpty(disposableLinkFragmentPositionCv.getContentText())) {
            ToastUtil.showToast(getContext(), "请选择签到点位置");
            return;
        }
        if (TextUtils.isEmpty(disposableLinkFragmentCodeWv.getContentText())) {
            ToastUtil.showToast(getContext(), "请填写签到点编号");
            return;
        }
        DialogFactory.showMsgDialog(getContext(), "提示", "是否提交？", "提交", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit();
            }
        }, null);
    }

    private void initializeUIWidget() {
        disposableLinkFragmentImgPickerLayout.addView(mAccessoryView);
        mAddressView = disposableLinkFragmentPositionCv.getContentView();
        mAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISkipActivityUtil.startIntentForResult(getActivity(), SelectTroublePositionActivity.class, REQUEST_POSITION_SELECTOR);
            }
        });
    }

    public void onPointSelected(Point point) {
        mLng = point.getX();
        mLat = point.getY();
        mAddressView.setText("X:" + mLng + "\nY:" + mLat);
    }

    private DeviceInfo catchData() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCode(disposableLinkFragmentCodeWv.getContentText());
        deviceInfo.setName(disposableLinkFragmentAddressWv.getContentText());
        deviceInfo.setLat(mLat);
        deviceInfo.setLng(mLng);
        deviceInfo.setRFID(mRFID);
        deviceInfo.setDeviceTypeId(1);
        if (mAccessoryView.getAccContent().size() > 0) {
            deviceInfo.setImgs(IUtils.convertStringListToImgList(mAccessoryView.getAccContent()));
        } else {
            deviceInfo.setImgs(null);
        }
        return deviceInfo;
    }

    @Override
    public void showUploadingDialog() {
        mLoadingDialog = DialogFactory.createLoadingDialog(getActivity(), "正在上传..");
    }

    @Override
    public void dismissUploadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showUploadSuccessMsgAndFinishActivity() {
        ToastUtil.showToast(getContext(), "上传成功");
        getActivity().finish();
    }

    @Override
    public void showUploadFailedMsg() {
        ToastUtil.showToast(getActivity(), "上传失败，请检查网络并重试");
    }

    @Override
    public DeviceInfo getViewEntity() {
        return catchData();
    }

    @Override
    public void setPresenter(DisposableLinkContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
