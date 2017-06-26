package com.yunwei.library.http.LiteHttp;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;

import java.io.File;
import java.util.Objects;

/**
 * @Package: com.yunwei.library.http.LiteHttp
 * @Description: Http请求接口
 * @author: Aaron
 * @date: 2016-06-07
 * @Time: 16:42
 * @version: V1.0
 */
public interface DownloadRequestCallBack {
    public void onStart(AbstractRequest request);

    public void onSuccess(File file, Response response);

    public void onDownloadloading(AbstractRequest<File> request, long total, long len);

    public void onFailure(HttpException e, Response response);

    public void onEnd(Response response);

}
