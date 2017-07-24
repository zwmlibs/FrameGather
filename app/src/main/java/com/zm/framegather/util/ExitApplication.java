package com.zm.framegather.util;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ExitApplication extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	// 创建单例模式
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;

	}

	// 添加activity
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 退出软件

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);

	}
	
	// 循环finish
	public void finsh(){
		for (Activity activity : activityList) {
			activity.finish();
		}
	}

	// 循环finish
//		public void finshCache(){
//			for (Activity activity : activityList) {
//				if(activity instanceof NavigationFragAct) activity.finish();
//			}
//		}
}
