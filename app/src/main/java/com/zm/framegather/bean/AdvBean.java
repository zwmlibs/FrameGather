package com.zm.framegather.bean;

import java.io.Serializable;

/**
 * Description 广告信息
 * <p/>
 * Created by 张伟明 on 2016/12/16.
 */
public class AdvBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;//编号
    private String title;//标题
    private String imageUrl;//图片地址
    private String url;//跳转地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
