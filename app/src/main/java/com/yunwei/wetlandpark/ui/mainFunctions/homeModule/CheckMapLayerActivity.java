package com.yunwei.wetlandpark.ui.mainFunctions.homeModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.DownloadRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.library.utils.IStringUtils;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.activity.main
 * @Description:地图更新类
 * @date 2016/11/1 10:57
 */

public class CheckMapLayerActivity extends BaseActivity implements DownloadRequestCallBack {

    public final static String DOWNLOAD_PATH_KEY = "download_path";
    public final static String SAVE_PATH_KEY = "save_path";
    public final static String SPF_SAVE_KEY = "save_key";
    public final static String MAP_LAYER_VERSION = "map_version";

    @BindView(R.id.CheckMapLayerActivity_progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.CheckMapLayerActivity_progress_tv)
    TextView mProgressTv;

    private String key;
    private int version;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_check_maplayer);
        ButterKnife.bind(this);
        setSwipeEnabled(false);
        setToolbarVisibility(View.GONE);

        String downloadPath = getIntent().getStringExtra(DOWNLOAD_PATH_KEY);
        key = getIntent().getStringExtra(SPF_SAVE_KEY);
        version = getIntent().getIntExtra(MAP_LAYER_VERSION, 0);
        String savePath = getIntent().getStringExtra(SAVE_PATH_KEY);
        if (TextUtils.isEmpty(downloadPath)) {
            showToast("图层更新失败!");
            this.finish();
        } else {
            LiteHttpManage.downloadRequest(this, downloadPath, savePath, this);
        }
    }

    @Override
    public void onSuccess(File file, Response response) {
        ISpfUtil.setValue(this, key, version);
        showToast("图层更新成功!");
        this.finish();
    }

    @Override
    public void onStart(AbstractRequest request) {

    }

    @Override
    public void onDownloadloading(AbstractRequest<File> request, long total, long len) {
        int a = IStringUtils.toInt(new DecimalFormat("######0").format(len * 100 / total));
        mProgressBar.setProgress(a);
        mProgressTv.setText(a + "%");
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        showToast("更新失败!");
        this.finish();
    }

    @Override
    public void onEnd(Response response) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
