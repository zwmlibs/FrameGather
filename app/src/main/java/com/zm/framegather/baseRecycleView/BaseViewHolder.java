package com.zm.framegather.baseRecycleView;

/**
 * Description 描述
 * Created by 张伟明 on 2017/4/7.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }

    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setImageResource(int id, int resouceId) {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
    }

}

