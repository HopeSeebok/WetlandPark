package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.UnitInfoEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.biz.interfac
 * @Description:
 * @date 2016/11/1 19:30
 */

public interface UnitListListener {

    void reqUnitListStart();

    void reqUnitListEnd();

    void reqUnitListSuccess(List<UnitInfoEntity.UnitEntity> entity);

    void reqUnitListFailure(String error);
}
