package com.zm.framegather.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.zm.framegather.R;
import com.zm.framegather.adapter.ListItemAdapter;
import com.zm.framegather.baseRecycleView.BaseRecycleAdapter;
import com.zm.framegather.bean.UserBean;
import com.zm.framegather.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SuperRecyclerView listTestData;
    private ListItemAdapter adapter;
    private ArrayList<UserBean> data;
    private ArrayList<UserBean> temp;

    private LinearLayoutManager lm;
    private GridLayoutManager gm;

    private int pageNoStr = 1;  //页码
    private boolean isEnd;      //是否已加载完
    private boolean isLoading;  //是否正在加载中
    private boolean needClear;  //是否清空
    private boolean isFirstIn = true;  //是否第一次进

    private int tag;

    public ListItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_list_item,container, false);
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
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        listTestData = (SuperRecyclerView) view.findViewById(R.id.listTestData);
    }

    private void initData(){
        tag = getArguments().getInt("tag", 0) % 3;

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.base_color);

        data = new ArrayList<>();
        temp = new ArrayList<>();
        for(int i=0;i<10;i++){
            UserBean userBean = new UserBean();
            userBean.setNickName("用户" + i);
            userBean.setGender(i);
            temp.add(userBean);
            data.add(userBean);
        }
        adapter = new ListItemAdapter(context, data);
        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Toast.makeText(context,data.get(position).getNickName() + "被点击",Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListner(new BaseRecycleAdapter.OnItemLongClickListner() {
            @Override
            public void onItemLongClickListner(View v, int position) {
                Toast.makeText(context,"长单击事件回调"+position,Toast.LENGTH_SHORT).show();
            }
        });


        switch (tag){
            case 0:
                listTestData.setAdapter(adapter);
                gm = new GridLayoutManager(context,3);
                listTestData.setLayoutManager(gm);
                listTestData.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

                break;

            case 1:
                listTestData.setAdapter(adapter);
                lm = new LinearLayoutManager(context);
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                listTestData.setLayoutManager(lm);
                listTestData.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

                break;

            case 2:
                listTestData.setAdapter(adapter);
                gm = new GridLayoutManager(context,3);
                listTestData.setLayoutManager(gm);
                listTestData.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

                break;
        }

        listTestData.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                //上拉加载更多
                if (!isEnd && !isLoading) {
                    needClear = false;
                    pageNoStr ++;



                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    data.addAll(temp);
                                    adapter.notifyDataSetChanged();
                                    listTestData.hideMoreProgress();
                                }
                            });
                        }
                    },2000);
                }


            }
        },1);

    }

    @Override
    public void onRefresh() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        data.addAll(temp);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        },2000);
    }
}
