package com.yunwei.wetlandpark.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.esri.core.geometry.Point;
import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.ConfigEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.TrackPointEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.utils
 * @Description:
 * @date 2016/8/1 11:27
 */
public class IUtils {

    /**
     * 获取版本名
     *
     * @return 当前应用的版本名
     */
    public static String getVersionName(Activity activity) {
        String versionName = "";
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Activity activity) {
        int versionCode = 0;
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            PackageManager manager = ZNAPPlication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(ZNAPPlication.getInstance().getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     * 安装
     *
     * @param context
     */
    public static void installAPK(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(url)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取字符串资源
     *
     * @param context
     * @param res
     * @return
     */
    public static String getStrToRes(Context context, int res) {
        return context.getResources().getString(res);
    }

    /**
     * 获取字符串资源
     *
     * @param activity
     * @param res
     * @return
     */
    public static String getStrToRes(Activity activity, int res) {
        return activity.getResources().getString(res);
    }

    /**
     * 位置更新不同方式的记录时间
     *
     * @param mode
     * @return
     */
    public static int calculateRefreshTime(int mode) {
        int time = 1000;
        if (mode == Constants.TRACK_RECORD_MODE.WALK.getValue()) {
            time = Constants.THIRTY_SENCONDS;
        } else if (mode == Constants.TRACK_RECORD_MODE.RIDING.getValue()) {
            time = Constants.TWENTY_SECONDS;
        } else if (mode == Constants.TRACK_RECORD_MODE.DRIVE.getValue()) {
            time = Constants.TEN_SECONDES;
        }
        return time;
    }

    /**
     * 转换 List<String> --> List<Images>
     * @param stringList
     * @Creater chenhaobo
     * @return
     */
    public static List<Image> convertStringListToImgList(List<String> stringList){
        List<Image> imgList = new ArrayList<>();
        if(stringList!=null&&stringList.size()>0){
            for (int i = 0; i < stringList.size(); i++) {
                Image img = new Image();
                img.setUrl(stringList.get(i));
                imgList.add(img);
            }
        }
        return imgList;
    }

    public static ArrayList<String> convertImgListToStringList(List<Image> imageList) {
        ArrayList<String> stringList = new ArrayList<>();
        if (imageList != null && imageList.size() > 0) {
            for (Image image : imageList) {
                stringList.add(image.getUrl());
            }
        }
        return stringList;
    }

    /**
     * 转换 List<String> --> String
     * @param stringList
     * @Creater chenhaobo
     * @return
     */
    public static String convertStringListToString(List<String> stringList){
        String imageString = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (stringList != null && stringList.size() > 0) {
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i)).append(",");
            }
        }
        if (!TextUtils.isEmpty(stringBuilder.toString())) {
            imageString = stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(","));
        }
        return imageString;
    }

    /**
     *
     * @funcation String--> List<String>
     * @Creater QianRuiWu
     * @return
     */
    public static List<String> convertStringToList(String string){
        List<String> strings = new ArrayList<>();
        if(!TextUtils.isEmpty(string)){
            strings=Arrays.asList(string.split(","));
        }
        return  strings;
    }

    /**
     * String --> List<String>
     * @param text
     * @param splitChar 分隔符
     * @author du yang
     * @return
     */
    public static List<String> string2List(String text,String splitChar){
        List<String> list=null;
        if (TextUtils.isEmpty(text)==false) {
            String[] strArray=text.split(splitChar);
            if (strArray!=null) {
                list=new ArrayList<String>();
                for (String str : strArray) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    /**
     * List<String> -->  String
     * @param list
     * @param splitChar 分隔符
     * @author du yang
     * @return
     */
    public static String list2String(List<String> list,String splitChar){
        StringBuilder sb=new StringBuilder();
        if (list!=null&&list.size()>0) {
            for (String str : list) {
                sb.append(str+splitChar);
            }
            if (sb.lastIndexOf(splitChar)>0) {
                sb.deleteCharAt(sb.lastIndexOf(splitChar));
            }
        }
        return sb.toString();
    }

    /**
     *   将配置信息转化为map类型
     *   @Creater： wuqianrui
     */
    public static Map<String,String> configInfo2Map(List<ConfigEntity.ConfigValue> conList){
        Map<String,String> map=new LinkedHashMap<>();
        if(conList!=null&&conList.size()!=0){
            for(int a=0;a<conList.size();a++){
                map.put( conList.get(a).getName(),conList.get(a).getValue());
            }
        }
        return  map;
    }

    /**
     * 获取配置文件中的组成员信息(人员名称，人员ID) <Name，Id>
     * @author du yang
     * @param ctx
     * @return
     */
    public static Map<String,String> getSpfCrewInfo(Context ctx){
        Map<String,String> crewMap=null;
        String configValue = (String) ISpfUtil.getValue(ctx, ConfigEntity.FLAG, "");
        if (!TextUtils.isEmpty(configValue)) {
            ConfigEntity configEntity = ParseJson.toObject(configValue, ConfigEntity.class);
            List<ConfigEntity.ConfigValue> crewList=configEntity!=null?configEntity.getCrewList():null;
            crewMap=crewList!=null?IUtils.configInfo2Map(crewList):null;
        }
        return crewMap;
    }

    /**
     * Map<String,String>的KeySet转List<String> 方法
     * @author du yang
     * @param map
     * @return
     */
    public static List<String> convertMapKeySet2List(Map<String,String> map){
        List<String> list = null;
        if (map != null&&!map.isEmpty()) {
            list = new ArrayList<>();
            Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().getKey());
            }
        }
        return list;
    }

    public static List<String> getSpfHDType(Context context){
        List<String> list;
        String jsonStr = (String) ISpfUtil.getValue(context,Constants.SPF_HIDDEN_DANGER_TYPE_KEY,"");
        if (!IStringUtils.isEmpty(jsonStr)){
            list = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);
                for (int i= 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(jsonObject.getString("Value"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            list = Constants.HDTYPES_LIST;
        }
        return list;

    }

    /**
     * 解鎖、點亮屏幕
     * @param context
     */
    public static void wakeAndUnlock(Context context) {
        {
            //获取电源管理器对象
            PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
            mWakelock.acquire();
            mWakelock.release();

            //得到键盘锁管理器对象
            KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("");
            keyguardLock.disableKeyguard();
        }
    }

    /**
     * CBOK +
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    /**
     * CBOK +
     * @param reference
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 提取足迹节点的位置信息
     * du yang
     * @param entities
     * @return
     */
    public static List<Point> extracPoints(List<TrackPointEntity> entities){
        List<Point> pointList = null;
        if (entities != null&&entities.size()>0) {
            pointList = new ArrayList<>();
            Iterator<TrackPointEntity> iterator = entities.iterator();
            while (iterator.hasNext()) {
                TrackPointEntity entity=iterator.next();
                Point pt = new Point(entity.getX(), entity.getY());
                pointList.add(pt);
            }
        }
        return pointList;
    }

}
