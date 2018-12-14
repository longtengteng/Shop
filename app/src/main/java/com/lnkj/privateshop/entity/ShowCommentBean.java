package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ShowCommentBean {

    /**
     * status : 1
     * info : 评论统计
     * data : {"goods_rank":"3.7","express_rank":"3.7","service_rank":"4.3","favorableRate":67,"commentCount":"3","likeCount":"2","medCount":"1","lowCount":"0","imgCount":"0"}
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
         * goods_rank : 3.7
         * express_rank : 3.7
         * service_rank : 4.3
         * favorableRate : 67
         * commentCount : 3
         * likeCount : 2
         * medCount : 1
         * lowCount : 0
         * imgCount : 0
         */

        private String goods_rank;
        private String express_rank;
        private String service_rank;
        private int favorableRate;
        private String commentCount;
        private String likeCount;
        private String medCount;
        private String lowCount;
        private String imgCount;
        private String reviewCount;

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getGoods_rank() {
            return goods_rank;
        }

        public void setGoods_rank(String goods_rank) {
            this.goods_rank = goods_rank;
        }

        public String getExpress_rank() {
            return express_rank;
        }

        public void setExpress_rank(String express_rank) {
            this.express_rank = express_rank;
        }

        public String getService_rank() {
            return service_rank;
        }

        public void setService_rank(String service_rank) {
            this.service_rank = service_rank;
        }

        public int getFavorableRate() {
            return favorableRate;
        }

        public void setFavorableRate(int favorableRate) {
            this.favorableRate = favorableRate;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getMedCount() {
            return medCount;
        }

        public void setMedCount(String medCount) {
            this.medCount = medCount;
        }

        public String getLowCount() {
            return lowCount;
        }

        public void setLowCount(String lowCount) {
            this.lowCount = lowCount;
        }

        public String getImgCount() {
            return imgCount;
        }

        public void setImgCount(String imgCount) {
            this.imgCount = imgCount;
        }
    }
}
