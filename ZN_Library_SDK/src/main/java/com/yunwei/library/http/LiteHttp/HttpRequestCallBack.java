package com.yunwei.library.http.LiteHttp;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;

/**
 * @Package: com.yunwei.library.http.LiteHttp
 * @Description: Http请求接口
 * @author: Aaron
 * @date: 2016-06-07
 * @Time: 16:42
 * @version: V1.0
 */
public interface HttpRequestCallBack {
    public void onStart(AbstractRequest request);

    public void onSuccess(Object o, Response response);

    public void onFailure(HttpException e, Response response);

    public void onEnd(Response response);

}
