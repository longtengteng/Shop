package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class SeachGoodsBean {

    /**
     * data : [{"goods_id":"217","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_name":"纯棉小衫特价，质量好，莫代尔纯棉","pack_price":"34.00","sale_num":"0","shop_price":"44.00"},{"goods_id":"211","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","goods_name":"爆款套装 五个码 一件代发","pack_price":"0.00","sale_num":"0","shop_price":"0.00"},{"goods_id":"207","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_name":"新款韩版气质半身裙","pack_price":"0.00","sale_num":"0","shop_price":"0.00"},{"goods_id":"219","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_name":"雪纺吊带裙配白色T，套装！！！","pack_price":"53.00","sale_num":"5","shop_price":"55.00"},{"goods_id":"218","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_name":"雪纺吊带裙配白色T，套装！！！","pack_price":"53.00","sale_num":"5","shop_price":"55.00"},{"goods_id":"220","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_name":"雪纺吊带裙配白色T，套装！！！","pack_price":"53.00","sale_num":"5","shop_price":"55.00"},{"goods_id":"222","goods_img":"/Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg","goods_name":"大码女装 衬衫连衣裙","pack_price":"49.00","sale_num":"3","shop_price":"51.00"},{"goods_id":"221","goods_img":"/Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg","goods_name":"大码女装 衬衫连衣裙","pack_price":"49.00","sale_num":"3","shop_price":"51.00"},{"goods_id":"223","goods_img":"/Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg","goods_name":"大码女装 衬衫连衣裙","pack_price":"49.00","sale_num":"3","shop_price":"51.00"},{"goods_id":"203","goods_img":"","goods_name":"433443","pack_price":"0.00","sale_num":"0","shop_price":"0.00"}]
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
         * goods_id : 217
         * goods_img : /Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg
         * goods_name : 纯棉小衫特价，质量好，莫代尔纯棉
         * pack_price : 34.00
         * sale_num : 0
         * shop_price : 44.00
         */

        private String goods_id;
        private String goods_img;
        private String goods_name;
        private String pack_price;
        private String sale_num;
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

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }
    }
}
