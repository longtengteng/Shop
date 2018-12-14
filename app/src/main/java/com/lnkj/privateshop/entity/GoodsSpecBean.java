package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class GoodsSpecBean {


    /**
     * data : [{"pack_price":"0.00","price":"78.00","spec_key":"2_14","spec_name":"红色 XL","store_count":"0"}]
     * info : 规格参数
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
         * pack_price : 0.00
         * price : 78.00
         * spec_key : 2_14
         * spec_name : 红色 XL
         * store_count : 0
         */

        private String pack_price;
        private String price;
        private String spec_key;
        private String spec_name;
        private String store_count;
        private int has_number;

        private int  nummber=0;

        public int getNummber() {
            return nummber;
        }

        public int getHas_number() {
            return has_number;
        }

        public void setHas_number(int has_number) {
            this.has_number = has_number;
        }

        public void setNummber(int nummber) {
            this.nummber = nummber;
        }

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpec_key() {
            return spec_key;
        }

        public void setSpec_key(String spec_key) {
            this.spec_key = spec_key;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }
    }
}
