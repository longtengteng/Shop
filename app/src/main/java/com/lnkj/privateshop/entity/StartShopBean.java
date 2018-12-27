package com.lnkj.privateshop.entity;

import java.util.List;

public class StartShopBean {
    private String info;
    private int status;
    private List<StartShopBean.DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StartShopBean.DataBean> getData() {
        return data;
    }

    public void setData(List<StartShopBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * shop_type_id : 1
         * name : 省级代理
         */

        private int shop_type_id;
        private String name;

        public int getShop_type_id() {
            return shop_type_id;
        }

        public void setShop_type_id(int shop_type_id) {
            this.shop_type_id = shop_type_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
