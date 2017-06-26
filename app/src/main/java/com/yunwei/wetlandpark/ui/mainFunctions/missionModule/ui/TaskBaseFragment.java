package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.BaseRecyclerViewAdapter;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemClickListener;
import com.yunwei.wetlandpark.ui.biz.interfac.OnRecyclerViewItemLongClickListener;
import com.yunwei.wetlandpark.utils.ILog;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history
 * @Description:历史记录BaseFragment
 * @date 2016/9/12 11:01
 */
public class TaskBaseFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnRecyclerViewItemClickListener,
        OnRecyclerViewItemLongClickListener{

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected TextView emptyTextView;
    private BaseRecyclerViewAdapter mAdapter;

    //分页
    protected int page = 0;//当前页数
    public final static int PAGE_COUNT = 10;//一次加载的数据个数
    private boolean isUpload = false;//标记是否在加载数据
    private int lastVisibleItem = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_layout, null);
        findViewById(view);
        initRecycler();
        return view;
    }

    private void findViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.his_recycler_view);
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.his_swipeRefresh);
        emptyTextView=(TextView)view.findViewById(R.id.his_recycler_empty_view);
        refreshLayout.setOnRefreshListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecycler() {
        //创建默认的线性LayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        //item动画
        recyclerView.setLayoutAnimation(new LayoutAnimationController(
                AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoomin_anim)));
        //设置布局方向
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount() && !isUpload && mAdapter.isShowFootFlag()) {
                    isUpload = true;
                    ILog.d("BaseHistoryFragment","加载……");
                    mAdapter.changeMoreStatus(BaseRecyclerViewAdapter.LOADING_MORE);
                    //加载内容
                    update();
                    mAdapter.changeMoreStatus(BaseRecyclerViewAdapter.PULLUP_LOAD_MORE);
                    isUpload = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });

    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    protected void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        if (adapter instanceof BaseRecyclerViewAdapter)
            this.mAdapter = (BaseRecyclerViewAdapter) adapter;
    }

    /**
     * 刷新
     */
    protected void refresh(){
        refreshLayout.post(() -> refreshLayout.setRefreshing(true));
        onRefresh();
    }

    protected void update(){

    }

    /**
     * 关闭刷新
     */
    protected void coloseRefresh(){
        refreshLayout.post(() -> refreshLayout.setRefreshing(false));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }



    @Override
    public void onLongItemClick(View view, final Object data, final int position) {
//        DialogFactory.showMsgDialog(getActivity(), "删除提示", "是否删除该条历史记录？", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                longItemClickEvent(data,position);
//            }
//        });
    }

    public void longItemClickEvent(Object data, int position) {

    }

    public void setRefreshLayoutInvisiable() {
        refreshLayout.setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }
}
