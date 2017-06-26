package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.FormWriteView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/12/2 16:53
 * @description:
 */

public class ShootTroubleFragment extends FillBaseFragment implements ShootTroubleContract.View {


    @BindView(R.id.shootTroubleFragment_troubleDesc_wv)
    FormWriteView shootTroubleFragmentTroubleDescWv;
    @BindView(R.id.shootTroubleFragment_imgPicker_layout)
    FrameLayout shootTroubleFragmentImgPickerLayout;
    private TroubleShooterTable mTroubleShooterTable;
    ShootTroubleContract.Presenter mPresenter;
    private Dialog mLoadingDialog;

    @Override
    protected View getContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_shoot_trouble, null);
        ButterKnife.bind(this, view);

        if (getArguments().containsKey(ShootTroubleActivity.TASK_POINT_ID)) {
            /*新增界面调转*/
            initializeUIWidget();
            createEntity();
        } else if (getArguments().containsKey(Constants.KEY_BUNDLE_TROUBLE_TABLE)){
            /*历史界面跳转*/
            initializeUIWidget();
            mTroubleShooterTable = (TroubleShooterTable) getArguments().getSerializable(Constants.KEY_BUNDLE_TROUBLE_TABLE);
            validateUI();
        }
        return view;
    }


    private void initializeUIWidget() {
        shootTroubleFragmentImgPickerLayout.addView(mAccessoryView);
    }

    private void createEntity() {
        mTroubleShooterTable = new TroubleShooterTable();
        mTroubleShooterTable.setUserID(ZNAPPlication.getUserInfoEntity().getId());
        mTroubleShooterTable.setUserName(ZNAPPlication.getUserInfoEntity().getName());
        mTroubleShooterTable.setMissionDetailID(getArguments().getString(ShootTroubleActivity.TASK_POINT_ID));
        mTroubleShooterTable.setMissionID(String.valueOf(getArguments().getInt(ShootTroubleActivity.TASK_ID)));
    }

    private void validateUI() {
        shootTroubleFragmentTroubleDescWv.setContentText(mTroubleShooterTable.getNote());

        switch (mTroubleShooterTable.getLocalTag()) {
            case Constants.VALUE_UNSUBMITTED:
                if (!IStringUtils.isEmpty(mTroubleShooterTable.getImagesUrl())) {
                    mAccessoryView.setImgList(Arrays.asList(mTroubleShooterTable.getImagesUrl().split(",")));
                }
                break;
            case Constants.VALUE_SUBMITTED:
                if (!IStringUtils.isEmpty(mTroubleShooterTable.getImagesUrl())) {
                    mAccessoryView.setShowImageList(mTroubleShooterTable.getImagesUrl().split(","));
                } else {
                    shootTroubleFragmentImgPickerLayout.setVisibility(View.GONE);
                }
                shootTroubleFragmentTroubleDescWv.setNonEditable();
                mBottomLayout.setVisibility(View.GONE);
                break;
        }
    }

    private TroubleShooterTable catchData() {
        mTroubleShooterTable.setTime(System.currentTimeMillis());
        mTroubleShooterTable.setLng(ZNAPPlication.getInstance().getLng());
        mTroubleShooterTable.setLat(ZNAPPlication.getInstance().getLat());
        mTroubleShooterTable.setNote(shootTroubleFragmentTroubleDescWv.getContentText());
        if (mAccessoryView.getAccContent().size() > 0) {
            mTroubleShooterTable.setImagesUrl(IUtils.convertStringListToString(mAccessoryView.getAccContent()));
        } else {
            mTroubleShooterTable.setImagesUrl("");
        }
        return mTroubleShooterTable;
    }

    @Override
    protected void saveAction() {
        mPresenter.save();
    }

    @Override
    protected void submitAction() {
        if (TextUtils.isEmpty(shootTroubleFragmentTroubleDescWv.getContentText())) {
            ToastUtil.showToast(getContext(), "请填写维修反馈");
            return;
        }
        DialogFactory.showMsgDialog(getContext(), "提示", "是否提交表单？", "提交", "取消", new View.OnClickListener() {
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
    public TroubleShooterTable getUIEntity() {
        return catchData();
    }

    @Override
    public void setPresenter(ShootTroubleContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
