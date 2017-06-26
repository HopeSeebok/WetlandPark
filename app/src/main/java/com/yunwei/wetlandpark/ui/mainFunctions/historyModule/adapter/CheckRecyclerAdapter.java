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
import com.yunwei.wetlandpark.greedao.WorkRecordTable;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SEEBOK on 2016/9/14.
 */
public class CheckRecyclerAdapter extends BaseRecyclerViewAdapter<WorkRecordTable> {


    private Context mContext;
    private WorkRecordTable mTable;

    public CheckRecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getBaseItemCount() {
        return mLists.size();
    }

    @Override
    public ItemViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_work_history_recycler_layout, null));
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder holders = (ItemViewHolder)holder;

        mTable = mLists.get(position);

        holders.itemWorkHistoryLayoutFirstTitleTextView.setText("设施编号:" + mTable.getDeviceCode());
        holders.itemWorkHistoryLayoutSecondTitleTextView.setText("设施类型:" + mTable.getDeviceType());
        holders.itemWorkHistoryLayoutThirdTitleTextView.setText("巡查时间:" + IDateTimeUtils.formatDate(mTable.getSaveTime()));

        //根据设施状态展示图标的样子
        if (( mTable).getLocalTag() == Constants.LocalState.UNSUBMITTED) {
            holders.itemWorkHistoryLayoutStateImageView.setImageResource(R.mipmap.icon_state_unsubmitted);
        } else {
            holders.itemWorkHistoryLayoutStateImageView.setImageResource(R.mipmap.icon_state_submitted);
        }

        holders.itemWorkHistoryLayoutItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mLists.get(position), position);
            }
        });
        holders.itemWorkHistoryLayoutItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longListener.onLongItemClick(v,mLists.get(position),position);
                return true;
            }
        });
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemWorkHistoryLayout_logo_imageView)
        ImageView itemWorkHistoryLayoutLogoImageView;
        @BindView(R.id.itemWorkHistoryLayout_firstTitle_textView)
        TextView itemWorkHistoryLayoutFirstTitleTextView;
        @BindView(R.id.itemWorkHistoryLayout_secondTitle_textView)
        TextView itemWorkHistoryLayoutSecondTitleTextView;
        @BindView(R.id.itemWorkHistoryLayout_thirdTitle_textView)
        TextView itemWorkHistoryLayoutThirdTitleTextView;
        @BindView(R.id.itemWorkHistoryLayout_forthTitle_textView)
        TextView itemWorkHistoryLayoutForthTitleTextView;
        @BindView(R.id.itemWorkHistoryLayout_turnLeft_imageView)
        ImageView itemWorkHistoryLayoutTurnLeftImageView;
        @BindView(R.id.itemWorkHistoryLayout_state_imageView)
        ImageView itemWorkHistoryLayoutStateImageView;
        @BindView(R.id.itemWorkHistoryLayout_item_layout)
        RelativeLayout itemWorkHistoryLayoutItemLayout;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
