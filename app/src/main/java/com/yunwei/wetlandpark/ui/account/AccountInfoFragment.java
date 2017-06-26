package com.yunwei.wetlandpark.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.entity.UserInfoEntity;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.common.ShowImageActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.view.RoundedBitmapImageViewTarget;
import com.yunwei.wetlandpark.widget.FormShowView;

import java.util.ArrayList;

/**
 * @Package: com.yunwei.zaina.ui.activity.set.fragment
 * @Description:个人资料
 * @author: Aaron
 * @date: 2016-06-26
 * @Time: 11:22
 * @version: V1.0
 */
public class AccountInfoFragment extends BaseFragment {

    public static final String ACCOUNT_INFO_FLAG = "account_info";

    private FormShowView account, depar, company;
    private ImageView headIv;

    private UserInfoEntity infoEntity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_info_fragment, null);
        findViewById(view);
        initUI();
        return view;
    }

    private void findViewById(View view) {
        account = (FormShowView) view.findViewById(R.id.acc_name_text);
//        depar = (FormShowView) view.findViewById(R.id.acc_name_depart);
        company = (FormShowView) view.findViewById(R.id.acc_name_company);
        headIv=(ImageView)view.findViewById(R.id.acc_head_iv) ;

        headIv.setOnClickListener(this);
    }

    private void initUI() {
        account.setPropertyText("账号");
//        depar.setPropertyText("组别");
        company.setPropertyText("单位");
        infoEntity = ZNAPPlication.getInstance().getUserInfoEntity(getActivity());
        Glide.with(getActivity()).load(infoEntity.getIcon()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headIv));
        if (infoEntity != null) {
            account.setPropertyContentText(ISpfUtil.getValue(getActivity(), Constants.USER_NAME_KEY, "").toString());
            company.setPropertyContentText(infoEntity.getUnitName());
//            depar.setPropertyContentText(infoEntity.getGroup());
        }
    }

    @Override
    public void onMainUserEvent(NoticeEvent event) {
        super.onMainUserEvent(event);
        switch (event.getFlag()) {
            case EventConstant.NOTICE6:
                //拿到最新的用户信息
                infoEntity = ZNAPPlication.getInstance().getUserInfoEntity(getActivity());
                Glide.with(getActivity()).load(infoEntity.getIcon()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(headIv));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.acc_head_iv:
                ArrayList<String> list = new ArrayList<>();
                list.add(infoEntity.getIcon());
                Bundle bundle = new Bundle();
                bundle.putInt(ShowImageActivity.SHOW_IMG_LIST, 0);
                bundle.putSerializable(ShowImageActivity.SHOW_IMG_LIST, list);
                ISkipActivityUtil.startIntent(getActivity(), ShowImageActivity.class, bundle);
                break;
        }
    }
}
