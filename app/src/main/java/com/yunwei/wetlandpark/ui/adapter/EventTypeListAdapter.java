package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.EventTypeEntity;


/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.adapter
 * @Description:
 * @date 2016/8/19 15:26
 */
public class EventTypeListAdapter extends ArrayListAdapter<EventTypeEntity> {

    public EventTypeListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_event_list_layout, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.event_list_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.event_list_item_name);

        name.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getIcon()).error(R.mipmap.main_img_defaultpic_small).into(imageView);

        return convertView;
    }
}
