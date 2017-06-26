package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.view.SwitchButton;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.activity.mine.fragment
 * @Description:足迹采集设置
 * @date 2016/8/8 17:49
 */
public class TrackSetFragment extends BaseFragment {

    public static final String SET_TRACK_VALUE = "set_track";

    private TextView trackModeText;
    private SwitchButton traceSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_track_layout, null);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        trackModeText = (TextView) view.findViewById(R.id.track_mode_text);
        traceSwitch = (SwitchButton) view.findViewById(R.id.trace_switch);
        view.findViewById(R.id.track_mode_layout).setOnClickListener(this);


        int mode = IStringUtils.toInt(ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_MODE_KEY, Constants.TRACK_RECORD_MODE.DRIVE.getValue()).toString());
        if (mode == Constants.TRACK_RECORD_MODE.WALK.getValue()) {
            trackModeText.setText("步行");
        } else if (mode == Constants.TRACK_RECORD_MODE.RIDING.getValue()) {
            trackModeText.setText("骑行");
        } else if (mode == Constants.TRACK_RECORD_MODE.DRIVE.getValue()) {
            trackModeText.setText("驾驶");
        }

        boolean sw = (Boolean) ISpfUtil.getValue(getActivity(), Constants.TRACK_RECORD_SWITCH_KEY, false);
        if (sw) {
            traceSwitch.setChecked(true);
        } else {
            traceSwitch.setChecked(false);
        }

        traceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_SWITCH_KEY, isChecked);
                if (isChecked) {
                    NoticeEvent event = new NoticeEvent();
                    event.setFlag(EventConstant.NOTICE4);
                    EventBus.getDefault().post(event);
                } else {
                    NoticeEvent event = new NoticeEvent();
                    event.setFlag(EventConstant.NOTICE5);
                    EventBus.getDefault().post(event);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.track_mode_layout:
                showTrackModeDialog();
                break;
            case R.id.mode_walk:
                trackModeText.setText("步行");
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_MODE_KEY, Constants.TRACK_RECORD_MODE.WALK.getValue());
                DialogFactory.dimissDialog(dialog);
                break;
            case R.id.mode_riding:
                trackModeText.setText("骑行");
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_MODE_KEY, Constants.TRACK_RECORD_MODE.RIDING.getValue());
                DialogFactory.dimissDialog(dialog);
                break;
            case R.id.mode_drive:
                trackModeText.setText("驾驶");
                ISpfUtil.setValue(getActivity(), Constants.TRACK_RECORD_MODE_KEY, Constants.TRACK_RECORD_MODE.DRIVE.getValue());
                DialogFactory.dimissDialog(dialog);
                break;
        }
        super.onClick(v);
    }

    /**
     * 足迹采集方式选择Dialog
     */
    private void showTrackModeDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.track_mode_layout, null);
        view.findViewById(R.id.mode_walk).setOnClickListener(this);
        view.findViewById(R.id.mode_riding).setOnClickListener(this);
        view.findViewById(R.id.mode_drive).setOnClickListener(this);
        dialog = DialogFactory.showMsgDialog(getActivity(), "选择记录方式", view, "取消", null);
    }
}
