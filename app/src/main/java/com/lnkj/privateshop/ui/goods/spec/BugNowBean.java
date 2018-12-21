package com.lnkj.privateshop.ui.goods.spec;

import java.util.List;

public class BugNowBean {

    /**
     * address_id : 63
     * province : 山东省
     * city : 临沂市
     * district : 罗庄区
     * consignee : 66666
     * mobile : 13355555555
     * address : 付庄办事处
     * zipcode : 269000
     * order_price : 24.00
     * order_buy_counts : 2
     * order_express_price : 0.00
     * final_price : 24.00
     * list : [{"shop_id":"0","shop_name":"自营店","shop_express_price":"0.00","goods_list":{"cart_id":0,"user_id":"64","goods_id":7,"goods_name":"智能温度仪","buy_number":2,"price":"12.00","weight":"0","goods_img":"/Uploads/Picture/Goods/2018-12-17/5c175f5a68b78.jpg","goods_spec_key":"11_12","shop_id":"0","shop_name":"自营店","act_id":0,"act_type":0,"spec_name":"长度:20cm 灵敏度:A"}}]
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

    public static class ListBean {
        /**
         * shop_id : 0
         * shop_name : 自营店
         * shop_express_price : 0.00
         * goods_list : {"cart_id":0,"user_id":"64","goods_id":7,"goods_name":"智能温度仪","buy_number":2,"price":"12.00","weight":"0","goods_img":"/Uploads/Picture/Goods/2018-12-17/5c175f5a68b78.jpg","goods_spec_key":"11_12","shop_id":"0","shop_name":"自营店","act_id":0,"act_type":0,"spec_name":"长度:20cm 灵敏度:A"}
         */

        private String shop_id;
        private String shop_name;
        private String shop_express_price;
        private GoodsListBean goods_list;

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

        public GoodsListBean getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(GoodsListBean goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * cart_id : 0
             * user_id : 64
             * goods_id : 7
             * goods_name : 智能温度仪
             * buy_number : 2
             * price : 12.00
             * weight : 0
             * goods_img : /Uploads/Picture/Goods/2018-12-17/5c175f5a68b78.jpg
             * goods_spec_key : 11_12
             * shop_id : 0
             * shop_name : 自营店
             * act_id : 0
             * act_type : 0
             * spec_name : 长度:20cm 灵敏度:A
             */

            private int cart_id;
            private String user_id;
            private int goods_id;
            private String goods_name;
            private int buy_number;
            private String price;
            private String weight;
            private String goods_img;
            private String goods_spec_key;
            private String shop_id;
            private String shop_name;
            private int act_id;
            private int act_type;
            private String spec_name;

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getBuy_number() {
                return buy_number;
            }

            public void setBuy_number(int buy_number) {
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

            public int getAct_id() {
                return act_id;
            }

            public void setAct_id(int act_id) {
                this.act_id = act_id;
            }

            public int getAct_type() {
                return act_type;
            }

            public void setAct_type(int act_type) {
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
