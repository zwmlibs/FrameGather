package com.zm.framegather.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description 描述 数据缓存工具类
 * Created by 张伟明 on 2016/11/15.
 */

public class DataCacheUtils {

    private String cacheName = "data_cache";
    private static DataCacheUtils instance;

    private DataCacheUtils() {
    }

    // 创建单例模式
    public static DataCacheUtils getInstance() {
        if (null == instance) {
            instance = new DataCacheUtils();
        }
        return instance;

    }

    //保存
    public void saveGroupMember(Context context,String groupId,String text) {
        // 获取搜索框信息
        SharedPreferences mysp = context.getSharedPreferences(groupId + "group_member_history", Context.MODE_PRIVATE);
        SharedPreferences.Editor myeditor = mysp.edit();
        myeditor.putString(cacheName, text);
        myeditor.commit();

    }

    //获取
    public String getGroupMember(Context context,String groupId) {
        // 获取历史信息
        String cacheString = "";
        SharedPreferences mysp = context.getSharedPreferences(groupId + "group_member_history", Context.MODE_PRIVATE);
        cacheString = mysp.getString(cacheName, "");
        return cacheString;

    }

}
