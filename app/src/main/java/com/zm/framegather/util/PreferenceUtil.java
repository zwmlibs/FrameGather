package com.zm.framegather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint;

import com.zm.framegather.bean.UserBean;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 
 * 
 * @ClassName: PreferenceUtil
 * @Description: SharedPreferences操作工具包
 * @author 张伟明
 * @date 2016年5月4日
 * 
 */
public class PreferenceUtil {
	public final static String TEXTSETSIZE = "TEXTSIZE";
	public final static String SHAREDPREFERENCES_NAME = "Vl_app_shared_prefs";

	/**
	 * 写入版本选择
	 * 
	 * @param context
	 * @param longitude
	 * @return
	 */
	public static boolean setUpdateFlag(Context context, Boolean flag) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putBoolean("UpdateFlag", flag);
		return edit.commit();
	}

	/**
	 * 读取版本选择
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getUpdateFlag(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean("UpdateFlag", true);
	}

	/**
	 * 
	 * @Title:
	 * @Description: TODO
	 * @param @param 写入是否第一次运行
	 * @param @param flag
	 * @param @return
	 * @return
	 * @throws
	 */
	public static boolean setFirstFlag(Context context, Boolean flag) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putBoolean("FirstFlag", flag);
		return edit.commit();
	}

	/*
	 * 读取是否第一次运行
	 */
	public static boolean getFirstFlag(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean("FirstFlag", true);
	}

	public static void write(Context context, String SHAREDPREFERENCES_NAME,
			String k, int v) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putInt(k, v);
		editor.commit();
	}

	public static void write(Context context, String k, boolean v) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putBoolean(k, v);
		editor.commit();
	}

	public static void writeString(Context context, String k, String v) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putString(k, v);
		editor.commit();
	}

	public static int readInt(Context context, String k) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Paint sPaint = new Paint();
		return preference.getInt(k, (int) sPaint.getTextSize());
	}

	public static int readInt(Context context, String k, int defv) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getInt(k, defv);
	}

	public static boolean readBoolean(Context context, String k) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getBoolean(k, false);
	}

	public static void writeBoolean(Context context, String k, boolean defBool) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putBoolean(k, defBool);
		editor.commit();
	}

	public static boolean readBoolean(Context context, String k, boolean defBool) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getBoolean(k, defBool);
	}

	public static String readString(Context context, String k) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getString(k, "");
	}

	public static String readCollege(Context context) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getString("CollegeName", "湖北大学");
	}

	public static void writeCollege(Context context, String v) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putString("CollegeName", v);
		editor.commit();
	}

	public static String readString(Context context, String k, String defV) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		return preference.getString(k, defV);
	}

	public static void remove(Context context, String k) {
		SharedPreferences preference = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.remove(k);
		editor.commit();
	}

	/**
	 * 存储userbean
	 */
	public static void writeUser(Context context, UserBean userbean) {
		SharedPreferences preferences = context.getSharedPreferences(
				"userbean", Context.MODE_PRIVATE);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(userbean);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encodeBase64(baos
					.toByteArray()));
			Editor editor = preferences.edit();
			editor.putString("userbean", oAuth_Base64);
			editor.commit();
		} catch (IOException e) {
			// TODO Auto-generated
		}
	}

	/*
	 * 读取userbean
	 */
	@SuppressWarnings("static-access")
	public static UserBean readUser(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"userbean", context.MODE_PRIVATE);
		String productBase64 = preferences.getString("userbean", "");

		// 读取字节
		byte[] base64 = Base64.decodeBase64(productBase64.getBytes());

		// 封装到字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 再次封装
			ObjectInputStream bis = new ObjectInputStream(bais);
			try {
				// 读取对象
				return (UserBean) bis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void clean(Context cxt) {
		SharedPreferences preference = cxt.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.clear();
		editor.commit();
	}

}
