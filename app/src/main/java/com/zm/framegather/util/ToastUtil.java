package com.zm.framegather.util;


import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zm.framegather.R;


public class ToastUtil {

	public static void showShort(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLong(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void showLong(Context context,String text, int gravity) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		ViewGroup vg = (ViewGroup)toast.getView();
		TextView txt = (TextView) vg.getChildAt(0);
		txt.setPadding(10,10,10,10);
		txt.setTextColor(Color.DKGRAY);
		vg.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_drawable));
		toast.setGravity(gravity, 0, 0);
		toast.show();
	}
	
	public static void showShort(Context context,String text, int gravity) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		ViewGroup vg = (ViewGroup)toast.getView();
		TextView txt = (TextView) vg.getChildAt(0);
		txt.setPadding(10,10,10,10);
		txt.setTextColor(Color.DKGRAY);
		vg.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_drawable));
		toast.setGravity(gravity, 0, 0);
		toast.show();
	}

}
