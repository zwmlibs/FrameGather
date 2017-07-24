package com.zm.framegather.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zm.framegather.R;
import com.zm.framegather.bean.UserBean;
import com.zm.framegather.network.BaseRequest;
import com.zm.framegather.network.RestClient;
import com.zm.framegather.util.Constants;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private SimpleDraweeView ImgIconBg;
    private SimpleDraweeView ImgIcon;
    private CardView CVInfo;
    private TextView TXTInfo;
    private SimpleDraweeView ImgIcon2;
    private CardView CVInfo2;
    private TextView TXTInfo2;
    private int userId = 1;
    private boolean isRun;
    private boolean isShow;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    //更新你相应的UI
                    userId ++;
                    getInfo();
                    break;
                case 1:
                    break;
            }
        }
    };


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home,container, false);
            initViews(view);
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void initViews(View view){

        ImgIconBg = (SimpleDraweeView) view.findViewById(R.id.ImgIconBg);
        ImgIcon = (SimpleDraweeView) view.findViewById(R.id.ImgIcon);
        TXTInfo = (TextView) view.findViewById(R.id.TXTInfo);
        CVInfo = (CardView) view.findViewById(R.id.CVInfo);

        ImgIcon2 = (SimpleDraweeView) view.findViewById(R.id.ImgIcon2);
        TXTInfo2 = (TextView) view.findViewById(R.id.TXTInfo2);
        CVInfo2 = (CardView) view.findViewById(R.id.CVInfo2);
        view.findViewById(R.id.BtnGetInfo).setOnClickListener(this);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && !isRun){
            //Fragment可见
            mhandler.sendEmptyMessageDelayed(0, 1000);
            isRun = true;
        }else{
            //Fragment不可见
            mhandler.removeMessages(0);
            isRun = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mhandler.removeMessages(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnGetInfo:
                userId ++;
                getInfo();
                break;
        }
    }

    private void getInfo(){

        RestClient.api().getString(userId + "").enqueue(new Callback<BaseRequest<UserBean>>() {

            @Override
            public void onResponse(Call<BaseRequest<UserBean>> call, Response<BaseRequest<UserBean>> response) {
                if (response.body() == null) {
                    return;
                }
                if ("0".equals(response.body().getCode())){
                    try{
                        UserBean user = response.body().getUser();
                        String temp=getResources().getString(R.string.user_format);
                        int index = new Random().nextInt(19);
                        ImgIconBg.setImageURI(Uri.parse(Constants.urls[index]));

                        if(isShow){
                            ImgIcon.setImageURI(Uri.parse(Constants.urls[index]));
                            setAnimation(1,ImgIcon);
                            setAnimation(2,CVInfo);
                            setAnimation(4,ImgIcon2);
                            setAnimation(3,CVInfo2);
                            TXTInfo.setText(String.format(temp,user.getLoginName(),user.getNickName(),user.getUserId(),user.getGender() == 0 ? "女" : "男"));

                        }else{
                            ImgIcon2.setImageURI(Uri.parse(Constants.urls[index]));
                            setAnimation(1,ImgIcon2);
                            setAnimation(2,CVInfo2);
                            setAnimation(4,ImgIcon);
                            setAnimation(3,CVInfo);
                            TXTInfo2.setText(String.format(temp,user.getLoginName(),user.getNickName(),user.getUserId(),user.getGender() == 0 ? "女" : "男"));
                        }

                        isShow = !isShow;

                        mhandler.sendEmptyMessageDelayed(0, 5000);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else{
                    //userId = 1;
                    String temp=getResources().getString(R.string.user_format);
                    int index = new Random().nextInt(19);
                    ImgIconBg.setImageURI(Uri.parse(Constants.urls[index]));

                    if(isShow){
                        ImgIcon.setImageURI(Uri.parse(Constants.urls[index]));
                        setAnimation(1,ImgIcon);
                        setAnimation(2,CVInfo);
                        setAnimation(4,ImgIcon2);
                        setAnimation(3,CVInfo2);
                        TXTInfo.setText(String.format(temp,"1","2",3,"4"));

                    }else{
                        ImgIcon2.setImageURI(Uri.parse(Constants.urls[index]));
                        setAnimation(1,ImgIcon2);
                        setAnimation(2,CVInfo2);
                        setAnimation(4,ImgIcon);
                        setAnimation(3,CVInfo);
                        TXTInfo2.setText(String.format(temp,"1","2",3,"4"));
                    }

                    isShow = !isShow;
                    mhandler.sendEmptyMessageDelayed(0, 5000);
                }
            }

            @Override
            public void onFailure(Call<BaseRequest<UserBean>> call, Throwable t) {
                try{
                    String temp=getResources().getString(R.string.user_format);
                    int index = new Random().nextInt(19);
                    ImgIconBg.setImageURI(Uri.parse(Constants.urls[index]));

                    if(isShow){
                        ImgIcon.setImageURI(Uri.parse(Constants.urls[index]));
                        setAnimation(1,ImgIcon);
                        setAnimation(2,CVInfo);
                        setAnimation(4,ImgIcon2);
                        setAnimation(3,CVInfo2);
                        TXTInfo.setText(String.format(temp,"1","2",3,"4"));

                    }else{
                        ImgIcon2.setImageURI(Uri.parse(Constants.urls[index]));
                        setAnimation(1,ImgIcon2);
                        setAnimation(2,CVInfo2);
                        setAnimation(4,ImgIcon);
                        setAnimation(3,CVInfo);
                        TXTInfo2.setText(String.format(temp,"1","2",3,"4"));
                    }
                    isShow = !isShow;
                    mhandler.sendEmptyMessageDelayed(0, 5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setAnimation(int type,View view){

        switch (type){
            case 1:
                 /*
                AnimationSet相当于一个动画的集合，true表示使用Animation的interpolator
                false则是使用自己的。
                Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果
                accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
             */

                AnimationSet animationSet = new AnimationSet(true);
            /*
                    Animation还有几个方法
                    setFillAfter(boolean fillAfter)
                    如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                    setFillBefore(boolean fillBefore)
                    如果fillBefore的值为真的话，动画结束后，控件停留在动画开始的状态
                    setStartOffset(long startOffset)
                    设置动画控件执行动画之前等待的时间
                    setRepeatCount(int repeatCount)
                    设置动画重复执行的次数
             */
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        //X轴初始位置
                        Animation.RELATIVE_TO_SELF, 3.0f,
                        //X轴移动的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴开始位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴移动后的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f);

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
                        Animation.RELATIVE_TO_SELF,2.5f,
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
            case 3:
                 /*
                AnimationSet相当于一个动画的集合，true表示使用Animation的interpolator
                false则是使用自己的。
                Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果
                accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
             */

                AnimationSet animationSet3 = new AnimationSet(true);
            /*
                    Animation还有几个方法
                    setFillAfter(boolean fillAfter)
                    如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                    setFillBefore(boolean fillBefore)
                    如果fillBefore的值为真的话，动画结束后，控件停留在动画开始的状态
                    setStartOffset(long startOffset)
                    设置动画控件执行动画之前等待的时间
                    setRepeatCount(int repeatCount)
                    设置动画重复执行的次数
             */
                TranslateAnimation translateAnimation3 = new TranslateAnimation(
                        //X轴初始位置
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        //X轴移动的结束位置
                        Animation.RELATIVE_TO_SELF,-3.0f,
                        //y轴开始位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴移动后的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f);

                //3秒完成动画
                translateAnimation3.setDuration(3000);
                //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                animationSet3.setFillAfter(true);
                //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
                animationSet3.addAnimation(translateAnimation3);
                //启动动画
                view.startAnimation(animationSet3);
                break;
            case 4:
                AnimationSet animationSet4 = new AnimationSet(true);
                TranslateAnimation translateAnimation4 = new TranslateAnimation(
                        //X轴初始位置
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        //X轴移动的结束位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴开始位置
                        Animation.RELATIVE_TO_SELF,0.0f,
                        //y轴移动后的结束位置
                        Animation.RELATIVE_TO_SELF,-2.5f);

                //3秒完成动画
                translateAnimation4.setDuration(3000);
                //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
                animationSet4.setFillAfter(true);
                //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
                animationSet4.addAnimation(translateAnimation4);
                //启动动画
                view.startAnimation(animationSet4);
                break;
        }

    }

}
