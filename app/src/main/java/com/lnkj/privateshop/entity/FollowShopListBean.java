package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class FollowShopListBean {

    /**
     * status : 1
     * info : 收藏店铺列表
     * data : [{"fav_id":"84","shop_info":{"shop_id":"64","shop_logo":"/Uploads/Picture/Shop/2017-07-11/59642b4666feb.png","shop_name":"接口店铺","province":"山东省","city":"临沂市","address":""}},{"fav_id":"83","shop_info":{"shop_id":"1","shop_logo":"","shop_name":"六牛科技自营","province":"山东省","city":"临沂市","address":"临沂市河东区山东六牛网络科技有限公司"}}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fav_id : 84
         * shop_info : {"shop_id":"64","shop_logo":"/Uploads/Picture/Shop/2017-07-11/59642b4666feb.png","shop_name":"接口店铺","province":"山东省","city":"临沂市","address":""}
         */

        private String fav_id;
        private ShopInfoBean shop_info;
        private Boolean ischeck=false;

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }
        public Boolean getIscheck() {
            if (ischeck==null){
                return false;
            }else {
                return ischeck;
            }
        }
        public String getFav_id() {
            return fav_id;
        }

        public void setFav_id(String fav_id) {
            this.fav_id = fav_id;
        }

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public static class ShopInfoBean {
            /**
             * shop_id : 64
             * shop_logo : /Uploads/Picture/Shop/2017-07-11/59642b4666feb.png
             * shop_name : 接口店铺
             * province : 山东省
             * city : 临沂市
             * address :
             */

            private String shop_id;
            private String shop_logo;
            private String shop_name;
            private String province;
            private String city;
            private String address;



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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
