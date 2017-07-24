package com.zm.framegather.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zm.framegather.R;

/**
 * Description 描述
 * Created by 张伟明 on 2016/12/29.
 */

public class AnimUtils {

    //为控件添加点击缩放动画
    public static void AddScaleAnim(final Context context,final View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        beginScale(context,view,R.anim.zoom_in);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        beginScale(context,view,R.anim.zoom_out);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        beginScale(context,view,R.anim.zoom_out);
                        break;
                }
                return false;
            }
        });
    }

    private static void beginScale(Context context,View view,int animation) {
        Animation an = AnimationUtils.loadAnimation(context, animation);
        an.setDuration(80);
        an.setFillAfter(true);
        view.startAnimation(an);
    }

    public static void beginScaleCart(Context context,View view) {
        Animation an = AnimationUtils.loadAnimation(context, R.anim.zoom_cart);
        an.setDuration(100);
        an.setFillAfter(true);
        view.startAnimation(an);
    }

}
