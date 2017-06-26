package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data.DeviceTypesEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.FillBaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.FormConfigPresenter;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.widget.FormClickableView;
import com.yunwei.wetlandpark.widget.FormSpinnerSelectorView;
import com.yunwei.wetlandpark.widget.FormTimeSelectorView;
import com.yunwei.wetlandpark.widget.FormWriteView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/11/3 17:42
 * @description:
 */

public class AddDeviceFragment extends FillBaseFragment {


    @BindView(R.id.addDeviceFragment_deviceName_wv)
    FormWriteView addDeviceFragmentDeviceNameWv;
    @BindView(R.id.addDeviceFragment_deviceCode_wv)
    FormWriteView addDeviceFragmentDeviceCodeWv;
    @BindView(R.id.addDeviceFragment_deviceType_ssv)
    FormSpinnerSelectorView addDeviceFragmentDeviceTypeSsv;
    @BindView(R.id.addDeviceFragment_deviceStartTime_tsv)
    FormTimeSelectorView addDeviceFragmentDeviceStartTimeTsv;
    @BindView(R.id.addDeviceFragment_deviceStopTime_tsv)
    FormTimeSelectorView addDeviceFragmentDeviceStopTimeTsv;
    @BindView(R.id.addDeviceFragment_deviceAddress_cv)
    FormClickableView addDeviceFragmentDeviceAddressCv;
    @BindView(R.id.addDeviceFragment_useDepartment_wv)
    FormWriteView addDeviceFragmentUseDepartmentWv;
    @BindView(R.id.addDeviceFragment_specModel_wv)
    FormWriteView addDeviceFragmentSpecModelWv;
    @BindView(R.id.addDeviceFragment_imgPicker_layout)
    FrameLayout addDeviceFragmentImgPickerLayout;

    @Override
    protected View getContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_add_device, null);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI(){
        Gson gson = new Gson();
        String value = (String) ISpfUtil.getValue(getContext(), FormConfigPresenter.DEVICE_TYPES_KEY,"");
        List<DeviceTypesEntity.Item> itemList = gson.fromJson(value,
                new TypeToken<List<DeviceTypesEntity.Item>>() {}.getType());
        List<String> types = new ArrayList<>();
        if (itemList!=null){
            for (DeviceTypesEntity.Item item : itemList) {
                types.add(item.getName());
            }
            addDeviceFragmentDeviceTypeSsv.setDataSource(getContext(),types);
        }
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected void submitAction() {

    }

}
