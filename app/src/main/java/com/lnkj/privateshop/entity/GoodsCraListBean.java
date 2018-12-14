package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class GoodsCraListBean {


    /**
     * data : [{"goods":[{"goods_id":"207","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_name":"新款韩版气质半身裙","is_selected":"1","is_valid":"1","price":"55.00","spec":[{"color":"蓝色","size":[{"buy_number":"2","goods_spec_key":"3_5","size_name":"XS","storage":"111"},{"buy_number":"1","goods_spec_key":"3_6","size_name":"S","storage":"111"},{"buy_number":"1","goods_spec_key":"3_7","size_name":"M","storage":"111"}]},{"color":"粉色","size":[{"buy_number":"1","goods_spec_key":"1_5","size_name":"XS","storage":"23"},{"buy_number":"2","goods_spec_key":"1_7","size_name":"M","storage":"111"},{"buy_number":"1","goods_spec_key":"1_6","size_name":"S","storage":"111"}]}],"weight":"100"}],"shop_id":"2","shop_name":"六年科技 "},{"goods":[{"goods_id":"211","goods_img":"/Uploads/Picture/Goods/2017-07-12/5965c20154c41.jpg","goods_name":"爆款套装 五个码 一件代发","is_selected":"1","is_valid":"1","price":"0.01","spec":[{"color":"红色","size":[{"buy_number":"2","goods_spec_key":"2_5","size_name":"XS","storage":"11"},{"buy_number":"3","goods_spec_key":"2_6","size_name":"S","storage":"22"}]}],"weight":"0"}],"shop_id":"1","shop_name":"商城自营"}]
     * info : 进货车列表
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
         * goods : [{"goods_id":"207","goods_img":"/Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg","goods_name":"新款韩版气质半身裙","is_selected":"1","is_valid":"1","price":"55.00","spec":[{"color":"蓝色","size":[{"buy_number":"2","goods_spec_key":"3_5","size_name":"XS","storage":"111"},{"buy_number":"1","goods_spec_key":"3_6","size_name":"S","storage":"111"},{"buy_number":"1","goods_spec_key":"3_7","size_name":"M","storage":"111"}]},{"color":"粉色","size":[{"buy_number":"1","goods_spec_key":"1_5","size_name":"XS","storage":"23"},{"buy_number":"2","goods_spec_key":"1_7","size_name":"M","storage":"111"},{"buy_number":"1","goods_spec_key":"1_6","size_name":"S","storage":"111"}]}],"weight":"100"}]
         * shop_id : 2
         * shop_name : 六年科技
         */

        private String shop_id;
        private String shop_name;
        private List<GoodsBean> goods;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_id : 207
             * goods_img : /Uploads/Picture/Goods/2017-07-11/596440911bb0a.jpg
             * goods_name : 新款韩版气质半身裙
             * is_selected : 1
             * is_valid : 1
             * price : 55.00
             * spec : [{"color":"蓝色","size":[{"buy_number":"2","goods_spec_key":"3_5","size_name":"XS","storage":"111"},{"buy_number":"1","goods_spec_key":"3_6","size_name":"S","storage":"111"},{"buy_number":"1","goods_spec_key":"3_7","size_name":"M","storage":"111"}]},{"color":"粉色","size":[{"buy_number":"1","goods_spec_key":"1_5","size_name":"XS","storage":"23"},{"buy_number":"2","goods_spec_key":"1_7","size_name":"M","storage":"111"},{"buy_number":"1","goods_spec_key":"1_6","size_name":"S","storage":"111"}]}]
             * weight : 100
             */

            private String goods_id;
            private String goods_img;
            private String goods_name;
            private String is_selected;
            private String is_valid;
            private String price;
            private String weight;
            private List<SpecBean> spec;
            private String buy_total_number;

            public String getBuy_total_number() {
                return buy_total_number;
            }

            public void setBuy_total_number(String buy_total_number) {
                this.buy_total_number = buy_total_number;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getIs_selected() {
                return is_selected;
            }

            public void setIs_selected(String is_selected) {
                this.is_selected = is_selected;
            }

            public String getIs_valid() {
                return is_valid;
            }

            public void setIs_valid(String is_valid) {
                this.is_valid = is_valid;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public List<SpecBean> getSpec() {
                return spec;
            }

            public void setSpec(List<SpecBean> spec) {
                this.spec = spec;
            }

            public static class SpecBean {
                /**
                 * color : 蓝色
                 * size : [{"buy_number":"2","goods_spec_key":"3_5","size_name":"XS","storage":"111"},{"buy_number":"1","goods_spec_key":"3_6","size_name":"S","storage":"111"},{"buy_number":"1","goods_spec_key":"3_7","size_name":"M","storage":"111"}]
                 */

                private String color;
                private List<SizeBean> size;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public List<SizeBean> getSize() {
                    return size;
                }

                public void setSize(List<SizeBean> size) {
                    this.size = size;
                }

                public static class SizeBean {
                    /**
                     * buy_number : 2
                     * goods_spec_key : 3_5
                     * size_name : XS
                     * storage : 111
                     */

                    private String buy_number;
                    private String goods_spec_key;
                    private String size_name;
                    private String storage;

                    public String getBuy_number() {
                        return buy_number;
                    }

                    public void setBuy_number(String buy_number) {
                        this.buy_number = buy_number;
                    }

                    public String getGoods_spec_key() {
                        return goods_spec_key;
                    }

                    public void setGoods_spec_key(String goods_spec_key) {
                        this.goods_spec_key = goods_spec_key;
                    }

                    public String getSize_name() {
                        return size_name;
                    }

                    public void setSize_name(String size_name) {
                        this.size_name = size_name;
                    }

                    public String getStorage() {
                        return storage;
                    }

                    public void setStorage(String storage) {
                        this.storage = storage;
                    }
                }
            }
        }
    }
}
