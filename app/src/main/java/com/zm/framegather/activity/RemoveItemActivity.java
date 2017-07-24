package com.zm.framegather.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zm.framegather.R;
import com.zm.framegather.adapter.RemoveItemAdapter;
import com.zm.framegather.listener.EndlessRecyclerOnScrollListener;
import com.zm.framegather.listener.OnItemClickListener;
import com.zm.framegather.view.ItemRemoveRecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//侧滑删除
public class RemoveItemActivity extends BaseActivity {

    private ItemRemoveRecyclerView recyclerView;
    private ArrayList<String> mList;
    private RemoveItemAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
        initViews();
        initData();
    }

    private void initViews(){
        recyclerView = (ItemRemoveRecyclerView) findViewById(R.id.id_item_remove_recyclerview);
    }

    private void initData(){
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add("左滑删除条目：" + (i + 1));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RemoveItemAdapter(this, mList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position < 0) return;
                Toast.makeText(RemoveItemActivity.this, "** " + mList.get(position) + " **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
                handler.sendEmptyMessage(EndlessRecyclerOnScrollListener.REMOVE_ITEM);
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if(mList.size() > 19){
                    adapter.showFootView(false);
                    return;
                }

                loadMore();

            }

            @Override
            public void setHandler(Handler handler) {
                RemoveItemActivity.this.handler = handler;
            }
        });

    }

    private void loadMore(){
        adapter.showFootView(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mList.add("动态增加条目：" + (mList.size() + 1));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        },1000);
    }

}
