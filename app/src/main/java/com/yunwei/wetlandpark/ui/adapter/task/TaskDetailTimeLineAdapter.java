package com.yunwei.wetlandpark.ui.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longma on 2016/6/13.
 */
public class TaskDetailTimeLineAdapter extends RecyclerView.Adapter<TaskDetailTimeLineAdapter.ViewHolder> {

    private final Context mContext;
    private List<String> mTimeLineEntities = new ArrayList<>();
    private LayoutInflater mInflater;

    public TaskDetailTimeLineAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext=context;
    }

    public void clear(){
        mTimeLineEntities.clear();
        notifyDataSetChanged();
    }

    public void setTimeLineEntitys(List<String> timeLineEntities){
        mTimeLineEntities =timeLineEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.task_timeline_item_view, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (position) {
            case 0:
                holder.mTimeLeftLl.setVisibility(View.GONE);
                holder.mTimeIvRight.setImageResource(R.mipmap.time_pssj);
                holder.mTimeTvRight.setTextColor(mContext.getResources().getColor(R.color.time_line));
                holder.mTimeTvRight.setText("处理时间");
                holder.mTimeRight.setText(mTimeLineEntities.get(position));

                if (mTimeLineEntities.get(position+1).isEmpty()) {
                    holder.mLineDown.setBackgroundResource(R.color.time_line_wc);
                }else {
                    holder.mLineDown.setBackgroundResource(R.color.time_line);
                }
                break;
            case 1:
                holder.mTimeRightLl.setVisibility(View.GONE);
                holder.mTimeIvLeft.setImageResource(R.mipmap.time_jdsj);
                holder.mTimeTvLeft.setTextColor(mContext.getResources().getColor(R.color.red));
                holder.mTimeTvLeft.setText("审核时间");
                holder.mTimeLeft.setText(mTimeLineEntities.get(position));

                if (mTimeLineEntities.get(position).isEmpty()) {
                    holder.mLineUp.setBackgroundResource(R.color.time_line_wc);
                    holder.mCenterImage.setImageResource(R.mipmap.timeline_undo);
                }else {
                    holder.mLineUp.setBackgroundResource(R.color.time_line);
                    holder.mCenterImage.setImageResource(R.mipmap.timeline_done);
                }
                if (mTimeLineEntities.get(position+1).isEmpty()) {
                    holder.mLineDown.setBackgroundResource(R.color.time_line_wc);
                }else {
                    holder.mLineDown.setBackgroundResource(R.color.time_line);
                }
                break;
//            case 2:
//                holder.mTimeLeftLl.setVisibility(View.GONE);
//                holder.mTimeIvRight.setImageResource(R.mipmap.time_clsj);
//                holder.mTimeTvRight.setTextColor(mContext.getResources().getColor(R.color.time_line_cl));
//                holder.mTimeTvRight.setText("完成时间");
//                holder.mLineDown.setBackgroundResource(R.color.time_line_wc);
//                holder.mTimeRight.setText(mTimeLineEntities.get(position));
//
//                if (mTimeLineEntities.get(position).isEmpty()) {
//                    holder.mLineUp.setBackgroundResource(R.color.time_line_wc);
//                    holder.mCenterImage.setImageResource(R.mipmap.timeline_undo);
//                }else {
//                    holder.mLineUp.setBackgroundResource(R.color.time_line);
//                    holder.mCenterImage.setImageResource(R.mipmap.timeline_done);
//                }
//                if (mTimeLineEntities.get(position+1).isEmpty()) {
//                    holder.mLineDown.setBackgroundResource(R.color.time_line_wc);
//                }else {
//                    holder.mLineDown.setBackgroundResource(R.color.time_line);
//                }
//                break;
            case 2:
                holder.mTimeRightLl.setVisibility(View.GONE);
                holder.mTimeIvLeft.setImageResource(R.mipmap.time_wcsj);
                holder.mTimeTvLeft.setTextColor(mContext.getResources().getColor(R.color.time_line_wc));
                holder.mTimeTvLeft.setText("完成时间");
                holder.mCenterImage.setImageResource(R.mipmap.timeline_undo);
                holder.mTimeLeft.setText(mTimeLineEntities.get(position));
                if (mTimeLineEntities.get(position).isEmpty()) {
                    holder.mLineUp.setBackgroundResource(R.color.time_line_wc);
                    holder.mLineDown.setBackgroundResource(R.color.time_line_wc);
                    holder.mCenterImage.setImageResource(R.mipmap.timeline_undo);
                }else {
                    holder.mLineUp.setBackgroundResource(R.color.time_line);
                    holder.mLineDown.setBackgroundResource(R.color.time_line);
                    holder.mCenterImage.setImageResource(R.mipmap.timeline_done);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTimeLineEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mTimeIvLeft;
        private final TextView mTimeTvLeft;
        private final TextView mTimeLeft;
        private final ImageView mTimeIvRight;
        private final TextView mTimeTvRight;
        private final TextView mTimeRight;
        private final LinearLayout mTimeLeftLl;
        private final LinearLayout mTimeRightLl;
        private final View mLineDown;
        private final View mLineUp;
        private final ImageView mCenterImage;

        private ViewHolder(View view) {
            super(view);
            mLineUp = view.findViewById(R.id.line_normal_up);
            mLineDown = view.findViewById(R.id.line_normal_down);
            mTimeLeftLl = (LinearLayout) view.findViewById(R.id.time_left_ll);
            mTimeRightLl = (LinearLayout) view.findViewById(R.id.time_right_ll);
            mCenterImage = (ImageView) view.findViewById(R.id.center_image);
            mTimeIvLeft = (ImageView) view.findViewById(R.id.time_iv_left);
            mTimeTvLeft = (TextView) view.findViewById(R.id.time_tv_left);
            mTimeLeft = (TextView) view.findViewById(R.id.time_left);
            mTimeIvRight = (ImageView) view.findViewById(R.id.time_iv_right);
            mTimeTvRight = (TextView) view.findViewById(R.id.time_tv_right);
            mTimeRight = (TextView) view.findViewById(R.id.time_right);
        }
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        OnRecyclerViewItemClickListener mOnItemClickListener = listener;
    }


}
