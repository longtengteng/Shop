package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class RetrunDetailsBean {

    /**
     * status : 1
     * info : 详情
     * data : {"shop_state":"等待买家发货处理","shop_name":"商城自营","refund_id":"351","add_time":"2017-08-25 13:43:41","refund_type":"仅退款","reason":"dsadasdsadsad","description":"dsasss","images":["http://img3.redocn.com/tupian/20150312/haixinghezhenzhubeikeshiliangbeijing_3937174.jpg"]}
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
         * shop_state : 等待买家发货处理
         * shop_name : 商城自营
         * refund_id : 351
         * add_time : 2017-08-25 13:43:41
         * refund_type : 仅退款
         * reason : dsadasdsadsad
         * description : dsasss
         * images : ["http://img3.redocn.com/tupian/20150312/haixinghezhenzhubeikeshiliangbeijing_3937174.jpg"]
         */
        private String shop_mobile;
        private String shop_state;
        private String shop_name;
        private String refund_id;
        private String add_time;
        private String refund_type;
        private String reason;
        private String description;
        private List<String> images;
        private String amount;
        private  Emchat emchat;

        public Emchat getEmchat() {
            return emchat;
        }

        public void setEmchat(Emchat emchat) {
            this.emchat = emchat;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getShop_mobile() {
            return shop_mobile;
        }

        public void setShop_mobile(String shop_mobile) {
            this.shop_mobile = shop_mobile;
        }



        public String getShop_state() {
            return shop_state;
        }

        public void setShop_state(String shop_state) {
            this.shop_state = shop_state;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

       public class Emchat {
            private String head_pic;
            private String nickname;
            private String emchat_username;
            private String emchat_password;

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

            public String getEmchat_username() {
                return emchat_username;
            }

            public void setEmchat_username(String emchat_username) {
                this.emchat_username = emchat_username;
            }

            public String getEmchat_password() {
                return emchat_password;
            }

            public void setEmchat_password(String emchat_password) {
                this.emchat_password = emchat_password;
            }
        }

    }
}
