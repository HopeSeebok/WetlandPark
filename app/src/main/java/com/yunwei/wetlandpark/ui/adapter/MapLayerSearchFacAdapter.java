package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.MapSearchEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.adapter
 * @Description:
 * @date 2016/7/29 8:58
 */
public class MapLayerSearchFacAdapter extends ArrayListAdapter<MapSearchEntity> {

    public MapLayerSearchFacAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_map_layer_search_fac, null);

            holder.facCode=(TextView)convertView.findViewById(R.id.search_map_layer_fac_code);
            holder.facType=(TextView)convertView.findViewById(R.id.search_map_layer_fac_type);
            holder.facCreateTime=(TextView)convertView.findViewById(R.id.search_map_layer_fac_create_time);
            holder.type_tv=(TextView)convertView.findViewById(R.id.search_map_layer_fac_create_time);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.facCode.setText(mList.get(position).getCode());
        holder.facType.setText(mList.get(position).getFacType());
        holder.facCreateTime.setText(mList.get(position).getCreateTime());

        return convertView;
    }

    private class ViewHolder{
        private TextView facCode,facType,facCreateTime,type_tv;
    }
}
