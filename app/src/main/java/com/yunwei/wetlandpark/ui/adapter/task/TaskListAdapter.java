package com.yunwei.wetlandpark.ui.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;
import com.yunwei.wetlandpark.view.TimeTextView;

/**
 * @Description:任务列表适配器 Created by zls on 2016/6/13.
 */
public class TaskListAdapter extends BaseRecyclerViewAdapter<TaskListAndDetailEntity> {
    private final Context mContext;
    private LayoutInflater mInflater;

    public TaskListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void clear() {
        mLists.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置button
     *
     * @param holder                  略
     * @param TaskListAndDetailEntity 任务实体
     */
    private void setStatusBt(ItemViewHolder holder, TaskListAndDetailEntity TaskListAndDetailEntity) {
        //处理中、待审核、已完成、已终止
        switch (TaskListAndDetailEntity.getTaskStatus()) {
            //处理中
            case 1:
                holder.mProcessingBt.setVisibility(View.VISIBLE);
                holder.mAuditBt.setVisibility(View.GONE);
                break;
            //待审核
            case 2:
                holder.mProcessingBt.setVisibility(View.GONE);
                holder.mAuditBt.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.task_list_item, parent, false);
        return new ItemViewHolder(mView);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.mItemRl.setOnClickListener(v -> listener.onItemClick(v, mLists.get(position), position));

        final TaskListAndDetailEntity taskListAndDetailEntity = mLists.get(position);
        try {
//            itemViewHolder.mTaskTitleTv.setText(taskListAndDetailEntity.getTaskTypeString(taskListAndDetailEntity.getTaskType()));
//        itemViewHolder.mTiemCountDown.setTimes(Long.parseLong(IDateTimeUtils.getTimestamp(taskListAndDetailEntity.getEndTime(),
//                "yyyy-MM-dd-HH-mm-ss")));
            itemViewHolder.mTaskListPsTimeTv.setText(taskListAndDetailEntity.getProcessingTime());
            itemViewHolder.mTaskListEndTimeTv.setText(taskListAndDetailEntity.getEndTime());
            itemViewHolder.mTaskDecriptionTv.setText(taskListAndDetailEntity.getTaskNote());
            if (taskListAndDetailEntity.getTaskLevel() == Constants.TASK_LEVEL.NORMAL.getValue()) {
                itemViewHolder.mTaskStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_ordinary);
            } else if (taskListAndDetailEntity.getTaskLevel() == Constants.TASK_LEVEL.IMPORTANT.getValue()) {
                itemViewHolder.mTaskStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_important);
            } else {
                itemViewHolder.mTaskStatusIv.setImageResource(R.mipmap.task_list_zhuangtai_urgent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setStatusBt(itemViewHolder, taskListAndDetailEntity);
    }

    @Override
    public int getBaseItemCount() {
        return mLists.size();
    }

    //    单击事件
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TimeTextView mTiemCountDown;
        private final Button mAuditBt;
        View rootView;
        ImageView mEndStatusIv;
        TextView mTaskDecriptionTv;
        TextView mTaskListPsTimeTv;
        LinearLayout mTaskListPdTimeLl;
        TextView mTaskListEndTimeTv;
        LinearLayout mTaskListEndTimeLl;
        TextView mLineTv;
        Button mAcceptBt;
        Button mProcessingBt;
        Button mCurrentBt;
        Button mGivebackBt;
        LinearLayout mBtnLl;
        TextView mTaskIdTv;
        TextView mTaskTitleTv;
        ImageView mTaskStatusIv;
        RelativeLayout mItemRl;

        public ItemViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mEndStatusIv = (ImageView) rootView.findViewById(R.id.end_status_iv);
            this.mTaskDecriptionTv = (TextView) rootView.findViewById(R.id.task_decription_tv);
            this.mTaskListPsTimeTv = (TextView) rootView.findViewById(R.id.task_list_ps_time_tv);
            this.mTaskListPdTimeLl = (LinearLayout) rootView.findViewById(R.id.task_list_pd_time_ll);
            this.mTaskListEndTimeTv = (TextView) rootView.findViewById(R.id.task_list_end_time_tv);
            this.mTaskListEndTimeLl = (LinearLayout) rootView.findViewById(R.id.task_list_end_time_ll);
            this.mLineTv = (TextView) rootView.findViewById(R.id.line_tv);
            this.mAcceptBt = (Button) rootView.findViewById(R.id.accept_bt);
            this.mProcessingBt = (Button) rootView.findViewById(R.id.processing_bt);
            this.mCurrentBt = (Button) rootView.findViewById(R.id.current_bt);
            this.mGivebackBt = (Button) rootView.findViewById(R.id.giveback_bt);
            this.mAuditBt = (Button) rootView.findViewById(R.id.audit_bt);
            this.mBtnLl = (LinearLayout) rootView.findViewById(R.id.btn_Ll);
            this.mTaskIdTv = (TextView) rootView.findViewById(R.id.task_id_tv);
            this.mTaskTitleTv = (TextView) rootView.findViewById(R.id.task_title_tv);
            this.mTaskStatusIv = (ImageView) rootView.findViewById(R.id.task_status_iv);
            this.mItemRl = (RelativeLayout) rootView.findViewById(R.id.item_Rl);
            this.mTiemCountDown = (TimeTextView) rootView.findViewById(R.id.task_count_down);
        }
    }
}
