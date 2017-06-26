package com.yunwei.wetlandpark.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import android.os.Environment;

/**
 * 文件工具类
 *
 * @author hzwu on 2014-10-27
 */
public class IFileUtils {

    public static final String FILE_ROOT_DIRECTORY = "Yunwei/CMCC";// 文件根目录

    public static final String VOICE_DIRECTORY = "voice";// 音频文件夹

    public static final String IMAGE_DEIRECTORY = "image";// 图片文件夹

    public static final String IMAGE_CATCH_DIR = "catch";//图片缓存

    public static final String DOWNLOAD_DIR = "download";//下载目录

    public static final String CRASH_DIR = "crash";//异常目录

    public static final String LOG_DIR = "Log";//日志输入

    public static final String MAP_LAYER = "MapLayer";//地图图层

    public static final String IMG_DER = getSDROOT() + "/" + FILE_ROOT_DIRECTORY + "/" + IMAGE_DEIRECTORY;

    /**
     * 获取SD卡根目录
     *
     * @return
     */
    public static String getSDROOT() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 检测SD卡是否存在
     *
     * @return
     */
    public static boolean checkSDcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static void createDirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param path
     */
    public static void createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拍照照片路径
     *
     * @return
     */
    public static String getImgPath() {
        createDirs(IMG_DER);
        String path = IMG_DER + System.currentTimeMillis() + ".png";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    /**
     * 图片缓存地址
     *
     * @return
     */
    public static String getImageCatchDir() {
        String path = getSDROOT() + File.separator + FILE_ROOT_DIRECTORY + File.separator + IMAGE_CATCH_DIR + File.separator + UUID.randomUUID().toString() + ".png";
        File file = new File(path);
        if (!file.exists()) {
            File filePath = file.getParentFile();
            filePath.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 下载目录
     *
     * @return
     */
    public static String getDownloadDir() {
        return getSDROOT() + File.separator + FILE_ROOT_DIRECTORY + File.separator + DOWNLOAD_DIR;
    }

    /**
     * APK下载目录
     *
     * @return
     */
    public static String getAPKDownloadDir() {
        return getDownloadDir() + File.separator + "cmcc.apk";
    }

    /**
     * 异常目录
     *
     * @return
     */
    public static String getCrashDir() {
        return getSDROOT() + File.separator + FILE_ROOT_DIRECTORY + File.separator + CRASH_DIR + File.separator;
    }

    /**
     * 日志输入目录
     *
     * @return
     */
    public static String getLogDir() {
        return getSDROOT() + File.separator + FILE_ROOT_DIRECTORY + File.separator + LOG_DIR + File.separator;
    }

    /**
     * 地图缓存图层
     *
     * @return
     */
    public static String getMapLayerDir() {
        return getSDROOT() + File.separator + FILE_ROOT_DIRECTORY + File.separator + MAP_LAYER + File.separator;
    }

}
