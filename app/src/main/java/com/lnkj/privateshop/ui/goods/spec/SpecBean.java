package com.lnkj.privateshop.ui.goods.spec;

import com.lnkj.privateshop.entity.GoodsBean;

import java.io.Serializable;

public class SpecBean implements Serializable{

    /**
     * id : 89
     * goods_id : 2
     * key : 1_3
     * key_name : 尺寸:40 颜色:白
     * price : 111.00
     * store_count : 11
     */
    private int status;
    private String info;
    private SpecBean.DataBean data;

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

    public SpecBean.DataBean getData() {
        return data;
    }

    public void setData(SpecBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String goods_id;
        private String key;
        private String key_name;
        private String price;
        private String store_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }
    }

}
