package com.zm.framegather.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zm.framegather.R;
import com.zm.framegather.adapter.TabAdapter;

//   博客地址：http://blog.csdn.net/baidu_31093133/article/details/52807465   （内有详细介绍）
public class CoordinatorActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout toolbar_tab;
    private TabAdapter adapter;
    private String[] mTitles = new String[] { "简介", "评价", "相关" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        initViews();
        initData();
    }

    private void initViews() {

        //mViewPager = (ViewPager) findViewById(R.id.viewpager);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"协调布局",Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                            }
                        })
                        .show();
            }
        });
    }


    private void initData() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("HD TEST");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);

//        toolbar_tab = (TabLayout) findViewById(R.id.tabs);
//
//        //动态生成TabLayout子条目
//        toolbar_tab.removeAllTabs();
//        toolbar_tab.addTab(toolbar_tab.newTab().setText(mTitles[0]), true);
//        toolbar_tab.addTab(toolbar_tab.newTab().setText(mTitles[1]));
//        toolbar_tab.addTab(toolbar_tab.newTab().setText(mTitles[2]));
//        toolbar_tab.setTabGravity(TabLayout.GRAVITY_FILL);
//        toolbar_tab.setTabMode(TabLayout.MODE_FIXED);
//
//
//
//        adapter = new TabAdapter(this,getSupportFragmentManager(),mTitles.length);
//        mViewPager.setAdapter(adapter);
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(toolbar_tab));
//        toolbar_tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
