package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class OrderDetailderBean {

    /**
     * status : 1
     * info : 定单数据
     * data : {"order_id":"292","order_sn":"S201708011404194223","order_status":"3","pay_status":"0","total_amount":"60.00","express_amount":"0.00","real_pay_amount":"60.00","total_buy_number":"2","shop_id":"1","shop_name":"商城自营","add_time":"2017-08-01 14:04:18","remark":"123456","consignee":"宋国远","mobile":"13953940424","zipcode":"","address":"山东省济南市槐荫区经六路延长线小区","order_goods":[{"goods_id":"216","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","price":"25.00","is_comment":"0","total_buy_number":"1","total_money":"25.00"},{"goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","price":"35.00","is_comment":"0","total_buy_number":"1","total_money":"35.00"}],"pay_time":"2017-08-01 14:05:58","delivery_time":"2017-08-01 17:59:48","finish_time":""}
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
         * order_id : 292
         * order_sn : S201708011404194223
         * order_status : 3
         * pay_status : 0
         * total_amount : 60.00
         * express_amount : 0.00
         * real_pay_amount : 60.00
         * total_buy_number : 2
         * shop_id : 1
         * shop_name : 商城自营
         * add_time : 2017-08-01 14:04:18
         * remark : 123456
         * consignee : 宋国远
         * mobile : 13953940424
         * zipcode :
         * address : 山东省济南市槐荫区经六路延长线小区
         * order_goods : [{"goods_id":"216","goods_name":"莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究","goods_img":"/Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg","price":"25.00","is_comment":"0","total_buy_number":"1","total_money":"25.00"},{"goods_id":"225","goods_name":"个性8字韩版TTT","goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg","price":"35.00","is_comment":"0","total_buy_number":"1","total_money":"35.00"}]
         * pay_time : 2017-08-01 14:05:58
         * delivery_time : 2017-08-01 17:59:48
         * finish_time :
         */
        private String shop_mobile;

        private String order_id;
        private String order_sn;
        private String order_status;
        private String pay_status;
        private String total_amount;
        private String express_amount;
        private String real_pay_amount;
        private String total_buy_number;
        private String shop_id;
        private String shop_name;
        private String add_time;
        private String remark;
        private String consignee;
        private String mobile;
        private String zipcode;
        private String address;
        private String pay_time;
        private String delivery_time;
        private String finish_time;
        private List<OrderGoodsBean> order_goods;
        private Echat echat ;
        private String is_comment ;



        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getShop_mobile() {
            return shop_mobile;
        }

        public void setShop_mobile(String shop_mobile) {
            this.shop_mobile = shop_mobile;
        }

        public Echat getEchat() {
            return echat;
        }

        public void setEchat(Echat echat) {
            this.echat = echat;
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

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
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

        public String getTotal_buy_number() {
            return total_buy_number;
        }

        public void setTotal_buy_number(String total_buy_number) {
            this.total_buy_number = total_buy_number;
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }
        public static class Echat{

            /**
             * emchat_username : 20171007_101158_906202
             * head_pic : /Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg
             * nickname : 33333
             */

            private String emchat_username;
            private String head_pic;
            private String nickname;

            public String getEmchat_username() {
                return emchat_username;
            }

            public void setEmchat_username(String emchat_username) {
                this.emchat_username = emchat_username;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
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
             * goods_id : 216
             * goods_name : 莫代尔纯棉小衫特价，质量好，莫代尔纯棉，质量考究
             * goods_img : /Uploads/Picture/Goods/2017-07-13/5966e087688fa.jpg
             * price : 25.00
             * is_comment : 0
             * total_buy_number : 1
             * total_money : 25.00
             */
            private String allow_return;
            private String goods_id;
            private String goods_name;
            private String goods_img;
            private String price;
            private String is_comment;
            private String total_buy_number;
            private String total_money;
            private String order_goods_id;
            private String order_return_status;
            private List<Spec_list> spec_list;

            public String getAllow_return() {
                return allow_return;
            }

            public void setAllow_return(String allow_return) {
                this.allow_return = allow_return;
            }

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

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
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
            public String spec;
            public String buy_number;

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
