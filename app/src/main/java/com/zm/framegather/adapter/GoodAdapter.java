package com.zm.framegather.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zm.framegather.R;
import com.zm.framegather.bean.UserBean;
import com.zm.framegather.listener.HolderClickListener;
import com.zm.framegather.view.ScaleButton;

import java.util.ArrayList;

/**
 * 商品列表Adapter
 * @author antkingwei
 *
 */
public class GoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<UserBean> data;
    private HolderClickListener mHolderClickListener;

    public GoodAdapter(Context context, ArrayList<UserBean> data, HolderClickListener mHolderClickListener) {
        this.context = context;
        this.data = data;
        this.mHolderClickListener = mHolderClickListener;
    }

    public void refresh(ArrayList<UserBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final UserBean userBean = data.get(position);
            ((ItemViewHolder) holder).imgview.setImageURI(Uri.parse(userBean.getHeadImg()));
            ((ItemViewHolder) holder).button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(mHolderClickListener!=null){
                        int[] start_location = new int[2];
                        ((ItemViewHolder) holder).imgview.getLocationInWindow(start_location);//获取点击商品图片的位置
                        Drawable drawable = ((ItemViewHolder) holder).imgview.getDrawable();//复制一个新的商品图标
                        mHolderClickListener.onHolderClick(drawable,start_location);
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_listview, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imgview;
        ScaleButton button;

        public ItemViewHolder(View view) {
            super(view);
            imgview = (SimpleDraweeView) view.findViewById(R.id.item_img);
            button = (ScaleButton) view.findViewById(R.id.item_button);
        }
    }

}