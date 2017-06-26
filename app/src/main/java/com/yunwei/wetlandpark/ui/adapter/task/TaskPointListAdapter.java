package com.yunwei.wetlandpark.ui.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.greedao.TaskPointListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.adapter.task
 * @Description:任务点适配器
 * @date 2016/9/27
 * @changeby:
 */

public class TaskPointListAdapter extends RecyclerView.Adapter<TaskPointListAdapter.ViewHolder> {

    private final Context mContext;
    private List<TaskPointListEntity> mWorkTaskPoints = new ArrayList<>();
    private LayoutInflater mInflater;
    private View mView;
    private int mPosition;

    public TaskPointListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void addItem(TaskPointListEntity item) {
        mWorkTaskPoints.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mWorkTaskPoints.clear();
        notifyDataSetChanged();
    }

    public void setTaskEntitys(List<TaskPointListEntity> taskPointEntities) {
        mWorkTaskPoints = taskPointEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mInflater.inflate(R.layout.task_point_list_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TaskPointListEntity taskPointListEntity = mWorkTaskPoints.get(position);
        holder.mItemFl.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, (String) v.getTag(), position));

//        holder.mTaskpointDeviceType.setText(taskPointListEntity.getTaskPointKindString(taskPointListEntity.getTaskPointKind()));
//        if (taskPointListEntity.getHdtype() == null) {
//            holder.mTaskHiddenType.setVisibility(View.GONE);
//            holder.mTvLable.setVisibility(View.GONE);
//            holder.mHiddentIv.setVisibility(View.GONE);
//        } else {
//            holder.mTaskHiddenType.setVisibility(View.VISIBLE);
//            holder.mTaskHiddenType.setText(taskPointListEntity.getHdtype());
//        }
        holder.mTaskpointId.setText(taskPointListEntity.getTaskPointId());
//        if (taskPointListEntity.getCompleted()) {
//            holder.mIsComplete.setBackgroundResource(R.mipmap.complete);
//        } else {
//            holder.mIsComplete.setBackgroundResource(R.mipmap.no_complete);
//        }
        holder.mTaskAddr.setText(taskPointListEntity.getTaskPointAddr());
//
//        holder.mLocationLl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle =new Bundle();
//                MapLocationEntity mapLocationEntity = new MapLocationEntity(mLocations.get(position).getLng(),mLocations.get(position).getLat());
//                bundle.putInt(MapLocationActivity.FLAG,MapLocationActivity.MODE_VIEW);
//                bundle.putSerializable(MapLocationActivity.FLAG_DATA,mapLocationEntity);
//                ISkipActivityUtil.startIntent(mContext, MapLocationActivity.class,bundle);
//            }
//        });

//        holder.mGivebackBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFactory.showMsgDialog(mContext, mContext.getString(R.string.dialog_tips),mContext.getString(R.string.task_list_back_tips) ,
//                        mContext.getString(R.string.dialog_sure), mContext.getString(R.string.dialog_cancel_), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                new TaskPointListBiz((Activity) mContext).revokeTaskPoint(taskPointListEntity.getTaskId(),
//                                        taskPointListEntity.getTaskPointId(), new RevokeTaskPointLinstener() {
//                                            @Override
//                                            public void revokeSuccess() {
//                                                setTaskEntitys(new TaskPointListBiz((Activity) mContext).getTaskPoint());
//                                                notifyDataSetChanged();
//                                            }
//                                            @Override
//                                            public void revokeFailure() {
//                                            }
//                                        });
//                            }
//                        }, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                            }
//                        });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mWorkTaskPoints.size();
    }


    //    单击事件
    private TaskListAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(TaskListAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvLable;
        public final ImageView mHiddentIv;
        public final ImageView mIsComplete;
        private final LinearLayout mLocationLl;
        private final Button mGivebackBt;
        private final TextView mTaskAddr;
        public FrameLayout mItemFl;
        public View rootView;
        public TextView mTaskpointDeviceType;
        public TextView mTaskHiddenType;
        public TextView mTaskpointId;

        public ViewHolder(View rootView) {
            super(rootView);
            mItemFl = (FrameLayout) rootView.findViewById(R.id.item_Fl);
            this.rootView = rootView;
            this.mTaskpointDeviceType = (TextView) rootView.findViewById(R.id.taskpoint_device_type);
            this.mTaskHiddenType = (TextView) rootView.findViewById(R.id.task_hiddenType);
            this.mTaskpointId = (TextView) rootView.findViewById(R.id.taskpoint_id);
            this.mTvLable = (TextView) rootView.findViewById(R.id.hiddent_tv_label);
            this.mTaskAddr = (TextView) rootView.findViewById(R.id.task_addr);
            this.mLocationLl = (LinearLayout) rootView.findViewById(R.id.location_ll);
            this.mHiddentIv = (ImageView) rootView.findViewById(R.id.hiddent_iv);
            this.mIsComplete = (ImageView) rootView.findViewById(R.id.mIsComplete);
            this.mGivebackBt = (Button) rootView.findViewById(R.id.taskpointlist_giveback_bt);
        }

    }
}
