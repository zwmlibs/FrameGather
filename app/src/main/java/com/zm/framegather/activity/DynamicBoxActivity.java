package com.zm.framegather.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zm.framegather.R;
import com.zm.framegather.view.DynamicBox;

public class DynamicBoxActivity extends BaseActivity implements View.OnClickListener {

    private DynamicBox box;
    private LinearLayout LLContent;
    private SimpleDraweeView ImgGifPic;
    private SimpleDraweeView ImgGifPic2;
    private SimpleDraweeView ImgGifPic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_box);
        initViews();
        initData();
    }

    private void initViews(){
        LLContent = (LinearLayout) findViewById(R.id.LLContent);
        ImgGifPic = (SimpleDraweeView) findViewById(R.id.ImgGifPic);
        ImgGifPic2 = (SimpleDraweeView) findViewById(R.id.ImgGifPic2);
        ImgGifPic3 = (SimpleDraweeView) findViewById(R.id.ImgGifPic3);
        findViewById(R.id.BtnLoading).setOnClickListener(this);
        findViewById(R.id.BtnLoading2).setOnClickListener(this);
        findViewById(R.id.BtnLoading3).setOnClickListener(this);
        findViewById(R.id.BtnLoading4).setOnClickListener(this);
    }

    private void initData(){
        box = new DynamicBox(this,LLContent);
        box.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DynamicBoxActivity.this,"重新加载",Toast.LENGTH_SHORT).show();
            }
        });

        box.showLoadingLayout();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnLoading:
                box.showLoadingLayout();
                break;
            case R.id.BtnLoading2:
                box.showExceptionLayout();
                break;
            case R.id.BtnLoading3:
                box.showInternetOffLayout();
                break;
            case R.id.BtnLoading4:
                box.hideAll();
                loadGif();
                loadGif2();
                loadGif3();
                break;
        }
    }

    private void loadGif(){
        Uri uri = Uri.parse("http://img01.static.yohobuy.com/cms/2015/05/17/14/013714da63471394722365c9d24226590f.gif");
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        ImgGifPic.setController(draweeController);
    }

    private void loadGif2(){
        Uri uri = Uri.parse("http://img3.imgtn.bdimg.com/it/u=2749190246,3857616763&fm=23&gp=0.jpg");
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        ImgGifPic2.setController(draweeController);
    }

    private void loadGif3(){
        Uri uri = Uri.parse("http://att.bbs.duowan.com/forum/201511/30/191256u433s1lwsyesxtwa.gif");
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        ImgGifPic3.setController(draweeController);
    }

}
