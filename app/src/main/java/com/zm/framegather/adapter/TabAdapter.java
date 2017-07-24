package com.zm.framegather.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zm.framegather.fragment.TabFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/7.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private int size = 0;

    public TabAdapter(Context context, FragmentManager fm, int size) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.size = size;
        fragments = new ArrayList<>();
        for(int i=0;i<size;i++){
            TabFragment listItemFragment = new TabFragment();
            fragments.add(listItemFragment);
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