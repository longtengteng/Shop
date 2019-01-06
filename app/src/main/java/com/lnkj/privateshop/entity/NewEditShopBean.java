package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class NewEditShopBean {

    /**
     * status : 1
     * info : 店铺资料
     * data : {"shop_id":"1","shop_type":"1","shop_type_name":"实体店","shop_logo":"/Uploads/Picture/Shop/2017-09-13/59b876cfc6968.jpg","shop_real_pic":"/Uploads/Picture/Shop/2017-09-13/59b88372957cc.jpg","shop_name":"六牛科技自营","user_id":"104","contacts_name":"六牛科技","user_mobile":"15665856595","province":"山东省","city":"临沂市","basic_amount":"9","allow_mixture":"0","allow_return":"1","add_time":"1469426288","description":"","retail_amount":"1","is_bond":"1","shop_score":"2000000","category_id":"153","category_name":"男装","address":"临沂市河东区山东六牛网络科技有限公司","market_name":"","floor_number":null}
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
         * shop_id : 4
         * shop_type : 1
         * shop_logo :
         * shop_real_pic :
         * shop_name : 65号店
         * user_id : 65
         * contacts_name :
         * user_mobile :
         * province : 山东省
         * city : 济南市
         * country : 历下区
         * province_id : 23
         * city_id : 122
         * country_id : 421
         * address : 历下区地址
         * add_time : 0
         * description :
         * shop_score : 1
         * shop_type_name : 省级代理
         * lat : 77.33
         * lng : 118.233
         */

        private String shop_id;
        private String shop_type;
        private String shop_logo;
        private String shop_real_pic;
        private String shop_name;
        private String user_id;
        private String contacts_name;
        private String user_mobile;
        private String province;
        private String city;
        private String country;
        private int province_id;
        private String city_id;
        private String country_id;
        private String address;
        private String add_time;
        private String description;
        private String shop_score;
        private String shop_type_name;
        private String lat;
        private String lng;

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

        public String getShop_real_pic() {
            return shop_real_pic;
        }

        public void setShop_real_pic(String shop_real_pic) {
            this.shop_real_pic = shop_real_pic;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContacts_name() {
            return contacts_name;
        }

        public void setContacts_name(String contacts_name) {
            this.contacts_name = contacts_name;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public String getShop_type_name() {
            return shop_type_name;
        }

        public void setShop_type_name(String shop_type_name) {
            this.shop_type_name = shop_type_name;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }
}
