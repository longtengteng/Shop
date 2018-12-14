package com.lnkj.privateshop.entity;

/**
 * 作者：赵林 on 2017/10/31 0031.
 */

public class ShopGradeBean {


    /**
     * data : {"next_upgrade":100,"shop_level":{"num":1,"type":"G1"}}
     * info : 店铺等级
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
         * next_upgrade : 100
         * shop_level : {"num":1,"type":"G1"}
         */

        private int next_upgrade;
        private ShopLevelBean shop_level;

        public int getNext_upgrade() {
            return next_upgrade;
        }

        public void setNext_upgrade(int next_upgrade) {
            this.next_upgrade = next_upgrade;
        }

        public ShopLevelBean getShop_level() {
            return shop_level;
        }

        public void setShop_level(ShopLevelBean shop_level) {
            this.shop_level = shop_level;
        }

        public static class ShopLevelBean {
            /**
             * num : 1
             * type : G1
             */

            private int num;
            private String type;

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
    }
}
