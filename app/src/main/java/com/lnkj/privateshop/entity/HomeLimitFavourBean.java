package com.lnkj.privateshop.entity;

import java.util.List;

public class HomeLimitFavourBean {

    /**
     * goods_id : 37
     * act_id : 2
     * goods_img : /Uploads/Picture/Goods/12/20171107_082150_15100141107433_7091.jpg
     * goods_name : 欧式长裙女装
     * shop_price : 2.00
     * now_price : 1.80
     * now_time : 1544077418
     * end_time : 1546271999
     */
    private int status;
    private String info;
    private List<HomeLimitFavourBean.DataBean> data;

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

    public List<HomeLimitFavourBean.DataBean> getData() {
        return data;
    }

    public void setData(List<HomeLimitFavourBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String goods_id;
        private String act_id;
        private String goods_img;
        private String goods_name;
        private String shop_price;
        private String now_price;
        private long now_time;
        private long end_time;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getAct_id() {
            return act_id;
        }

        public void setAct_id(String act_id) {
            this.act_id = act_id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getNow_price() {
            return now_price;
        }

        public void setNow_price(String now_price) {
            this.now_price = now_price;
        }

        public long getNow_time() {
            return now_time;
        }

        public void setNow_time(long now_time) {
            this.now_time = now_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }
    }

}
