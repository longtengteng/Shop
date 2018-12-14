package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public class FollowBean {

    /**
     * status : 1
     * info : 关注店铺商品
     * data : [{"goods_id":"203","goods_name":"433443","pack_price":"0","shop_price":"0","goods_img":""}]
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
         * goods_id : 203
         * goods_name : 433443
         * pack_price : 0
         * shop_price : 0
         * goods_img :
         */

        private String goods_id;
        private String goods_name;
        private String pack_price;
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

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
}
