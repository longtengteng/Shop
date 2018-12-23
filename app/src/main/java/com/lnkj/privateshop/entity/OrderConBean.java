package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class OrderConBean implements Serializable {

    /**
     * status : 1
     * info : 订单确认
     * data : {"addr_info":{"address_id":"72","consignee":"赵林","address":"合肥路12222","province":"山东省","city":"临沂市","district":"兰山区","mobile":"15562909154","zipcode":"","is_default":"1"},"goods_list":[{"shop_id":"1","shop_name":"","goods":[{"goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","price":"120.00","weight":"900","is_selected":"1","is_valid":"1","spec":[{"color":"卡其色","size":[{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]}]},{"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","price":"39.00","weight":"100","is_selected":"1","is_valid":"1","spec":[{"color":"如图","size":[{"size_name":"XL","storage":"111","buy_number":"1","goods_spec_key":"31_14"}]},{"color":"白色","size":[{"size_name":"2XL","storage":"111","buy_number":"4","goods_spec_key":"15_25"}]}]}],"shop_express":"200.00"}],"total_goods_num":6,"total_goods_amount":"315.00","total_express":"200.00","total_amount":"515.00"}
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

    public static class DataBean implements Serializable{

        /**
         * address_id : 63
         * province : 山东省
         * city : 临沂市
         * district : 罗庄区
         * consignee : 66666
         * mobile : 13355555555
         * address : 付庄办事处
         * zipcode : 269000
         * order_price : 357.00
         * order_buy_counts : 5
         * order_express_price : 1000.00
         * final_price : 1357.00
         * list : [{"shop_id":"4","shop_name":"4号店","shop_express_price":"0.00","goods_list":[{"cart_id":"876","user_id":"64","goods_id":"2","goods_name":"阿尔法机器人","buy_number":"1","price":"111.00","weight":"500","goods_img":"/Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png","goods_spec_key":"1_3","shop_id":"4","shop_name":"4号店","act_id":"0","act_type":"0","spec_name":"尺寸:40 颜色:白"}]},{"shop_id":"0","shop_name":"自营店","shop_express_price":"1000.00","goods_list":[{"cart_id":"877","user_id":"64","goods_id":"2","goods_name":"阿尔法机器人","buy_number":"2","price":"111.00","weight":"500","goods_img":"/Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png","goods_spec_key":"1_3","shop_id":"0","shop_name":"自营店","act_id":"0","act_type":"0","spec_name":"尺寸:40 颜色:白"},{"cart_id":"878","user_id":"64","goods_id":"7","goods_name":"智能温度仪","buy_number":"2","price":"12.00","weight":"0","goods_img":"/Uploads/Picture/Goods/2018-12-17/5c175f5a68b78.jpg","goods_spec_key":"11_12","shop_id":"0","shop_name":"自营店","act_id":"0","act_type":"0","spec_name":"长度:20cm 灵敏度:A"}]}]
         */

        private String address_id;
        private String province;
        private String city;
        private String district;
        private String consignee;
        private String mobile;
        private String address;
        private String zipcode;
        private String order_price;
        private int order_buy_counts;
        private String order_express_price;
        private String final_price;
        private List<ListBean> list;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public int getOrder_buy_counts() {
            return order_buy_counts;
        }

        public void setOrder_buy_counts(int order_buy_counts) {
            this.order_buy_counts = order_buy_counts;
        }

        public String getOrder_express_price() {
            return order_express_price;
        }

        public void setOrder_express_price(String order_express_price) {
            this.order_express_price = order_express_price;
        }

        public String getFinal_price() {
            return final_price;
        }

        public void setFinal_price(String final_price) {
            this.final_price = final_price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * shop_id : 4
             * shop_name : 4号店
             * shop_express_price : 0.00
             * goods_list : [{"cart_id":"876","user_id":"64","goods_id":"2","goods_name":"阿尔法机器人","buy_number":"1","price":"111.00","weight":"500","goods_img":"/Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png","goods_spec_key":"1_3","shop_id":"4","shop_name":"4号店","act_id":"0","act_type":"0","spec_name":"尺寸:40 颜色:白"}]
             */

            private String shop_id;
            private String shop_name;
            private String shop_express_price;
            private List<GoodsListBean> goods_list;

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

            public String getShop_express_price() {
                return shop_express_price;
            }

            public void setShop_express_price(String shop_express_price) {
                this.shop_express_price = shop_express_price;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            public static class GoodsListBean implements Serializable {
                /**
                 * cart_id : 876
                 * user_id : 64
                 * goods_id : 2
                 * goods_name : 阿尔法机器人
                 * buy_number : 1
                 * price : 111.00
                 * weight : 500
                 * goods_img : /Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png
                 * goods_spec_key : 1_3
                 * shop_id : 4
                 * shop_name : 4号店
                 * act_id : 0
                 * act_type : 0
                 * spec_name : 尺寸:40 颜色:白
                 */

                private String cart_id;
                private String user_id;
                private String goods_id;
                private String goods_name;
                private String buy_number;
                private String price;
                private String weight;
                private String goods_img;
                private String goods_spec_key;
                private String shop_id;
                private String shop_name;
                private String act_id;
                private String act_type;
                private String spec_name;

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
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

                public String getBuy_number() {
                    return buy_number;
                }

                public void setBuy_number(String buy_number) {
                    this.buy_number = buy_number;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public String getGoods_spec_key() {
                    return goods_spec_key;
                }

                public void setGoods_spec_key(String goods_spec_key) {
                    this.goods_spec_key = goods_spec_key;
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

                public String getAct_id() {
                    return act_id;
                }

                public void setAct_id(String act_id) {
                    this.act_id = act_id;
                }

                public String getAct_type() {
                    return act_type;
                }

                public void setAct_type(String act_type) {
                    this.act_type = act_type;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }
            }
        }
    }
}
