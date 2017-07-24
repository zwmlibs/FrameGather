package com.zm.framegather.bean;

import com.zm.framegather.baseRecycleView.BaseBean;

import java.io.Serializable;

/**
 * Description 用户信息
 * <p/>
 * Created by 张伟明 on 2016/4/5.
 */
public class UserBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;//用户编号
    private String nickName;//昵称
    private String loginName;//用户名
    private String headImg;//头像
    private int gender;//性别 1男，2女 ,3保密
    private String birthday;//生日

    public void setLoginName(String loginName) {

        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /*0629新加*/
    private String school;//学校
    private String address;//地址

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
