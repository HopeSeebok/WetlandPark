package com.yunwei.wetlandpark.ui.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;

/**
 * Created by longma on 2016/6/13.
 */
public class TaskHistoryListAdapter extends BaseRecyclerViewAdapter<TaskListAndDetailEntity> {

    private final Context mContext;
    private LayoutInflater mInflater;
    private View mView;

    public TaskHistoryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext=context;
    }

    @Override
    public ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        mView = mInflater.inflate(R.layout.task_history_list_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holders = (ViewHolder) holder;

        holders.item_Rl.setOnClickListener(v -> listener.onItemClick(v, mLists.get(position), position));
        holders.item_Rl.setOnLongClickListener(v -> {
            longListener.onLongItemClick(v,mLists.get(position), position);
            return true;
        });

        TaskListAndDetailEntity taskListAndDetailEntity = mLists.get(position);
//        holders.task_title_tv.setText(taskListAndDetailEntity.getTaskTypeString(taskListAndDetailEntity.getTaskType()));
//        holders.task_id_tv.setText("ID:"+taskEntity.getTaskId());
//        holders.task_ps_time_tv.setText(taskEntity.getOrderTime());
        holders.task_decription_tv.setText(taskListAndDetailEntity.getTaskNote());
        if (taskListAndDetailEntity.getTaskStatus()==4) {
            holders.end_status_iv.setBackgroundResource(R.mipmap.his_terminated);
        }else {
            holders.end_status_iv.setBackgroundResource(R.mipmap.his_complete);
        }
    }

    @Override
    public int getBaseItemCount() {
        return mLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView end_status_iv;
        public View rootView;
        public TextView task_decription_tv;
        public TextView task_ps_time_tv;
        public LinearLayout linearLayout;
        public TextView task_id_tv;
        public TextView task_title_tv;
//        public ImageView task_status_iv;
        public RelativeLayout item_Rl;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.task_decription_tv = (TextView) rootView.findViewById(R.id.task_decription_tv);
            this.task_ps_time_tv = (TextView) rootView.findViewById(R.id.task_ps_time_tv);
            this.linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
            this.task_id_tv = (TextView) rootView.findViewById(R.id.task_id_tv);
            this.task_title_tv = (TextView) rootView.findViewById(R.id.task_title_tv);
//            this.task_status_iv = (ImageView) rootView.findViewById(R.id.task_status_iv);
            this.end_status_iv = (ImageView) rootView.findViewById(R.id.end_status_iv);
            this.item_Rl = (RelativeLayout) rootView.findViewById(R.id.item_Rl);
        }

    }

}
