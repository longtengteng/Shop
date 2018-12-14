package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class AlterGoodsCountBean {

    /**
     * status : 1
     * info : 商品信息
     * data : {"goods_info":{"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","pack_price":"39.00","shop_price":"39.00","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","shop_id":"1","is_favorite":0,"basic_amount":"9","retail_amount":"1","allow_mixture":"0"},"goods_spec":[{"spec_name":"如图 XL","store_count":"111","spec_key":"31_14","has_number":0},{"spec_name":"如图 2XL","store_count":"111","spec_key":"31_25","has_number":"1"},{"spec_name":"白色 XL","store_count":"111","spec_key":"15_14","has_number":0},{"spec_name":"白色 2XL","store_count":"111","spec_key":"15_25","has_number":0}]}
     */

    private int status;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_info : {"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","pack_price":"39.00","shop_price":"39.00","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","shop_id":"1","is_favorite":0,"basic_amount":"9","retail_amount":"1","allow_mixture":"0"}
         * goods_spec : [{"spec_name":"如图 XL","store_count":"111","spec_key":"31_14","has_number":0},{"spec_name":"如图 2XL","store_count":"111","spec_key":"31_25","has_number":"1"},{"spec_name":"白色 XL","store_count":"111","spec_key":"15_14","has_number":0},{"spec_name":"白色 2XL","store_count":"111","spec_key":"15_25","has_number":0}]
         */

        private GoodsInfoBean goods_info;
        private List<GoodsSpecBean> goods_spec;

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public List<GoodsSpecBean> getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(List<GoodsSpecBean> goods_spec) {
            this.goods_spec = goods_spec;
        }

        public static class GoodsInfoBean {
            /**
             * goods_id : 254
             * goods_name : 朵咪家。实拍笑脸印花宽松长袖卫衣
             * pack_price : 39.00
             * shop_price : 39.00
             * goods_img : /Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg
             * shop_id : 1
             * is_favorite : 0
             * basic_amount : 9
             * retail_amount : 1
             * allow_mixture : 0
             */

            private String goods_id;
            private String goods_name;
            private String pack_price;
            private String shop_price;
            private String goods_img;
            private String shop_id;
            private int is_favorite;
            private String basic_amount;
            private String retail_amount;
            private String allow_mixture;

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

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public String getBasic_amount() {
                return basic_amount;
            }

            public void setBasic_amount(String basic_amount) {
                this.basic_amount = basic_amount;
            }

            public String getRetail_amount() {
                return retail_amount;
            }

            public void setRetail_amount(String retail_amount) {
                this.retail_amount = retail_amount;
            }

            public String getAllow_mixture() {
                return allow_mixture;
            }

            public void setAllow_mixture(String allow_mixture) {
                this.allow_mixture = allow_mixture;
            }
        }

        public static class GoodsSpecBean {
            /**
             * spec_name : 如图 XL
             * store_count : 111
             * spec_key : 31_14
             * has_number : 0
             */

            private String spec_name;
            private String store_count;
            private String spec_key;
            private int has_number;

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public int getHas_number() {
                return has_number;
            }

            public void setHas_number(int has_number) {
                this.has_number = has_number;
            }
        }
    }
}
