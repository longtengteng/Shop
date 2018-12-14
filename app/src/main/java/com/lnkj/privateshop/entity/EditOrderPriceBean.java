package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * 创建时间： 2017/9/22 0022.
 * 创建人：    赵林
 * 功能描述：
 */

public class EditOrderPriceBean {

    /**
     * status : 1
     * info : 订单信息
     * data : {"order_id":"373","shop_id":"1","order_sn":"S201709210823460178","pay_status":"0","order_status":"1","consignee":"诸葛俊娴","province":"山东省","city":"临沂市","district":"兰山区","address":"山东省 临沂市 河东区 芝麻墩街道 山东省临沂市河东区冠亚星城3期","express_amount":"0.00","total_amount":"158.00","real_pay_amount":"158.00","add_time":"2017-09-21","order_goods":[{"order_goods_id":"247","goods_id":"265","goods_name":"曲珠料中袖外套，气质大牌","goods_img":"","price":"79.00","total_buy_number":"2","total_money":"158.00"}]}
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
         * order_id : 373
         * shop_id : 1
         * order_sn : S201709210823460178
         * pay_status : 0
         * order_status : 1
         * consignee : 诸葛俊娴
         * province : 山东省
         * city : 临沂市
         * district : 兰山区
         * address : 山东省 临沂市 河东区 芝麻墩街道 山东省临沂市河东区冠亚星城3期
         * express_amount : 0.00
         * total_amount : 158.00
         * real_pay_amount : 158.00
         * add_time : 2017-09-21
         * order_goods : [{"order_goods_id":"247","goods_id":"265","goods_name":"曲珠料中袖外套，气质大牌","goods_img":"","price":"79.00","total_buy_number":"2","total_money":"158.00"}]
         */

        private String order_id;
        private String shop_id;
        private String order_sn;
        private String pay_status;
        private String order_status;
        private String consignee;
        private String province;
        private String city;
        private String district;
        private String address;
        private String express_amount;
        private String total_amount;
        private String real_pay_amount;
        private String add_time;
        private List<OrderGoodsBean> order_goods;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExpress_amount() {
            return express_amount;
        }

        public void setExpress_amount(String express_amount) {
            this.express_amount = express_amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getReal_pay_amount() {
            return real_pay_amount;
        }

        public void setReal_pay_amount(String real_pay_amount) {
            this.real_pay_amount = real_pay_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class OrderGoodsBean {
            /**
             * order_goods_id : 247
             * goods_id : 265
             * goods_name : 曲珠料中袖外套，气质大牌
             * goods_img :
             * price : 79.00
             * total_buy_number : 2
             * total_money : 158.00
             */
            private String after_money;
            private String order_goods_id;
            private String goods_id;
            private String goods_name;
            private String goods_img;
            private String price;
            private String total_buy_number;
            private String total_money;
            private String newPrice;
            private String changePrice="0";
            private String index="0";

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getAfter_money() {
                return after_money;
            }

            public void setAfter_money(String after_money) {
                this.after_money = after_money;
            }

            public String getChangePrice() {
                return changePrice;
            }

            public void setChangePrice(String changePrice) {
                this.changePrice = changePrice;
            }



            public String getNewPrice() {
                return newPrice;
            }

            public void setNewPrice(String newPrice) {
                this.newPrice = newPrice;
            }

            public String getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(String order_goods_id) {
                this.order_goods_id = order_goods_id;
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
