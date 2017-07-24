package com.zm.framegather.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zm.framegather.R;
import com.zm.framegather.bean.AdvBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private View view;
    private ViewPager viewpaer;
    private TextView TXTTitle;
    private LinearLayout viewGroup;
    private ImageView ImgRedPoint;

    private ArrayList<ImageView> imageViewList;
    private ArrayList<AdvBean> advs;
    private int mPointDis;   // 小红点移动距离

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    viewpaer.setCurrentItem(viewpaer.getCurrentItem() + 1);
                    mHandler.sendEmptyMessageDelayed(0, 3000);// 继续发送延时3秒的消息,形成内循环
                    break;
            }
        }
    };

    public AdvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_adv,container, false);
            initViews(view);
            initData();
            initGroup();
            initAdapter();
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void initViews(View view) {
        //初始化控件
        viewpaer = (ViewPager) view.findViewById(R.id.viewpaer);
        TXTTitle = (TextView) view.findViewById(R.id.TXTTitle);
        viewGroup = (LinearLayout) view.findViewById(R.id.viewGroup);
        ImgRedPoint = (ImageView) view.findViewById(R.id.ImgRedPoint);
    }

    private void initData(){
        advs = (ArrayList<AdvBean>) getArguments().getSerializable("advData");


        // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
        // 视图树
        ImgRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 移除监听,避免重复回调
                ImgRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // layout方法执行结束的回调
                mPointDis = viewGroup.getChildAt(1).getLeft() - viewGroup.getChildAt(0).getLeft();
            }
        });

        viewpaer.setOnPageChangeListener(this);// 设置页面更新监听

        // 保证启动自动轮播逻辑只执行一次
        mHandler.sendEmptyMessageDelayed(0, 3000);// 发送延时3秒的消息

        viewpaer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //移除自动滑动事件
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_CANCEL:// 取消事件,
                        // 当按下viewpager后,直接滑动listview,导致抬起事件无法响应,但会走此事件
                        // 启动广告
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case MotionEvent.ACTION_UP:
                        // 启动广告
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });



    }

    private void initGroup(){
        imageViewList = new ArrayList<ImageView>();

        SimpleDraweeView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < advs.size(); i++) {
            // 初始化要显示的图片对象
            imageView = new SimpleDraweeView(getActivity());
            imageView.setImageURI(Uri.parse(advs.get(i).getImageUrl()));
            final String url = advs.get(i).getUrl();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),url,Toast.LENGTH_SHORT).show();
                }
            });
            imageViewList.add(imageView);
            // 加小白点, 指示器
            pointView = new View(getActivity());
            pointView.setBackgroundResource(R.mipmap.dot_unselect);
            float density = getResources().getDisplayMetrics().density;
            int a = (int) (density * 9 + 0.5);
            layoutParams = new LinearLayout.LayoutParams(a, a);
            if (i != 0)
                layoutParams.leftMargin = 15;
            // 设置默认所有都不可用
            viewGroup.addView(pointView, layoutParams);
        }
    }

    private void initAdapter() {
        TXTTitle.setText(advs.get(0).getTitle());
        // 设置适配器
        viewpaer.setAdapter(new AdvAdapter());
        // 默认设置到中间的某个位置
        //viewpaer.setCurrentItem(5000000); // 设置到某个位置
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    class AdvAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % imageViewList.size();
            ImageView imageView = imageViewList.get(newPosition);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 更新小红点距离
        int newPosition = position % imageViewList.size();
        if (newPosition!=imageViewList.size()-1) {
            int leftMargin = (int) (mPointDis * positionOffset) + newPosition * mPointDis;// 计算小红点当前的左边距
            RelativeLayout.LayoutParams paramsx = (RelativeLayout.LayoutParams) ImgRedPoint.getLayoutParams();
            paramsx.leftMargin = leftMargin;// 修改左边距
            // 重新设置布局参数
            ImgRedPoint.setLayoutParams(paramsx);
        }
    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
        int newPosition = position % imageViewList.size();
        //设置文本
        TXTTitle.setText(advs.get(newPosition).getTitle());

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ImgRedPoint.getLayoutParams();
        params.leftMargin = mPointDis * newPosition;// 修改左边距
        // 重新设置布局参数
        ImgRedPoint.setLayoutParams(params);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
