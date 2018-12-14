package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class FootPrintBean {


    /**
     * data : [{"_goods_list":[{"goods_id":"204","goods_img":"/Uploads/Picture/Goods/2017-07-08/59608eec66a28.jpg","goods_name":"HLA/海澜之家基础款t恤2017夏季新品圆领舒适透气t恤短袖男","pack_price":"128.00","shop_price":"68.00"}],"date":"08月02日"},{"_goods_list":[{"goods_id":"243","goods_img":"/Uploads/Picture/Goods/1/20170728_101240_15012079609297_4898.jpg","goods_name":"休闲长装T仔爆款","pack_price":"22.00","shop_price":"28.00"},{"pack_price":"0.00","shop_price":"0.00"},{"goods_id":"216","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","pack_price":"35.00","shop_price":"25.00"},{"goods_id":"211","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","goods_name":"爆款套装 五个码 一件代发","pack_price":"0.00","shop_price":"0.00"},{"goods_id":"227","goods_img":"/Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg","goods_name":"8934套装58包邮","pack_price":"57.00","shop_price":"58.00"},{"goods_id":"242","goods_img":"/Uploads/Picture/Goods/2017-07-27/5979b6c6e3858.jpg","goods_name":"气质款针织连衣裙","pack_price":"25.00","shop_price":"27.00"},{"goods_id":"217","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_name":"纯棉小衫特价，质量好，莫代尔纯棉","pack_price":"34.00","shop_price":"44.00"}],"date":"08月01日"},{"_goods_list":[{"goods_id":"225","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","goods_name":"个性8字韩版TTT","pack_price":"32.00","shop_price":"35.00"},{"goods_id":"226","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","pack_price":"74.00","shop_price":"78.00"}],"date":"07月28日"}]
     * info : 浏览记录
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
         * _goods_list : [{"goods_id":"204","goods_img":"/Uploads/Picture/Goods/2017-07-08/59608eec66a28.jpg","goods_name":"HLA/海澜之家基础款t恤2017夏季新品圆领舒适透气t恤短袖男","pack_price":"128.00","shop_price":"68.00"}]
         * date : 08月02日
         */

        private String date;
        private List<GoodsListBean> goods_list;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<GoodsListBean> get_goods_list() {
            return goods_list;
        }

        public void set_goods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 204
             * goods_img : /Uploads/Picture/Goods/2017-07-08/59608eec66a28.jpg
             * goods_name : HLA/海澜之家基础款t恤2017夏季新品圆领舒适透气t恤短袖男
             * pack_price : 128.00
             * shop_price : 68.00
             */

            private String goods_id;
            private String goods_img;
            private String goods_name;
            private String pack_price;
            private String shop_price;
            private Boolean inchenk=false;

            public Boolean getInchenk() {
                return inchenk;
            }

            public void setInchenk(Boolean inchenk) {
                this.inchenk = inchenk;
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

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }
        }
    }
}
