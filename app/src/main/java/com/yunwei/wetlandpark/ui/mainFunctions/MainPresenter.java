package com.yunwei.wetlandpark.ui.mainFunctions;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.mainFunctions.data.UnitInfoEntity;
import com.yunwei.wetlandpark.ui.mainFunctions.data.source.MainDataSource;
import com.yunwei.wetlandpark.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions
 * @Description:
 * @date 2016/11/16 17:03
 */

public class MainPresenter implements MainContract.presenter, MainDataSource.RequestQiNiuTokenCallBack, MainDataSource.RequestUnitListCallBack {
    private final String TAG = getClass().getSimpleName();

    private MainDataSource mRepo;
    private MainContract.View mView;

    public MainPresenter(MainDataSource dataSource, MainContract.View view) {
        this.mRepo = dataSource;
        this.mView = view;
    }

    @Override
    public void reqQiNiuToken() {
        mRepo.reqQiNiuToken(mView.getActivity(), this);
    }

    @Override
    public void reqUniteList() {
        mRepo.reqUnitList(mView.getActivity(), this);
    }

    @Override
    public void getQiNiuTokenSuccess(String token) {
        ISpfUtil.setValue(mView.getActivity(), Constants.QINIU_TOKEN_KEY, token);
    }

    @Override
    public void getUnitListSuccess(UnitInfoEntity entity) {
        mView.requestUniteListSuccess(entity);
    }

    @Override
    public void getUnitListFailure() {

    }
}
