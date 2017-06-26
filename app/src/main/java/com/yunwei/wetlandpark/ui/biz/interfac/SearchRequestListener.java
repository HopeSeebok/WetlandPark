package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.SearchEntity;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/28
 * @changeby:
 */

public interface SearchRequestListener {
    void success(SearchEntity searchEntitie);
    void failure();
}
