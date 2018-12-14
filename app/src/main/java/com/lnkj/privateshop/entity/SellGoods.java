package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class SellGoods {

    /**
     * status : 1
     * info : 商品列表信息
     * data : {"goods_count":{"on_count":"12","off_count":"1"},"goods_list":[{"goods_id":"242","goods_name":"气质款针织连衣裙","goods_img":"/Uploads/Picture/Goods/2017-07-27/5979b6c6e3858.jpg","pack_price":"25.00","shop_price":"27.00","click_count":"3","sale_num":"0"},{"goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","pack_price":"32.00","shop_price":"35.00","click_count":"18","sale_num":"0"}]}
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
         * goods_count : {"on_count":"12","off_count":"1"}
         * goods_list : [{"goods_id":"242","goods_name":"气质款针织连衣裙","goods_img":"/Uploads/Picture/Goods/2017-07-27/5979b6c6e3858.jpg","pack_price":"25.00","shop_price":"27.00","click_count":"3","sale_num":"0"},{"goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","pack_price":"32.00","shop_price":"35.00","click_count":"18","sale_num":"0"}]
         */

        private GoodsCountBean goods_count;
        private List<GoodsListBean> goods_list;

        public GoodsCountBean getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(GoodsCountBean goods_count) {
            this.goods_count = goods_count;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsCountBean {
            /**
             * on_count : 12
             * off_count : 1
             */

            private String on_count;
            private String off_count;

            public String getOn_count() {
                return on_count;
            }

            public void setOn_count(String on_count) {
                this.on_count = on_count;
            }

            public String getOff_count() {
                return off_count;
            }

            public void setOff_count(String off_count) {
                this.off_count = off_count;
            }
        }

        public static class GoodsListBean {
            /**
             * goods_id : 242
             * goods_name : 气质款针织连衣裙
             * goods_img : /Uploads/Picture/Goods/2017-07-27/5979b6c6e3858.jpg
             * pack_price : 25.00
             * shop_price : 27.00
             * click_count : 3
             * sale_num : 0
             */

            private String goods_id;
            private String goods_name;
            private String goods_img;
            private String pack_price;
            private String shop_price;
            private String click_count;
            private String sale_num;
            private Boolean ischecked = false;

            public Boolean getIschecked() {
                return ischecked;
            }

            public void setIschecked(Boolean ischecked) {
                this.ischecked = ischecked;
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

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getSale_num() {
                return sale_num;
            }

            public void setSale_num(String sale_num) {
                this.sale_num = sale_num;
            }
        }
    }
}
