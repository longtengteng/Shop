package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class WhoLesaleBean {


    /**
     * data : [{"basic_amount":"10","goods_id":"16","goods_img":"/Uploads/Picture/Goods/117/20171007_130604_15073527644665_5726.jpg","goods_name":"测试安卓商品1007","pack_price":"10.00","shop_id":"117","shop_price":"20.00"},{"basic_amount":"14","goods_id":"15","goods_img":"/Uploads/Picture/Goods/107/20171007_111752_15073462720740_5605.jpg","goods_name":"1212","pack_price":"10.00","shop_id":"107","shop_price":"10.00"}]
     * info : 定制商品列表
     * status : 1
     */

    private String info;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * basic_amount : 10
         * goods_id : 16
         * goods_img : /Uploads/Picture/Goods/117/20171007_130604_15073527644665_5726.jpg
         * goods_name : 测试安卓商品1007
         * pack_price : 10.00
         * shop_id : 117
         * shop_price : 20.00
         */

        private String basic_amount;
        private String goods_id;
        private String goods_img;
        private String goods_name;
        private String pack_price;
        private String shop_id;
        private String shop_price;

        public String getBasic_amount() {
            return basic_amount;
        }

        public void setBasic_amount(String basic_amount) {
            this.basic_amount = basic_amount;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }
    }
}
