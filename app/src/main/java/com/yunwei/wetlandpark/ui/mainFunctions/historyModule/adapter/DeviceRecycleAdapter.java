package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history.adapter
 * @Description:设施历史记录适配器
 * @date 2016/9/12 14:42
 */
public class DeviceRecycleAdapter extends BaseRecyclerViewAdapter<Facility> {

    private Context mContext;

    public DeviceRecycleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getBaseItemCount() {
        return mLists.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_lv_home_history, null));
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder holders = (ItemViewHolder)holder;

        holders.title.setText("设施类型:" + mLists.get(position).getType());
        holders.createTime.setText("采集时间:" + mLists.get(position).getCreateTime());
        holders.content.setText("采集地址:" + mLists.get(position).getAddress());
        //根据设施状态展示图标的样子
        if (mLists.get(position).getState() == Constants.StandardState.SaveLocal) {
            holders.submitState.setImageResource(R.mipmap.icon_state_unsubmitted);
        } else {
            holders.submitState.setImageResource(R.mipmap.icon_state_submitted);
        }
        holders.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mLists.get(position), position);
            }
        });
        holders.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longListener.onLongItemClick(v,mLists.get(position),position);
                return true;
            }
        });
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title, createTime, content;
        private ImageView submitState;
        private RelativeLayout layout;

        public ItemViewHolder(View convertView) {
            super(convertView);
            title = (TextView) convertView.findViewById(R.id.tv_item_home_history_title);
            content = (TextView) convertView.findViewById(R.id.tv_item_home_history_content);
            createTime = (TextView) convertView.findViewById(R.id.tv_item_home_history_createtime);
            submitState = (ImageView) convertView.findViewById(R.id.iv_item_home_history_state);
            layout = (RelativeLayout) convertView.findViewById(R.id.item_fac_layout);
        }
    }

}
