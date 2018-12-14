package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class TimeGoodsBean {


    /**
     * status : 1
     * info :
     * data : {"info":{"act_id":"65","act_name":"仲秋活动大酬宾","act_desc":"特价1折拉","act_img":"","act_type":"5","start_time":"1504487034","end_time":"1504499638","is_finished":"0","discount":"1.0","now":"1504491672"},"list":[{"goods_id":"205","goods_name":"大码女装 衬衫连衣裙","shop_price":"51.00","goods_img":"/Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg","goods_stock":"500","goods_sale":"0","activity_price":"51.00","discount":"1.0","act_id":"65","retail_amount":"1"},{"goods_id":"206","goods_name":"雪纺吊带裙配白色T，套装！！！","shop_price":"55.00","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_stock":"600","goods_sale":"0","activity_price":"55.00","discount":"1.0","act_id":"65","retail_amount":"1"},{"goods_id":"207","goods_name":"新款韩版气质半身裙","shop_price":"55.00","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_stock":"500","goods_sale":"0","activity_price":"55.00","discount":"1.0","act_id":"65","retail_amount":"1"}]}
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
         * info : {"act_id":"65","act_name":"仲秋活动大酬宾","act_desc":"特价1折拉","act_img":"","act_type":"5","start_time":"1504487034","end_time":"1504499638","is_finished":"0","discount":"1.0","now":"1504491672"}
         * list : [{"goods_id":"205","goods_name":"大码女装 衬衫连衣裙","shop_price":"51.00","goods_img":"/Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg","goods_stock":"500","goods_sale":"0","activity_price":"51.00","discount":"1.0","act_id":"65","retail_amount":"1"},{"goods_id":"206","goods_name":"雪纺吊带裙配白色T，套装！！！","shop_price":"55.00","goods_img":"/Uploads/Picture/Goods/2017-07-11/59641a592e95a.jpg","goods_stock":"600","goods_sale":"0","activity_price":"55.00","discount":"1.0","act_id":"65","retail_amount":"1"},{"goods_id":"207","goods_name":"新款韩版气质半身裙","shop_price":"55.00","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_stock":"500","goods_sale":"0","activity_price":"55.00","discount":"1.0","act_id":"65","retail_amount":"1"}]
         */

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * act_id : 65
             * act_name : 仲秋活动大酬宾
             * act_desc : 特价1折拉
             * act_img :
             * act_type : 5
             * start_time : 1504487034
             * end_time : 1504499638
             * is_finished : 0
             * discount : 1.0
             * now : 1504491672
             */

            private String act_id;
            private String act_name;
            private String act_desc;
            private String act_img;
            private String act_type;
            private String start_time;
            private String end_time;
            private String is_finished;
            private String discount;
            private String now;

            public String getAct_id() {
                return act_id;
            }

            public void setAct_id(String act_id) {
                this.act_id = act_id;
            }

            public String getAct_name() {
                return act_name;
            }

            public void setAct_name(String act_name) {
                this.act_name = act_name;
            }

            public String getAct_desc() {
                return act_desc;
            }

            public void setAct_desc(String act_desc) {
                this.act_desc = act_desc;
            }

            public String getAct_img() {
                return act_img;
            }

            public void setAct_img(String act_img) {
                this.act_img = act_img;
            }

            public String getAct_type() {
                return act_type;
            }

            public void setAct_type(String act_type) {
                this.act_type = act_type;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getIs_finished() {
                return is_finished;
            }

            public void setIs_finished(String is_finished) {
                this.is_finished = is_finished;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getNow() {
                return now;
            }

            public void setNow(String now) {
                this.now = now;
            }
        }

        public static class ListBean {
            /**
             * goods_id : 205
             * goods_name : 大码女装 衬衫连衣裙
             * shop_price : 51.00
             * goods_img : /Uploads/Picture/Goods/2017-07-10/5962e62d4353a.jpg
             * goods_stock : 500
             * goods_sale : 0
             * activity_price : 51.00
             * discount : 1.0
             * act_id : 65
             * retail_amount : 1
             */

            private String goods_id;
            private String goods_name;
            private String shop_price;
            private String goods_img;
            private String goods_stock;
            private String goods_sale;
            private String activity_price;
            private String discount;
            private String act_id;
            private String retail_amount;
            private String basic_amount;
            private int sale_rate;

            public String getBasic_amount() {
                return basic_amount;
            }

            public void setBasic_amount(String basic_amount) {
                this.basic_amount = basic_amount;
            }

            public int getSale_rate() {
                return sale_rate;
            }

            public void setSale_rate(int sale_rate) {
                this.sale_rate = sale_rate;
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

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_stock() {
                return goods_stock;
            }

            public void setGoods_stock(String goods_stock) {
                this.goods_stock = goods_stock;
            }

            public String getGoods_sale() {
                return goods_sale;
            }

            public void setGoods_sale(String goods_sale) {
                this.goods_sale = goods_sale;
            }

            public String getActivity_price() {
                return activity_price;
            }

            public void setActivity_price(String activity_price) {
                this.activity_price = activity_price;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getAct_id() {
                return act_id;
            }

            public void setAct_id(String act_id) {
                this.act_id = act_id;
            }

            public String getRetail_amount() {
                return retail_amount;
            }

            public void setRetail_amount(String retail_amount) {
                this.retail_amount = retail_amount;
            }
        }
    }
}
