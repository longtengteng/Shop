package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class SeachShopBean {

    /**
     * data : {"keyword":"","list":[{"add_time":"2016-07-25 13:58:08","address":"临沂市河东区山东六牛网络科技有限公司","adress":"山东省临沂市临沂市河东区山东六牛网络科技有限公司","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"1","shop_logo":"/Uploads/Picture/Shop/2017-09-13/59b876cfc6968.jpg","shop_name":"六牛科技自营","shop_real_pic":"/Uploads/Picture/Shop/2017-09-13/59b88372957cc.jpg","shop_type":"1","supplement":"0"},{"add_time":"2017-09-28 16:04:11","address":"沃尔沃路","adress":"山东省临沂市沃尔沃路","city":"临沂市","comment_rank":"0","goods_counts":"0","month_goods_count":"2","province":"山东省","shop_id":"106","shop_logo":"/Uploads/Picture/Shop/104/20170928_160517_15065859170309_6588.jpg","shop_name":"军军小铺","shop_real_pic":"/Uploads/Picture/Shop/104/20170928_160517_15065859170310_3109.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-09-29 14:49:06","address":"兰山","adress":"山东省临沂市兰山","city":"临沂市","comment_rank":"0","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"108","shop_logo":"/Uploads/Picture/Shop/123/20170929_144906_15066677464037_7156.jpg","shop_name":"安卓测试店铺","shop_real_pic":"/Uploads/Picture/Shop/123/20170929_144906_15066677464038_4543.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-09-29 14:54:51","address":"罗庄区","adress":"山东省临沂市罗庄区","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"109","shop_logo":"/Uploads/Picture/Shop/122/20170929_145452_15066680920999_8910.jpg","shop_name":"iOS测试店铺","shop_real_pic":"/Uploads/Picture/Shop/122/20170929_145452_15066680921000_1940.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-10-07 10:37:12","address":"","adress":"山东省临沂市","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"5","province":"山东省","shop_id":"116","shop_logo":"/Uploads/Picture/Shop/126/20171007_103712_15073438325162_5057.jpg","shop_name":"测试iOS店铺","shop_real_pic":"/Uploads/Picture/Shop/126/20171007_103712_15073438325164_2640.jpg","shop_type":"1","supplement":"0"},{"add_time":"2017-10-07 10:37:18","address":"","adress":"山东省临沂市","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"4","province":"山东省","shop_id":"117","shop_logo":"/Uploads/Picture/Shop/125/20171007_103718_15073438383160_6805.jpg","shop_name":"测试安卓实体店","shop_real_pic":"/Uploads/Picture/Shop/125/20171007_103718_15073438383162_3095.jpg","shop_type":"1","supplement":"0"}]}
     * info :
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
         * keyword :
         * list : [{"add_time":"2016-07-25 13:58:08","address":"临沂市河东区山东六牛网络科技有限公司","adress":"山东省临沂市临沂市河东区山东六牛网络科技有限公司","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"1","shop_logo":"/Uploads/Picture/Shop/2017-09-13/59b876cfc6968.jpg","shop_name":"六牛科技自营","shop_real_pic":"/Uploads/Picture/Shop/2017-09-13/59b88372957cc.jpg","shop_type":"1","supplement":"0"},{"add_time":"2017-09-28 16:04:11","address":"沃尔沃路","adress":"山东省临沂市沃尔沃路","city":"临沂市","comment_rank":"0","goods_counts":"0","month_goods_count":"2","province":"山东省","shop_id":"106","shop_logo":"/Uploads/Picture/Shop/104/20170928_160517_15065859170309_6588.jpg","shop_name":"军军小铺","shop_real_pic":"/Uploads/Picture/Shop/104/20170928_160517_15065859170310_3109.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-09-29 14:49:06","address":"兰山","adress":"山东省临沂市兰山","city":"临沂市","comment_rank":"0","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"108","shop_logo":"/Uploads/Picture/Shop/123/20170929_144906_15066677464037_7156.jpg","shop_name":"安卓测试店铺","shop_real_pic":"/Uploads/Picture/Shop/123/20170929_144906_15066677464038_4543.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-09-29 14:54:51","address":"罗庄区","adress":"山东省临沂市罗庄区","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"0","province":"山东省","shop_id":"109","shop_logo":"/Uploads/Picture/Shop/122/20170929_145452_15066680920999_8910.jpg","shop_name":"iOS测试店铺","shop_real_pic":"/Uploads/Picture/Shop/122/20170929_145452_15066680921000_1940.jpg","shop_type":"3","supplement":"0"},{"add_time":"2017-10-07 10:37:12","address":"","adress":"山东省临沂市","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"5","province":"山东省","shop_id":"116","shop_logo":"/Uploads/Picture/Shop/126/20171007_103712_15073438325162_5057.jpg","shop_name":"测试iOS店铺","shop_real_pic":"/Uploads/Picture/Shop/126/20171007_103712_15073438325164_2640.jpg","shop_type":"1","supplement":"0"},{"add_time":"2017-10-07 10:37:18","address":"","adress":"山东省临沂市","city":"临沂市","comment_rank":"5","goods_counts":"0","month_goods_count":"4","province":"山东省","shop_id":"117","shop_logo":"/Uploads/Picture/Shop/125/20171007_103718_15073438383160_6805.jpg","shop_name":"测试安卓实体店","shop_real_pic":"/Uploads/Picture/Shop/125/20171007_103718_15073438383162_3095.jpg","shop_type":"1","supplement":"0"}]
         */

        private String keyword;
        private List<ListBean> list;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * add_time : 2016-07-25 13:58:08
             * address : 临沂市河东区山东六牛网络科技有限公司
             * adress : 山东省临沂市临沂市河东区山东六牛网络科技有限公司
             * city : 临沂市
             * comment_rank : 5
             * goods_counts : 0
             * month_goods_count : 0
             * province : 山东省
             * shop_id : 1
             * shop_logo : /Uploads/Picture/Shop/2017-09-13/59b876cfc6968.jpg
             * shop_name : 六牛科技自营
             * shop_real_pic : /Uploads/Picture/Shop/2017-09-13/59b88372957cc.jpg
             * shop_type : 1
             * supplement : 0
             */

            private String add_time;
            private String address;
            private String adress;
            private String city;
            private String comment_rank;
            private String goods_counts;
            private String month_goods_count;
            private String province;
            private String shop_id;
            private String shop_logo;
            private String shop_name;
            private String shop_real_pic;
            private String shop_type;
            private String supplement;

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

            public String getAdress() {
                return adress;
            }

            public void setAdress(String adress) {
                this.adress = adress;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getComment_rank() {
                return comment_rank;
            }

            public void setComment_rank(String comment_rank) {
                this.comment_rank = comment_rank;
            }

            public String getGoods_counts() {
                return goods_counts;
            }

            public void setGoods_counts(String goods_counts) {
                this.goods_counts = goods_counts;
            }

            public String getMonth_goods_count() {
                return month_goods_count;
            }

            public void setMonth_goods_count(String month_goods_count) {
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

            public String getSupplement() {
                return supplement;
            }

            public void setSupplement(String supplement) {
                this.supplement = supplement;
            }
        }
    }
}
