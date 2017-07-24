package com.zm.framegather.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Description 描述 添加商品到购物车的过渡动画
 * Created by 张伟明 on 2016/12/20.
 */

public class ShoppingAnimUtil {

    private static ShoppingAnimUtil instance;
    private Activity context;
    private View view;

    //动画时间
    private int AnimationDuration = 1000;
    //正在执行的动画数量
    private int number = 0;
    //是否完成清理
    private boolean isClean = false;
    private FrameLayout animation_viewGroup;
    private Handler myHandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 0:
                    //用来清除动画后留下的垃圾
                    try{
                        animation_viewGroup.removeAllViews();
                    }catch(Exception e){

                    }

                    isClean = false;

                    break;
                default:
                    break;
            }
        }
    };

    // 创建单例模式
    public static ShoppingAnimUtil getInstance() {
        if (null == instance) {
            instance = new ShoppingAnimUtil();
        }
        return instance;

    }

    public void doAnim(Activity context,FrameLayout animation_viewGroup,View view, Drawable drawable, int[] start_location){
        this.context = context;
        this.view = view;
        this.animation_viewGroup = animation_viewGroup;
        if(!isClean){
            setAnim(drawable,start_location);
        }else{
            try{
                animation_viewGroup.removeAllViews();
                isClean = false;
                setAnim(drawable,start_location);
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                isClean = true;
            }
        }
    }

    /**
     * @deprecated 将要执行动画的view 添加到动画层
     * @param vg
     *        动画运行的层 这里是frameLayout
     * @param view
     *        要运行动画的View
     * @param location
     *        动画的起始位置
     * @return
     */
    private View addViewToAnimLayout(ViewGroup vg, View view, int[] location){
        int x = location[0];
        int y = location[1];
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                dip2px(context,90),dip2px(context,90));
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setPadding(5, 5, 5, 5);
        view.setLayoutParams(lp);

        return view;
    }
    /**
     * dip，dp转化成px 用来处理不同分辨路的屏幕
     * @param context
     * @param dpValue
     * @return
     */
    private int dip2px(Context context, float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale +0.5f);
    }

    /**
     * 动画效果设置
     * @param drawable
     *       将要加入购物车的商品
     * @param start_location
     *        起始位置
     */
    private void setAnim(Drawable drawable,int[] start_location){


        Animation mScaleAnimation = new ScaleAnimation(1.5f,0.0f,1.5f,0.0f,Animation.RELATIVE_TO_SELF,0.1f,Animation.RELATIVE_TO_SELF,0.1f);
        mScaleAnimation.setDuration(AnimationDuration);
        mScaleAnimation.setFillAfter(true);


        final ImageView iview = new ImageView(context);
        iview.setImageDrawable(drawable);
        final View view = addViewToAnimLayout(animation_viewGroup,iview,start_location);
        view.setAlpha(0.6f);

        int[] end_location = new int[2];
        this.view.getLocationInWindow(end_location);
        int endX = end_location[0];
        int endY = end_location[1]-start_location[1];

        Animation mTranslateAnimation = new TranslateAnimation(0,endX,0,endY);
        Animation mRotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(AnimationDuration);
        mTranslateAnimation.setDuration(AnimationDuration);
        AnimationSet mAnimationSet = new AnimationSet(true);

        mAnimationSet.setFillAfter(true);
        mAnimationSet.addAnimation(mRotateAnimation);
        mAnimationSet.addAnimation(mScaleAnimation);
        mAnimationSet.addAnimation(mTranslateAnimation);

        mAnimationSet.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                number++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

                number--;
                if(number==0){
                    isClean = true;
                    myHandler.sendEmptyMessage(0);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

        });
        view.startAnimation(mAnimationSet);

    }
    /**
     * 内存过低时及时处理动画产生的未处理冗余
     */
    public void onLowMemory() {
        // TODO Auto-generated method stub
        isClean = true;
        try{
            animation_viewGroup.removeAllViews();
        }catch(Exception e){
            e.printStackTrace();
        }
        isClean = false;
    }

}
