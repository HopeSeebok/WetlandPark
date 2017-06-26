package com.yunwei.wetlandpark.widget;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.MultiSelectorAdapter;
import com.yunwei.wetlandpark.utils.IUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @package com.yunwei.library.view
 * @description 多选View
 * @author yangdu
 * @date 2016/10/18
 * @time 下午3:11
 * @version
 * @use MultiSelectorView mMultiSelectorView = new MultiSelectorView(getActivity(), MultiSelectorView.MULTI_SELECT_MODE);
 *     mMultiSelectorView.setInitialItems(new String[]{"测试1","测试2","测试3","测试3"});//设置初始化值
 *    mMultiSelectorView.setSelectedItems(new String[]{"测试1"});//设置默认选择项
 **/
public class MultiSelectorView extends LinearLayout implements MultiSelectorAdapter.OnRecyclerViewItemClickListener{

    private Activity mContext;
    private RecyclerView mRecyclerView;
    private MultiSelectorAdapter mMultiSelectorAdapter;
    private TextView mEmptyDataTv;

    private List<String> mSelectedItems;//已选择项目
    private List<String> mInitialItems;//初始化选项

    public static final int SINGLE_SELECT_MODE=0x001;
    public static final int MULTI_SELECT_MODE=0x002;

    private int mSelectMode=SINGLE_SELECT_MODE;//默认单选

    private String mSplitChar=";";//多选项之间的分隔符

    /**
     * @param context
     * @param selectMode 选择模式
     */
    public MultiSelectorView(Activity context,int selectMode) {
        super(context);
        this.mContext = context;
        this.mSelectMode = selectMode;
        initView(context);
    }

    private void updateData(){
        mMultiSelectorAdapter.setSelectedItems(mSelectedItems);
        mMultiSelectorAdapter.setInitialItems(mInitialItems);
        mRecyclerView.setAdapter(mMultiSelectorAdapter);
        mMultiSelectorAdapter.notifyDataSetChanged();
        if (mMultiSelectorAdapter.getItemCount() <= 0) {
            mEmptyDataTv.setVisibility(View.VISIBLE);
        } else {
            mEmptyDataTv.setVisibility(View.GONE);
        }
    }

     /**
     * 设置列表默认已选择的项
     * @param resId 资源ID
     */
    public void setSelectedItems(int resId) {
        String [] items=mContext.getResources().getStringArray(resId);
        if (items != null&&items.length>0) {
            if (mSelectedItems==null){
                mSelectedItems = new ArrayList<>();
            }
            for (String item : items) {
                mSelectedItems.add(item);
            }
        }
        updateData();
    }

    /**
     * 设置列表默认已选择的项
     * @param items
     */
    public void setSelectedItems(String ... items) {
        if (items != null&&items.length>0) {
            if (items != null&&items.length>0) {
                if (mSelectedItems==null){
                    mSelectedItems = new ArrayList<>();
                }
                for (String item : items) {
                    mSelectedItems.add(item);
                }
            }
        }
        updateData();
    }

    /**
     * 设置列表默认已选择的项
     * @param mSelectedItems
     */
    public void setSelectedItems(List<String> mSelectedItems) {
        this.mSelectedItems = mSelectedItems;
        updateData();
    }

    /**
     * 设置初始化列表
     * @param items
     */
    public void setInitialItems(String ... items) {
        if (items != null&&items.length>0) {
            if (items != null&&items.length>0) {
                if (mInitialItems==null){
                    mInitialItems = new ArrayList<>();
                }
                for (String item : items) {
                    mInitialItems.add(item);
                }
            }
        }
        updateData();
    }

    /**
     * 设置初始化列表
     * @param resId 资源Id
     */
    public void setInitialItems(int resId) {
        String [] items=mContext.getResources().getStringArray(resId);
        if (items != null&&items.length>0) {
            if (mInitialItems==null){
                mInitialItems = new ArrayList<>();
            }
            for (String item : items) {
                mInitialItems.add(item);
            }
        }
        updateData();
    }

    /**
     * 设置初始化列表
     * @param mInitialItems
     */
    public void setInitialItems(List<String> mInitialItems) {
        this.mInitialItems = mInitialItems;
        updateData();
    }

    /**
     * 设置多选择项之间的分隔符
     * @param splitChar
     */
    public void setSplitChar(String splitChar){
        if (!TextUtils.isEmpty(splitChar)) {
            this.mSplitChar = splitChar;
        } else {
            this.mSplitChar = ";";
        }
    }

    public MultiSelectorView(Activity context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        initView(context);
    }

    public MultiSelectorView(Activity context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initView(context);
    }

    private void initView(Activity context){
        View layout = context.getLayoutInflater().inflate(R.layout.view_selector_multi, null);
        mEmptyDataTv = (TextView) layout.findViewById(R.id.multi_selector_empty_tv);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.multi_selector_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mInitialItems = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
        mMultiSelectorAdapter = new MultiSelectorAdapter(mContext);
        mMultiSelectorAdapter.setSelectedItems(mSelectedItems);
        mMultiSelectorAdapter.setInitialItems(mInitialItems);
        mMultiSelectorAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mMultiSelectorAdapter);
        addView(layout);//记得调用该方法
    }

    @Override
    public void onItemClick(View view, String data,int position) {
        if (mInitialItems != null&&view!=null&& view instanceof CheckedTextView) {
            CheckedTextView ctv=(CheckedTextView)view;
            boolean isChecked=!ctv.isChecked();
            ctv.setChecked(isChecked);
            if (mSelectedItems==null) {
                mSelectedItems=new ArrayList<String>();
            }
            if (isChecked) {
                if (mSelectMode==SINGLE_SELECT_MODE) {//单选模式
                    mSelectedItems.clear();
                }else if (mSelectMode == MULTI_SELECT_MODE){//多选模式
                    if (mSelectedItems.contains(data)==false) {
                        mSelectedItems.add(data);
                    }
                }
            }else{
                mSelectedItems.remove(data);
            }
        }
        mMultiSelectorAdapter.notifyItemChanged(position);
    }

    /**
     * 提供给外部获取选择项的方法
     * @return
     */
    public List<String> getSelectedItems() {
        return mSelectedItems;
    }

    /**
     * 提供给外部获取选择项的方法
     * @return 
     */
    public String getSelectedItemToStr(){
        return IUtils.list2String(mSelectedItems, mSplitChar);
    }

}
