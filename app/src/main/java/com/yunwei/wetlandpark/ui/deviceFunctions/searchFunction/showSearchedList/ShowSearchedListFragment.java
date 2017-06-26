package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.showDeviceInfo.ShowDeviceActivity;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.MapEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.showTroubleInfo.ShowTroubleActivity;
import com.yunwei.wetlandpark.utils.DividerItemDecoration;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.dialog.DialogFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author CBOK
 * @date 2016/11/10 9:13
 * @description:
 */
public class ShowSearchedListFragment extends BaseFragment implements ShowSearchedListContract.View {

    @BindView(R.id.recyclerViewFragment_recyclerView)
    RecyclerView recyclerViewFragmentRecyclerView;

    private List<MapEntity> mDataList;
    private int mClickedPosition;
    private ShowSearchedListContract.Presenter mPresenter;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList = (List<MapEntity>) getArguments().getSerializable(Constants.KEY_BUNDLE_DEVICE_INFO);
        IUtils.checkNotNull(mDataList);
    }


    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);
        recyclerViewFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFragmentRecyclerView.setHasFixedSize(true);
        recyclerViewFragmentRecyclerView.setScrollBarStyle(android.view.View.SCROLLBARS_OUTSIDE_OVERLAY);
        recyclerViewFragmentRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewFragmentRecyclerView.setAdapter(new RecyclerViewAdapter(getContext(),mDataList).
                setOnItemClickListener(new RecyclerViewAdapter.RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mClickedPosition = position;
                mPresenter.searchByID();
            }
        }));
        return view;
    }

    @Override
    public void showDeviceInfo(DeviceInfo deviceInfo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_BUNDLE_DEVICE_INFO,deviceInfo);
        ISkipActivityUtil.startIntent(getContext(), ShowDeviceActivity.class, bundle);
    }

    @Override
    public void showTroubleInfo(TroubleInfo troubleInfo) {
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable(Constants.KEY_BUNDLE_TROUBLE_INFO,troubleInfo);
        ISkipActivityUtil.startIntent(getContext(), ShowTroubleActivity.class,bundle1);
    }

    @Override
    public void showIDSearchFailedMsg() {
        ToastUtil.showToast(getContext(),"查询失败");
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog = DialogFactory.createLoadingDialog(getActivity(), "查询设施..");
    }

    @Override
    public void dismissLoadingDialog() {
        mLoadingDialog.dismiss();
    }

    @Override
    public String getID() {
        return mDataList.get(mClickedPosition).getId();
    }

    @Override
    public int getLayerID() {
        return mDataList.get(mClickedPosition).getLayerID();
    }

    @Override
    public void setPresenter(ShowSearchedListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static class RecyclerViewAdapter extends RecyclerView.Adapter {
        private final List<MapEntity> mDataList;
        private Context mContext;
        private RecyclerViewItemClickListener mRecyclerViewItemClickListener;

        RecyclerViewAdapter(Context context, List<MapEntity> dataList) {
            this.mContext = context;
            mDataList = dataList;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_searched_devices, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.searchedDevicesItemWholeLayout.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    mRecyclerViewItemClickListener.onItemClick(itemViewHolder.getAdapterPosition());
                }
            });
            switch (mDataList.get(position).getLayerID()) {
                case 0:
                    itemViewHolder.searchedDevicesItemTitleTv.setText("隐患");
                    itemViewHolder.searchedDevicesItemDeviceCodeTv.setText("紧急程度:\t"+Constants.TROUBLE_EMERGENCY_LEVEL.get(mDataList.get(position).getEmergencyLevel()));
                    itemViewHolder.searchedDevicesItemDeviceTypeTv.setText("当前状态:\t"+Constants.TROUBLE_STATUS.get(mDataList.get(position).getTroubleStatus()));
                    itemViewHolder.searchedDevicesItemCreateTimeTv.setVisibility(View.GONE);
                    break;
                case 1:
                    itemViewHolder.searchedDevicesItemTitleTv.setText("签到");
                    itemViewHolder.searchedDevicesItemDeviceCodeTv.setText("签到点编号:\t"+mDataList.get(position).getCode());
                    itemViewHolder.searchedDevicesItemDeviceTypeTv.setVisibility(View.GONE);
                    itemViewHolder.searchedDevicesItemCreateTimeTv.setVisibility(View.GONE);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.searchedDevicesItem_wholeLayout)
            RelativeLayout searchedDevicesItemWholeLayout;
            @BindView(R.id.searchedDevicesItem_title_tv)
            TextView searchedDevicesItemTitleTv;
            @BindView(R.id.searchedDevicesItem_deviceCode_tv)
            TextView searchedDevicesItemDeviceCodeTv;
            @BindView(R.id.searchedDevicesItem_deviceType_tv)
            TextView searchedDevicesItemDeviceTypeTv;
            @BindView(R.id.searchedDevicesItem_createTime_tv)
            TextView searchedDevicesItemCreateTimeTv;

            ItemViewHolder(android.view.View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public RecyclerViewAdapter setOnItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
            mRecyclerViewItemClickListener = recyclerViewItemClickListener;
            return this;
        }

        interface RecyclerViewItemClickListener {
            void onItemClick(int position);
        }
    }

}
