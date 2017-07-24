package com.zm.framegather.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zm.framegather.R;
import com.zm.framegather.adapter.ListItemAdapter;
import com.zm.framegather.baseRecycleView.BaseRecycleAdapter;
import com.zm.framegather.bean.UserBean;
import com.zm.framegather.view.DividerItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    private Context context;
    private View view;
    private RecyclerView listTabData;
    private ListItemAdapter adapter;
    private ArrayList<UserBean> data;
    private ArrayList<UserBean> temp;

    private LinearLayoutManager lm;

    private int tag;

    public TabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_list_tab,container, false);
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
        listTabData = (RecyclerView) view.findViewById(R.id.listTabData);
    }

    private void initData(){

        data = new ArrayList<>();
        temp = new ArrayList<>();
        for(int i=0;i<30;i++){
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

        listTabData.setAdapter(adapter);
        lm = new LinearLayoutManager(context);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        listTabData.setLayoutManager(lm);
        listTabData.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));




    }
}
