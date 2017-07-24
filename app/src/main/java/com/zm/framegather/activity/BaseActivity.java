package com.zm.framegather.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;

import com.zm.framegather.util.CustomDialog;
import com.zm.framegather.util.ToastUtil;

public abstract class BaseActivity extends AppCompatActivity {

    private CustomDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }

    /**
     * 弹出一个显示时间较短的 Toast
     * @param text 要显示的内容
     */
    public void shortToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    ToastUtil.showShort(getApplicationContext(), text, Gravity.CENTER);
                }
            });
        }
    }

    /**
     * 弹出一个显示时间较长的 Toast
     * @param text 要显示的内容
     */
    public void longToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    ToastUtil.showLong(getApplicationContext(), text, Gravity.CENTER);
                }
            });
        }
    }

    /**
     * 创建并显示对话框
     * @param msg 消息内容
     * @param context 上下文
     * @param cancelable 点击返回键是否取消对话框显示
     * @param touchCancelable 点击对话框外部是否取消对话框显示
     * @return
     */
    public CustomDialog createLoading(String msg, Context context, boolean cancelable, boolean touchCancelable) {
        loading = new CustomDialog(context, msg);
        loading.setCancelable(cancelable);
        loading.setCanceledOnTouchOutside(touchCancelable);
        loading.show();
        return loading;
    }

    /**
     * 关闭对话框
     */
    public void closeLoading() {
        if(loading != null && loading.isShowing()) loading.dismiss();
    }

    public void log(String msg){
        Log.d("zwm",msg);
    }
}
