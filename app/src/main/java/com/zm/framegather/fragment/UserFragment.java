package com.zm.framegather.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zm.framegather.R;
import com.zm.framegather.activity.AdvertisementActivity;
import com.zm.framegather.activity.AnimationActivity;
import com.zm.framegather.activity.CoordinatorActivity;
import com.zm.framegather.activity.CurveActivity;
import com.zm.framegather.activity.DynamicBoxActivity;
import com.zm.framegather.activity.NestedScrollViewActivity;
import com.zm.framegather.activity.NestedScrollingActivity;
import com.zm.framegather.activity.ProgressActivity;
import com.zm.framegather.activity.RemoveItemActivity;
import com.zm.framegather.activity.SuperTextViewActivity;
import com.zm.framegather.activity.WidgetActivity;
import com.zm.framegather.util.ToastUtil;
import com.zm.framegather.view.SelectDatePop;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private View view;
    private TextView TXTTitle;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_user,container, false);
            initViews(view);
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
        TXTTitle.setText("我的");

        view.findViewById(R.id.BtnRemoveItem).setOnClickListener(this);
        view.findViewById(R.id.BtnNestedScrolling).setOnClickListener(this);
        view.findViewById(R.id.BtnNestedScrollView).setOnClickListener(this);
        view.findViewById(R.id.BtnCoordinator).setOnClickListener(this);
        view.findViewById(R.id.BtnDynamicBox).setOnClickListener(this);
        view.findViewById(R.id.BtnWidget).setOnClickListener(this);
        view.findViewById(R.id.BtnAdvertisement).setOnClickListener(this);
        view.findViewById(R.id.BtnAnimation).setOnClickListener(this);
        view.findViewById(R.id.BtnCurve).setOnClickListener(this);
        view.findViewById(R.id.BtnProgress).setOnClickListener(this);
        view.findViewById(R.id.BtnSuperTextView).setOnClickListener(this);
        view.findViewById(R.id.BtnTime).setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnRemoveItem:
                startActivity(new Intent(context, RemoveItemActivity.class));
                break;
            case R.id.BtnNestedScrolling:
                startActivity(new Intent(context, NestedScrollingActivity.class));
                break;
            case R.id.BtnNestedScrollView:
                startActivity(new Intent(context, NestedScrollViewActivity.class));
                break;
            case R.id.BtnCoordinator:
                startActivity(new Intent(context, CoordinatorActivity.class));
                break;
            case R.id.BtnDynamicBox:
                startActivity(new Intent(context, DynamicBoxActivity.class));
                break;
            case R.id.BtnWidget:
                startActivity(new Intent(context, WidgetActivity.class));
                break;
            case R.id.BtnAdvertisement:
                startActivity(new Intent(context, AdvertisementActivity.class));
                break;
            case R.id.BtnAnimation:
                startActivity(new Intent(context, AnimationActivity.class));
                break;
            case R.id.BtnCurve:
                startActivity(new Intent(context, CurveActivity.class));
                break;
            case R.id.BtnProgress:
                startActivity(new Intent(context, ProgressActivity.class));
                break;
            case R.id.BtnSuperTextView:
                startActivity(new Intent(context, SuperTextViewActivity.class));
                break;
            case R.id.BtnTime:
                SelectDatePop dp = SelectDatePop.getInstance(getActivity());
                dp.setListener(new SelectDatePop.OnDatePopClickListener() {
                    @Override
                    public void onClick(SelectDatePop dp, String date) {
                        if (!TextUtils.isEmpty(date)) {
                            String[] split = date.split("/");
                            String birthday = split[0] + "-" + split[1] + "-" + split[2];
                            ToastUtil.showShort(getActivity(),"时间：" + birthday);
                            dp.dismiss();
                        } else {
                            ToastUtil.showShort(getActivity(),"请选择正确的时间！");
                            return;
                        }
                    }
                });
                dp.show();
                break;
        }
    }
}
