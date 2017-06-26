package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.MapSearchEntity;

import java.util.ArrayList;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/30
 * @changeby:
 */

public interface HomeView extends BaseView {
    void search();
    void goToActivity(ArrayList<MapSearchEntity> dataEntities);
}
