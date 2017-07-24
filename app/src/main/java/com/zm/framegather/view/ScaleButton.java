package com.zm.framegather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.zm.framegather.R;

/**
 * Description 描述
 * Created by 张伟明 on 2016/12/20.
 */

public class ScaleButton extends Button {
    private Context context;

    public ScaleButton(Context context) {
        this(context, null);
        this.context = context;
    }

    public ScaleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public ScaleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                beginScale(R.anim.zoom_in);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                beginScale(R.anim.zoom_out);
                break;
            case MotionEvent.ACTION_CANCEL:
                beginScale(R.anim.zoom_out);
                break;
        }
        return true;
    }

    private synchronized void beginScale(int animation) {
        Animation an = AnimationUtils.loadAnimation(context, animation);
        an.setDuration(80);
        an.setFillAfter(true);
        this.startAnimation(an);
    }
}
