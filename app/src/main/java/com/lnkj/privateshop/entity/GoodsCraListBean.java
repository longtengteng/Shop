package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class GoodsCraListBean {


    /**
     * data : [{"goods":[{"goods_id":"207","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_name":"新款韩版气质半身裙","is_selected":"1","is_valid":"1","price":"55.00","spec":[{"color":"蓝色","size":[{"buy_number":"2","goods_spec_key":"3_5","size_name":"XS","storage":"111"},{"buy_number":"1","goods_spec_key":"3_6","size_name":"S","storage":"111"},{"buy_number":"1","goods_spec_key":"3_7","size_name":"M","storage":"111"}]},{"color":"粉色","size":[{"buy_number":"1","goods_spec_key":"1_5","size_name":"XS","storage":"23"},{"buy_number":"2","goods_spec_key":"1_7","size_name":"M","storage":"111"},{"buy_number":"1","goods_spec_key":"1_6","size_name":"S","storage":"111"}]}],"weight":"100"}],"shop_id":"2","shop_name":"六年科技 "},{"goods":[{"goods_id":"211","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","goods_name":"爆款套装 五个码 一件代发","is_selected":"1","is_valid":"1","price":"0.01","spec":[{"color":"红色","size":[{"buy_number":"2","goods_spec_key":"2_5","size_name":"XS","storage":"11"},{"buy_number":"3","goods_spec_key":"2_6","size_name":"S","storage":"22"}]}],"weight":"0"}],"shop_id":"1","shop_name":"商城自营"}]
     * info : 进货车列表
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
         * all_pirce : 357.00
         * list : [{"shop_id":"0","shop_name":"自营店","goods":[{"cart_id":"873","user_id":"64","goods_id":"2","goods_name":"阿尔法机器人","buy_number":"2","price":"111.00","weight":"500","goods_img":"/Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png","goods_spec_key":"1_3","add_time":"1545220887","shop_id":"0","shop_name":"自营店","is_selected":"1","is_valid":"1","act_id":"0","act_type":"0","spec_name":"尺寸:40 颜色:白"}]}]
         */

        private String all_pirce;
        private List<ListBean> list;

        public String getAll_pirce() {
            return all_pirce;
        }

        public void setAll_pirce(String all_pirce) {
            this.all_pirce = all_pirce;
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
             * goods : [{"cart_id":"873","user_id":"64","goods_id":"2","goods_name":"阿尔法机器人","buy_number":"2","price":"111.00","weight":"500","goods_img":"/Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png","goods_spec_key":"1_3","add_time":"1545220887","shop_id":"0","shop_name":"自营店","is_selected":"1","is_valid":"1","act_id":"0","act_type":"0","spec_name":"尺寸:40 颜色:白"}]
             */
            private String is_selected;

            public String getIs_selected() {
                return is_selected;
            }

            public void setIs_selected(String is_selected) {
                this.is_selected = is_selected;
            }

            private String shop_id;
            private String shop_name;
            private List<GoodsBean> goods;

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

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * cart_id : 873
                 * user_id : 64
                 * goods_id : 2
                 * goods_name : 阿尔法机器人
                 * buy_number : 2
                 * price : 111.00
                 * weight : 500
                 * goods_img : /Uploads/Picture/Goods/2018-12-16/5c160d8f51a84.png
                 * goods_spec_key : 1_3
                 * add_time : 1545220887
                 * shop_id : 0
                 * shop_name : 自营店
                 * is_selected : 1
                 * is_valid : 1
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
                private String add_time;
                private String shop_id;
                private String shop_name;
                private String is_selected;
                private String is_valid;
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

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
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

                public String getIs_selected() {
                    return is_selected;
                }

                public void setIs_selected(String is_selected) {
                    this.is_selected = is_selected;
                }

                public String getIs_valid() {
                    return is_valid;
                }

                public void setIs_valid(String is_valid) {
                    this.is_valid = is_valid;
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
