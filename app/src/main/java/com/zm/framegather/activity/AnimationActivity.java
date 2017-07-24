package com.zm.framegather.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.zm.framegather.R;
import com.zm.framegather.util.AnimUtils;

public class AnimationActivity extends BaseActivity implements Animation.AnimationListener {

    private ImageView animationIV;
    private Button buttonA, buttonB, buttonC, buttonD, buttonE,buttonF, buttonG, buttonH, buttonI, buttonJ, buttonK, buttonL;
    private AnimationDrawable animationDrawable;
    private int currAngle;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Animation anim;
            switch (msg.what){
                case 1:
                    //第二种方式
                    anim = new AlphaAnimation(1.0f, 0.0f);
                    anim.setDuration(3000);
                    anim.setFillAfter(true);
                    animationIV.startAnimation(anim);
                    break;
                case 2:
                    //第二种方式
                    anim = new TranslateAnimation(200, 0, 300, 0);
                    anim.setDuration(2000);
                    anim.setFillAfter(true);
                    OvershootInterpolator overshoot = new OvershootInterpolator();
                    anim.setInterpolator(overshoot);
                    animationIV.startAnimation(anim);
                    break;
                case 3:
                    anim = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    anim.setDuration(2000);
                    anim.setFillAfter(true);
                    BounceInterpolator bounce = new BounceInterpolator();
                    anim.setInterpolator(bounce);
                    animationIV.startAnimation(anim);
                    break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_animation);


        animationIV = (ImageView) findViewById(R.id.animationIV);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        buttonE = (Button) findViewById(R.id.buttonE);
        buttonF = (Button) findViewById(R.id.buttonF);
        buttonG = (Button) findViewById(R.id.buttonG);
        buttonH = (Button) findViewById(R.id.buttonH);
        buttonI = (Button) findViewById(R.id.buttonI);
        buttonJ = (Button) findViewById(R.id.buttonJ);
        buttonK = (Button) findViewById(R.id.buttonK);
        buttonL = (Button) findViewById(R.id.buttonL);

        AnimUtils.AddScaleAnim(getApplicationContext(),buttonA);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonB);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonC);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonD);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonE);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonF);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonG);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonH);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonI);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonJ);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonK);
        AnimUtils.AddScaleAnim(getApplicationContext(),buttonL);

        //顺序显示
        buttonA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.animation1);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }

        });

        //停止
        buttonB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.stop();
            }

        });

        //倒序显示
        buttonC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.animation2);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }
        });

        //顺时针
        buttonD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Animation anim = new RotateAnimation(currAngle, currAngle + 180, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                /** 匀速插值器 */
                LinearInterpolator lir = new LinearInterpolator();
                anim.setInterpolator(lir);
                anim.setDuration(1000);
                /** 动画完成后不恢复原状 */
                anim.setFillAfter(true);
                currAngle += 180;
                if (currAngle > 360) {
                    currAngle = currAngle - 360;
                }
                animationIV.startAnimation(anim);
            }
        });

        //逆时针
        buttonE.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Animation anim = new RotateAnimation(currAngle, currAngle - 180, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                /** 匀速插值器 */
                LinearInterpolator lir = new LinearInterpolator();
                anim.setInterpolator(lir);
                anim.setDuration(1000);
                /** 动画完成后不恢复原状 */
                anim.setFillAfter(true);
                currAngle -= 180;
                if (currAngle < -360) {
                    currAngle = currAngle + 360;
                }
                animationIV.startAnimation(anim);
            }
        });

        //透明
        buttonF.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //第一种方式
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
                anim.setAnimationListener(AnimationActivity.this);
                animationIV.startAnimation(anim);

                handler.sendEmptyMessageDelayed(1,2000);
                //第二种方式
//                Animation anim2 = new AlphaAnimation(1.0f, 0.0f);
//                anim2.setDuration(3000);
//                anim2.setFillAfter(true);
//                animationIV.startAnimation(anim2);
            }
        });

        //位移
        buttonG.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //第一种方式
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.translate);
                anim.setFillAfter(true);
                animationIV.startAnimation(anim);
                handler.sendEmptyMessageDelayed(2,2000);
                //第二种方式
//                Animation anim2 = new TranslateAnimation(200, 0, 300, 0);
//                anim2.setDuration(2000);
//                anim2.setFillAfter(true);
//                OvershootInterpolator overshoot = new OvershootInterpolator();
//                anim.setInterpolator(overshoot);
//                animationIV.startAnimation(anim2);
            }
        });

        //缩放
        buttonH.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //第一种方式
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.scale);
                anim.setFillAfter(true);
                animationIV.startAnimation(anim);
                handler.sendEmptyMessageDelayed(3,2000);
                //第二种方式
//                Animation anim = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
//                        Animation.RELATIVE_TO_SELF, 0.5f,
//                        Animation.RELATIVE_TO_SELF, 0.5f);
//                anim.setDuration(2000);
//                anim.setFillAfter(true);
//                BounceInterpolator bounce = new BounceInterpolator();
//                anim.setInterpolator(bounce);
//                animationIV.startAnimation(anim);
            }
        });

        //翻转
        buttonI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ObjectAnimator//
                        .ofFloat(animationIV, "rotationX", 0.0F, 360.0F)//
                        .setDuration(500)//
                        .start();
            }
        });

        //组合
        buttonJ.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //第一种方式
//                ObjectAnimator anim = ObjectAnimator//
//                        .ofFloat(animationIV, "zhy", 1.0F,  0.0F)//
//                        .setDuration(500);//
//                anim.start();
//                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//                {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation)
//                    {
//                        float cVal = (Float) animation.getAnimatedValue();
//                        animationIV.setAlpha(cVal);
//                        animationIV.setScaleX(cVal);
//                        animationIV.setScaleY(cVal);
//                    }
//                });

                //第二种方式
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                        0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                        0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                        0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(animationIV, pvhX, pvhY,pvhZ).setDuration(500).start();

            }
        });

        //抛物线
        buttonK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(3000);
                valueAnimator.setObjectValues(new PointF(buttonK.getX(), buttonK.getY()));
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
                {
                    // fraction = t / duration
                    @Override
                    public PointF evaluate(float fraction, PointF startValue,
                                           PointF endValue)
                    {
                        // x方向200px/s ，则y方向0.5 * 10 * t
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });

                valueAnimator.start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        PointF point = (PointF) animation.getAnimatedValue();
                        animationIV.setX(point.x);
                        animationIV.setY(point.y);

                    }
                });
            }
        });

        //组合2
        buttonL.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                float cx = animationIV.getX();

                ObjectAnimator anim1 = ObjectAnimator.ofFloat(animationIV, "scaleX",
                        1.0f, 2f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(animationIV, "scaleY",
                        1.0f, 2f);
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(animationIV,
                        "x",  cx ,  0f);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(animationIV,
                        "x", cx);

                /**
                 * anim1，anim2,anim3同时执行
                 * anim4接着执行
                 */
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(anim1).with(anim2);
                animSet.play(anim2).with(anim3);
                animSet.play(anim4).after(anim3);
                animSet.setDuration(1000);
                animSet.start();
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}