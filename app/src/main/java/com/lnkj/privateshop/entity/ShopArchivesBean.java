package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class ShopArchivesBean {

    /**
     * data : {"address":"临沂市河东区山东六牛网络科技有限公司","city":"临沂市","collect_num":"5","comment":{"commentCount":"3","express_rank":"3.7","favorableRate":67,"goods_rank":"3.7","imgCount":"0","likeCount":"2","lowCount":"0","medCount":"1","reviewCount":0,"service_rank":"4.3"},"contacts_name":"","express_rank":"2.8","express_rank_num":3,"good_comment_rate":"33.33","goods_rank":"2.5","goods_rank_num":3,"is_favorite":0,"month_goods_count":1,"province":"山东省","refund_num":"2","service_rank":"3.2","service_rank_num":3,"shop_bond":"0","shop_id":"1","shop_logo":"","shop_name":"六牛科技自营","shop_real_pic":"/Uploads/Picture/Supplier/2017-06-30/5955ef3cdf6e8.png","shop_type":"1","user_mobile":""}
     * info : 店铺档案列表
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
         * city : 临沂市
         * collect_num : 5
         * comment : {"commentCount":"3","express_rank":"3.7","favorableRate":67,"goods_rank":"3.7","imgCount":"0","likeCount":"2","lowCount":"0","medCount":"1","reviewCount":0,"service_rank":"4.3"}
         * contacts_name :
         * express_rank : 2.8
         * express_rank_num : 3
         * good_comment_rate : 33.33
         * goods_rank : 2.5
         * goods_rank_num : 3
         * is_favorite : 0
         * month_goods_count : 1
         * province : 山东省
         * refund_num : 2
         * service_rank : 3.2
         * service_rank_num : 3
         * shop_bond : 0
         * shop_id : 1
         * shop_logo :
         * shop_name : 六牛科技自营
         * shop_real_pic : /Uploads/Picture/Supplier/2017-06-30/5955ef3cdf6e8.png
         * shop_type : 1
         * user_mobile :
         */

        private String address;
        private String city;
        private String collect_num;
        private CommentBean comment;
        private String contacts_name;
        private String express_rank;
        private int express_rank_num;
        private String good_comment_rate;
        private String goods_rank;
        private int goods_rank_num;
        private int is_favorite;
        private int month_goods_count;
        private String province;
        private String refund_num;
        private String service_rank;
        private int service_rank_num;
        private String shop_bond;
        private String shop_id;
        private String shop_logo;
        private String shop_name;
        private String shop_real_pic;
        private String shop_type;
        private String user_mobile;
        private String add_time;
        private Shop_grade shop_grade;

        public Shop_grade getShop_grade() {
            return shop_grade;
        }

        public void setShop_grade(Shop_grade shop_grade) {
            this.shop_grade = shop_grade;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public String getContacts_name() {
            return contacts_name;
        }

        public void setContacts_name(String contacts_name) {
            this.contacts_name = contacts_name;
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

        public String getRefund_num() {
            return refund_num;
        }

        public void setRefund_num(String refund_num) {
            this.refund_num = refund_num;
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

        public String getShop_bond() {
            return shop_bond;
        }

        public void setShop_bond(String shop_bond) {
            this.shop_bond = shop_bond;
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

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }
        public static class Shop_grade{
             public String type;
            public int num;

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
        public static class CommentBean {
            /**
             * commentCount : 3
             * express_rank : 3.7
             * favorableRate : 67
             * goods_rank : 3.7
             * imgCount : 0
             * likeCount : 2
             * lowCount : 0
             * medCount : 1
             * reviewCount : 0
             * service_rank : 4.3
             */

            private String commentCount;
            private String express_rank;
            private int favorableRate;
            private String goods_rank;
            private String imgCount;
            private String likeCount;
            private String lowCount;
            private String medCount;
            private int reviewCount;
            private String service_rank;

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getExpress_rank() {
                return express_rank;
            }

            public void setExpress_rank(String express_rank) {
                this.express_rank = express_rank;
            }

            public int getFavorableRate() {
                return favorableRate;
            }

            public void setFavorableRate(int favorableRate) {
                this.favorableRate = favorableRate;
            }

            public String getGoods_rank() {
                return goods_rank;
            }

            public void setGoods_rank(String goods_rank) {
                this.goods_rank = goods_rank;
            }

            public String getImgCount() {
                return imgCount;
            }

            public void setImgCount(String imgCount) {
                this.imgCount = imgCount;
            }

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
            }

            public String getLowCount() {
                return lowCount;
            }

            public void setLowCount(String lowCount) {
                this.lowCount = lowCount;
            }

            public String getMedCount() {
                return medCount;
            }

            public void setMedCount(String medCount) {
                this.medCount = medCount;
            }

            public int getReviewCount() {
                return reviewCount;
            }

            public void setReviewCount(int reviewCount) {
                this.reviewCount = reviewCount;
            }

            public String getService_rank() {
                return service_rank;
            }

            public void setService_rank(String service_rank) {
                this.service_rank = service_rank;
            }
        }
    }
}
