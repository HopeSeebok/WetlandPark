package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.RFIDScanEntity;


/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.adapter
 * @Description:RFID扫描结果列表适配器
 * @date 2016/10/9 14:08
 */
public class RFIDListAdapter extends ArrayListAdapter<RFIDScanEntity> {

    public RFIDListAdapter(Activity activity){
        super(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.rfid_item_layout,null);
        TextView textView=(TextView) convertView.findViewById(R.id.rfid_item_text);
        textView.setText("消火栓编号:"+mList.get(position).getCode());
        return convertView;
    }
}
