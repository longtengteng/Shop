package com.lnkj.privateshop.entity;

import java.util.List;

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
         * shop_id : 0
         * shop_logo :
         * shop_real_pic :
         * shop_name : 自营店
         * province : 山东省
         * city : 临沂市
         * country : 罗庄区
         * address : 这是地址
         * has_focus : 0
         * notice : 暂无公告
         * cat_info : [{"cat_id":"1","cat_name":"智能设备"},{"cat_id":"6","cat_name":"孕婴服饰"}]
         */

        private String shop_id;
        private String shop_logo;
        private String shop_real_pic;
        private String shop_name;
        private String province;
        private String city;
        private String country;
        private String address;
        private int has_focus;
        private String notice;
        private List<CatInfoBean> cat_info;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getHas_focus() {
            return has_focus;
        }

        public void setHas_focus(int has_focus) {
            this.has_focus = has_focus;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public List<CatInfoBean> getCat_info() {
            return cat_info;
        }

        public void setCat_info(List<CatInfoBean> cat_info) {
            this.cat_info = cat_info;
        }

        public static class CatInfoBean {
            /**
             * cat_id : 1
             * cat_name : 智能设备
             */

            private String cat_id;
            private String cat_name;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }
        }
    }
}
