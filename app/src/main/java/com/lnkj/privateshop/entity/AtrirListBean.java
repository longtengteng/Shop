package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class AtrirListBean {

    Boolean ischeck =false;
    String text;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
