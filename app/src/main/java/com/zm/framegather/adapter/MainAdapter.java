package com.zm.framegather.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zm.framegather.fragment.HomeFragment;
import com.zm.framegather.fragment.ListFragment;
import com.zm.framegather.fragment.UserFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/7.
 */
public class MainAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private final int size = 3;

    public MainAdapter(Context context,  FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
        fragments = new ArrayList<>();
        Fragment tempFragment;

        for(int i=0;i<size;i++){
            switch (i){
                case 0:
                    tempFragment = fm.findFragmentByTag("HomeFragment");
                    HomeFragment homeFragment;
                    if(tempFragment == null){
                        //初始化首页
                        homeFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("index",1);
                        homeFragment.setArguments(bundle);
                        fragments.add(homeFragment);
                    }else{
                        fragments.add(tempFragment);
                    }
                    break;
                case 1:
                    tempFragment = fm.findFragmentByTag("listFragment");
                    ListFragment listFragment;
                    if(tempFragment == null){
                        //初始化列表页
                        listFragment = new ListFragment();
                        fragments.add(listFragment);
                    }else{
                        fragments.add(tempFragment);
                    }
                    break;
                case 2:
                    tempFragment = fm.findFragmentByTag("UserFragment");
                    UserFragment userFragment;
                    if(tempFragment == null){
                        //初始化用户页
                        userFragment = new UserFragment();
                        fragments.add(userFragment);
                    }else{
                        fragments.add(tempFragment);
                    }

                    break;
            }
        }
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub

        if(null != fragments.get(arg0)){
            return fragments.get(arg0);
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return size;
    }

}