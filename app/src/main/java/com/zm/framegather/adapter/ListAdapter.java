package com.zm.framegather.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zm.framegather.fragment.ListItemFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/7.
 */
public class ListAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private int size = 0;

    public ListAdapter(Context context, FragmentManager fm,int size) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.size = size;
        fragments = new ArrayList<>();
        for(int i=0;i<size;i++){
            ListItemFragment listItemFragment = new ListItemFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag", i);
            listItemFragment.setArguments(bundle);
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