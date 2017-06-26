package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.yunwei.wetlandpark.R;

import java.util.List;

/**
 * @author yangdu
 * @package com.yunwei.camera.ui.adapter
 * @description 多项选择器适配器
 * @date 2016/10/18
 * @time 下午4:05
 **/
public class MultiSelectorAdapter extends RecyclerView.Adapter<MultiSelectorAdapter.ViewHolder> implements View.OnClickListener{

    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener mOnItemClickListener=null;
    private int selectPosition=0;

    private List<String> mSelectedItems;//已选择项目
    private List<String> mInitialItems;//初始化选项


    public MultiSelectorAdapter(Activity context) {
        this.mInflater = LayoutInflater.from(context);
    }

    /**设置列表默认已选择的项*/
    public void setSelectedItems(List<String> mSelectedItems) {
        this.mSelectedItems = mSelectedItems;
    }

    /**设置初始化列表*/
    public void setInitialItems(List<String> mInitialItems) {
        this.mInitialItems = mInitialItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_multi_selector, parent, false);
//        YoYo.with(Techniques.DropOut)
//                .duration(500)
//                .interpolate(new AccelerateDecelerateInterpolator())
//                .withListener(new MyAnimator())
//                .playOn(view);
        return new MultiSelectorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTv.setText(mInitialItems.get(position));
        holder.itemTv.setTag(position);
        holder.itemTv.setOnClickListener(this);
        if (mSelectedItems != null) {
            boolean isChecked=false;
            for (String str : mSelectedItems) {
                if (TextUtils.equals(holder.itemTv.getText().toString(), str)) {
                    isChecked=true;
                    break;
                }
            }
            holder.itemTv.setChecked(isChecked);
        }
    }


    @Override
    public int getItemCount() {
        return mInitialItems!=null?mInitialItems.size():0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            CheckedTextView ctv = (CheckedTextView) v;
            mOnItemClickListener.onItemClick(v,ctv.getText().toString(), v.getTag() != null ? (Integer) v.getTag() :0);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data,int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CheckedTextView itemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTv = (CheckedTextView) itemView.findViewById(R.id.title_checktv);
        }
    }
}
