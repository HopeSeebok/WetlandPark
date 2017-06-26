package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.yunwei.wetlandpark.ui.biz.interfac.SearchMapLayerListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz
 * @Description:
 * @date 2016/7/28 19:43
 */
public interface ISearchMapLayer {

    public void search(Activity activity,String url, IdentifyParameters parameters, SearchMapLayerListener layerListener);
}
