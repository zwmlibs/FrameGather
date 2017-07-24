package com.zm.framegather.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.view.ViewHelper;
import com.zm.framegather.R;
import com.zm.framegather.adapter.MainAdapter;
import com.zm.framegather.dialog.LoginDialogFragment;

/**
 *
 * @ClassName: MainActivity
 * @Description: 导航页  使用TabLayout搭配ViewPager实现首页导航栏
 * @author 张伟明
 * @date 2016年11月23日
 *
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener,LoginDialogFragment.LoginInputListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private TabLayout tabLayout;
    private ViewPager pager;
    private MainAdapter adapter;

    private String[] titles = {"首页","列表","我的"};
    private int[] icons = {R.drawable.main_home_selector,R.drawable.main_list_selector,R.drawable.main_user_selector};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化Fresco
        Fresco.initialize(this);
        initViews();
        initData();
    }

    private void initViews(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOnPageChangeListener(this);
    }

    private void initData(){
          //侧滑菜单覆盖主布局
//        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,null,R.string.app_name,R.string.app_name);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerContent(mNavigationView);
        //侧滑菜单不覆盖主布局
        mDrawerLayout.setDrawerListener(listen);

        adapter = new MainAdapter(this,getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        pager.setCurrentItem(1);
        pager.setCurrentItem(0);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_main, new LinearLayout(this),false);
        TextView txt_title = (TextView) view.findViewById(R.id.TXTTitle);
        ImageView ImgIcon = (ImageView) view.findViewById(R.id.ImgIcon);
        txt_title.setText(titles[position]);
        ImgIcon.setImageResource(icons[position]);
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

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_item_1:
                                //shortToast("条目1被选择");
                                LoginDialogFragment dialog = new LoginDialogFragment();
                                dialog.show(getSupportFragmentManager(), "loginDialog");
                                break;
                            case R.id.navigation_item_2:
                                shortToast("条目2被选择");
                                break;
                            case R.id.navigation_item_3:
                                shortToast("条目3被选择");
                                break;
                            case R.id.navigation_item_4:
                                shortToast("条目4被选择");
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    DrawerLayout.DrawerListener listen = new DrawerLayout.DrawerListener() {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            //设置主布局随侧滑菜单一起移动
            View mContent = mDrawerLayout.getChildAt(0);
            View mMenu = drawerView;
            float scale = 1 + slideOffset;
            //改变DrawLayout侧栏透明度，若不需要效果可以不设置
//            ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
            ViewHelper.setTranslationX(mContent,
                    mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
            ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
            mContent.invalidate();
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    //如果不需要滑动关闭，则重写 enableSliding 并返回 false 。
    protected boolean enableSliding() {
        return false;
    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
                Toast.LENGTH_SHORT).show();
    }
}
