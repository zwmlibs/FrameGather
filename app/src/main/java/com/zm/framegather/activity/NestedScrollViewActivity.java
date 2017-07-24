package com.zm.framegather.activity;

import android.os.Bundle;

import com.zm.framegather.R;
import com.zm.framegather.fragment.TabFragment;

/**
 * NestedScrollView简单地说就是支持嵌套滑动的ScrollView, 内部逻辑简单, 而且它既可以是内控件,
 * 也可以是外控件,所以选择分析它来了解嵌套滑动机制.注意 : 因为NestedScrollingChildHelper和
 * NestedScrollingParent这两个辅助类的实现跟View和ViewGroup中的对应方法是一样的,而且View和
 * ViewGroup的源码没有使用兼容类, 所以下面分析相关方法的时候源码都使用View和ViewGroup中的代码.
 * 上面已经说了嵌套滑动是从startNestedScroll开始,所以先看看哪里调用了这个方法, 在源码里一搜就
 * 能知道有两个地方调用了这个方法.onInterceptTouchEvent中ACTION_DOWN的情况;onTouchEvent中A
 * CTION_DOWN的情况.因为ACTION_DOWN是滑动操作的开始事件, 所以当接收到这个事件的时候尝试找对应
 * 的外控件. 只有找到了外控件才有后续的嵌套滑动的逻辑发生
 */

public class NestedScrollViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);

        TabFragment itemFragment = new TabFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.adsFragment, itemFragment).commit();

    }
}
