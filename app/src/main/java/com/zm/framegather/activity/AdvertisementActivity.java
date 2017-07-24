package com.zm.framegather.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zm.framegather.R;
import com.zm.framegather.bean.AdvBean;
import com.zm.framegather.fragment.AdvFragment;
import com.zm.framegather.util.Constants;

import java.util.ArrayList;

public class AdvertisementActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout LLContent;
    private SimpleDraweeView ImgGifPic;
    private TextView TXTTitle1;
    private TextView TXTContext1;
    private LinearLayout LLContent2;
    private SimpleDraweeView ImgGifPic2;
    private TextView TXTTitle2;
    private TextView TXTContext2;
    private boolean isShow = true;
    private ArrayList<String> titles;
    private ArrayList<String> contents;
    private int maxSize = 30;
    private int index = 0;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    //更新相应的UI
                    changeUi();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        initViews();
        initData();
    }


    private void initViews() {
        LLContent = (LinearLayout) findViewById(R.id.LLContent);
        ImgGifPic = (SimpleDraweeView) findViewById(R.id.ImgGifPic);
        TXTTitle1 = (TextView) findViewById(R.id.TXTTitle1);
        TXTContext1 = (TextView) findViewById(R.id.TXTContext1);
        LLContent2 = (LinearLayout) findViewById(R.id.LLContent2);
        ImgGifPic2 = (SimpleDraweeView) findViewById(R.id.ImgGifPic2);
        TXTTitle2 = (TextView) findViewById(R.id.TXTTitle2);
        TXTContext2 = (TextView) findViewById(R.id.TXTContext2);

        LLContent.setOnClickListener(this);
        LLContent2.setOnClickListener(this);
    }

    private void initData() {

        //创建广告数据，实际开发中请放在获取到后台数据后调用
        ArrayList<AdvBean> advData = new ArrayList<>();
        ArrayList<AdvBean> advData2 = new ArrayList<>();
        for(int i=0;i<20;i++){
            AdvBean advBean = new AdvBean();
            advBean.setId(i);
            advBean.setImageUrl(Constants.urls[i]);
            advBean.setTitle("标题 " + i);
            advBean.setUrl("跳转地址 " + i);
            if(i<10){
                advData.add(advBean);
            }else{
                advData2.add(advBean);
            }
        }
        AdvFragment adv = new AdvFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("advData",advData);
        adv.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.adsFragment, adv).commit();

        AdvFragment adv2 = new AdvFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("advData",advData2);
        adv2.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().replace(R.id.adsFragment2, adv2).commit();


        titles = new ArrayList<>();
        contents = new ArrayList<>();

        for(int i=0;i<maxSize;i++){
            titles.add("仿淘宝滚动广告---标题" + i);
            contents.add("仿淘宝滚动广告---内容" + i);
        }

        Uri uri = Uri.parse("res://"+getPackageName()+"/" +R.drawable.hot);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        ImgGifPic.setController(controller);

        Uri uri2 = Uri.parse("res://"+getPackageName()+"/" +R.drawable.hot);
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(uri2)
                .setAutoPlayAnimations(true)
                .build();
        ImgGifPic2.setController(controller2);

        changeUi();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.LLContent:
                shortToast(titles.get(index) + "被选择");
                break;
            case R.id.LLContent2:
                shortToast(titles.get(index) + "被选择");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mhandler.removeMessages(0);
    }

    private void changeUi(){

        if(index == maxSize) index = 0;

        if(isShow){
            TXTTitle1.setText(titles.get(index));
            TXTContext1.setText(contents.get(index));
            TXTTitle2.setText(titles.get(index == 29 ? 0 : index + 1));
            TXTContext2.setText(contents.get(index == 29 ? 0 : index + 1));
            setAnimation(1,LLContent);
            setAnimation(2,LLContent2);
        }else{
            TXTTitle1.setText(titles.get(index == 29 ? 0 : index + 1));
            TXTContext1.setText(contents.get(index == 29 ? 0 : index + 1));
            TXTTitle2.setText(titles.get(index));
            TXTContext2.setText(contents.get(index));
            setAnimation(2,LLContent);
            setAnimation(1,LLContent2);
        }
        isShow = !isShow;
        index ++;
        mhandler.sendEmptyMessageDelayed(0, 5000);

    }

    private void setAnimation(int type,View view){
        switch (type){
            case 1:
                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        //X轴初始位置
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        //X轴移动的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴开始位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴移动后的结束位置
                        Animation.RELATIVE_TO_SELF,-1.0f);

                //3秒完成动画
                translateAnimation.setDuration(3000);
                //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                animationSet.setFillAfter(true);
                //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
                animationSet.addAnimation(translateAnimation);
                //启动动画
                view.startAnimation(animationSet);
                break;
            case 2:
                AnimationSet animationSet2 = new AnimationSet(true);
                TranslateAnimation translateAnimation2 = new TranslateAnimation(
                        //X轴初始位置
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        //X轴移动的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴开始位置
                        Animation.RELATIVE_TO_SELF,1.0f,
                        //y轴移动后的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f);

                //3秒完成动画
                translateAnimation2.setDuration(3000);
                //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                animationSet2.setFillAfter(true);
                //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
                animationSet2.addAnimation(translateAnimation2);
                //启动动画
                view.startAnimation(animationSet2);
                break;
        }

    }


}
