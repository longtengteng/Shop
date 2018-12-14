package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class ShopListBean {

    /**
     * data : [{"add_time":"1469426288","city":"临沂市","click_count":"218","comment":{"content":"这是一条5星好评","head_pic":"","rank":"5","user_name":"匿名用户"},"company_name":"六牛科技","images":[{"goods_img":"/Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg"},{"goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg"},{"goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg"}],"is_owner":"0","market_name":"","month_goods_count":11,"province":"山东省","shop_id":"1","shop_logo":"","shop_name":"六牛科技自营","shop_real_pic":"","shop_type":"1"}]
     * info : 获取成功
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
         * add_time : 1469426288
         * city : 临沂市
         * click_count : 218
         * comment : {"content":"这是一条5星好评","head_pic":"","rank":"5","user_name":"匿名用户"}
         * company_name : 六牛科技
         * images : [{"goods_img":"/Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg"},{"goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg"},{"goods_img":"/Uploads/Picture/Goods/2017-07-20/59700f8889979.jpg"}]
         * is_owner : 0
         * market_name :
         * month_goods_count : 11
         * province : 山东省
         * shop_id : 1
         * shop_logo :
         * shop_name : 六牛科技自营
         * shop_real_pic :
         * shop_type : 1
         */

        private String add_time;
        private String city;
        private String click_count;
        private CommentBean comment;
        private String company_name;
        private String is_owner;
        private String market_name;
        private int month_goods_count;
        private String province;
        private String shop_id;
        private String shop_logo;
        private String shop_name;
        private String shop_real_pic;
        private String shop_type;
        private List<ImagesBean> images;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getIs_owner() {
            return is_owner;
        }

        public void setIs_owner(String is_owner) {
            this.is_owner = is_owner;
        }

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public int getMonth_goods_count() {
            return month_goods_count;
        }

        public void setMonth_goods_count(int month_goods_count) {
            this.month_goods_count = month_goods_count;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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

        public String getShop_real_pic() {
            return shop_real_pic;
        }

        public void setShop_real_pic(String shop_real_pic) {
            this.shop_real_pic = shop_real_pic;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class CommentBean {
            /**
             * content : 这是一条5星好评
             * head_pic :
             * rank : 5
             * user_name : 匿名用户
             */

            private String content;
            private String head_pic;
            private String rank;
            private String user_name;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }

        public static class ImagesBean {
            /**
             * goods_img : /Uploads/Picture/Goods/2017-07-20/5970790d1236d.jpg
             */

            private String goods_img;

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
