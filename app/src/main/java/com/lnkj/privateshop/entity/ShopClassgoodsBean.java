package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ShopClassgoodsBean {

    /**
     * data : [{"goods_id":"4","goods_img":"/Uploads/Picture/Goods/1/20170928_141545_15065793454166_6586.jpg","goods_name":"2017秋冬新款韩版套头纯色V领修身打底毛衣短款长","pack_price":"15.00","shop_price":"17.00"}]
     * info : 商品列表
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
         * goods_id : 4
         * goods_img : /Uploads/Picture/Goods/1/20170928_141545_15065793454166_6586.jpg
         * goods_name : 2017秋冬新款韩版套头纯色V领修身打底毛衣短款长
         * pack_price : 15.00
         * shop_price : 17.00
         */

        private String goods_id;
        private String goods_img;
        private String goods_name;
        private String pack_price;
        private String shop_price;

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

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }
    }
}
