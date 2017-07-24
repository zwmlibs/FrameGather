package com.zm.framegather.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zm.framegather.R;
import com.zm.framegather.wheel.OnWheelChangedListener;
import com.zm.framegather.wheel.WheelView;
import com.zm.framegather.wheel.adapters.ArrayWheelAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 张伟明 on 2017/5/27
 * 日期选择期
 */
public class SelectDatePop implements OnWheelChangedListener {
    private static SelectDatePop mInstance;
    private OnDatePopClickListener listener;
    private final int DAY_28 = 28;
    private final int DAY_30 = 30;
    private final int DAY_29 = 20;
    private final int DAY_31 = 31;
    private Activity mContext;
    private PopupWindow pop;
    private String[] days;
    private int year, month, day;
    private WheelView mWheelViewYear;
    private WheelView mWheelViewMonth;
    private WheelView mWheelViewDay;
    private String[] years;
    private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12"};

    private SelectDatePop(Activity context) {
        this.mContext = context;
        init();
    }

    public static SelectDatePop getInstance(Activity context) {
        return mInstance == null ? new SelectDatePop(context) : mInstance;
    }

    private void init() {
        View view = initView(getView());

        createPop(view);
    }

    public void show() {
        if (!pop.isShowing()) {
            pop.showAtLocation(mContext.findViewById(R.id.activity_main),
                    Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        }
    }

    public void dismiss() {
        if (pop.isShowing()) {
            pop.dismiss();
        }
    }

    private View getView() {
        return LayoutInflater.from(mContext).inflate(R.layout.select_birthday_pop, null);
    }

    private View initView(View view) {
        int Year = getDate("yyyy");
        int toYear = Year + 50;
        years = new String[50];

        int j = 0;
        for (int i = Year; i < toYear; i++) {
            years[j] = i + "";
            j++;
        }


        mWheelViewYear = (WheelView) view
                .findViewById(R.id.date_pop_wheelview_year);
        mWheelViewMonth = (WheelView) view
                .findViewById(R.id.date_pop_wheelview_month);
        mWheelViewDay = (WheelView) view
                .findViewById(R.id.date_pop_wheelview_day);
        mWheelViewYear.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
                years));
        mWheelViewMonth.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
                months));
        mWheelViewDay.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
                getDays(DAY_30)));
        mWheelViewYear.setAlpha((float) 1.0);
        mWheelViewMonth.setAlpha((float) 1.0);
        mWheelViewDay.setAlpha((float) 1.0);
        mWheelViewYear.setVisibleItems(7);
        mWheelViewMonth.setVisibleItems(7);
        mWheelViewDay.setVisibleItems(7);

        mWheelViewYear.addChangingListener(this);
        mWheelViewMonth.addChangingListener(this);
        mWheelViewDay.addChangingListener(this);
        mWheelViewYear.setCurrentItem(0);
        mWheelViewMonth.setCurrentItem(getDate("MM") - 1);
        mWheelViewDay.setCurrentItem(getDate("dd") - 1);
        view.findViewById(R.id.date_pop_textview_cancel).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
        view.findViewById(R.id.date_pop_textview_confirm).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            //listener.onClick(SelectDatePop.this, compareWithTime(getDate()));
                            listener.onClick(SelectDatePop.this, compareWithTime(getDate()));
                        }
                    }
                });

        return view;
    }

    /**
     * 把选择的时间和当前时间作比较
     *
     * @param date
     * @return
     */
    private String compareWithTime(String date) {
        //选择的时间
        String selectTimes = years[year] + "-" + months[month] + "-" + days[day];
        //系统当前的时间
        String nowTimes = getDate("yyyy") + "-" + getDate("MM") + "-" + getDate("dd");

        int returnNum = compare_date(selectTimes, nowTimes);
        Log.i("test", returnNum + "---returnNum---");
        if (1 == returnNum || 0 == returnNum) {
            return date;
        }
        return null;
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private void createPop(View view) {
        // 创建PopupWindow对象
        pop = new PopupWindow(view);
        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        //
        pop.update();
        //实例化ColorDrawable对象
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        pop.setBackgroundDrawable(dw);
        // 设置动画
        pop.setAnimationStyle(R.style.SelectDateStyle);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);
        // 设置背景颜色变暗
//        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        mContext.getWindow().setAttributes(lp);
//        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
//                lp.alpha = 1f;
//                mContext.getWindow().setAttributes(lp);
//            }
//        });
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        switch (wheel.getId()) {
            case R.id.date_pop_wheelview_day:
                day = newValue;
                break;
            case R.id.date_pop_wheelview_month:
                month = newValue;
                updateDays(newValue);
                mWheelViewDay.setViewAdapter(new ArrayWheelAdapter<String>(
                        mContext, days));
                if (day > days.length - 1) {
                    mWheelViewDay.setCurrentItem(days.length - 1);
                } else {
                    mWheelViewDay.setCurrentItem(day);
                }
                break;
            case R.id.date_pop_wheelview_year:
                year = newValue;
                Log.i("test", month + "---------month---------");
                updateDays(month);
                mWheelViewDay.setViewAdapter(new ArrayWheelAdapter<String>(
                        mContext, days));
                if (day > days.length - 1) {
                    mWheelViewDay.setCurrentItem(days.length - 1);
                } else {
                    mWheelViewDay.setCurrentItem(day);
                }
                break;
        }
    }

    /**
     * 方法说明：根据WheelView中的月份来判断当月的天数
     *
     * @param position
     */
    private void updateDays(int position) {
        days = null;
        switch (position) {
            case 1:
                // 2月
                int yyyy = Integer.parseInt(years[year]);
                Log.i("test", yyyy + "---------yyyy---------");
                if (yyyy % 4 == 0) {
                    // 判断是否是闰年
                    days = getDays(DAY_29);
                } else {
                    days = getDays(DAY_28);
                }
                break;
            case 3:
                days = getDays(DAY_30);
                break;
            case 5:
                days = getDays(DAY_30);
                break;
            case 8:
                days = getDays(DAY_30);
                break;
            case 10:
                days = getDays(DAY_30);
                break;
            default:
                days = getDays(DAY_31);
                break;
        }
    }


    /**
     * 方法说明：月份的天数类型，根据不同的月份获取当前月份的不同天数
     *
     * @param dayType
     */
    private String[] getDays(int dayType) {
        String[] days = null;
        switch (dayType) {
            case DAY_28:
                days = new String[]{"01", "02", "03", "04", "05", "06", "07",
                        "08", "09", "10", "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20", "21", "22", "23",
                        "24", "25", "26", "27", "28"};
                break;
            case DAY_29:
                days = new String[]{"01", "02", "03", "04", "05", "06", "07",
                        "08", "09", "10", "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20", "21", "22", "23",
                        "24", "25", "26", "27", "28", "29"};
                break;
            case DAY_30:
                days = new String[]{"01", "02", "03", "04", "05", "06", "07",
                        "08", "09", "10", "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20", "21", "22", "23",
                        "24", "25", "26", "27", "28", "29", "30"};
                break;
            case DAY_31:
                days = new String[]{"01", "02", "03", "04", "05", "06", "07",
                        "08", "09", "10", "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20", "21", "22", "23",
                        "24", "25", "26", "27", "28", "29", "30", "31"};
                break;
        }
        return days;
    }

    private String getDate() {
        return years[year] + "/" + months[month] + "/" + days[day];
    }

    /**
     * 方法说明：根据不同的时间格式，获取不同的时间
     *
     * @param format 时间格式
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private int getDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return Integer
                .parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    /**
     * 方法说明：设置确定、取消按钮的监听器
     *
     * @param listener
     */
    public void setListener(OnDatePopClickListener listener) {
        this.listener = listener;
    }

    /**
     * 一个简单的回调监听
     */
    public interface OnDatePopClickListener {
        void onClick(SelectDatePop dp, String date);
    }
}