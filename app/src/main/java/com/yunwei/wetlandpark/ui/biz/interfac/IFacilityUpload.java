package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.greedao.Facility;

import java.util.List;

/**
 * @author WuQianRui
 * @version V1.0
 * @Package com.yunwei.camera.ui.biz.interfac
 * @Moudle 模块
 * @Description 更新设施位置
 * @Date 2016/10/14 .
 */
public interface IFacilityUpload {

        public void uploadFacility(Facility facility, List<String> imgs);

}
