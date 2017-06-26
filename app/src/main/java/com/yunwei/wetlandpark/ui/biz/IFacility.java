package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.greedao.Facility;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.qiniu.UploadCallBackListener;

import java.util.List;

/**
 * Created by SlientWhale on 2016/9/1.
 * <p/>
 * 规范 FacilityBiz 设施的业务处理
 */
public interface IFacility {

    /**
     * 提交设施数据
     */
    void submitFacility(Facility facility, List<String> image);

    /**
     * 图片上传
     */
    void updatePhotos(List<String> images, UploadCallBackListener listener);

    /**
     * 新增设施
     */
    void updateAddFac(Activity activity, Facility addFacilityEntity, HttpRequestCallBack callBack);

    /**
     * 保存设施数据
     */
    void saveData(Facility facility);


}
