package com.yunwei.wetlandpark.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.utils.parse
 * @Description:GSON解析工具类
 * @date 2016/9/28 11:39
 */

public class ParseJson {
    private static final String TAG="ParseJson";
    /**
     * 转成字条串
     *
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        return new Gson().toJson(src);
    }

    /**
     * 实体序列化成Json(只序列化有Exposed注解的字段)
     * @author du yang
     * @param obj
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJsonExcluedUnExposed(Object obj){
        try {
            Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            return gson.toJson((T)obj);
        } catch (Exception e) {
            ILog.e(TAG,"解析异常 error=="+e.getMessage());
            return null;
        }
    }

    /**
     * 字符串转实体
     *
     * @param json
     * @param claxx
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> claxx) {
        T obj;
        try{
             obj = new Gson().fromJson(json, claxx);
        }catch (Exception e){
            ILog.e(TAG,"解析异常 error=="+e.getMessage());
            return null;
        }
        return obj;
    }

    /**
     * 字节转实体
     *
     * @param bytes
     * @param claxx
     * @param <T>
     * @return
     */
    public static <T> T toObject(byte[] bytes, Class<T> claxx) {
        T obj;
        try{
            obj = new Gson().fromJson(new String(bytes), claxx);
        }catch (Exception e){
            ILog.e(TAG,"解析异常 error=="+e.getMessage());
            return null;
        }
        return obj;
    }

    /**
     * 字符串转集合
     *
     * @param json
     * @param claxx
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> claxx) {
        List<T> list;
        try{
            list = new Gson().fromJson(json,new TypeToken<ArrayList<T>>(){}.getType());
        }catch (Exception e){
            ILog.e(TAG,"解析异常 error=="+e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * List序列化->Json(所有字段)
     * @author du yang
     * @param mList
     * @param excludeUnExposed 排除没有加Exposed注解的字段，true:只序列化Expose的字段 false :序列化所有字段
     * @return
     */
    public static <T> String listToJson(List<T> mList,boolean excludeUnExposed) {
        StringBuilder sb = null;
        if (mList != null && mList.size() > 0) {
            sb = new StringBuilder();
            sb.append("[");
            for (T t : mList) {
                if (excludeUnExposed) {
                    sb.append(toJsonExcluedUnExposed(t));
                }else{
                    sb.append(toJson(t));
                }
                sb.append(",");
            }
            if (sb.lastIndexOf(",") > 0) {
                sb.deleteCharAt(sb.lastIndexOf(","));
            }
            sb.append("]");
        }
        return sb != null ? sb.toString() : "";
    }


}
