package com.yunwei.wetlandpark.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.BaseHistoryFragment;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemClickListener;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history.adapter
 * @Description:RecyclerView.Adapter基类
 * @date 2016/10/09 17:04
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static String TAG = "BaseRecyclerViewAdapter";

    /**
     * item 类型
     */
    public final static int TYPE_FOOTER = 0;//底部--往往是pullup_load_more
    public final static int TYPE_LIST = 1;//代表展示内容部分

    public final static int PULLUP_LOAD_MORE = 2;
    public final static int LOADING_MORE = 3;

    protected List<T> mLists;

    //标记是否展示了底部
    private boolean showFootFlag = true;

    private int currentStatus = 2;

    protected OnRecyclerViewItemClickListener listener;
    protected OnRecyclerViewItemLongClickListener longListener;

    public BaseRecyclerViewAdapter(){
        mLists = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        int footerPosition = getItemCount() - 1;
        if (footerPosition == position && showFootFlag) {
            return TYPE_FOOTER;
        }else{
            return TYPE_LIST;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_base_recycler_view_adapter_foot, parent, false));
        } else {
            return onCreateBaseViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type != TYPE_FOOTER) {
            onBindBaseViewHolder(holder, position);
        }else{
            switch (currentStatus){
                case PULLUP_LOAD_MORE:
                    ((FooterViewHolder)holder).tv.setText(R.string.recycler_view_item_foot_pullup);
                    ((FooterViewHolder)holder).progressBar.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    ((FooterViewHolder)holder).progressBar.setVisibility(View.VISIBLE);
                    ((FooterViewHolder)holder).tv.setText(R.string.recycler_view_item_foot_loading);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = getBaseItemCount();
        if(showFootFlag) ++count;
        return count;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.base_recycler_view_adapter_foot_progress_bar);
            tv = (TextView) itemView.findViewById(R.id.base_recycler_view_adapter_foot_tv);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract int getBaseItemCount();



    public void setLists(List<T> list) {
        mLists = list;
        if (list.size() < BaseHistoryFragment.PAGE_COUNT) {//如果个数不满足设置的个数,说明数据加载到头,取消底部View的显示
            showFootFlag = false;
        }else {
            //数据大于设置的时候,使底部可见
            showFootFlag = true;
        }
        notifyDataSetChanged();
    }

    public void addItems(List<T> list){
        mLists.addAll(list);
        if (list.size() < BaseHistoryFragment.PAGE_COUNT) {//如果个数不满足设置的个数,说明数据加载到头,取消底部View的显示
            showFootFlag = false;
        }else {
            //数据大于设置的时候,使底部可见
            showFootFlag = true;
        }
        notifyDataSetChanged();
    }

    public void addItem(T t){
        mLists.add(t);
        notifyDataSetChanged();
    }

    public void clearList(){
        mLists.clear();
        notifyDataSetChanged();
    }

    public boolean isShowFootFlag() {
        return showFootFlag;
    }

    public void setShowFootFlag(boolean showFootFlag) {
        this.showFootFlag = showFootFlag;
        notifyDataSetChanged();
    }

    public void changeMoreStatus(int status){
        currentStatus = status;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener longListener) {
        this.longListener = longListener;
    }
}
