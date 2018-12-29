package com.lnkj.privateshop.entity;

import java.util.List;

public class NewShopHomeBean {
    private int status;
    private String info;
    private List<NewShopHomeBean.DataBean> data;

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

    public List<NewShopHomeBean.DataBean> getData() {
        return data;
    }

    public void setData(List<NewShopHomeBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * goods_id : 3
         * goods_name : 胸部按摩仪
         * shop_price : 1
         * goods_img : /Uploads/Picture/Goods/2018-12-17/5c1709a04c39d.jpg
         */

        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String goods_img;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
}
