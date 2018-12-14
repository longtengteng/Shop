package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ClientCommentBean {


    /**
     * data : [{"add_time":"2017-10-18","comment_id":"49","comment_img":[{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260256_5683.jpg"},{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260262_9055.jpg"},{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260265_2466.jpg"}],"content":"123456789*","head_pic":"/Uploads/Picture/User/133/20171018_171444_15083180846240_4203.jpg","rank":"3","user_id":"133","user_name":"赵林"},{"add_time":"2017-10-18","comment_id":"48","comment_img":[],"content":"好","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"3","user_id":"126","user_name":"33333"},{"add_time":"2017-10-17","comment_id":"39","comment_img":[{"img_url":"/Uploads/Picture/Comment/126/20171017_153405_15082256451723_4676.jpg"},{"img_url":"/Uploads/Picture/Comment/126/20171017_153405_15082256451730_8790.jpg"},{"img_url":"/Uploads/Picture/Comment/126/20171017_153405_15082256451733_7410.jpg"}],"content":"12121212","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"1","user_id":"126","user_name":"33333"},{"add_time":"2017-10-16","comment_id":"33","comment_img":[],"content":"12112121","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"2","user_id":"126","user_name":"33333"},{"add_time":"2017-10-13","comment_id":"28","comment_img":[],"content":"11212","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"4","review":{"add_time":"2017-10-13","content":"1212"},"user_id":"126","user_name":"33333"},{"add_time":"2017-10-10","comment_id":"20","comment_img":[],"content":"测试iOS评价","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"5","review":{"add_time":"2017-10-13","content":"121212"},"user_id":"126","user_name":"33333"},{"add_time":"2017-10-08","comment_id":"15","comment_img":[],"content":"测试iOS给的评价","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"5","review":{"add_time":"2017-10-13","content":"122121"},"user_id":"126","user_name":"33333"},{"add_time":"2017-10-07","comment_id":"11","comment_img":[],"content":"测试iOS评价","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"5","review":{"add_time":"2017-10-13","content":"121221"},"user_id":"126","user_name":"33333"},{"add_time":"2017-10-07","comment_id":"9","comment_img":[],"content":"vvhh","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"5","review":{"add_time":"2017-10-13","content":"156516"},"user_id":"126","user_name":"33333"},{"add_time":"2017-10-07","comment_id":"7","comment_img":[],"content":"测试iOS评价","head_pic":"/Uploads/Picture/User/126/20171009_091525_15075117253445_1419.jpg","rank":"5","review":{"add_time":"2017-10-13","content":"111112"},"user_id":"126","user_name":"33333"}]
     * info : 评论列表
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
         * add_time : 2017-10-18
         * comment_id : 49
         * comment_img : [{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260256_5683.jpg"},{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260262_9055.jpg"},{"img_url":"/Uploads/Picture/Comment/133/20171018_171526_15083181260265_2466.jpg"}]
         * content : 123456789*
         * head_pic : /Uploads/Picture/User/133/20171018_171444_15083180846240_4203.jpg
         * rank : 3
         * user_id : 133
         * user_name : 赵林
         * review : {"add_time":"2017-10-13","content":"1212"}
         */

        private String add_time;
        private String comment_id;
        private String content;
        private String head_pic;
        private String rank;
        private String user_id;
        private String user_name;
        private ReviewBean review;
        private List<CommentImgBean> comment_img;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
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

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
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

        public ReviewBean getReview() {
            return review;
        }

        public void setReview(ReviewBean review) {
            this.review = review;
        }

        public List<CommentImgBean> getComment_img() {
            return comment_img;
        }

        public void setComment_img(List<CommentImgBean> comment_img) {
            this.comment_img = comment_img;
        }

        public static class ReviewBean {
            /**
             * add_time : 2017-10-13
             * content : 1212
             */

            private String add_time;
            private String content;

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
        }

        public static class CommentImgBean {
            /**
             * img_url : /Uploads/Picture/Comment/133/20171018_171526_15083181260256_5683.jpg
             */

            private String img_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
