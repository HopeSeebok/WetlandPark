package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.greedao.Facility;

import java.util.List;

/**
 * @author WuQianRui
 * @version V1.0
 * @Package com.yunwei.camera.ui.view
 * @Description
 * @Date 2016/10/9 .
 */
public interface FacilityView extends BaseSubmitView<Facility> {

    //获取界面上的设施实体数据
    public Facility getFacility();

    //获取界面上的附件
    public List<String> getAttaches();

    //关闭视图
    public void closeView();


}
