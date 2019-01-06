package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class EditShopBean {

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
         * shop_id : 1
         * shop_type : 1
         * shop_type_name : 实体店
         * shop_logo : /Uploads/Picture/Shop/2017-09-13/59b876cfc6968.jpg
         * shop_real_pic : /Uploads/Picture/Shop/2017-09-13/59b88372957cc.jpg
         * shop_name : 六牛科技自营
         * user_id : 104
         * contacts_name : 六牛科技
         * user_mobile : 15665856595
         * province : 山东省
         * city : 临沂市
         * basic_amount : 9
         * allow_mixture : 0
         * allow_return : 1
         * add_time : 1469426288
         * description :
         * retail_amount : 1
         * is_bond : 1
         * shop_score : 2000000
         * category_id : 153
         * category_name : 男装
         * address : 临沂市河东区山东六牛网络科技有限公司
         * market_name :
         * floor_number : null
         */
        private String country;
        private String lat;

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        private String lng;
        private String shop_id;
        private String shop_type;
        private String shop_type_name;
        private String shop_logo;
        private String shop_real_pic;
        private String shop_name;
        private String user_id;
        private String contacts_name;
        private String user_mobile;
        private String province;
        private String city;
        private String basic_amount;
        private String allow_mixture;
        private String allow_return;
        private String add_time;
        private String description;
        private String retail_amount;
        private String is_bond;
        private String shop_score;
        private String category_id;
        private String category_name;
        private String address;
        private String market_name;
        private String floor_number;
        private String room_number;
        private String company_name;
        private String company_website;
        private String reg_number;
        private String factory_pic;
        private String plant_pic;
        private String license;
        private Shop_grade shop_grade;

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getRoom_number() {
            return room_number;
        }

        public void setRoom_number(String room_number) {
            this.room_number = room_number;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_website() {
            return company_website;
        }

        public void setCompany_website(String company_website) {
            this.company_website = company_website;
        }

        public String getReg_number() {
            return reg_number;
        }

        public void setReg_number(String reg_number) {
            this.reg_number = reg_number;
        }

        public String getFactory_pic() {
            return factory_pic;
        }

        public void setFactory_pic(String factory_pic) {
            this.factory_pic = factory_pic;
        }

        public String getPlant_pic() {
            return plant_pic;
        }

        public void setPlant_pic(String plant_pic) {
            this.plant_pic = plant_pic;
        }

        public Shop_grade getShop_grade() {
            return shop_grade;
        }

        public void setShop_grade(Shop_grade shop_grade) {
            this.shop_grade = shop_grade;
        }

        public static class Shop_grade {
            private String type;
            private int num;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

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

        public String getShop_type_name() {
            return shop_type_name;
        }

        public void setShop_type_name(String shop_type_name) {
            this.shop_type_name = shop_type_name;
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

        public String getBasic_amount() {
            return basic_amount;
        }

        public void setBasic_amount(String basic_amount) {
            this.basic_amount = basic_amount;
        }

        public String getAllow_mixture() {
            return allow_mixture;
        }

        public void setAllow_mixture(String allow_mixture) {
            this.allow_mixture = allow_mixture;
        }

        public String getAllow_return() {
            return allow_return;
        }

        public void setAllow_return(String allow_return) {
            this.allow_return = allow_return;
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

        public String getRetail_amount() {
            return retail_amount;
        }

        public void setRetail_amount(String retail_amount) {
            this.retail_amount = retail_amount;
        }

        public String getIs_bond() {
            return is_bond;
        }

        public void setIs_bond(String is_bond) {
            this.is_bond = is_bond;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
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

        public String getFloor_number() {
            return floor_number;
        }

        public void setFloor_number(String floor_number) {
            this.floor_number = floor_number;
        }
    }
}
