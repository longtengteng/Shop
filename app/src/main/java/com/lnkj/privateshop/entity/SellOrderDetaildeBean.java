package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26 0026.
 */

public class SellOrderDetaildeBean {


    /**
     * data : {"add_time":"2017-09-29 16:32:40","address":"山东省临沂市兰山区测试安卓地址","city":"","consignee":"测试安卓","delivery_time":"","district":"","express_amount":"0.00","finish_time":"","order_goods":[{"goods_id":"9","goods_img":"/Uploads/Picture/Goods/108/20170929_155545_15066717455308_9166.jpg","goods_name":"测试安卓商品","order_goods_id":"7","price":"20.00","total_buy_number":"10","total_money":"200.00"}],"order_id":"11","order_sn":"S201709291632402387","order_status":"2","pay_time":"","province":"","real_pay_amount":"200.00","remark":"null","total_amount":"200.00","total_buy_number":"10"}
     * info : 定单数据
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
         * add_time : 2017-09-29 16:32:40
         * address : 山东省临沂市兰山区测试安卓地址
         * city :
         * consignee : 测试安卓
         * delivery_time :
         * district :
         * express_amount : 0.00
         * finish_time :
         * order_goods : [{"goods_id":"9","goods_img":"/Uploads/Picture/Goods/108/20170929_155545_15066717455308_9166.jpg","goods_name":"测试安卓商品","order_goods_id":"7","price":"20.00","total_buy_number":"10","total_money":"200.00"}]
         * order_id : 11
         * order_sn : S201709291632402387
         * order_status : 2
         * pay_time :
         * province :
         * real_pay_amount : 200.00
         * remark : null
         * total_amount : 200.00
         * total_buy_number : 10
         */

        private String add_time;
        private String address;
        private String city;
        private String consignee;
        private String delivery_time;
        private String district;
        private String express_amount;
        private String finish_time;
        private String order_id;
        private String order_sn;
        private String order_status;
        private String pay_time;
        private String province;
        private String real_pay_amount;
        private String remark;
        private String total_amount;
        private String shop_name;
        private String total_buy_number;
        private String mobile;
        private List<OrderGoodsBean> order_goods;
        private Echat echat;

        public Echat getEchat() {
            return echat;
        }

        public void setEchat(Echat echat) {
            this.echat = echat;
        }

        public String getShop_name() {
            return shop_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }


        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getExpress_amount() {
            return express_amount;
        }

        public void setExpress_amount(String express_amount) {
            this.express_amount = express_amount;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
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

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReal_pay_amount() {
            return real_pay_amount;
        }

        public void setReal_pay_amount(String real_pay_amount) {
            this.real_pay_amount = real_pay_amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }
        public static class Echat{
            private String emchat_username;
            private String nickname;
            private String head_pic;

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getEmchat_username() {
                return emchat_username;
            }

            public void setEmchat_username(String emchat_username) {
                this.emchat_username = emchat_username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
        public static class OrderGoodsBean {
            /**
             * goods_id : 9
             * goods_img : /Uploads/Picture/Goods/108/20170929_155545_15066717455308_9166.jpg
             * goods_name : 测试安卓商品
             * order_goods_id : 7
             * price : 20.00
             * total_buy_number : 10
             * total_money : 200.00
             */

            private String goods_id;
            private String goods_img;
            private String goods_name;
            private String order_goods_id;
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

            private List<Spec_list> spec_list;

            public List<Spec_list> getSpec_list() {
                return spec_list;
            }

            public void setSpec_list(List<Spec_list> spec_list) {
                this.spec_list = spec_list;
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

            public String getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(String order_goods_id) {
                this.order_goods_id = order_goods_id;
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
        public static class Spec_list{
            String spec;
            String buy_number;

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
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
