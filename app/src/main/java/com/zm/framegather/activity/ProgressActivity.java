package com.zm.framegather.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.zm.framegather.R;
import com.zm.framegather.view.MyCircle;
import com.zm.framegather.view.MyProgessLine;

public class ProgressActivity extends BaseActivity {

    private MyCircle progress1;
    private MyCircle progress2;
    private MyCircle progress3;
    private MyCircle progress4;
    private MyProgessLine progress5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progress1 = (MyCircle) findViewById(R.id.progress1);
        progress2 = (MyCircle) findViewById(R.id.progress2);
        progress3 = (MyCircle) findViewById(R.id.progress3);
        progress4 = (MyCircle) findViewById(R.id.progress4);
        progress5 = (MyProgessLine) findViewById(R.id.progress5);
        progress1.dodo(50,50);
        progress2.dodo(80,80);
        progress3.dodo(90,90);
        progress4.dodo(30,30);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        progress5.dodo(50,50,bitmap);
    }
}
