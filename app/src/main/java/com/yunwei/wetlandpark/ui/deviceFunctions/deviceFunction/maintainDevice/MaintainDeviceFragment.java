package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.MaintainDeviceTable;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.widget.FormSeletorView;
import com.yunwei.wetlandpark.widget.FormWriteView;
import com.yunwei.wetlandpark.widget.MenuMultiSelectView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice
 * @Description:
 * @date 2016/11/17 15:00
 */

public class MaintainDeviceFragment extends FillBaseFragment implements MaintainDeviceContract.View, FormSeletorView.OnFormSeletorClickListener{

    @BindView(R.id.maintainFragment_facilityCode_formView)
    FormWriteView maintainDeviceDeviceCodeFormView;
    @BindView(R.id.maintainFragment_facilityType_formView)
    FormWriteView maintainDeviceDeviceTypeFormView;
    @BindView(R.id.maintainFragment_address_formView)
    FormWriteView maintainDeviceAddressFormView;
    @BindView(R.id.maintainFragment_time_formView)
    FormWriteView maintainDeviceTimeFormView;
    @BindView(R.id.maintainFragment_maintainDesc_formView)
    FormSeletorView maintainDeviceMaintainDescSelector;
    @BindView(R.id.maintainFragment_HDType_formView)
    FormWriteView maintainDeviceHDTypeFormSelector;
    @BindView(R.id.maintainFragment_HDDesc_formView)
    FormWriteView maintainDeviceHDDescFormView;
    @BindView(R.id.maintainFragment_HD_layout)
    LinearLayout maintainDeviceHDLayout;
    @BindView(R.id.maintainFragment_remark_formView)
    FormWriteView maintainDeviceNoteFormView;
    @BindView(R.id.maintainFragment_imgPicker_layout)
    FrameLayout maintainDeviceImgPickerLayout;

    private MaintainDeviceTable mTable;
    private DeviceInfo mDeviceInfo;
    private MaintainDeviceContract.Presenter mPresenter;
    private Dialog mDialog;
    private String[] maintainContentRes;
    private MenuMultiSelectView menuSeletorView;

    @Override
    protected View getContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_maintain, null);
        ButterKnife.bind(this, view);
        maintainContentRes = getResources().getStringArray(R.array.maintain_content);

        if (getArguments().containsKey(Constants.KEY_BUNDLE_DEVICE_INFO)) {
            /* BUNDLE_DEVICE_INFO : 设施信息展示界面跳转实体*/
            mDeviceInfo = (DeviceInfo) getArguments().getSerializable(Constants.KEY_BUNDLE_DEVICE_INFO);
            createEntity();
            initUI();
        } else if (getArguments().containsKey(Constants.KEY_BUNDLE_MAINTAIN_DEVICE_TABLE)) {
            /* KEY_BUNDLE_CHECK_DEVICE_TABLE : 历史界面跳转实体*/
            mTable = (MaintainDeviceTable) getArguments().getSerializable(Constants.KEY_BUNDLE_MAINTAIN_DEVICE_TABLE);
            initUI();
        } else {
            /* 若无数据源，则退出Activity，防止app崩溃*/
            showToast("无法进行操作，请联系技术支持人员");
            getActivity().finish();
        }
        return view;
    }

    /**
     * 将'设备信息实体'转成'维护设备实体'
     */
    private void createEntity() {
        mTable = new MaintainDeviceTable();
        mTable.setDeviceID(mDeviceInfo.getId());
        mTable.setDeviceCode(mDeviceInfo.getCode());
        mTable.setDeviceTypeCode(mDeviceInfo.getDeviceTypeId());
        mTable.setDeviceTypeName(mDeviceInfo.getDeviceTypeName());
        mTable.setDeviceAddress(mDeviceInfo.getAddr());
        mTable.setLocalTag(Constants.LocalState.UNSUBMITTED);
    }

    /**
     * 根据实体加载界面数据
     */
    protected void initUI(){
        //处理一些与实体无关的界面展示
        maintainDeviceTimeFormView.setContentText(IDateTimeUtils.getDateNow());
        //添加实体信息
        maintainDeviceDeviceCodeFormView.setContentText(mTable.getDeviceCode()).setNonEditable();
        maintainDeviceDeviceTypeFormView.setContentText(mTable.getDeviceTypeName()).setNonEditable();
        maintainDeviceAddressFormView.setContentText(mTable.getDeviceAddress()).setNonEditable();
        maintainDeviceNoteFormView.setContentText(mTable.getNote());
        //添加图片控件
        maintainDeviceImgPickerLayout.addView(mAccessoryView);
        //将图片展示在控件上
        if (!IStringUtils.isEmpty(mTable.getImagesUrl())) {
            mAccessoryView.setImgList(Arrays.asList(mTable.getImagesUrl().split(",")));
        }
        maintainDeviceMaintainDescSelector.setListener(this);
        //如果已提交,就把最下面的按钮隐藏
        if (mTable.getLocalTag() == Constants.LocalState.SUBMITTED){
            //设置图片不可添加
            mAccessoryView.initShowImage();
            //隐藏按钮
            mBottomLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFormSeletorClick() {
        menuSeletorView = new MenuMultiSelectView(getContext());
        menuSeletorView.setDataSource(maintainContentRes);
        if (!TextUtils.isEmpty(maintainDeviceMaintainDescSelector.getContentTextView().toString())) {
            String[] strs = maintainDeviceMaintainDescSelector.getContentTextView().toString().split(",");
            menuSeletorView.setSelectContent(Arrays.asList(strs));
        }
        DialogFactory.showMsgDialog(getContext(), IUtils.getStrToRes(getActivity(), R.string.maintain_content_title), menuSeletorView, IUtils.getStrToRes(getActivity(), R.string.dialog_sure), IUtils.getStrToRes(getActivity(), R.string.dialog_cancel), null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> select = menuSeletorView.getSelectContent();
                StringBuffer sb = new StringBuffer();
                if (select != null && select.size() > 0) {
                    for (String str : select)
                        sb.append(str).append(",");
                } else {
                    maintainDeviceMaintainDescSelector.setPropertyContentText("");
                }

                if (!TextUtils.isEmpty(sb.toString())) {
                    maintainDeviceMaintainDescSelector.setPropertyContentText(sb.substring(0, sb.lastIndexOf(",")));
                }

//                if (sb.toString().contains(IUtils.getStrToRes(getActivity(), R.string.pressure_value))) {
//                    mPressureValue.setVisibility(View.VISIBLE);
//                } else {
//                    mPressureValue.setVisibility(View.GONE);
//                }
            }
        });
    }

    @Override
    protected void saveAction() {
        mPresenter.save();
    }

    @Override
    protected void submitAction() {
        mPresenter.submit();
    }

    @Override
    public void showUploadingDialog() {
        mDialog = DialogFactory.createLoadingDialog(getActivity(), "正在上传..");
    }

    @Override
    public void dismissUploadingDialog() {
        if (mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    public void showUploadSuccessMsgAndFinishActivity() {
        showToast("上传成功");
        getActivity().finish();
    }

    @Override
    public void showUploadFailedMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void showSaveSuccessMsg() {
        showToast("保存成功");
    }

    @Override
    public void showSaveFailedMsg(String msg) {
        showToast(msg);
    }

    @Override
    public MaintainDeviceTable getMaintainData() {
        mTable.setLocalTag(Constants.LocalState.UNSUBMITTED);
        mTable.setNote(maintainDeviceNoteFormView.getContentText());
        mTable.setLng(ZNAPPlication.getInstance().getLng());
        mTable.setLat(ZNAPPlication.getInstance().getLat());
        mTable.setWorkAddress(ZNAPPlication.getInstance().getCurrentAddr());
        mTable.setUserName(ZNAPPlication.getUserInfoEntity(getActivity()).getName());
        mTable.setUserID(ZNAPPlication.getUserInfoEntity(getActivity()).getId());
        mTable.setSaveTime(System.currentTimeMillis());

        if (mAccessoryView.getAccContent().size() > 0) {
            mTable.setImagesUrl(IUtils.convertStringListToString(mAccessoryView.getAccContent()));
        } else {
            mTable.setImagesUrl("");
        }
        return mTable;
    }

    @Override
    public void setPresenter(MaintainDeviceContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
