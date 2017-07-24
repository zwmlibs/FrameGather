package com.zm.framegather.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description 描述 帐号信息屏蔽工具类
 * Created by 张伟明 on 2016/11/15.
 */

public class AccountSearchUtils {

    private String[] history_arr;
    private String searchName = "history_account";
    private static AccountSearchUtils instance;

    private AccountSearchUtils() {
    }

    // 创建单例模式
    public static AccountSearchUtils getInstance() {
        if (null == instance) {
            instance = new AccountSearchUtils();
        }
        return instance;

    }

    //判断帐号是否有保存
    public boolean hasAccount(Context context,String text){

        // 获取搜索框信息
        SharedPreferences mysp = context.getSharedPreferences("chat_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");

        // 判断搜索内容是否已经存在于历史文件
        if (old_text.contains(text)) {
            return true;
        }
        return false;
    }

    //保存
    public void save(Context context,String text) {
        // 获取搜索框信息
        SharedPreferences mysp = context.getSharedPreferences("chat_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");

        // 利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder builder = new StringBuilder(old_text);
        builder.append(text + ",");

        // 判断搜索内容是否已经存在于历史文件，已存在则不重复添加
        if (!old_text.contains(text + ",")) {
            SharedPreferences.Editor myeditor = mysp.edit();
            myeditor.putString(searchName, builder.toString());
            myeditor.commit();
        }

    }

    //删除
    public void delete(Context context,String text) {
        // 获取历史信息
        SharedPreferences mysp = context.getSharedPreferences("chat_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");
        String new_text = "";
        // 判断是否存在
        if (old_text.contains(text + ",")) {
            // 用逗号分割内容返回数组
            history_arr = old_text.split(",");
            for(int i=0;i<history_arr.length;i++){
                if(!history_arr[i].equals(text)){
                    new_text += history_arr[i] + ",";
                }
            }
            SharedPreferences.Editor myeditor = mysp.edit();
            myeditor.putString(searchName, new_text);
            myeditor.commit();
        }

    }

    //判断帐号是否有保存
    public boolean hasAccountForGroup(Context context,String text){

        // 获取搜索框信息
        SharedPreferences mysp = context.getSharedPreferences("group_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");

        // 判断搜索内容是否已经存在于历史文件
        if (old_text.contains(text)) {
            return true;
        }
        return false;
    }

    //保存
    public void saveForGroup(Context context,String text) {
        // 获取搜索框信息
        SharedPreferences mysp = context.getSharedPreferences("group_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");

        // 利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder builder = new StringBuilder(old_text);
        builder.append(text + ",");

        // 判断搜索内容是否已经存在于历史文件，已存在则不重复添加
        if (!old_text.contains(text + ",")) {
            SharedPreferences.Editor myeditor = mysp.edit();
            myeditor.putString(searchName, builder.toString());
            myeditor.commit();
        }

    }

    //删除
    public void deleteForGroup(Context context,String text) {
        // 获取历史信息
        SharedPreferences mysp = context.getSharedPreferences("group_history", Context.MODE_PRIVATE);
        String old_text = mysp.getString(searchName, "");
        String new_text = "";
        // 判断是否存在
        if (old_text.contains(text + ",")) {
            // 用逗号分割内容返回数组
            history_arr = old_text.split(",");
            for(int i=0;i<history_arr.length;i++){
                if(!history_arr[i].equals(text)){
                    new_text += history_arr[i] + ",";
                }
            }
            SharedPreferences.Editor myeditor = mysp.edit();
            myeditor.putString(searchName, new_text);
            myeditor.commit();
        }

    }

}
