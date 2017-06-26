package com.yunwei.library.http.LiteHttp;

import android.app.Activity;
import android.content.Context;

import com.litesuits.http.HttpConfig;
import com.litesuits.http.LiteHttp;
import com.litesuits.http.data.NameValuePair;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.FileRequest;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.request.content.HttpBody;
import com.litesuits.http.request.param.HttpMethods;
import com.litesuits.http.response.Response;
import com.yunwei.library.utils.LLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.yunwei.library.http.LiteHttp
 * @Description:LiteHttp请求管理类
 * @author: Aaron
 * @date: 2016-06-07
 * @Time: 16:39
 * @version: V1.0
 */
public class LiteHttpManage {
    /**
     * 异步Get请求
     *
     * @param activity
     * @param url
     * @param listener
     */
    public static void Http_Get_Sync(Activity activity, String url, HttpRequestCallBack listener) {
        Http_Get_Sync(activity, "", url, listener);
    }


    /**
     * 异步Get请求
     *
     * @param activity
     * @param token
     * @param url
     * @param listener
     */
    public static void Http_Get_Sync(Activity activity, String token, String url, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
        liteHttp.executeAsync(new StringRequest(url).setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        }));
    }

    public static void Http_Get_Sync(Context context, String token, String url, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(context, token));
        liteHttp.executeAsync(new StringRequest(url).setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        }));
    }

    /**
     * 获取LiteHttp
     *
     * @param activity
     * @return
     */
    public static LiteHttp getListeHttp(Activity activity) {
        return getListeHttp(activity, "");
    }

    /**
     * 获取LiteHttp
     *
     * @param activity
     * @param token
     * @return
     */
    public static LiteHttp getListeHttp(Activity activity, String token) {
        return LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
    }

    /**
     * 异步Post请求
     *
     * @param activity
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Post_Sync(Activity activity, String url, HttpBody body, final HttpRequestCallBack listener) {
        Http_Post_Sync(activity, "", url, body, listener);
    }

    /**
     * 异步Post请求
     *
     * @param context
     * @param token
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Post_Sync(Context context, String token, String url, HttpBody body, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(context, token));
        StringRequest request = new StringRequest(url);
        request.setCacheExpireMillis(0);
        request.setMethod(HttpMethods.Post);
        request.setHttpBody(body);
        request.setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        });
        liteHttp.executeAsync(request);
    }

    /**
     * 异步Post请求
     *
     * @param context
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Post_Sync(Context context, String url, HttpBody body, final HttpRequestCallBack listener) {
        Http_Post_Sync(context, "", url, body, listener);
    }

    /**
     * 异步Post请求
     *
     * @param activity
     * @param token
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Post_Sync(Activity activity, String token, String url, HttpBody body, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
        StringRequest request = new StringRequest(url);
        request.setCacheExpireMillis(0);
        request.setMethod(HttpMethods.Post);
        request.setHttpBody(body);
        request.setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        });
        liteHttp.executeAsync(request);
    }


    /**
     * 文件上传
     *
     * @param activity
     * @param url
     * @param body
     * @param listener
     */
    public static void uploadRequest(Activity activity, String url, HttpBody body, final UploadRequestCallBack listener) {
        uploadRequest(activity, "", url, body, listener);
    }

    /**
     * 文件上传
     *
     * @param activity
     * @param token
     * @param url
     * @param body
     * @param listener
     */
    public static void uploadRequest(Activity activity, String token, String url, HttpBody body, final UploadRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
        StringRequest request = new StringRequest(url);
        request.setCacheExpireMillis(0);
        request.setMethod(HttpMethods.Post);
        request.setHttpBody(body);
        request.setHttpListener(new HttpListener<String>(true, true, false) {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
                if (listener!=null) {
                    listener.onUploading(request, total, len);
                }
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        });
        liteHttp.executeAsync(request);
    }

    /**
     * Http Put 请求
     *
     * @param context
     * @param token
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Put_Sync(Context context, String token, String url, HttpBody body, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(context, token));
        StringRequest request = new StringRequest(url);
        request.setCacheExpireMillis(0);
        request.setMethod(HttpMethods.Put);
        request.setHttpBody(body);
        request.setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        });
        liteHttp.executeAsync(request);
    }

    /**
     * Http Put 请求
     *
     * @param activity
     * @param token
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Put_Sync(Activity activity, String token, String url, HttpBody body, final HttpRequestCallBack listener) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
        StringRequest request = new StringRequest(url);
        request.setCacheExpireMillis(0);
        request.setMethod(HttpMethods.Put);
        request.setHttpBody(body);
        request.setHttpListener(new HttpListener<String>() {
            @Override
            public void onEnd(Response<String> response) {
                super.onEnd(response);
                if (listener!=null) {
                    listener.onEnd(response);
                }
            }

            @Override
            public long getDelayMillis() {
                return super.getDelayMillis();
            }

            @Override
            public HttpListener<String> setDelayMillis(long delayMillis) {
                return super.setDelayMillis(delayMillis);
            }

            @Override
            public boolean disableListener() {
                return super.disableListener();
            }

            @Override
            public void onStart(AbstractRequest<String> request) {
                super.onStart(request);
                if (listener!=null) {
                    listener.onStart(request);
                }
            }

            @Override
            public void onSuccess(String s, Response<String> response) {
                super.onSuccess(s, response);
                if (listener!=null) {
                    listener.onSuccess(s, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                super.onFailure(e, response);
                if (listener!=null) {
                    listener.onFailure(e, response);
                }
            }

            @Override
            public void onCancel(String s, Response<String> response) {
                super.onCancel(s, response);
            }

            @Override
            public void onLoading(AbstractRequest<String> request, long total, long len) {
                super.onLoading(request, total, len);
            }

            @Override
            public void onUploading(AbstractRequest<String> request, long total, long len) {
                super.onUploading(request, total, len);
            }

            @Override
            public void onRetry(AbstractRequest<String> request, int max, int times) {
                super.onRetry(request, max, times);
            }

            @Override
            public void onRedirect(AbstractRequest<String> request, int max, int times) {
                super.onRedirect(request, max, times);
            }
        });
        liteHttp.executeAsync(request);
    }

    /**
     * Http Put请求
     *
     * @param activity
     * @param url
     * @param body
     * @param listener
     */
    public static void Http_Put_Sync(Activity activity, String url, HttpBody body, final HttpRequestCallBack listener) {
        Http_Put_Sync(activity, "", url, body, listener);
    }

    /**
     * 下载请求
     *
     * @param activity
     * @param token
     * @param url
     * @param saveFilePath
     * @param downloadRequestCallBack
     */
    public static void downloadRequest(Context activity, final String token, String url, String saveFilePath, final DownloadRequestCallBack downloadRequestCallBack) {
        LiteHttp liteHttp = LiteHttp.newApacheHttpClient(initHttpConfig(activity, token));
        liteHttp.executeAsync(new FileRequest(url, saveFilePath).setHttpListener(new HttpListener<File>(true, true, false) {
            @Override
            public void onStart(AbstractRequest<File> request) {
                super.onStart(request);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onStart(request);
                }
            }

            @Override
            public void onSuccess(File file, Response<File> response) {
                super.onSuccess(file, response);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onSuccess(file, response);
                }
            }

            @Override
            public void onFailure(HttpException e, Response<File> response) {
                super.onFailure(e, response);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onFailure(e, response);
                }
            }

            @Override
            public void onUploading(AbstractRequest<File> request, long total, long len) {
                super.onUploading(request, total, len);
                LLog.i("LiteHttpmanage", "onUploading total==" + total + ", len==" + len);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onDownloadloading(request, total, len);
                }
            }

            @Override
            public void onLoading(AbstractRequest<File> request, long total, long len) {
                super.onLoading(request, total, len);
                LLog.i("LiteHttpmanage", "onLoading total==" + total + ", len==" + len);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onDownloadloading(request, total, len);
                }
            }

            @Override
            public void onEnd(Response<File> response) {
                super.onEnd(response);
                if (downloadRequestCallBack!=null) {
                    downloadRequestCallBack.onEnd(response);
                }
            }
        }));
    }

    /**
     * 下载请求
     *
     * @param activity
     * @param url
     * @param saveFilePaht
     * @param downloadRequestCallBack
     */
    public static void downloadRequest(Activity activity, String url, String saveFilePaht, final DownloadRequestCallBack downloadRequestCallBack) {
        downloadRequest(activity, "", url, saveFilePaht, downloadRequestCallBack);
    }


    /**
     * 初始化HttpConfig
     *
     * @param context
     * @return
     */
    private static HttpConfig initHttpConfig(Context context, String token) {
        //添加Http请求头验证
        List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new NameValuePair("Authorization", "bearer " + token));

        HttpConfig httpConfig = new HttpConfig(context);
        httpConfig.setDebugged(false);                 // log output when debugged
        httpConfig.setDetectNetwork(true);             // detect network before connect
        httpConfig.setDoStatistics(true);            // statistics of time and traffic
        httpConfig.setTimeOut(10000, 10000);
        httpConfig.setDefaultCacheExpireMillis(0);//设置缓存
        httpConfig.setCommonHeaders(headers);

        return httpConfig;
    }
}
