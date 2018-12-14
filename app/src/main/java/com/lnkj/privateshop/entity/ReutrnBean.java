package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class ReutrnBean {


    /**
     * status : 1
     * info : 列表
     * data : [{"order_sn":"S201709051348565646","shop_name":"商城自营","refund_state":"1","shop_state":"1","type":"part","goods_list":[{"order_goods_id":"208","order_sn":"S201709051348565646","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","price":"120.00","buy_number":"1"}],"express_amount":0,"real_pay_amount":"120.00","goods_count":1},{"order_sn":"S201709051120132267","shop_name":"商城自营","refund_state":"1","shop_state":"1","type":"all","goods_list":[{"order_goods_id":"207","order_sn":"S201709051120132267","goods_img":"/Uploads/Picture/Goods/1/20170728_101240_15012079609297_4898.jpg","goods_id":"243","goods_name":"休闲长装T仔爆款","price":"28.00","buy_number":"1"},{"order_goods_id":"206","order_sn":"S201709051120132267","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","price":"120.00","buy_number":"1"}],"express_amount":"100.00","real_pay_amount":"248.00","goods_count":2}]
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
         * order_sn : S201709051348565646
         * shop_name : 商城自营
         * refund_state : 1
         * shop_state : 1
         * type : part
         * goods_list : [{"order_goods_id":"208","order_sn":"S201709051348565646","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","price":"120.00","buy_number":"1"}]
         * express_amount : 0
         * real_pay_amount : 120.00
         * goods_count : 1
         */

        private String order_sn;
        private String shop_name;
        private String refund_state;
        private String shop_state;
        private String type;
        private String express_amount;
        private String refund_type;
        private String real_pay_amount;
        private int goods_count;

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        private List<GoodsListBean> goods_list;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public String getShop_state() {
            return shop_state;
        }

        public void setShop_state(String shop_state) {
            this.shop_state = shop_state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExpress_amount() {
            return express_amount;
        }

        public void setExpress_amount(String express_amount) {
            this.express_amount = express_amount;
        }

        public String getReal_pay_amount() {
            return real_pay_amount;
        }

        public void setReal_pay_amount(String real_pay_amount) {
            this.real_pay_amount = real_pay_amount;
        }

        public int getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(int goods_count) {
            this.goods_count = goods_count;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * order_goods_id : 208
             * order_sn : S201709051348565646
             * goods_img : /Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg
             * goods_id : 250
             * goods_name : 休闲男装 2017秋季新款男士大衣 韩版潮翻领中长
             * price : 120.00
             * buy_number : 1
             */

            private String order_goods_id;
            private String order_sn;
            private String goods_img;
            private String goods_id;
            private String goods_name;
            private String price;
            private String buy_number;

            public String getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(String order_goods_id) {
                this.order_goods_id = order_goods_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getBuy_number() {
                return buy_number;
            }

            public void setBuy_number(String buy_number) {
                this.buy_number = buy_number;
            }
        }
    }
}
