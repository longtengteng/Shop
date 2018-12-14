package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class GoodsBean implements Serializable{


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

    public static class DataBean implements Serializable{
        /**
         * goods_attr : [{"attr_id":"28","attr_name":"风格","attr_value":"日韩"},{"attr_id":"29","attr_name":"厚度","attr_value":"适中"},{"attr_id":"36","attr_name":"面料","attr_value":"涤纶"},{"attr_id":"44","attr_name":"季节","attr_value":"春夏"}]
         * goods_desc : []
         * goods_gallery : []
         * goods_info : {"add_time":"1500538652","cat_id":"171","click_count":"38","collect_num":"1","comment_num":"0","goods_description":"","goods_id":"226","goods_img":"/Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg","goods_name":"小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙","is_favorite":0,"pack_price":"74.00","sale_num":"0","shop_id":"85","shop_name":"","shop_price":"78.00","storage":"55"}
         * shop_info : {"month_goods_count":0,"month_sale_count":"0","open_time":"2017-08-17","shop_addr":"山东·临沂·","shop_id":"85","shop_logo":"/Uploads/Picture/Shop/98/20170817_160351_15029570314925_8584.jpg","shop_name":"梦梦猪","supplement":0}
         */

        private GoodsInfoBean goods_info;
        private ShopInfoBean shop_info;
        private List<GoodsAttrBean> goods_attr;
        private List<GoodsDescBean> goods_desc;
        private List<GoodsGalleryBean> goods_gallery;
        private Shop_comment  shop_comment;
        private  Goods_spec  goods_spec;
        private Emchat emchat;

        public Emchat getEmchat() {
            return emchat;
        }

        public void setEmchat(Emchat emchat) {
            this.emchat = emchat;
        }

        public Goods_spec getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(Goods_spec goods_spec) {
            this.goods_spec = goods_spec;
        }

        public Shop_comment getShop_comment() {
            return shop_comment;
        }

        public void setShop_comment(Shop_comment shop_comment) {
            this.shop_comment = shop_comment;
        }

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

        public List<GoodsGalleryBean> getGoods_gallery() {
            return goods_gallery;
        }

        public void setGoods_gallery(List<GoodsGalleryBean> goods_gallery) {
            this.goods_gallery = goods_gallery;
        }
        public static class Emchat implements Serializable{
          private  String emchat_username;
            private   String head_pic;
            private  String nickname;

            public String getEmchat_username() {
                return emchat_username;
            }

            public void setEmchat_username(String emchat_username) {
                this.emchat_username = emchat_username;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
        public static class Goods_spec implements Serializable{

            /**
             * color : 白色,粉色
             * size : S,M,L,XL,2XL
             */

            private String color;
            private String size;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }
        }

        public static class Shop_comment implements Serializable{


            /**
             * commentCount : 3
             * newComment : [{"add_time":"2017-08-23","content":"ssdffsfds","head_pic":"/Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg","user_id":"98","user_name":"小小草"}]
             * ravorableRate : 67
             */

            private String commentCount;
            private int ravorableRate;
            private List<NewCommentBean> newComment;

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public int getRavorableRate() {
                return ravorableRate;
            }

            public void setRavorableRate(int ravorableRate) {
                this.ravorableRate = ravorableRate;
            }

            public List<NewCommentBean> getNewComment() {
                return newComment;
            }

            public void setNewComment(List<NewCommentBean> newComment) {
                this.newComment = newComment;
            }

            public static class NewCommentBean implements Serializable{
                /**
                 * add_time : 2017-08-23
                 * content : ssdffsfds
                 * head_pic : /Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg
                 * user_id : 98
                 * user_name : 小小草
                 */

                private String add_time;
                private String content;
                private String head_pic;
                private String user_id;
                private String user_name;

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getHead_pic() {
                    return head_pic;
                }

                public void setHead_pic(String head_pic) {
                    this.head_pic = head_pic;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }
            }
        }
        public static class GoodsInfoBean implements Serializable{
            /**
             * add_time : 1500538652
             * cat_id : 171
             * click_count : 38
             * collect_num : 1
             * comment_num : 0
             * goods_description :
             * goods_id : 226
             * goods_img : /Uploads/Picture/Goods/2017-07-20/59706709bd6ad.jpg
             * goods_name : 小丸子 设计款斜边不规则白扣线条显瘦半身裙短裙
             * is_favorite : 0
             * pack_price : 74.00
             * sale_num : 0
             * shop_id : 85
             * shop_name :
             * shop_price : 78.00
             * storage : 55
             */
            private String is_on_sale;
            private String add_time;
            private String cat_id;
            private String click_count;
            private String collect_num;
            private String comment_num;
            private String goods_description;
            private String goods_id;
            private String goods_img;
            private String goods_name;
            private int is_favorite;
            private String pack_price;
            private String sale_num;
            private String shop_id;
            private String shop_name;
            private String shop_price;
            private String storage;
            private String basic_amount;
            private String retail_amount;
            private Activity activity;

            public String getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(String is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public Activity getActivity() {
                return activity;
            }

            public void setActivity(Activity activity) {
                this.activity = activity;
            }

            public String getRetail_amount() {
                return retail_amount;
            }

            public void setRetail_amount(String retail_amount) {
                this.retail_amount = retail_amount;
            }

            public String getBasic_amount() {
                return basic_amount;
            }

            public void setBasic_amount(String basic_amount) {
                this.basic_amount = basic_amount;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getGoods_description() {
                return goods_description;
            }

            public void setGoods_description(String goods_description) {
                this.goods_description = goods_description;
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

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public String getPack_price() {
                return pack_price;
            }

            public void setPack_price(String pack_price) {
                this.pack_price = pack_price;
            }

            public String getSale_num() {
                return sale_num;
            }

            public void setSale_num(String sale_num) {
                this.sale_num = sale_num;
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

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getStorage() {
                return storage;
            }

            public void setStorage(String storage) {
                this.storage = storage;
            }
        }
    public static class Activity implements  Serializable{

        /**
         * activity_price : 63.2
         * discount : 0.8
         * end_time : 1505908500
         * now_time : 1505891225
         * shop_price : 79.00
         * start_time : 1505843700
         */

        private double activity_price;
        private double discount;
        private String end_time;
        private int now_time;
        private String shop_price;
        private String start_time;

        public double getActivity_price() {
            return activity_price;
        }

        public void setActivity_price(double activity_price) {
            this.activity_price = activity_price;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getNow_time() {
            return now_time;
        }

        public void setNow_time(int now_time) {
            this.now_time = now_time;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }
    }
        public static class ShopInfoBean implements Serializable{
            /**
             * month_goods_count : 0
             * month_sale_count : 0
             * open_time : 2017-08-17
             * shop_addr : 山东·临沂·
             * shop_id : 85
             * shop_logo : /Uploads/Picture/Shop/98/20170817_160351_15029570314925_8584.jpg
             * shop_name : 梦梦猪
             * supplement : 0
             */

            private int month_goods_count;
            private String month_sale_count;
            private String open_time;
            private String shop_addr;
            private String shop_id;
            private String shop_logo;
            private String shop_name;
            private int supplement;

            public int getMonth_goods_count() {
                return month_goods_count;
            }

            public void setMonth_goods_count(int month_goods_count) {
                this.month_goods_count = month_goods_count;
            }

            public String getMonth_sale_count() {
                return month_sale_count;
            }

            public void setMonth_sale_count(String month_sale_count) {
                this.month_sale_count = month_sale_count;
            }

            public String getOpen_time() {
                return open_time;
            }

            public void setOpen_time(String open_time) {
                this.open_time = open_time;
            }

            public String getShop_addr() {
                return shop_addr;
            }

            public void setShop_addr(String shop_addr) {
                this.shop_addr = shop_addr;
            }

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

            public int getSupplement() {
                return supplement;
            }

            public void setSupplement(int supplement) {
                this.supplement = supplement;
            }
        }

        public static class GoodsAttrBean implements Serializable{
            /**
             * attr_id : 28
             * attr_name : 风格
             * attr_value : 日韩
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
        public static class GoodsDescBean implements Serializable{
            private String img_id;
            private String image_path;

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
        }
        public static class GoodsGalleryBean implements Serializable{
            private String img_id;
            private String image_path;

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
        }
    }
}
