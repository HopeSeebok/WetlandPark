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
import com.yunwei.wetlandpark.greedao.SignInTable;
import com.yunwei.wetlandpark.greedao.TroubleTable;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;
import com.yunwei.wetlandpark.utils.IDateTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/12/15 11:07
 * @description:
 */

public class SignInHistoryAdapter extends BaseRecyclerViewAdapter<SignInTable> {

    private Context mContext;
    private SignInTable mTable;

    public SignInHistoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getBaseItemCount() {
        return mLists.size();
    }

    @Override
    public SignInHistoryAdapter.ItemViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        SignInHistoryAdapter.ItemViewHolder viewHolder = new SignInHistoryAdapter.ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_work_history_recycler_layout, null));
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SignInHistoryAdapter.ItemViewHolder holders = (SignInHistoryAdapter.ItemViewHolder) holder;

        mTable = mLists.get(position);

        holders.itemWorkHistoryLayoutFirstTitleTextView.setText("签到编号:" + mTable.getCode());
        holders.itemWorkHistoryLayoutSecondTitleTextView.setVisibility(View.GONE);
        holders.itemWorkHistoryLayoutThirdTitleTextView.setText("签到时间:" + IDateTimeUtils.formatDate(mTable.getTime()));

        holders.itemWorkHistoryLayoutTurnLeftImageView.setVisibility(View.INVISIBLE);
        holders.itemWorkHistoryLayoutStateImageView.setVisibility(View.INVISIBLE);

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