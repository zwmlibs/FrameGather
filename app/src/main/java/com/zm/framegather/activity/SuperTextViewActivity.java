package com.zm.framegather.activity;

import android.os.Bundle;

import com.zm.framegather.R;
import com.zm.framegather.view.Adjuster.MoveEffectAdjuster;
import com.zm.framegather.view.Adjuster.OpportunityDemoAdjuster;
import com.zm.framegather.view.Adjuster.RippleAdjuster;
import com.zm.framegather.view.SuperTextView;

/**
 * SuperTextView继承自TextView，它能够大量的减少布局的复杂程度，并且使得一些常见的效果变得十分容易实现且高效。
 * 同时，它内置了动画驱动，你只需要合理编写Adjuster，然后 startAnim() 就可以看到预期的动画效果。
 * 它仅仅是一个控件，所以你可以不费吹灰之力的在你的项目中集成使用。
 *
 * 详细说明：https://github.com/chenBingX/SuperTextView/blob/master/README_zh.md
 *
 * 特点：
 * 1.你从此不必再为背景图编写和管理大量文件了。
 * 2.重新优化的状态图功能使得你能够精确的控制状态图的大小，以及在SuperTextView中的位置。
 * 3.支持设置圆角，并且能够精确的控制圆角位置。
 * 4.能够轻松的实现控件边框效果。
 * 5.支持文字描边，这使得空心文字效果成为了可能。
 * 6.内置动画驱动，你只需配合Adjuster合理的使用即可。
 * 7.Adjuster的出现，使得你对控件的绘制过程具有了掌控权，良好的设计使得它能够完美的实现绝大部分你脑海中的效果
 *
 */
public class SuperTextViewActivity extends BaseActivity {

    private SuperTextView stv_17;
    private SuperTextView stv_18;
    private SuperTextView stv_19;
    private SuperTextView stv_20;
    private SuperTextView stv_21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_text_view);
        initView();
    }

    private void initView() {
        findViews();

        stv_17.setAdjuster(new MoveEffectAdjuster());
        stv_17.setAutoAdjust(true);
        stv_17.startAnim();

        stv_18.setAdjuster(new RippleAdjuster(getResources().getColor(R.color.purple)));

        OpportunityDemoAdjuster opportunityDemoAdjuster1 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster1.setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_DRAWABLE);
        stv_19.setAdjuster(opportunityDemoAdjuster1);
        stv_19.setAutoAdjust(true);

        OpportunityDemoAdjuster opportunityDemoAdjuster2 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster2.setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_TEXT);
        stv_20.setAdjuster(opportunityDemoAdjuster2);
        stv_20.setAutoAdjust(true);

        OpportunityDemoAdjuster opportunityDemoAdjuster3 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster3.setOpportunity(SuperTextView.Adjuster.Opportunity.AT_LAST);
        stv_21.setAdjuster(opportunityDemoAdjuster3);
        stv_21.setAutoAdjust(true);
    }

    private void findViews() {
        stv_17 = (SuperTextView) findViewById(R.id.stv_17);
        stv_18 = (SuperTextView) findViewById(R.id.stv_18);
        stv_19 = (SuperTextView) findViewById(R.id.stv_19);
        stv_20 = (SuperTextView) findViewById(R.id.stv_20);
        stv_21 = (SuperTextView) findViewById(R.id.stv_21);
    }
}
