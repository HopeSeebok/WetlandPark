package com.yunwei.wetlandpark.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * Created by SlientWhale on 2016/8/30.
 *
 * 说明添加设施的GridView
 */
public class AddFacilityAdapter extends BaseAdapter {

    Context con=null;
    String[] itemName=new String[]{
            "易涝点",
            "截污点",
            "泵站"
    };
    int[] itemPic=new int[]{
            R.mipmap.icon_facility_waterlogged,
            R.mipmap.icon_facility_interpoint,
            R.mipmap.icon_facility_pumstation,
    };//图片的

    public AddFacilityAdapter(Context con) {
        this.con=con;
    }

    @Override
    public int getCount() {
        return itemName.length;
    }

    @Override
    public String getItem(int position) {
        return itemName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent){
        ViewHolder holder;
        if(convert==null){
            convert= LayoutInflater.from(con).inflate(R.layout.item_gridview_addfacility,null);
            holder=new ViewHolder();
            holder.tv=(TextView)convert.findViewById(R.id.tv_item_grid_addfacility_name);
            holder.iv=(ImageView)convert.findViewById(R.id.iv_item_grid_addfacility_img);
            convert.setTag( holder );
        }else{
            holder=(ViewHolder)convert.getTag();
        }
        holder.tv.setText(itemName[position]);
        holder.iv.setImageDrawable(con.getResources().getDrawable(itemPic[position]));
        return convert;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
    }
}
