package com.zm.framegather.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.zm.framegather.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;


public class StringUtils {

	//MD5加密
	public static String MD5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
	
	/**
	 * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 */
	public static boolean isEmpty(CharSequence input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
	
	//非空检测
		public static boolean isEmpty(String str) {
			if (TextUtils.isEmpty(str)) {
				return true;
			} else {
				return false;
			}
		}

		//转换数组
		public static String[] convertStrToArray(String str) {
			String[] strings = str.split(",");
			return strings;
		}

		//参数对比
		public static boolean isEquals(String str1, String str2) {
			if (str1.equals(str2)) {
				return true;
			} else {
				return false;
			}
		}

	//价格格式化,保留小数点后两位
	public static String dateFormatting(double date){
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(date);
	}

	//价格格式化,保留小数点后两位
	public static String dateFormatting(String str){
		double date = Double.valueOf(str);
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(date);
	}

	/**
	 * 检验是否为手机号
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	public static void showSnackBar(Context context, View view, String msg) {
		try {
			Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("我知道了", new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).setActionTextColor(context.getResources().getColor(R.color.base_color)).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
