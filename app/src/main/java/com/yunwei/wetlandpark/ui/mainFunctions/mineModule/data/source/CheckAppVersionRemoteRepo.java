package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.api.ResponseModel;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.AppVersionEntity;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.DownloadRequestCallBack;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import java.io.File;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.data.source
 * @Description:App版本检测
 * @date 2016/11/17 10:34
 */

public class CheckAppVersionRemoteRepo implements CheckAppVersionDataSource {

    private static CheckAppVersionRemoteRepo checkAppVersionRepo;

    public static CheckAppVersionRemoteRepo newInstance(){
        if (checkAppVersionRepo==null){
            checkAppVersionRepo=new CheckAppVersionRemoteRepo();
        }
        return checkAppVersionRepo;
    }

    @Override
    public void checkAppVersion(CheckAppVersionCallBack callBack) {
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        String CHECK_VERSION_URL = BuildConfig.DOMAI+BuildConfig.CHECK_VERSION_URL;
        LiteHttpManage.Http_Get_Sync(ZNAPPlication.getInstance(), TOKEN, CHECK_VERSION_URL, new HttpRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(Object o, Response response) {
                try {
                    if (response.getHttpStatus().getCode() == 200) {
                        Gson gson = new Gson();
                        ResponseModel<AppVersionEntity> responseModel = gson.fromJson(o.toString(),
                                new TypeToken<ResponseModel<AppVersionEntity>>() {
                                }.getType());
                        if (responseModel.isSuccess()) {
                            callBack.onCheckAppSuccess(responseModel.getData());
                        } else {
                            callBack.onCheckAppDataNotAvailable();
                        }
                    } else {
                        callBack.onCheckAppDataNotAvailable();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onCheckAppDataNotAvailable();
                }
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                callBack.onCheckAppDataNotAvailable();
            }

            @Override
            public void onEnd(Response response) {

            }
        });

    }

    @Override
    public void downloadApk(DownloadCallBack downloadCallBack) {
        String TOKEN = ISpfUtil.getValue(Constants.ACCESS_TOKEN_KEY, "").toString();
        String DOWNLOAD_URL = downloadCallBack.getDownloadURL();
        String SAVE_FILE_PATH = IFileUtils.getAPKDownloadDir();
        LiteHttpManage.downloadRequest(ZNAPPlication.getInstance(), TOKEN, DOWNLOAD_URL, SAVE_FILE_PATH, new DownloadRequestCallBack() {
            @Override
            public void onStart(AbstractRequest request) {

            }

            @Override
            public void onSuccess(File file, Response response) {
                downloadCallBack.onDownloadComplete(file);
            }

            @Override
            public void onDownloadloading(AbstractRequest<File> request, long total, long len) {
                downloadCallBack.onDownloadProgress((int) (len* 100 /total ));
            }

            @Override
            public void onFailure(HttpException e, Response response) {
                downloadCallBack.onDownloadDataNotAvailable();
            }

            @Override
            public void onEnd(Response response) {

            }
        });
    }
}
