package com.zm.framegather.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;

public class SDCardUtil {

	/**
	 * 检查内存卡是否可用
	 * 
	 * @return
	 */
	public static boolean checkSDCardAvailable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 创建图片缓存目录
	 */
	public static void createCacheDirectories() {
		if (checkSDCardAvailable()) {
			File picCacheDir = new File(
					Environment.getExternalStorageDirectory(),
					Constants.Cache.CACHE_PIC_DIRECTORY);
			if (!picCacheDir.exists()) {
				picCacheDir.mkdirs();
			}
		}
	}
	
	/**
	 * 创建更新app存放目录 
	 */
	
	public static File createUpdateAppPath() {
		if(checkSDCardAvailable()) {
			File appDir = new File(
					Environment.getExternalStorageDirectory(),
					Constants.Cache.APP_UPDATE);
			if(!appDir.exists()) {
				appDir.mkdirs();
			}
			
			return appDir;
		}
		return null;
	}

	/**
	 * 获取图片缓存目录的路径
	 * 
	 * @return
	 */
	public static String getCacheDir() {
		return Environment.getExternalStorageDirectory().toString()
				+ Constants.Cache.CACHE_PIC_DIRECTORY;
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static long getFileSize(File file) {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				size = fis.available();
				return size;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return size;
	}

	/**
	 * 获取当前缓存目录容量的大小
	 * 
	 * @return
	 */
	public static String getCachedCapacity() {
		File cacheDir = new File(
				Environment.getExternalStorageDirectory(),
				Constants.Cache.CACHE_PIC_DIRECTORY);
		long size = 0;
		File flist[] = cacheDir.listFiles();
		for (int i = 0; i < flist.length; i++) {
			size = size + getFileSize(flist[i]); // 此时size 单位为byte
		}
		long kSize = size / 1024;
		if (kSize < 1024) {
			return kSize + "KB";
		} else {
			float mSize = kSize / 1024;
			return mSize + "MB";
		}
	}

	/**
	 * 清空缓存
	 * 
	 * @return
	 */
	public static boolean clearAllCaches() {
		try {
			File cacheDir = new File(
					Environment.getExternalStorageDirectory(),
					Constants.Cache.CACHE_PIC_DIRECTORY);
			if (cacheDir.exists()) {
				File flist[] = cacheDir.listFiles();
				if (flist.length > 0) {
					for (File file : flist) {
						file.delete();
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
