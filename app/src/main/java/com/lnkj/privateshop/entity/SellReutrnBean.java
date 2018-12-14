package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class SellReutrnBean {


    /**
     * data : [{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"3","goods_id":"216","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","order_goods_id":"187","order_sn":"S201708211744373260","price":"25.00"}],"order_sn":"S201708211744373260","real_pay_amount":"75.00","refund_state":"1","refund_type":"1","shop_name":"商城自营","shop_state":"1","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"2","goods_id":"216","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","order_goods_id":"190","order_sn":"S201708211756149202","price":"25.00"}],"order_sn":"S201708211756149202","real_pay_amount":"50.00","refund_state":"1","refund_type":"1","shop_name":"商城自营","shop_state":"1","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"1","goods_id":"250","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","order_goods_id":"194","order_sn":"S201708301030231599","price":"120.00"}],"order_sn":"S201708301030231599","real_pay_amount":"120.00","refund_state":"2","refund_type":"2","shop_name":"商城自营","shop_state":"10","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"1","goods_id":"250","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","order_goods_id":"195","order_sn":"S201708301030458380","price":"120.00"}],"order_sn":"S201708301030458380","real_pay_amount":"240.00","refund_state":"1","refund_type":"2","shop_name":"商城自营","shop_state":"1","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"1","goods_id":"250","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","order_goods_id":"196","order_sn":"S201708301030458380","price":"120.00"}],"order_sn":"S2017083010304583801","real_pay_amount":"0.00","refund_state":"1","refund_type":"1","shop_name":"商城自营","shop_state":"1","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"2","goods_id":"250","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","order_goods_id":"197","order_sn":"S201708301033213451","price":"120.00"}],"order_sn":"S201708301033213451","real_pay_amount":"240.00","refund_state":"1","refund_type":"1","shop_name":"商城自营","shop_state":"1","type":"all"},{"express_amount":"0.00","goods_count":1,"goods_list":[{"buy_number":"1","goods_id":"242","goods_img":"/Uploads/Picture/Goods/2017-07-27/5979b6c6e3858.jpg","goods_name":"气质款针织连衣裙","order_goods_id":"169","order_sn":"S201708011121599702","price":"27.00"}],"order_sn":"S201708011121599702","real_pay_amount":"27.00","refund_state":"1","refund_type":"1","shop_name":"商城自营","shop_state":"1","type":"all"}]
     * info : 列表
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
         * express_amount : 0.00
         * goods_count : 1
         * goods_list : [{"buy_number":"3","goods_id":"216","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","order_goods_id":"187","order_sn":"S201708211744373260","price":"25.00"}]
         * order_sn : S201708211744373260
         * real_pay_amount : 75.00
         * refund_state : 1
         * refund_type : 1
         * shop_name : 商城自营
         * shop_state : 1
         * type : all
         */

        private String express_amount;
        private int goods_count;
        private String order_sn;
        private String real_pay_amount;
        private String refund_state;
        private String refund_type;
        private String refund_id;
        private String shop_name;
        private String shop_state;
        private String type;
        private List<GoodsListBean> goods_list;

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }

        public String getExpress_amount() {
            return express_amount;
        }

        public void setExpress_amount(String express_amount) {
            this.express_amount = express_amount;
        }

        public int getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(int goods_count) {
            this.goods_count = goods_count;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getReal_pay_amount() {
            return real_pay_amount;
        }

        public void setReal_pay_amount(String real_pay_amount) {
            this.real_pay_amount = real_pay_amount;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
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

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * buy_number : 3
             * goods_id : 216
             * goods_img : /Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg
             * goods_name : 莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究
             * order_goods_id : 187
             * order_sn : S201708211744373260
             * price : 25.00
             */

            private String buy_number;
            private String goods_id;
            private String goods_img;
            private String goods_name;
            private String order_goods_id;
            private String order_sn;
            private String price;

            public String getBuy_number() {
                return buy_number;
            }

            public void setBuy_number(String buy_number) {
                this.buy_number = buy_number;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
