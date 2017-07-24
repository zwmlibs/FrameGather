package com.zm.framegather.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.zm.framegather.R;
import com.zm.framegather.adapter.GoodAdapter;
import com.zm.framegather.bean.UserBean;
import com.zm.framegather.listener.HolderClickListener;
import com.zm.framegather.util.AnimUtils;
import com.zm.framegather.util.Constants;
import com.zm.framegather.util.ShoppingAnimUtil;
import com.zm.framegather.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CurveActivity extends BaseActivity {

    private SuperRecyclerView listTestData;
    private ImageView cart_btn;
    private TextView count;
    private GoodAdapter goodAdapter;

    private LinearLayoutManager lm;
    private ArrayList<UserBean> userBeans;

    private FrameLayout animation_viewGroup;
    private int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve);
        listTestData = (SuperRecyclerView)this.findViewById(R.id.listTestData);
        cart_btn = (ImageView)this.findViewById(R.id.button);
        count = (TextView)this.findViewById(R.id.count);


        userBeans = new ArrayList<>();
        for(int i=0;i<10;i++){
            UserBean userBean = new UserBean();
            userBean.setHeadImg(Constants.urls[i]);
            userBeans.add(userBean);
        }

        animation_viewGroup = createAnimLayout();

        goodAdapter = new GoodAdapter(this,userBeans,new HolderClickListener(){

            @Override
            public void onHolderClick(Drawable drawable, int[] start_location) {
                // TODO Auto-generated method stub
                ShoppingAnimUtil.getInstance().doAnim(CurveActivity.this,animation_viewGroup,cart_btn,drawable,start_location);
                size ++;
                count.setText("" + size);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AnimUtils.beginScaleCart(CurveActivity.this,cart_btn);
                            }
                        });
                    }
                },900);


            }

        });


        listTestData.setAdapter(goodAdapter);
        lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        listTestData.setLayoutManager(lm);
        listTestData.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    public FrameLayout createAnimLayout(){
        ViewGroup rootView = (ViewGroup)this.getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;

    }

    /**
     * 内存过低时及时处理动画产生的未处理冗余
     */
    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        ShoppingAnimUtil.getInstance().onLowMemory();
        super.onLowMemory();
    }

}
