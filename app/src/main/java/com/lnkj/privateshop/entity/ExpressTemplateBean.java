package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class ExpressTemplateBean {


    /**
     * data : [{"add_money":"30.00","add_time":"2017-10-07 08:24:27","add_weight":"1000","express_template_id":"409","express_template_name":"全国其他区域","first_money":"30.00","first_weight":"1000","is_default":"1","region_id":"","region_name":"","shop_id":"108"},{"add_money":"25.00","add_time":"2017-10-07 08:24:27","add_weight":"1000","express_template_id":"410","express_template_name":"河北省,内蒙古自治区,辽宁省","first_money":"25.00","first_weight":"1000","region_id":"0,0,0","region_name":"河北省,内蒙古自治区,辽宁省","shop_id":"108"},{"add_money":"25.00","add_time":"2017-10-07 08:24:27","add_weight":"1000","express_template_id":"411","express_template_name":"吉林省,黑龙江省,上海市","first_money":"25.00","first_weight":"1000","region_id":"0,0,0","region_name":"吉林省,黑龙江省,上海市","shop_id":"108"}]
     * info :
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
         * add_money : 30.00
         * add_time : 2017-10-07 08:24:27
         * add_weight : 1000
         * express_template_id : 409
         * express_template_name : 全国其他区域
         * first_money : 30.00
         * first_weight : 1000
         * is_default : 1
         * region_id :
         * region_name :
         * shop_id : 108
         */

        private String add_money;
        private String add_time;
        private String add_weight;
        private String express_template_id;
        private String express_template_name;
        private String first_money;
        private String first_weight;
        private String is_default;
        private String region_id;
        private String region_name;
        private String shop_id;

        public String getAdd_money() {
            return add_money;
        }

        public void setAdd_money(String add_money) {
            this.add_money = add_money;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_weight() {
            return add_weight;
        }

        public void setAdd_weight(String add_weight) {
            this.add_weight = add_weight;
        }

        public String getExpress_template_id() {
            return express_template_id;
        }

        public void setExpress_template_id(String express_template_id) {
            this.express_template_id = express_template_id;
        }

        public String getExpress_template_name() {
            return express_template_name;
        }

        public void setExpress_template_name(String express_template_name) {
            this.express_template_name = express_template_name;
        }

        public String getFirst_money() {
            return first_money;
        }

        public void setFirst_money(String first_money) {
            this.first_money = first_money;
        }

        public String getFirst_weight() {
            return first_weight;
        }

        public void setFirst_weight(String first_weight) {
            this.first_weight = first_weight;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }
    }
}
