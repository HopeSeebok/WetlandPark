package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemClickListener;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemLongClickListener;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.adapter
 * @Description:
 * @date 2016/8/11 11:46
 */
public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder> {

    public final static int FROM_LOCATION = 11;
    public final static int FROM_SERVER = 12;

    private Context mContext;
    private List<Track> mList;
    private List<TrackEntity> lists;

    private int flag;

    private OnRecyclerViewItemClickListener itemClickListener;
    private OnRecyclerViewItemLongClickListener itemLongClickListener;

    public TrackListAdapter(Activity activity, int flag) {
        this.mContext = activity;
        this.mList = new ArrayList<>();
        this.lists = new ArrayList<>();
        this.flag = flag;
    }

    public void addData(List<Track> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addDataV2(List<TrackEntity> list) {
        lists = list;
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
        lists.clear();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemLongListener(OnRecyclerViewItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if (flag == FROM_LOCATION) {
            return mList.size();
        } else if (flag == FROM_SERVER) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (flag == FROM_LOCATION) {
            holder.startTime.setText("开始时间:" + IDateTimeUtils.formatDate(mList.get(position).getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            holder.endTime.setText("结束时间:" + IDateTimeUtils.formatDate(mList.get(position).getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            String d = new DecimalFormat("######0.00").format(mList.get(position).getDistance() / 1000);
            holder.distance.setText(d + "KM");
            if (mList.get(position).getRevint1() == 0) {
                holder.status.setVisibility(View.VISIBLE);
            } else {
                holder.status.setVisibility(View.GONE);
            }
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, mList.get(position), position);
                    }
                }
            });
            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemLongClickListener != null) {
                        itemLongClickListener.onLongItemClick(v, mList.get(position), position);
                    }
                    return true;
                }
            });
        } else if (flag == FROM_SERVER) {
            holder.startTime.setText("开始时间:" + lists.get(position).getStartTime());
            holder.endTime.setText("结束时间:" + lists.get(position).getEndTime());
            String d = new DecimalFormat("######0.00").format(lists.get(position).getFootDistance() / 1000);
            holder.distance.setText(d + "KM");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, lists.get(position), position);
                    }
                }
            });
            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemLongClickListener != null) {
                        itemLongClickListener.onLongItemClick(v, mList.get(position), position);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_track_list, null));
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView startTime, endTime, distance;
        private LinearLayout layout;
        private ImageView status;

        public ViewHolder(View view) {
            super(view);
            startTime = (TextView) view.findViewById(R.id.item_track_start_time);
            endTime = (TextView) view.findViewById(R.id.item_track_end_time);
            distance = (TextView) view.findViewById(R.id.item_track_distance);
            layout = (LinearLayout) view.findViewById(R.id.item_track_layout);
            status = (ImageView) view.findViewById(R.id.item_track_status);
        }
    }
}
