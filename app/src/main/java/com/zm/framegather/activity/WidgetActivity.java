package com.zm.framegather.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.zm.framegather.R;
import com.zm.framegather.util.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

public class WidgetActivity extends BaseActivity {

    private SimpleDraweeView ImgIcon;
    private SimpleDraweeView ImgIcon2;
    private TextView TXTCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        initViews();
        initData();
    }

    private void initViews(){
        ImgIcon = (SimpleDraweeView) findViewById(R.id.ImgIcon);
        ImgIcon2 = (SimpleDraweeView) findViewById(R.id.ImgIcon2);
        TXTCacheSize = (TextView) findViewById(R.id.TXTCacheSize);

        findViewById(R.id.BtnClearCache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCaches();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StringUtils.showSnackBar(WidgetActivity.this,TXTCacheSize,"缓存已清除");
                                TXTCacheSize.setText("缓存大小：" + convertFileSize(getCacheSize()));
                            }
                        });
                    }
                },2000);

            }
        });
    }

    private void initData(){
        ImgIcon.setImageURI(Uri.parse("http://scs.ganjistatic1.com/gjfs11/M0B/06/13/CgEHDFTU6w7MiUymAAC2o6puSTA843_600-0_6-0.jpg"));
        ImgIcon2.setImageURI(Uri.parse("http://img3.redocn.com/tupian/20150806/weimeisheyingtupian_472.jpg"));

        TXTCacheSize.setText("缓存大小：" + convertFileSize(getCacheSize()));
    }


    //获取图片缓存大小
    private long getCacheSize(){
        long cacheSize = Fresco.getImagePipelineFactory().getMainDiskStorageCache().getSize();
        return cacheSize;
    }

    //清除缓存图片
    private void clearCaches(){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }

    //格式化缓存大小
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if(size <= 0){
            return "0MB";
        }
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

}
