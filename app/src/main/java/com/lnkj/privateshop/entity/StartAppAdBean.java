package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class StartAppAdBean {

    /**
     * status : 1
     * info : 启动页广告
     * data : ["/Uploads/Picture/Ad/2017-09-28/59cca3f3c1955.png"]
     */

    private int status;
    private String info;
    private List<String> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
