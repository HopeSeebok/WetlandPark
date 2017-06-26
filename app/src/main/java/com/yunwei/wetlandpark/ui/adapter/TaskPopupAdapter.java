package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * @Package: com.yunwei.zaina.ui.adapter
 * @Description:
 * @author: Aaron
 * @date: 2016-06-13
 * @Time: 15:16
 * @version: V1.0
 */
public class TaskPopupAdapter extends ArrayListAdapter<String> {


    public TaskPopupAdapter(Activity context){
        super(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();

            convertView= LayoutInflater.from(mContext).inflate(R.layout.task_item_layout,null);
            holder.contentTv=(TextView)convertView.findViewById(R.id.task_content_tv);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.contentTv.setText(mList.get(position));
        return convertView;
    }

    private class ViewHolder{
        private TextView contentTv;
    }
}
