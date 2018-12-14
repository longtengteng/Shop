package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ShopClassDerBean {

    /**
     * status : 1
     * info :
     * data : [{"goods_id":"211","goods_name":"爆款套装 五个码 一件代发","shop_id":"1","shop_name":"商城自营","pack_price":"1","shop_price":"1","storage":"33","sale_num":"0","comment_num":"0","collect_num":"1","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","click_count":"99","cat_id":"154","goods_description":"","add_time":"1499841044"},{"goods_id":"210","goods_name":"赵吉平的测试","shop_id":"1","shop_name":"商城自营","pack_price":"1","shop_price":"1","storage":"1604","sale_num":"0","comment_num":"0","collect_num":"1","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","click_count":"18","cat_id":"154","goods_description":"","add_time":"1499839572"},{"goods_id":"209","goods_name":"赵吉平","shop_id":"1","shop_name":"商城自营","pack_price":"0","shop_price":"0","storage":"0","sale_num":"6","comment_num":"0","collect_num":"0","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","click_count":"3","cat_id":"154","goods_description":"","add_time":"1499839367"}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_id : 211
         * goods_name : 爆款套装 五个码 一件代发
         * shop_id : 1
         * shop_name : 商城自营
         * pack_price : 1
         * shop_price : 1
         * storage : 33
         * sale_num : 0
         * comment_num : 0
         * collect_num : 1
         * goods_img : /Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg
         * click_count : 99
         * cat_id : 154
         * goods_description :
         * add_time : 1499841044
         */

        private String goods_id;
        private String goods_name;
        private String shop_id;
        private String shop_name;
        private String pack_price;
        private String shop_price;
        private String storage;
        private String sale_num;
        private String comment_num;
        private String collect_num;
        private String goods_img;
        private String click_count;
        private String cat_id;
        private String goods_description;
        private String add_time;

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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getStorage() {
            return storage;
        }

        public void setStorage(String storage) {
            this.storage = storage;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getGoods_description() {
            return goods_description;
        }

        public void setGoods_description(String goods_description) {
            this.goods_description = goods_description;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
