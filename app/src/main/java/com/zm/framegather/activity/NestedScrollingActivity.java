package com.zm.framegather.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zm.framegather.R;
import com.zm.framegather.adapter.TabAdapter;
import com.zm.framegather.view.CountView;

public class NestedScrollingActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private String[] mTitles = new String[] { "简介", "评价", "相关" };
    private CountView count;
    private TabLayout tabLayout;
    private ViewPager pager;
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling);
        initViews();
        initData();
    }

    private void initViews(){
        count = (CountView) findViewById(R.id.count);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        pager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        pager.setOnPageChangeListener(this);
    }

    private void initData(){

        count.showNumberWithAnimation(836975);

        adapter = new TabAdapter(this,getSupportFragmentManager(),mTitles.length);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        pager.setCurrentItem(1);
        pager.setCurrentItem(0);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_list, new LinearLayout(this),false);
        TextView txt_title = (TextView) view.findViewById(R.id.TXTTitle);
        txt_title.setText(mTitles[position]);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
