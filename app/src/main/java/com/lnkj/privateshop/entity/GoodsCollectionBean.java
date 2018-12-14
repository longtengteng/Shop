package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GoodsCollectionBean {

    /**
     * status : 1
     * info : 收藏商品列表
     * data : [{"favorite_id":"103","goods_id":"217","goods_name":"纯棉小衫特价，质量好，莫代尔纯棉","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","shop_price":"44.00","pack_price":"34.00"},{"favorite_id":"102","goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","shop_price":"35.00","pack_price":"32.00"}]
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
         * favorite_id : 103
         * goods_id : 217
         * goods_name : 纯棉小衫特价，质量好，莫代尔纯棉
         * goods_img : /Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg
         * shop_price : 44.00
         * pack_price : 34.00
         */

        private String favorite_id;
        private String goods_id;
        private String goods_name;
        private String goods_img;
        private String shop_price;
        private String pack_price;
        private Boolean ischeck;

        public Boolean getIscheck() {
            if (ischeck==null){
                return false;
            }else {
            return ischeck;
            }
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(String favorite_id) {
            this.favorite_id = favorite_id;
        }

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

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
        }
    }
}
