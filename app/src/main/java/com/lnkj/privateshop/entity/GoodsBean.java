package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class GoodsBean implements Serializable {


    /**
     * data : {"goods_attr":[{"attr_id":"28","attr_name":"风格","attr_value":"日韩"},{"attr_id":"29","attr_name":"厚度","attr_value":"适中"},{"attr_id":"36","attr_name":"面料","attr_value":"涤纶"},{"attr_id":"44","attr_name":"季节","attr_value":"春夏"}],"goods_desc":[],"goods_gallery":[],"goods_info":{"add_time":"1500538652","cat_id":"171","click_count":"38","collect_num":"1","comment_num":"0","goods_description":"","goods_id":"226","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","is_favorite":0,"pack_price":"74.00","sale_num":"0","shop_id":"85","shop_name":"","shop_price":"78.00","storage":"55"},"shop_info":{"month_goods_count":0,"month_sale_count":"0","open_time":"2017-08-17","shop_addr":"山东·临沂·","shop_id":"85","shop_logo":"/Uploads/Picture/Shop/98/20170817_160351_15029570314925_8584.jpg","shop_name":"梦梦猪","supplement":0}}
     * info : 商品详情
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

    public static class DataBean implements Serializable {

        /**
         * goods_gallery : [{"img_id":"109","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg","is_default":"1"},{"img_id":"110","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bcbd80e.jpg","is_default":"0"},{"img_id":"111","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bd19369.jpg","is_default":"0"},{"img_id":"112","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bd34dcf.jpg","is_default":"0"}]
         * goods_attr : [{"attr_id":"3","attr_name":"材质","attr_value":"硅胶"},{"attr_id":"4","attr_name":"生产地","attr_value":"山东"}]
         * goods_desc : [{"img_id":"109","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg","is_default":"1"},{"img_id":"110","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bcbd80e.jpg","is_default":"0"},{"img_id":"111","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bd19369.jpg","is_default":"0"},{"img_id":"112","goods_id":"8","image_path":"/Uploads/Picture/Goods/2018-12-17/5c1796bd34dcf.jpg","is_default":"0"}]
         * goods_spec : [{"spec_id":"1","spec_name":"尺寸","type_id":"1","shop_id":"0","sort":"50","item_array":[{"spec_item_id":"1","spec_item_name":"40"}]},{"spec_id":"2","spec_name":"颜色","type_id":"1","shop_id":"0","sort":"50","item_array":[{"spec_item_id":"3","spec_item_name":"白"},{"spec_item_id":"4","spec_item_name":"红"}]}]
         * goods_info : {"goods_id":"8","goods_sn":"AR181217055215","goods_name":"智能充气娃娃","market_price":"3000.00","shop_price":"2000.00","goods_img":"/Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg","sale_num":"0","comment_num":"0","collect_num":"0","get_points":"2000","cat_id":"1","add_time":"1545049805","update_time":"1545135693","click_count":0,"weight":"3000","is_free_shipping":"0","shop_id":"0","is_favorite":0,"day_ago":1}
         * shop_info : {"shop_id":"0","shop_name":"自营店","shop_logo":"","province":"山东省","city":"临沂市","country":"罗庄区","rank":4.9,"express_rank":4.9,"service_rank":4.9}
         */

        private GoodsInfoBean goods_info;
        private ShopInfoBean shop_info;
        private List<GoodsGalleryBean> goods_gallery;
        private List<GoodsAttrBean> goods_attr;
        private List<GoodsDescBean> goods_desc;
        private List<GoodsSpecBean> goods_spec;

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public List<GoodsGalleryBean> getGoods_gallery() {
            return goods_gallery;
        }

        public void setGoods_gallery(List<GoodsGalleryBean> goods_gallery) {
            this.goods_gallery = goods_gallery;
        }

        public List<GoodsAttrBean> getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
            this.goods_attr = goods_attr;
        }

        public List<GoodsDescBean> getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(List<GoodsDescBean> goods_desc) {
            this.goods_desc = goods_desc;
        }

        public List<GoodsSpecBean> getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(List<GoodsSpecBean> goods_spec) {
            this.goods_spec = goods_spec;
        }

        public static class GoodsInfoBean implements Serializable {
            /**
             * goods_id : 8
             * goods_sn : AR181217055215
             * goods_name : 智能充气娃娃
             * market_price : 3000.00
             * shop_price : 2000.00
             * goods_img : /Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg
             * sale_num : 0
             * comment_num : 0
             * collect_num : 0
             * get_points : 2000
             * cat_id : 1
             * add_time : 1545049805
             * update_time : 1545135693
             * click_count : 0
             * weight : 3000
             * is_free_shipping : 0
             * shop_id : 0
             * is_favorite : 0
             * day_ago : 1
             */

            private String goods_id;
            private String goods_sn;
            private String goods_name;
            private String market_price;
            private String shop_price;
            private String goods_img;
            private String sale_num;
            private String comment_num;
            private String collect_num;
            private String get_points;
            private String cat_id;
            private String add_time;
            private String update_time;
            private int click_count;
            private String weight;
            private String is_free_shipping;
            private String shop_id;
            private int is_favorite;
            private int day_ago;
            /**
             * storage : 200
             */

            private String storage;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getSale_num() {
                return sale_num;
            }

            public void setSale_num(String sale_num) {
                this.sale_num = sale_num;
            }

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public String getGet_points() {
                return get_points;
            }

            public void setGet_points(String get_points) {
                this.get_points = get_points;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getClick_count() {
                return click_count;
            }

            public void setClick_count(int click_count) {
                this.click_count = click_count;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public int getDay_ago() {
                return day_ago;
            }

            public void setDay_ago(int day_ago) {
                this.day_ago = day_ago;
            }

            public String getStorage() {
                return storage;
            }

            public void setStorage(String storage) {
                this.storage = storage;
            }
        }

        public static class ShopInfoBean {
            /**
             * shop_id : 0
             * shop_name : 自营店
             * shop_logo :
             * province : 山东省
             * city : 临沂市
             * country : 罗庄区
             * rank : 4.9
             * express_rank : 4.9
             * service_rank : 4.9
             */

            private String shop_id;
            private String shop_name;
            private String shop_logo;
            private String province;
            private String city;
            private String country;
            private double rank;
            private double express_rank;
            private double service_rank;

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

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
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

            public double getRank() {
                return rank;
            }

            public void setRank(double rank) {
                this.rank = rank;
            }

            public double getExpress_rank() {
                return express_rank;
            }

            public void setExpress_rank(double express_rank) {
                this.express_rank = express_rank;
            }

            public double getService_rank() {
                return service_rank;
            }

            public void setService_rank(double service_rank) {
                this.service_rank = service_rank;
            }
        }

        public static class GoodsGalleryBean {
            /**
             * img_id : 109
             * goods_id : 8
             * image_path : /Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg
             * is_default : 1
             */

            private String img_id;
            private String goods_id;
            private String image_path;
            private String is_default;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getImage_path() {
                return image_path;
            }

            public void setImage_path(String image_path) {
                this.image_path = image_path;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }

        public static class GoodsAttrBean implements Serializable {
            /**
             * attr_id : 3
             * attr_name : 材质
             * attr_value : 硅胶
             */

            private String attr_id;
            private String attr_name;
            private String attr_value;

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }
        }

        public static class GoodsDescBean {
            /**
             * img_id : 109
             * goods_id : 8
             * image_path : /Uploads/Picture/Goods/2018-12-17/5c17969ca3dfc.jpg
             * is_default : 1
             */

            private String img_id;
            private String goods_id;
            private String image_path;
            private String is_default;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getImage_path() {
                return image_path;
            }

            public void setImage_path(String image_path) {
                this.image_path = image_path;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }

        public static class GoodsSpecBean implements Serializable {
            /**
             * spec_id : 1
             * spec_name : 尺寸
             * type_id : 1
             * shop_id : 0
             * sort : 50
             * item_array : [{"spec_item_id":"1","spec_item_name":"40"}]
             */

            private String spec_id;
            private String spec_name;
            private String type_id;
            private String shop_id;
            private String sort;
            private List<ItemArrayBean> item_array;

            public String getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(String spec_id) {
                this.spec_id = spec_id;
            }

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public List<ItemArrayBean> getItem_array() {
                return item_array;
            }

            public void setItem_array(List<ItemArrayBean> item_array) {
                this.item_array = item_array;
            }

            public static class ItemArrayBean implements Serializable {
                /**
                 * spec_item_id : 1
                 * spec_item_name : 40
                 */
                private boolean check;

                public boolean isCheck() {
                    return check;
                }

                public void setCheck(boolean check) {
                    this.check = check;
                }

                private String spec_item_id;
                private String spec_item_name;

                public String getSpec_item_id() {
                    return spec_item_id;
                }

                public void setSpec_item_id(String spec_item_id) {
                    this.spec_item_id = spec_item_id;
                }

                public String getSpec_item_name() {
                    return spec_item_name;
                }

                public void setSpec_item_name(String spec_item_name) {
                    this.spec_item_name = spec_item_name;
                }
            }
        }
    }
}
