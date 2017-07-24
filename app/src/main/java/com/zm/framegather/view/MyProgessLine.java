package com.zm.framegather.view;

/**
 * Description 描述
 * Created by 张伟明 on 2017/2/14.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.zm.framegather.R;




/**
 * 自定义进度
 *
 * @author Rock Lee
 * @date 2016年4月18日
 */
public class MyProgessLine extends View {

    //需要执行动画的参数名
    private static final String PROGRESS_PROPERTY = "progress";

    private Paint paint;// 画笔

    RectF rectF;

    private int bmColor;// 底部横线颜色
    private float bmHight;// 底部横线高度
    private int color;// 进度条颜色
    private float hight;// 进度条高度

    protected float progress;

    public void setColor(int color) {
        this.color = color;
    }

    public MyProgessLine(Context context) {
        this(context, null);
    }

    public MyProgessLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgessLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        paint = new Paint();
        rectF = new RectF();

        TypedArray mTypedArray = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.myLine, defStyleAttr, 0);

        bmColor = mTypedArray.getColor(R.styleable.myLine_myLineBmColor,
                Color.GRAY);
        bmHight = mTypedArray
                .getDimension(R.styleable.myLine_myLineBmHight, 2);
        color = mTypedArray.getColor(R.styleable.myLine_myLineColor,
                Color.BLUE);
        hight = mTypedArray.getDimension(R.styleable.myLine_myLineHight, 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float width = getWidth() / 2; // 获取x坐标

        paint.setColor(bmColor);
        paint.setStrokeCap(Paint.Cap.SQUARE);// 圆角
        paint.setStyle(Paint.Style.FILL); // 设置实心
        paint.setStrokeWidth(bmHight); // 设置笔画的宽度
        paint.setAntiAlias(true); // 消除锯齿

        rectF.set(0, getHeight() - bmHight, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, bmHight / 2, bmHight / 2, paint);
        //canvas.drawRect(0, 0, getWidth(), bmHight, paint);

        paint.setColor(color);
        paint.setStrokeWidth(hight); // 设置笔画的宽度

        rectF.set(0, getHeight() - bmHight, progress, getHeight());
        //矩形
		canvas.drawRoundRect(rectF, hight / 2, hight / 2, paint);
        //canvas.drawRect(0, 0, progress, bmHight, paint);

        /**
         * 画进度百分比的text
         */
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);
        paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体
        float textWidth = paint.measureText(progressText + "%");
        canvas.drawBitmap(backgroundBitmap, progress - textWidth / 2 - 15,getHeight() / 2 - 100, paint);
        canvas.drawText(progressText + "%", progress - textWidth / 2,getHeight() / 2, paint); // 画出进度百分比

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress * getWidth() / 100;
        invalidate();
    }

    protected float progressText;
    protected Bitmap backgroundBitmap;

    /**
     * 赋值+执行动画
     *
     * @param progressText 进度 float
     */
    public void dodo(float Text,float progressText,Bitmap bitmap) {
        this.progressText = Text;
        this.backgroundBitmap = bitmap;
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(this, PROGRESS_PROPERTY,
                0f, progressText);
        progressAnimation.setDuration(3000);//动画耗时
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        animation.playTogether(progressAnimation);
        animation.start();
    }

}

