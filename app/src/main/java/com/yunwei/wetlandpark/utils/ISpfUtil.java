package com.yunwei.wetlandpark.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.yunwei.wetlandpark.entity.ConfigEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * SharedPreference 工具类
 * 
 * @author Aaroa
 * 
 */
public class ISpfUtil {
	private static final String NAME = "zaina";

	/**
	 * 设置SharePreference文件中的字段的值
	 *
	 * @param key
	 *            字段
	 * @param value
	 *            值
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public static boolean setValue(String key, Object value) {
		boolean status = false;
		SharedPreferences spf = null;
		try {
			spf = ZNAPPlication.getInstance().getSharedPreferences(NAME, Context.MODE_MULTI_PROCESS);
			String type = value.getClass().getSimpleName();// 获取数据类型
			SharedPreferences.Editor editor = spf.edit();
			if (spf != null) {
				if ("String".equals(type)) {
					editor.putString(key, (String) value);
				} else if ("Integer".equals(type)) {
					editor.putInt(key, (Integer) value);
				} else if ("Boolean".equals(type)) {
					editor.putBoolean(key, (Boolean) value);
				} else if ("Long".equals(type)) {
					editor.putLong(key, (Long) value);
				} else if ("Float".equals(type)) {
					editor.putFloat(key, (Float) value);
				}
				status = editor.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * 获得SharePreference的值
	 *
	 * @param key
	 *            字段
	 * @param defValue
	 *            默认值
	 * @return 获得对应key的值
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public static Object getValue(String key, Object defValue) {
		SharedPreferences spf = null;
		try {
			spf = ZNAPPlication.getInstance().getSharedPreferences(NAME, Context.MODE_MULTI_PROCESS);
			String type = defValue.getClass().getSimpleName();// 获取数据类型
			if (spf != null) {
				if (type.equals("String")) {
					return spf.getString(key, (String) defValue);
				} else if (type.equals("Integer")) {
					return spf.getInt(key, (Integer) defValue);
				} else if (type.equals("Boolean")) {
					return spf.getBoolean(key, (Boolean) defValue);
				} else if (type.equals("Long")) {
					return spf.getLong(key, (Long) defValue);
				} else if (type.equals("Float")) {
					return spf.getFloat(key, (Float) defValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置SharePreference文件中的字段的值
	 * 
	 * @param ctx
	 *            上下文
	 *            share文件名字
	 * @param key
	 *            字段
	 * @param value
	 *            值
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public static boolean setValue(Context ctx, String key, Object value) {
		boolean status = false;
		SharedPreferences spf = null;
		try {
			spf = ctx.getSharedPreferences(NAME, Context.MODE_MULTI_PROCESS);
			String type = value.getClass().getSimpleName();// 获取数据类型
			SharedPreferences.Editor editor = spf.edit();
			if (spf != null) {
				if ("String".equals(type)) {
					editor.putString(key, (String) value);
				} else if ("Integer".equals(type)) {
					editor.putInt(key, (Integer) value);
				} else if ("Boolean".equals(type)) {
					editor.putBoolean(key, (Boolean) value);
				} else if ("Long".equals(type)) {
					editor.putLong(key, (Long) value);
				} else if ("Float".equals(type)) {
					editor.putFloat(key, (Float) value);
				}
				status = editor.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * 获得SharePreference的值
	 * 
	 * @param ctx
	 *            上下文
	 *            share文件名字
	 * @param key
	 *            字段
	 * @param defValue
	 *            默认值
	 * @return 获得对应key的值
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public static Object getValue(Context ctx, String key, Object defValue) {
		SharedPreferences spf = null;
		try {
			spf = ctx.getSharedPreferences(NAME, Context.MODE_MULTI_PROCESS);
			String type = defValue.getClass().getSimpleName();// 获取数据类型
			if (spf != null) {
				if (type.equals("String")) { 
					return spf.getString(key, (String) defValue);
				} else if (type.equals("Integer")) {
					return spf.getInt(key, (Integer) defValue);
				} else if (type.equals("Boolean")) {
					return spf.getBoolean(key, (Boolean) defValue);
				} else if (type.equals("Long")) {
					return spf.getLong(key, (Long) defValue);
				} else if (type.equals("Float")) {
					return spf.getFloat(key, (Float) defValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 针对复杂类型存储<对象>
	 *
	 * @param key
	 * @param object
	 */
	public static void setObject(Context context,String key, Object object) {
		SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);

		//创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//创建字节对象输出流
		ObjectOutputStream out = null;
		try {
			//然后通过将字对象进行64转码，写入key值为key的sp中
			out = new ObjectOutputStream(baos);
			out.writeObject(object);
			String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
			SharedPreferences.Editor editor = sp.edit();
			editor.putString(key, objectVal);
			editor.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(Context context,String key, Class<T> clazz) {
		SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
		if (sp.contains(key)) {
			String objectVal = sp.getString(key, null);
			byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
			//一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bais);
				T t = (T) ois.readObject();
				return t;
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bais != null) {
						bais.close();
					}
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 小锐
	 * 福田宝安项目 ，SharedPreferences获取配置文件工具
	 * @param con
	 * @return
     */
	public static ConfigEntity getConfigData(Context con) {
		String configInfo = (String)getValue(con, ConfigEntity.FLAG,"");
		ConfigEntity configData =ParseJson.toObject(configInfo,ConfigEntity.class);
		if(configData==null){
			configData = new ConfigEntity();
		}
		return configData;
	}

	public static String[] getHiddenDangerTypes(Context context){
		ConfigEntity configData = getConfigData(context);
		List<ConfigEntity.ConfigValue> hiddenTypeList = configData.getHiddenTypeList();
		String[] strings = new String[hiddenTypeList.size()];
		for (int i = 0; i < hiddenTypeList.size(); i++) {
			strings[i] = hiddenTypeList.get(i).getValue();
		}
		return strings;
	}
}
