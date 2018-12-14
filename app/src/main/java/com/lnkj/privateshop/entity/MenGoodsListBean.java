package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class MenGoodsListBean {

    /**
     * status : 1
     * info : 列表
     * data : [{"goods_id":"259","goods_name":"新款高腰开叉牛仔半身短裙子弹力紧身中长","shop_name":"六牛科技自营","shop_id":"1","goods_img":"/Uploads/Picture/Goods/1/20170901_161350_15042536309202_1646.jpg","user_price":"1"},{"goods_id":"252","goods_name":"tttt","shop_name":"tyyy","shop_id":"87","goods_img":"","user_price":"23"}]
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
         * goods_id : 259
         * goods_name : 新款高腰开叉牛仔半身短裙子弹力紧身中长
         * shop_name : 六牛科技自营
         * shop_id : 1
         * goods_img : /Uploads/Picture/Goods/1/20170901_161350_15042536309202_1646.jpg
         * user_price : 1
         */

        private String goods_id;
        private String goods_name;
        private String shop_name;
        private String shop_id;
        private String goods_img;
        private String user_price;

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

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getUser_price() {
            return user_price;
        }

        public void setUser_price(String user_price) {
            this.user_price = user_price;
        }
    }
}
