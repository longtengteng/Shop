package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * 作者：赵林 on 2017/10/31 0031.
 */

public class SeachOrderBean {

    /**
     * data : {"order_list":[{"add_time":"2017-10-06 20:29:51","express_amount":"0.00","goods_info":[{"goods_id":"5","goods_img":"/Uploads/Picture/Goods/1/20170928_141813_15065794937146_5352.jpg","goods_name":"原宿BF风老虎印花长袖圆领加厚卫衣 610","order_return_status":"0","price":"62.00","refund_state":0,"total_buy_number":"1","total_money":"62.00"}],"is_comment":"0","is_evaluation":0,"order_id":"59","order_sn":"S201710062029512631","order_status":"2","pay_status":"1","real_pay_amount":"62.00","shop_id":"1","shop_name":"六牛科技自营","total_amount":"62.00","total_buy_number":"1"}]}
     * info : 订单信息
     * status : 1
     */

    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        private List<OrderListBean> order_list;

        public List<OrderListBean> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListBean> order_list) {
            this.order_list = order_list;
        }

        public static class OrderListBean {
            /**
             * add_time : 2017-10-06 20:29:51
             * express_amount : 0.00
             * goods_info : [{"goods_id":"5","goods_img":"/Uploads/Picture/Goods/1/20170928_141813_15065794937146_5352.jpg","goods_name":"原宿BF风老虎印花长袖圆领加厚卫衣 610","order_return_status":"0","price":"62.00","refund_state":0,"total_buy_number":"1","total_money":"62.00"}]
             * is_comment : 0
             * is_evaluation : 0
             * order_id : 59
             * order_sn : S201710062029512631
             * order_status : 2
             * pay_status : 1
             * real_pay_amount : 62.00
             * shop_id : 1
             * shop_name : 六牛科技自营
             * total_amount : 62.00
             * total_buy_number : 1
             */

            private String add_time;
            private String express_amount;
            private String is_comment;
            private int is_evaluation;
            private String order_id;
            private String order_sn;
            private String order_status;
            private String pay_status;
            private String real_pay_amount;
            private String shop_id;
            private String shop_name;
            private String total_amount;
            private String total_buy_number;
            private List<GoodsInfoBean> goods_info;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getExpress_amount() {
                return express_amount;
            }

            public void setExpress_amount(String express_amount) {
                this.express_amount = express_amount;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public int getIs_evaluation() {
                return is_evaluation;
            }

            public void setIs_evaluation(int is_evaluation) {
                this.is_evaluation = is_evaluation;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getReal_pay_amount() {
                return real_pay_amount;
            }

            public void setReal_pay_amount(String real_pay_amount) {
                this.real_pay_amount = real_pay_amount;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getTotal_buy_number() {
                return total_buy_number;
            }

            public void setTotal_buy_number(String total_buy_number) {
                this.total_buy_number = total_buy_number;
            }

            public List<GoodsInfoBean> getGoods_info() {
                return goods_info;
            }

            public void setGoods_info(List<GoodsInfoBean> goods_info) {
                this.goods_info = goods_info;
            }

            public static class GoodsInfoBean {
                /**
                 * goods_id : 5
                 * goods_img : /Uploads/Picture/Goods/1/20170928_141813_15065794937146_5352.jpg
                 * goods_name : 原宿BF风老虎印花长袖圆领加厚卫衣 610
                 * order_return_status : 0
                 * price : 62.00
                 * refund_state : 0
                 * total_buy_number : 1
                 * total_money : 62.00
                 */

                private String goods_id;
                private String goods_img;
                private String goods_name;
                private String order_return_status;
                private String price;
                private int refund_state;
                private String total_buy_number;
                private String total_money;

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

                public String getOrder_return_status() {
                    return order_return_status;
                }

                public void setOrder_return_status(String order_return_status) {
                    this.order_return_status = order_return_status;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getRefund_state() {
                    return refund_state;
                }

                public void setRefund_state(int refund_state) {
                    this.refund_state = refund_state;
                }

                public String getTotal_buy_number() {
                    return total_buy_number;
                }

                public void setTotal_buy_number(String total_buy_number) {
                    this.total_buy_number = total_buy_number;
                }

                public String getTotal_money() {
                    return total_money;
                }

                public void setTotal_money(String total_money) {
                    this.total_money = total_money;
                }
            }
        }
    }
}
