package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class ShopMerchandiseListBean implements Serializable{

    /**
     * status : 1
     * info : 获取成功
     * data : {"shopInfo":{"shop_id":"1","shop_type":"1","shop_logo":"","shop_name":"六牛科技自营","province":"山东省","city":"临沂市","address":"临沂市河东区山东六牛网络科技有限公司","market_name":"","company_name":"六牛科技","add_time":"2016-07-25","is_owner":"0","click_count":"241","description":"","shop_points":"0","collect_num":"1","is_favorite":0,"month_goods_count":11,"goods_rank":"3.8","goods_rank_num":4,"express_rank":"1.0","express_rank_num":1,"service_rank":"1.0","service_rank_num":1,"good_comment_rate":"66.67"},"goodsList":[{"goods_id":"227","goods_name":"8934套装58包邮","goods_img":"/Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg","pack_price":"57.00","shop_price":"58.00","collect_num":"1"},{"goods_id":"226","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","pack_price":"74.00","shop_price":"78.00","collect_num":"1"}],"sort":"new"}
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
         * shopInfo : {"shop_id":"1","shop_type":"1","shop_logo":"","shop_name":"六牛科技自营","province":"山东省","city":"临沂市","address":"临沂市河东区山东六牛网络科技有限公司","market_name":"","company_name":"六牛科技","add_time":"2016-07-25","is_owner":"0","click_count":"241","description":"","shop_points":"0","collect_num":"1","is_favorite":0,"month_goods_count":11,"goods_rank":"3.8","goods_rank_num":4,"express_rank":"1.0","express_rank_num":1,"service_rank":"1.0","service_rank_num":1,"good_comment_rate":"66.67"}
         * goodsList : [{"goods_id":"227","goods_name":"8934套装58包邮","goods_img":"/Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg","pack_price":"57.00","shop_price":"58.00","collect_num":"1"},{"goods_id":"226","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","pack_price":"74.00","shop_price":"78.00","collect_num":"1"}]
         * sort : new
         */

        private ShopInfoBean shopInfo;
        private String sort;
        private List<GoodsListBean> goodsList;

        public ShopInfoBean getShopInfo() {
            return shopInfo;
        }

        public void setShopInfo(ShopInfoBean shopInfo) {
            this.shopInfo = shopInfo;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class ShopInfoBean implements Serializable{
            /**
             * shop_id : 1
             * shop_type : 1
             * shop_logo :
             * shop_name : 六牛科技自营
             * province : 山东省
             * city : 临沂市
             * address : 临沂市河东区山东六牛网络科技有限公司
             * market_name :
             * company_name : 六牛科技
             * add_time : 2016-07-25
             * is_owner : 0
             * click_count : 241
             * description :
             * shop_points : 0
             * collect_num : 1
             * is_favorite : 0
             * month_goods_count : 11
             * goods_rank : 3.8
             * goods_rank_num : 4
             * express_rank : 1.0
             * express_rank_num : 1
             * service_rank : 1.0
             * service_rank_num : 1
             * good_comment_rate : 66.67
             */

            private String shop_id;
            private String shop_type;
            private String shop_logo;
            private String shop_name;
            private String province;
            private String city;
            private String address;
            private String market_name;
            private String company_name;
            private String add_time;
            private String is_owner;
            private String click_count;
            private String description;
            private String shop_points;
            private String collect_num;
            private int is_favorite;
            private int month_goods_count;
            private String goods_rank;
            private int goods_rank_num;
            private String express_rank;
            private int express_rank_num;
            private String service_rank;
            private int service_rank_num;
            private String good_comment_rate;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_type() {
                return shop_type;
            }

            public void setShop_type(String shop_type) {
                this.shop_type = shop_type;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getIs_owner() {
                return is_owner;
            }

            public void setIs_owner(String is_owner) {
                this.is_owner = is_owner;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShop_points() {
                return shop_points;
            }

            public void setShop_points(String shop_points) {
                this.shop_points = shop_points;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public int getMonth_goods_count() {
                return month_goods_count;
            }

            public void setMonth_goods_count(int month_goods_count) {
                this.month_goods_count = month_goods_count;
            }

            public String getGoods_rank() {
                return goods_rank;
            }

            public void setGoods_rank(String goods_rank) {
                this.goods_rank = goods_rank;
            }

            public int getGoods_rank_num() {
                return goods_rank_num;
            }

            public void setGoods_rank_num(int goods_rank_num) {
                this.goods_rank_num = goods_rank_num;
            }

            public String getExpress_rank() {
                return express_rank;
            }

            public void setExpress_rank(String express_rank) {
                this.express_rank = express_rank;
            }

            public int getExpress_rank_num() {
                return express_rank_num;
            }

            public void setExpress_rank_num(int express_rank_num) {
                this.express_rank_num = express_rank_num;
            }

            public String getService_rank() {
                return service_rank;
            }

            public void setService_rank(String service_rank) {
                this.service_rank = service_rank;
            }

            public int getService_rank_num() {
                return service_rank_num;
            }

            public void setService_rank_num(int service_rank_num) {
                this.service_rank_num = service_rank_num;
            }

            public String getGood_comment_rate() {
                return good_comment_rate;
            }

            public void setGood_comment_rate(String good_comment_rate) {
                this.good_comment_rate = good_comment_rate;
            }
        }

        public static class GoodsListBean implements Serializable{
            /**
             * goods_id : 227
             * goods_name : 8934套装58包邮
             * goods_img : /Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg
             * pack_price : 57.00
             * shop_price : 58.00
             * collect_num : 1
             */
            private String goods_id;
            private String goods_name;
            private String goods_img;
            private String pack_price;
            private String shop_price;
            private String collect_num;
            private Boolean ischeck=false;

            public Boolean getIscheck() {
                return ischeck;
            }

            public void setIscheck(Boolean ischeck) {
                this.ischeck = ischeck;
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

            public String getPack_price() {
                return pack_price;
            }

            public void setPack_price(String pack_price) {
                this.pack_price = pack_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }
        }
    }
}
