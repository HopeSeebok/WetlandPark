package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.UnitInfoEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.view
 * @Description:
 * @date 2016/11/1 19:26
 */

public interface UnitListView extends BaseView{

    void reqUnitListStart();

    void reqUnitListEnd();

    void reqUnitListSuccess(List<UnitInfoEntity.UnitEntity> entity);

    void reqUnitListFailure(String error);
}
