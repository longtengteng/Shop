package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class GoodsEditBean {


    /**
     * status : 0
     * info : 商品详情
     * data : {"goods_id":"243","goods_name":"休闲长装T仔爆版","pack_price":"20.00","shop_price":"25.00","cat_id":"204","goods_description":"","cat_name":"半身裙","selected_color":[{"color_id":20,"color_name":"紫色"},{"color_id":1,"color_name":"粉色"},{"color_id":21,"color_name":"卡其色"}],"selected_size":[{"size_id":5,"size_name":"XS"},{"size_id":7,"size_name":"M"}],"selected_attr":[{"attr_id":"28","attr_value":"大众"},{"attr_id":"29","attr_value":"偏薄"},{"attr_id":"36","attr_value":"雪纺蕾丝"}],"img_list":[{"img_id":"920","image_path":"/Uploads/Picture/goods/1/20170727_175144_15011491040343_2095.jpg","is_default":"1"},{"img_id":"919","image_path":"/Uploads/Picture/goods/1/20170727_175144_15011491040173_9233.jpg","is_default":"0"}]}
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
         * goods_id : 243
         * goods_name : 休闲长装T仔爆版
         * pack_price : 20.00
         * shop_price : 25.00
         * cat_id : 204
         * goods_description :
         * cat_name : 半身裙
         * selected_color : [{"color_id":20,"color_name":"紫色"},{"color_id":1,"color_name":"粉色"},{"color_id":21,"color_name":"卡其色"}]
         * selected_size : [{"size_id":5,"size_name":"XS"},{"size_id":7,"size_name":"M"}]
         * selected_attr : [{"attr_id":"28","attr_value":"大众"},{"attr_id":"29","attr_value":"偏薄"},{"attr_id":"36","attr_value":"雪纺蕾丝"}]
         * img_list : [{"img_id":"920","image_path":"/Uploads/Picture/goods/1/20170727_175144_15011491040343_2095.jpg","is_default":"1"},{"img_id":"919","image_path":"/Uploads/Picture/goods/1/20170727_175144_15011491040173_9233.jpg","is_default":"0"}]
         */

        private String goods_id;
        private String goods_name;
        private String pack_price;
        private String shop_price;
        private String cat_id;
        private String goods_description;
        private String cat_name;
        private List<SelectedColorBean> selected_color;
        private List<SelectedSizeBean> selected_size;
        private List<SelectedAttrBean> selected_attr;
        private List<ImgListBean> img_list;
        private String weight;
        private String is_made = "" ;

        public String getIs_made() {
            return is_made;
        }

        public void setIs_made(String is_made) {
            this.is_made = is_made;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

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

        public String getPack_price() {
            return pack_price;
        }

        public void setPack_price(String pack_price) {
            this.pack_price = pack_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getGoods_description() {
            return goods_description;
        }

        public void setGoods_description(String goods_description) {
            this.goods_description = goods_description;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public List<SelectedColorBean> getSelected_color() {
            return selected_color;
        }

        public void setSelected_color(List<SelectedColorBean> selected_color) {
            this.selected_color = selected_color;
        }

        public List<SelectedSizeBean> getSelected_size() {
            return selected_size;
        }

        public void setSelected_size(List<SelectedSizeBean> selected_size) {
            this.selected_size = selected_size;
        }

        public List<SelectedAttrBean> getSelected_attr() {
            return selected_attr;
        }

        public void setSelected_attr(List<SelectedAttrBean> selected_attr) {
            this.selected_attr = selected_attr;
        }

        public List<ImgListBean> getImg_list() {
            return img_list;
        }

        public void setImg_list(List<ImgListBean> img_list) {
            this.img_list = img_list;
        }

        public static class SelectedColorBean {
            /**
             * color_id : 20
             * color_name : 紫色
             */

            private int color_id;
            private String color_name;

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public String getColor_name() {
                return color_name;
            }

            public void setColor_name(String color_name) {
                this.color_name = color_name;
            }
        }

        public static class SelectedSizeBean {
            /**
             * size_id : 5
             * size_name : XS
             */

            private int size_id;
            private String size_name;

            public int getSize_id() {
                return size_id;
            }

            public void setSize_id(int size_id) {
                this.size_id = size_id;
            }

            public String getSize_name() {
                return size_name;
            }

            public void setSize_name(String size_name) {
                this.size_name = size_name;
            }
        }

        public static class SelectedAttrBean {
            /**
             * attr_id : 28
             * attr_value : 大众
             */

            private String attr_id;
            private String attr_value;

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }
        }

        public static class ImgListBean {
            /**
             * img_id : 920
             * image_path : /Uploads/Picture/goods/1/20170727_175144_15011491040343_2095.jpg
             * is_default : 1
             */

            private String img_id;
            private String image_path;
            private String is_default;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
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
    }
}
