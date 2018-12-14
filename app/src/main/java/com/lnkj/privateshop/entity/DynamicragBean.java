package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class DynamicragBean {

    /**
     * data : [{"add_time":"2017-09-01 15:07","content":"1212112","favorite_shop":0,"goods_list":[{"goods_id":"255","goods_img":"/Uploads/Picture/Goods/86/20170901_150707_15042496277870_8715.jpg","pack_price":"122111200","shop_price":"12121200"}],"id":"14","shop_id":"86","shop_info":{"shop_id":"86","shop_logo":"/Uploads/Picture/Shop/100/20170817_175824_15029639044919_5887.jpg","shop_name":"奥格瑞玛"}},{"add_time":"2017-08-30 11:11","content":"11111","favorite_shop":0,"goods_list":[{"goods_id":"204","goods_img":"/Uploads/Picture/Goods/2017-07-08/59608eec66a28.jpg","pack_price":"12800","shop_price":"6800"},{"goods_id":"211","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","pack_price":"1","shop_price":"1"}],"id":"13","shop_id":"1","shop_info":{"shop_id":"1","shop_logo":"","shop_name":"六牛科技自营"}}]
     * info : 圈子列表
     * status : 1
     */

    private String info;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * add_time : 2017-09-01 15:07
         * content : 1212112
         * favorite_shop : 0
         * goods_list : [{"goods_id":"255","goods_img":"/Uploads/Picture/Goods/86/20170901_150707_15042496277870_8715.jpg","pack_price":"122111200","shop_price":"12121200"}]
         * id : 14
         * shop_id : 86
         * shop_info : {"shop_id":"86","shop_logo":"/Uploads/Picture/Shop/100/20170817_175824_15029639044919_5887.jpg","shop_name":"奥格瑞玛"}
         */

        private String add_time;
        private String content;
        private int favorite_shop;
        private String id;
        private String shop_id;
        private ShopInfoBean shop_info;
        private List<GoodsListBean> goods_list;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getFavorite_shop() {
            return favorite_shop;
        }

        public void setFavorite_shop(int favorite_shop) {
            this.favorite_shop = favorite_shop;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class ShopInfoBean {
            /**
             * shop_id : 86
             * shop_logo : /Uploads/Picture/Shop/100/20170817_175824_15029639044919_5887.jpg
             * shop_name : 奥格瑞玛
             */

            private String shop_id;
            private String shop_logo;
            private String shop_name;
            private String emchat_username;
            private String nickname;
            private String emchat_password;

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

            public String getEmchat_password() {
                return emchat_password;
            }

            public void setEmchat_password(String emchat_password) {
                this.emchat_password = emchat_password;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
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
        }

        public static class GoodsListBean {
            /**
             * goods_id : 255
             * goods_img : /Uploads/Picture/Goods/86/20170901_150707_15042496277870_8715.jpg
             * pack_price : 122111200
             * shop_price : 12121200
             */

            private String goods_id;
            private String goods_img;
            private String pack_price;
            private String shop_price;

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
        }
    }
}
