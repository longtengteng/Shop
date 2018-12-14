package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OrderAllBean implements Serializable {


    /**
     * data : {"order_count":{"status_0":2,"status_1":2,"status_2":0,"status_3":0,"status_4":0},"order_list":[{"add_time":"2017-09-06 09:09:39","express_amount":"100.00","goods_info":[{"goods_id":"210","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","goods_name":"赵吉平的测试","price":"0.01","total_buy_number":"9","total_money":"0.09"}],"is_comment":"0","is_evaluation":"0","order_id":"340","order_sn":"S201709060909390871","order_status":"1","pay_status":"0","real_pay_amount":"100.09","shop_id":"1","shop_name":"商城自营","total_amount":"0.09","total_buy_number":"9"},{"add_time":"2017-09-07 23:26:33","express_amount":"300.00","goods_info":[{"goods_id":"210","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","goods_name":"赵吉平的测试","price":"0.01","total_buy_number":"22","total_money":"0.22"}],"is_comment":"0","is_evaluation":"0","order_id":"352","order_sn":"S201709072326335066","order_status":"1","pay_status":"0","real_pay_amount":"300.22","shop_id":"1","shop_name":"商城自营","total_amount":"0.22","total_buy_number":"22"}]}
     * info : 订单列表
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
        /**
         * order_count : {"status_0":2,"status_1":2,"status_2":0,"status_3":0,"status_4":0}
         * order_list : [{"add_time":"2017-09-06 09:09:39","express_amount":"100.00","goods_info":[{"goods_id":"210","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","goods_name":"赵吉平的测试","price":"0.01","total_buy_number":"9","total_money":"0.09"}],"is_comment":"0","is_evaluation":"0","order_id":"340","order_sn":"S201709060909390871","order_status":"1","pay_status":"0","real_pay_amount":"100.09","shop_id":"1","shop_name":"商城自营","total_amount":"0.09","total_buy_number":"9"},{"add_time":"2017-09-07 23:26:33","express_amount":"300.00","goods_info":[{"goods_id":"210","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","goods_name":"赵吉平的测试","price":"0.01","total_buy_number":"22","total_money":"0.22"}],"is_comment":"0","is_evaluation":"0","order_id":"352","order_sn":"S201709072326335066","order_status":"1","pay_status":"0","real_pay_amount":"300.22","shop_id":"1","shop_name":"商城自营","total_amount":"0.22","total_buy_number":"22"}]
         */

        private OrderCountBean order_count;
        private List<OrderListBean> order_list;

        public OrderCountBean getOrder_count() {
            return order_count;
        }

        public void setOrder_count(OrderCountBean order_count) {
            this.order_count = order_count;
        }

        public List<OrderListBean> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListBean> order_list) {
            this.order_list = order_list;
        }

        public static class OrderCountBean {
            /**
             * status_0 : 2
             * status_1 : 2
             * status_2 : 0
             * status_3 : 0
             * status_4 : 0
             */

            private int status_0;
            private int status_1;
            private int status_2;
            private int status_3;
            private int status_4;

            public int getStatus_0() {
                return status_0;
            }

            public void setStatus_0(int status_0) {
                this.status_0 = status_0;
            }

            public int getStatus_1() {
                return status_1;
            }

            public void setStatus_1(int status_1) {
                this.status_1 = status_1;
            }

            public int getStatus_2() {
                return status_2;
            }

            public void setStatus_2(int status_2) {
                this.status_2 = status_2;
            }

            public int getStatus_3() {
                return status_3;
            }

            public void setStatus_3(int status_3) {
                this.status_3 = status_3;
            }

            public int getStatus_4() {
                return status_4;
            }

            public void setStatus_4(int status_4) {
                this.status_4 = status_4;
            }
        }

        public static class OrderListBean {
            /**
             * add_time : 2017-09-06 09:09:39
             * express_amount : 100.00
             * goods_info : [{"goods_id":"210","goods_img":"/Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg","goods_name":"赵吉平的测试","price":"0.01","total_buy_number":"9","total_money":"0.09"}]
             * is_comment : 0
             * is_evaluation : 0
             * order_id : 340
             * order_sn : S201709060909390871
             * order_status : 1
             * pay_status : 0
             * real_pay_amount : 100.09
             * shop_id : 1
             * shop_name : 商城自营
             * total_amount : 0.09
             * total_buy_number : 9
             */

            private String add_time;
            private String express_amount;
            private String is_comment;
            private String is_evaluation;
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

            public String getIs_evaluation() {
                return is_evaluation;
            }

            public void setIs_evaluation(String is_evaluation) {
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
                 * goods_id : 210
                 * goods_img : /Uploads/Picture/Goods/75/20170816_175700_15028774204454_5762.jpg
                 * goods_name : 赵吉平的测试
                 * price : 0.01
                 * total_buy_number : 9
                 * total_money : 0.09
                 */

                private String goods_id;
                private String goods_img;
                private String goods_name;
                private String price;
                private String total_buy_number;
                private String total_money;
                private String order_return_status;
                private int refund_state;

                public int getRefund_state() {
                    return refund_state;
                }

                public void setRefund_state(int refund_state) {
                    this.refund_state = refund_state;
                }

                public String getOrder_return_status() {
                    return order_return_status;
                }

                public void setOrder_return_status(String order_return_status) {
                    this.order_return_status = order_return_status;
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

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
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
