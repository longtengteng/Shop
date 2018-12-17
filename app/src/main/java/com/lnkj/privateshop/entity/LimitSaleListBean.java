package com.lnkj.privateshop.entity;

import java.util.List;

public class LimitSaleListBean {

    /**
     * data : {"act_img":"/Uploads/Picture/Activity/2017-09-20/59c1d8e3db34d.jpg","end_time":"1505908500","now_time":1505892640,"start_time":"1505843700"}
     * info : 限时特惠
     * status : 1
     */

    private String info;
    private int status;
    private List<LimitSaleListBean.DataBean> data;

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

    public List<LimitSaleListBean.DataBean> getData() {
        return data;
    }

    public void setData(List<LimitSaleListBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * goods_id : 2
         * act_id : 4
         * goods_img : /Uploads/Picture/Goods/2018-12-13/5c1212858627c.jpg
         * goods_name : 阿尔法机器人
         * shop_price : 200.00
         * now_price : 180.00
         * now_time : 1544706819
         * end_time : 1546271999
         */

        private String goods_id;
        private String act_id;
        private String goods_img;
        private String goods_name;
        private String shop_price;
        private String now_price;
        private int now_time;
        private String end_time;

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

        public int getNow_time() {
            return now_time;
        }

        public void setNow_time(int now_time) {
            this.now_time = now_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
