package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ShopBean {


    /**
     * data : {"address":"临沂市河东区山东六牛网络科技有限公司","buy_again_rate":0,"city":"临沂市","collect_num":"2","comment_count":"4","description":"","express_rank":"3.8","express_rank_num":4,"good_comment_rate":"50.00","goods_rank":"3.2","goods_rank_num":3,"goods_sale_num":"27","is_favorite":0,"month_goods_count":2,"province":"山东省","service_rank":"4.2","service_rank_num":4,"shop_id":"1","shop_logo":"","shop_name":"六牛科技自营","shop_real_pic":"/Uploads/Picture/Supplier/2017-06-30/5955ef3cdf6e8.png","shop_type":"1"}
     * info : 获取成功
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
         * address : 临沂市河东区山东六牛网络科技有限公司
         * buy_again_rate : 0
         * city : 临沂市
         * collect_num : 2
         * comment_count : 4
         * description :
         * express_rank : 3.8
         * express_rank_num : 4
         * good_comment_rate : 50.00
         * goods_rank : 3.2
         * goods_rank_num : 3
         * goods_sale_num : 27
         * is_favorite : 0
         * month_goods_count : 2
         * province : 山东省
         * service_rank : 4.2
         * service_rank_num : 4
         * shop_id : 1
         * shop_logo :
         * shop_name : 六牛科技自营
         * shop_real_pic : /Uploads/Picture/Supplier/2017-06-30/5955ef3cdf6e8.png
         * shop_type : 1
         */

        private String address;
        private String allow_return;
        private int buy_again_rate;
        private String city;
        private String collect_num;
        private String comment_count;
        private String description;
        private String express_rank;
        private int express_rank_num;
        private String good_comment_rate;
        private String goods_rank;
        private int goods_rank_num;
        private String goods_sale_num;
        private int is_favorite;
        private int month_goods_count;
        private String province;
        private String service_rank;
        private int service_rank_num;
        private String shop_id;
        private String shop_logo;
        private String shop_name;
        private String shop_real_pic;
        private String shop_type;
        private String user_mobile;
        private String head_pic;

        private String nickname;
        private String emchat_username;
        private String emchat_password;
        private Shop_grade shop_grade;

        public Shop_grade getShop_grade() {
            return shop_grade;
        }

        public void setShop_grade(Shop_grade shop_grade) {
            this.shop_grade = shop_grade;
        }

        public String getAllow_return() {
            return allow_return;
        }

        public void setAllow_return(String allow_return) {
            this.allow_return = allow_return;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
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

        public String getEmchat_username() {
            return emchat_username;
        }

        public void setEmchat_username(String emchat_username) {
            this.emchat_username = emchat_username;
        }

        public String getEmchat_password() {
            return emchat_password;
        }

        public void setEmchat_password(String emchat_password) {
            this.emchat_password = emchat_password;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getBuy_again_rate() {
            return buy_again_rate;
        }

        public void setBuy_again_rate(int buy_again_rate) {
            this.buy_again_rate = buy_again_rate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getGood_comment_rate() {
            return good_comment_rate;
        }

        public void setGood_comment_rate(String good_comment_rate) {
            this.good_comment_rate = good_comment_rate;
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

        public String getGoods_sale_num() {
            return goods_sale_num;
        }

        public void setGoods_sale_num(String goods_sale_num) {
            this.goods_sale_num = goods_sale_num;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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


        public class Shop_grade{
            private String type;
            private int num;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }

    }
}
