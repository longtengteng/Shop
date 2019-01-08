package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class HomeGoodsListbean {

    /**
     * status : 1
     * info : 商品列表
     * data : [{"goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","shop_price":"35.00"},{"goods_id":"226","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","shop_price":"78.00"},{"goods_id":"217","goods_name":"纯棉小衫特价，质量好，莫代尔纯棉","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","shop_price":"44.00"},{"goods_id":"216","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","shop_price":"25.00"},{"goods_id":"211","goods_name":"爆款套装 五个码 一件代发","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","shop_price":"0.00"},{"goods_id":"210","goods_name":"赵吉平的测试","goods_img":"","shop_price":"0.01"},{"goods_id":"207","goods_name":"新款韩版气质半身裙","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","shop_price":"0.00"}]
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
         * goods_id : 225
         * goods_name : 个性8字韩版TTT
         * goods_img : /Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg
         * shop_price : 35.00
         */

        private String goods_id;
        private String goods_name;
        private String goods_img;
        private String shop_price;
        private String pack_price;
        private String from_shop_id;

        public String getFrom_shop_id() {
            return from_shop_id;
        }

        public void setFrom_shop_id(String from_shop_id) {
            this.from_shop_id = from_shop_id;
        }

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
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
    }
}
