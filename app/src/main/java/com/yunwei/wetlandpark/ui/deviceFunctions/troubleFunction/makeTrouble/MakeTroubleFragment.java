package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.esri.core.geometry.Point;
import com.yunwei.map.entity.LngLatEntity;
import com.yunwei.map.utils.ILngLatMercator;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.greedao.TroubleTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.FormConfigPresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.FormClickableView;
import com.yunwei.wetlandpark.widget.FormRadioSelectorView;
import com.yunwei.wetlandpark.widget.FormSpinnerSelectorView;
import com.yunwei.wetlandpark.widget.FormWriteView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.selectPosition.SelectTroublePositionActivity.REQUEST_POSITION_SELECTOR;

/**
 * @author CBOK
 * @date 2016/12/2 16:52
 * @description:
 */

public class MakeTroubleFragment extends FillBaseFragment implements MakeTroubleContract.View {

    @BindView(R.id.makeTroubleFragment_troubleAddress_cv)
    FormClickableView makeTroubleFragmentTroubleAddressCv;
    @BindView(R.id.makeTroubleFragment_remark_wv)
    FormWriteView makeTroubleFragmentRemarkWv;
    @BindView(R.id.makeTroubleFragment_imgPicker_layout)
    FrameLayout makeTroubleFragmentImgPickerLayout;
    @BindView(R.id.makeTroubleFragment_emergency_rsv)
    FormRadioSelectorView makeTroubleFragmentEmergencyRsv;
    private TextView mAddressView;
    private TroubleTable mTroubleTable;
    MakeTroubleContract.Presenter mPresenter;
    private Dialog mLoadingDialog;
    private double mLng;
    private double mLat;

    @Override
    protected View getContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_make_trouble, null);
        ButterKnife.bind(this, view);
        if (!getArguments().containsKey(Constants.KEY_BUNDLE_TROUBLE_TABLE)) {
            /*新增界面调转*/
            initializeUIWidget();
            createEntity();
        } else {
            /*历史界面跳转*/
            initializeUIWidget();
            mTroubleTable = (TroubleTable) getArguments().getSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE);
            if (mTroubleTable.getLng() > 0 && mTroubleTable.getLat() > 0) {
                mLng = mTroubleTable.getLng();
                mLat = mTroubleTable.getLat();
            }
            validateUI();
        }
        return view;
    }

    private void initializeUIWidget() {
        mAddressView = makeTroubleFragmentTroubleAddressCv.getContentView();
        mAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISkipActivityUtil.startIntentForResult(getActivity(), SelectTroublePositionActivity.class, REQUEST_POSITION_SELECTOR);
            }
        });
        /*设置Trouble类型*/
//        String string = (String) ISpfUtil.getValue(FormConfigPresenter.TROUBLE_TYPES_KEY, "");
//        List<String> stringList;
//        if (string != null) {
//            stringList = Arrays.asList(string.split(","));
//            makeTroubleFragmentTroubleTypeSsv.setDataSource(getContext(), stringList);
//        }
        /*需求：把“重要”状态去掉*/
        makeTroubleFragmentEmergencyRsv.setGone(1);
        makeTroubleFragmentEmergencyRsv.setOnMyCheckedListener(new FormRadioSelectorView.MyCheckedListener() {
            @Override
            public void onFirstButtonChecked() {
            }

            @Override
            public void onSecondButtonChecked() {
            }

            @Override
            public void onThirdButtonChecked() {
            }
        });
        makeTroubleFragmentImgPickerLayout.addView(mAccessoryView);
    }

    private void createEntity() {
        mTroubleTable = new TroubleTable();
        mTroubleTable.setUserID(ZNAPPlication.getUserInfoEntity().getId());
        mTroubleTable.setUserName(ZNAPPlication.getUserInfoEntity().getName());
    }

    private void validateUI() {
        if (mTroubleTable.getLng() > 0 && mTroubleTable.getLat() > 0) {
            mAddressView.setText("已选择");
        }
//        makeTroubleFragmentTroubleTypeSsv.setContentText(mTroubleTable.getTroubleType());
        makeTroubleFragmentEmergencyRsv.setCheckingButton(mTroubleTable.getEmergencyLevel());
        makeTroubleFragmentRemarkWv.setContentText(mTroubleTable.getNote());

        switch (mTroubleTable.getLocalTag()) {
            case Constants.VALUE_UNSUBMITTED:
                if (!IStringUtils.isEmpty(mTroubleTable.getImagesUrl())) {
                    mAccessoryView.setImgList(Arrays.asList(mTroubleTable.getImagesUrl().split(",")));
                }
                break;
            case Constants.VALUE_SUBMITTED:
                if (!IStringUtils.isEmpty(mTroubleTable.getImagesUrl())) {
                    mAccessoryView.setShowImageList(mTroubleTable.getImagesUrl().split(","));
                } else {
                    makeTroubleFragmentImgPickerLayout.setVisibility(View.GONE);
                }
                makeTroubleFragmentTroubleAddressCv.setNonEditable();
//                makeTroubleFragmentTroubleTypeSsv.setNonEditable();
                makeTroubleFragmentEmergencyRsv.setNonEditable();
                makeTroubleFragmentRemarkWv.setNonEditable();
                mBottomLayout.setVisibility(View.GONE);
                break;
        }
    }

    private TroubleTable catchData() {
        mTroubleTable.setTime(System.currentTimeMillis());
        mTroubleTable.setLng(mLng);
        mTroubleTable.setLat(mLat);
        mTroubleTable.setEmergencyLevel(makeTroubleFragmentEmergencyRsv.getCheckingButton());
//        mTroubleTable.setTroubleType(makeTroubleFragmentTroubleTypeSsv.getContentText());
        mTroubleTable.setNote(makeTroubleFragmentRemarkWv.getContentText());
        if (mAccessoryView.getAccContent().size() > 0) {
            mTroubleTable.setImagesUrl(IUtils.convertStringListToString(mAccessoryView.getAccContent()));
        } else {
            mTroubleTable.setImagesUrl("");
        }
        return mTroubleTable;
    }

    public void onPointSelected(Point point) {
        mLng = point.getX();
        mLat = point.getY();
        mAddressView.setText("X:"+mLng+"\nY:"+mLat);
    }

    @Override
    protected void saveAction() {
        mPresenter.save();
    }

    @Override
    protected void submitAction() {
        if (TextUtils.isEmpty(makeTroubleFragmentTroubleAddressCv.getContentText())) {
            ToastUtil.showToast(getContext(), "请选择隐患位置");
            return;
        }
//        if (TextUtils.isEmpty(makeTroubleFragmentTroubleTypeSsv.getContentText())) {
//            ToastUtil.showToast(getContext(), "请选择故障类型");
//            return;
//        }
        DialogFactory.showMsgDialog(getContext(), "提示", "是否提交？", "提交", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit();
            }
        }, null);
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
    public void showSaveSuccessMsg(String msg) {
        ToastUtil.showToast(getContext(), msg);
    }

    @Override
    public void showSaveFailedMsg() {
        ToastUtil.showToast(getContext(), "保存失败");
    }

    @Override
    public TroubleTable getTroubleData() {
        return catchData();
    }

    @Override
    public void setPresenter(MakeTroubleContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
