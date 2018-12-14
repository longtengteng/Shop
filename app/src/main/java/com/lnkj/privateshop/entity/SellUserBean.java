package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class SellUserBean {

    /**
     * status : 1
     * info : 店铺信息
     * data : {"shop_name":"六牛科技自营","shop_logo":"","is_set_template":1,"is_pay_bond":0}
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
         * shop_name : 六牛科技自营
         * shop_logo :
         * is_set_template : 1
         * is_pay_bond : 0
         */

        private String shop_name;
        private String shop_logo;
        private int is_set_template;
        private int is_pay_bond;
        private String audlt_status;
        private String shop_type;
        private String url_type;

        public String getUrl_type() {
            return url_type;
        }

        public void setUrl_type(String url_type) {
            this.url_type = url_type;
        }

        public String getAudlt_status() {
            return audlt_status;
        }

        public void setAudlt_status(String audlt_status) {
            this.audlt_status = audlt_status;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public int getIs_set_template() {
            return is_set_template;
        }

        public void setIs_set_template(int is_set_template) {
            this.is_set_template = is_set_template;
        }

        public int getIs_pay_bond() {
            return is_pay_bond;
        }

        public void setIs_pay_bond(int is_pay_bond) {
            this.is_pay_bond = is_pay_bond;
        }
    }
}
