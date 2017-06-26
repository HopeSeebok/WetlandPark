package com.yunwei.library.qiniu;

import android.text.TextUtils;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.yunwei.library.data.Image;
import com.yunwei.library.utils.LLog;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 七牛图片上封装类
 * Created by yangdu on 16/6/8.
 */
public class QiNiuImageUploadManager {
    private static final String TAG = "QiNiuImageUploadManager";

    private static int index = 0;
    private static int i = 0;


    /**
     * 多张图片上传处理
     *
     * @param tocken
     * @param images
     */
    public static void uploadImage(final String tocken, final List<String> images, final UploadCallBackListener listener) {
        if (images == null || images.size() == 0) {
            return;
        }
        if (listener != null) {
            listener.onUploadStart();
        }
        final List<Image> mList = new ArrayList<>();
        final UploadManager uploadManager = new UploadManager(QiNiuConfig.getConfig());
        final UploadOptions options = new UploadOptions(null, null, true, new UpProgressHandler() {
            @Override
            public void progress(String s, double v) {
                if (listener != null) {
                    listener.onProgess(v);
                }
                LLog.d(TAG, "v====" + v);
            }
        }, null);
        try {
            new Thread() {
                @Override
                public void run() {
                    for (i = 0; i < images.size(); i++) {
//                        byte[] data = BitmapUtils.bitmap2Bytes(BitmapUtils.getBitmapByPath(images.get(i)));
                        byte[] data = null;
                        try {
                            data = toByteArray(images.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                            LLog.e(TAG, "文件不存在");
                        }
                        uploadManager.put(data, QiNiuConfig.getFileName()+"", tocken, new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject response) {
                                if (info.statusCode == 200) {
                                    try {
                                        String fileName = response.getString("key");
                                        if (!TextUtils.isEmpty(fileName)) {
                                            String path = QiNiuConfig.getFileDomain() + fileName;
                                            /*为了添加Key 将回调接口的参数做了扩充（List<String> -> List<Image>）*/
                                            Image image = new Image();
                                            image.setUrl(path);
                                            image.setKey(key);

                                            mList.add(image);
                                        }
                                        if (index == images.size() - 1 && listener != null) {
                                            index = 0;
                                            listener.onUploadComplete(mList);
                                        } else {
                                            index++;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        listener.onUploadEnd();
                                    }
                                } else {
                                    listener.onUploadFailure();
                                }
                            }
                        }, options);
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onUploadEnd();
            }
        }
    }


    /**
     * the traditional io way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}
