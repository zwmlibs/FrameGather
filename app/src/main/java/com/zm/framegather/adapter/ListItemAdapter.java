package com.zm.framegather.adapter;

import android.content.Context;

import com.zm.framegather.R;
import com.zm.framegather.baseRecycleView.BaseBean;
import com.zm.framegather.baseRecycleView.BaseRecycleAdapter;
import com.zm.framegather.baseRecycleView.BaseViewHolder;
import com.zm.framegather.bean.UserBean;

import java.util.List;


/**
 * Created by Administrator on 2015/10/29.
 */
public class ListItemAdapter extends BaseRecycleAdapter {

//    private Context context;
//    private ArrayList<UserBean> data;
//    private MyItemClickListener listener;

    public ListItemAdapter(Context context, List<UserBean> list) {
        super(context, R.layout.list_view_item_1, list);
    }


    @Override
    protected <T extends BaseBean> void convert(BaseViewHolder holder, T bean) {
        UserBean userBean = (UserBean) bean;
        holder.setText(R.id.TXTName, "姓名：" + userBean.getNickName());
        holder.setText(R.id.TXTGender, "编号：" + userBean.getGender());
    }

//    public ListItemAdapter(Context context, ArrayList<UserBean> data, MyItemClickListener listener) {
//        this.context = context;
//        this.data = data;
//        this.listener = listener;
//    }
//
//    public void refresh(ArrayList<UserBean> data){
//        this.data = data;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if (holder instanceof ItemViewHolder) {
//            final UserBean userBean = data.get(position);
//            ((ItemViewHolder) holder).TXTName.setText("姓名：" + userBean.getNickName());
//            ((ItemViewHolder) holder).TXTGender.setText("编号：" + userBean.getGender() + "");
//        }
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(
//                R.layout.list_view_item_1, null);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v);
//            }
//        });
//        return new ItemViewHolder(view);
//    }
//
//    class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        TextView TXTName;
//        TextView TXTGender;
//
//        public ItemViewHolder(View view) {
//            super(view);
//            TXTName = (TextView) view.findViewById(R.id.TXTName);
//            TXTGender = (TextView) view.findViewById(R.id.TXTGender);
//        }
//    }

}
