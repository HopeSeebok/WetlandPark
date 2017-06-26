package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.entity.UnitInfoEntity;
import com.yunwei.wetlandpark.ui.biz.IUnitListAction;
import com.yunwei.wetlandpark.ui.biz.impl.UnitListBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.UnitListListener;
import com.yunwei.wetlandpark.ui.view.UnitListView;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.presenter
 * @Description:
 * @date 2016/11/1 19:37
 */

public class UnitListPresenter implements UnitListListener {

    private UnitListView unitListView;
    private IUnitListAction unitListBiz;

    public UnitListPresenter(UnitListView unitListView) {
        this.unitListView = unitListView;
        this.unitListBiz = new UnitListBiz();
    }

    public void reqUnitListAction() {
        unitListBiz.reqUnitList(unitListView.getActivity(), this);
    }

    @Override
    public void reqUnitListStart() {
        unitListView.reqUnitListStart();
    }

    @Override
    public void reqUnitListEnd() {
        unitListView.reqUnitListEnd();
    }

    @Override
    public void reqUnitListSuccess(List<UnitInfoEntity.UnitEntity> entity) {
        unitListView.reqUnitListSuccess(entity);
    }

    @Override
    public void reqUnitListFailure(String error) {
        unitListView.reqUnitListFailure(error);
    }
}
