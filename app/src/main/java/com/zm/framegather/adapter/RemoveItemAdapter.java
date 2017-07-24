package com.zm.framegather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zm.framegather.R;

import java.util.ArrayList;

public class RemoveItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private boolean isShow = true;

    public RemoveItemAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_layout, parent, false));
        }else{
            return new FootViewHolder(mInflater.inflate(R.layout.view_load_more, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.content.setText(mList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : isShow == true ? mList.size() + 1 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount() && isShow) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView delete;
        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            content = (TextView) itemView.findViewById(R.id.item_content);
            delete = (TextView) itemView.findViewById(R.id.item_delete);
            layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }

    public void showFootView(boolean isShow){
        this.isShow = isShow;
        notifyDataSetChanged();
    }

}
