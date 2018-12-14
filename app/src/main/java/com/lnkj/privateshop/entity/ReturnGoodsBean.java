package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ReturnGoodsBean {

    /**
     * status : 1
     * info : 退货原因
     * data : ["缺货、发货慢","瑕疵品，质量差","发错货，漏发货","拍错了，不想买","其他"]
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
