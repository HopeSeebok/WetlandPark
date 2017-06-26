package com.yunwei.wetlandpark.ui.mainFunctions.homeModule;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.RFIDScanEntity;
import com.yunwei.wetlandpark.ui.adapter.RFIDListAdapter;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.RFIDSearchDevicePresenter;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.ShowSearchedListContract;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source.SearchDeviceRemoteRepo;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.dialog.DialogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.activity.main
 * @Description:RFID扫描结果控件器
 * @date 2016/10/9 14:08
 */
public class RFIDScanControl implements ShowSearchedListContract.RFIDSearchView {

    private Activity activity;
    private Handler handler;
    private Dialog loadingDialog;
    private Dialog dialog;
    /**
     * 扫描结果集合
     */
    private List<String> rfids = new ArrayList<>();
    private List<RFIDScanEntity> scanEntities = new ArrayList<>();

    private RFIDSearchDevicePresenter searchDevicePresenter;

    private boolean isLoading = false;
    private boolean isOnCliclk = false;

    private String rfid;

    public RFIDScanControl(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;

        if (searchDevicePresenter == null) {
            searchDevicePresenter = new RFIDSearchDevicePresenter(SearchDeviceRemoteRepo.getInstance(), this);
        }
    }

    /**
     * 检测扫描结果
     */
    public void checkResult(String rfid) {
        ILog.d("BaseActivity", "rfid=====" + rfid);
        //读取得到的RFID记录在集合中，添加不重复的
        if (!TextUtils.isEmpty(rfid)) {
            if (!rfids.contains(rfid)) {
                rfids.add(rfid);
                this.rfid = rfid;
                searchDevicePresenter.searchByRFID();
            }
        }
    }


    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void showDeviceInfo(final DeviceInfo deviceInfo) {
        /**
         * 1秒内记录起来,否则直接跳转
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isOnCliclk && rfids != null && rfids.size() > 1) {
                    RFIDScanEntity entity1 = new RFIDScanEntity();
                    entity1.setCode(deviceInfo.getCode());
                    entity1.setRfid(deviceInfo.getRFID());
                    scanEntities.add(entity1);
                    creadRfidSeleteDialog(scanEntities);
                } else {
                    if (!isOnCliclk) {
                        rfids.clear();
                        scanEntities.clear();
                        isOnCliclk = false;
                    }
                }
            }
        }, 600);
    }

    @Override
    public void onRFIDNotRelated(String rfid) {
        showRFIDNotRelatedDialog(rfid);
    }

    @Override
    public void showRFIDSearchFailur(String error) {
        isLoading = false;
        DialogFactory.dimissDialog(loadingDialog);
    }

    @Override
    public void showLoadingDialog() {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            isLoading = true;
            loadingDialog = DialogFactory.createLoadingDialog(activity, IUtils.getStrToRes(activity, R.string.device_req));
        }
    }

    @Override
    public void dismissLoadingDialog() {
        isLoading = false;
        DialogFactory.dimissDialog(loadingDialog);
    }

    @Override
    public String getRFID() {
        return rfid;
    }

    /**
     * 扫描获取多个RFID`
     *
     * @param lists
     */
    private void creadRfidSeleteDialog(List<RFIDScanEntity> lists) {
        View rfidView = LayoutInflater.from(activity).inflate(R.layout.rfid_layout, null);
        ListView listView = (ListView) rfidView.findViewById(R.id.rfid_listview);
        final RFIDListAdapter adapter = new RFIDListAdapter(activity);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String rfid = adapter.getList().get(i).getRfid();
                if (isLoading) {
                    return;
                }
                isOnCliclk = true;
                RFIDScanControl.this.rfid = rfid;
                searchDevicePresenter.searchByRFID();
            }
        });
        adapter.clearList();
        adapter.appendToList(lists);
        if (dialog != null) {
            DialogFactory.dimissDialog(dialog);
            dialog = DialogFactory.createDialog(activity, IUtils.getStrToRes(activity, R.string.dialog_fire_list_title), rfidView, IUtils.getStrToRes(activity, R.string.dialog_close), null, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rfids.clear();
                    scanEntities.clear();
                    isOnCliclk = false;
//                    RFIDConfig.initReadLoop();
                }
            });
        } else {
            dialog = DialogFactory.createDialog(activity, IUtils.getStrToRes(activity, R.string.dialog_fire_list_title), rfidView, IUtils.getStrToRes(activity, R.string.dialog_close), null, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rfids.clear();
                    scanEntities.clear();
                    isOnCliclk = false;
//                    RFIDConfig.initReadLoop();
                }
            });
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH) {
//                    RFIDConfig.initReadLoop();
//                    DialogFactory.dimissDialog(dialog);
//                    isOnCliclk = false;
//                    rfids.clear();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示未关联RFID   Dialog
     *
     * @param rfid
     */
    private void showRFIDNotRelatedDialog(String rfid) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = DialogFactory.showMsgDialog(activity, IUtils.getStrToRes(activity, R.string.rfid_ass_title), IUtils.getStrToRes(activity, R.string.rfid_ass_content), IUtils.getStrToRes(activity, R.string.dialog_ass_btn), IUtils.getStrToRes(activity, R.string.dialog_cancel), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rfids.clear();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rfids.clear();
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    DialogFactory.dimissDialog(dialog);
                    rfids.clear();
                    return true;
                }
                return false;
            }
        });
    }
}
