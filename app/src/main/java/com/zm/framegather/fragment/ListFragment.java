package com.zm.framegather.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zm.framegather.R;
import com.zm.framegather.adapter.ListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private Context context;
    private View view;
    private TextView TXTTitle;

    private TabLayout tabLayout;
    private ViewPager pager;
    private ListAdapter adapter;

    private String[] titles = {"列表1","列表2","列表3","列表4","列表5","列表6","列表7","列表8","列表9","列表10","列表11","列表12"};

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_list,container, false);
            initViews(view);
            initData();
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    //内存泄漏问题。如果 Fragment 持有宿主 Activity 的引用，会导致宿主 Activity 无法回收，
    // 造成内存泄漏。所以，如果可以的话，尽量不要在 Fragment 中持有宿主 Activity 的引用。
    //为了解决 Context 上下文引用的问题，Fragment 提供了一个 onAttach(context) 方法，
    // 在此方法中我们可以获取到 Context 对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initViews(View view){

        TXTTitle = (TextView) view.findViewById(R.id.TXTTitle);
        TXTTitle.setText("列表");
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        pager = (ViewPager) view.findViewById(R.id.pagerList);
        pager.setOnPageChangeListener(this);

    }

    private void initData(){
        //Fragment 里面继续嵌套二级甚至三级 Fragment，即 Activity 嵌套多级 Fragment。
        // 此时在 Fragment 里管理子 Fragment 时，也需要使用到 FragmentManager。
        // 但是一定要使用 getChildFragmentManager() 方法获取 FragmentManager 对象！
        adapter = new ListAdapter(context,getChildFragmentManager(),titles.length);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        //设置可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        pager.setCurrentItem(1);
        pager.setCurrentItem(0);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_list, new LinearLayout(getActivity()),false);
        TextView txt_title = (TextView) view.findViewById(R.id.TXTTitle);
        txt_title.setText(titles[position]);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnGetInfo:

                break;
        }
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
