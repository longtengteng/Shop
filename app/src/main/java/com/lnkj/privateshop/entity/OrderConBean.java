package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class OrderConBean {

    /**
     * status : 1
     * info : 订单确认
     * data : {"addr_info":{"address_id":"72","consignee":"赵林","address":"合肥路12222","province":"山东省","city":"临沂市","district":"兰山区","mobile":"15562909154","zipcode":"","is_default":"1"},"goods_list":[{"shop_id":"1","shop_name":"","goods":[{"goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","price":"120.00","weight":"900","is_selected":"1","is_valid":"1","spec":[{"color":"卡其色","size":[{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]}]},{"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","price":"39.00","weight":"100","is_selected":"1","is_valid":"1","spec":[{"color":"如图","size":[{"size_name":"XL","storage":"111","buy_number":"1","goods_spec_key":"31_14"}]},{"color":"白色","size":[{"size_name":"2XL","storage":"111","buy_number":"4","goods_spec_key":"15_25"}]}]}],"shop_express":"200.00"}],"total_goods_num":6,"total_goods_amount":"315.00","total_express":"200.00","total_amount":"515.00"}
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
         * addr_info : {"address_id":"72","consignee":"赵林","address":"合肥路12222","province":"山东省","city":"临沂市","district":"兰山区","mobile":"15562909154","zipcode":"","is_default":"1"}
         * goods_list : [{"shop_id":"1","shop_name":"","goods":[{"goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","price":"120.00","weight":"900","is_selected":"1","is_valid":"1","spec":[{"color":"卡其色","size":[{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]}]},{"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","price":"39.00","weight":"100","is_selected":"1","is_valid":"1","spec":[{"color":"如图","size":[{"size_name":"XL","storage":"111","buy_number":"1","goods_spec_key":"31_14"}]},{"color":"白色","size":[{"size_name":"2XL","storage":"111","buy_number":"4","goods_spec_key":"15_25"}]}]}],"shop_express":"200.00"}]
         * total_goods_num : 6
         * total_goods_amount : 315.00
         * total_express : 200.00
         * total_amount : 515.00
         */

        private AddrInfoBean addr_info;
        private int total_goods_num;
        private String total_goods_amount;
        private String total_express;
        private String total_amount;
        private List<GoodsListBean> goods_list;

        public AddrInfoBean getAddr_info() {
            return addr_info;
        }

        public void setAddr_info(AddrInfoBean addr_info) {
            this.addr_info = addr_info;
        }

        public int getTotal_goods_num() {
            return total_goods_num;
        }

        public void setTotal_goods_num(int total_goods_num) {
            this.total_goods_num = total_goods_num;
        }

        public String getTotal_goods_amount() {
            return total_goods_amount;
        }

        public void setTotal_goods_amount(String total_goods_amount) {
            this.total_goods_amount = total_goods_amount;
        }

        public String getTotal_express() {
            return total_express;
        }

        public void setTotal_express(String total_express) {
            this.total_express = total_express;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class AddrInfoBean {
            /**
             * address_id : 72
             * consignee : 赵林
             * address : 合肥路12222
             * province : 山东省
             * city : 临沂市
             * district : 兰山区
             * mobile : 15562909154
             * zipcode :
             * is_default : 1
             */

            private String address_id;
            private String consignee;
            private String address;
            private String province;
            private String city;
            private String district;
            private String mobile;
            private String zipcode;
            private String is_default;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }

        public static class GoodsListBean {
            /**
             * shop_id : 1
             * shop_name :
             * goods : [{"goods_id":"250","goods_name":"休闲男装 2017秋季新款男士大衣 韩版潮翻领中长","goods_img":"/Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg","price":"120.00","weight":"900","is_selected":"1","is_valid":"1","spec":[{"color":"卡其色","size":[{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]}]},{"goods_id":"254","goods_name":"朵咪家。实拍笑脸印花宽松长袖卫衣","goods_img":"/Uploads/Picture/Goods/2017-08-31/59a7a3942d361.jpg","price":"39.00","weight":"100","is_selected":"1","is_valid":"1","spec":[{"color":"如图","size":[{"size_name":"XL","storage":"111","buy_number":"1","goods_spec_key":"31_14"}]},{"color":"白色","size":[{"size_name":"2XL","storage":"111","buy_number":"4","goods_spec_key":"15_25"}]}]}]
             * shop_express : 200.00
             */

            private String shop_id;
            private String shop_name;
            private String shop_express;
            private List<GoodsBean> goods;
            private String speack;

            public String getSpeack() {
                return speack;
            }

            public void setSpeack(String speack) {
                this.speack = speack;
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

            public String getShop_express() {
                return shop_express;
            }

            public void setShop_express(String shop_express) {
                this.shop_express = shop_express;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * goods_id : 250
                 * goods_name : 休闲男装 2017秋季新款男士大衣 韩版潮翻领中长
                 * goods_img : /Uploads/Picture/Goods/2017-08-30/59a5ffdd0f6c9.jpg
                 * price : 120.00
                 * weight : 900
                 * is_selected : 1
                 * is_valid : 1
                 * spec : [{"color":"卡其色","size":[{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]}]
                 */

                private String goods_id;
                private String goods_name;
                private String goods_img;
                private String price;
                private String weight;
                private String is_selected;
                private String is_valid;
                private List<SpecBean> spec;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
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

                public List<SpecBean> getSpec() {
                    return spec;
                }

                public void setSpec(List<SpecBean> spec) {
                    this.spec = spec;
                }

                public static class SpecBean {
                    /**
                     * color : 卡其色
                     * size : [{"size_name":"5XL","storage":"999","buy_number":"1","goods_spec_key":"21_28"}]
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
                         * size_name : 5XL
                         * storage : 999
                         * buy_number : 1
                         * goods_spec_key : 21_28
                         */

                        private String size_name;
                        private String storage;
                        private String buy_number;
                        private String goods_spec_key;

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
                    }
                }
            }
        }
    }
}
