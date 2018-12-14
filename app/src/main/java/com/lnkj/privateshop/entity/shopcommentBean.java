package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class shopcommentBean  {


    /**
     * status : 1
     * info : 评论列表
     * data : [{"comment_id":"70","user_id":"98","content":"商品很好","rank":"5","add_time":"2017-09-05","head_pic":"/Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg","user_name":"小小草","review":{"content":"nishdah","add_time":"2017-08-24"}},{"comment_id":"68","user_id":"98","content":"1111","rank":"2","add_time":"2017-08-31","head_pic":"/Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg","user_name":"小小草","review":[]},{"comment_id":"5","user_id":"98","content":"","rank":"4","add_time":"2017-08-23","head_pic":"/Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg","user_name":"小小草","review":{"content":"nishdah","add_time":"2017-08-24"}}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * comment_id : 70
         * user_id : 98
         * content : 商品很好
         * rank : 5
         * add_time : 2017-09-05
         * head_pic : /Uploads/Picture/User/98/20170831_165405_15041696456819_4376.jpg
         * user_name : 小小草
         * review : {"content":"nishdah","add_time":"2017-08-24"}
         */

        private String comment_id;
        private String user_id;
        private String content;
        private String rank;
        private String add_time;
        private String head_pic;
        private String user_name;
        private ReviewBean review;
        private List<CommentImgBean> comment_img;
        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
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

        public static class CommentImgBean {
            /**
             * img_url : /Uploads/Picture/Comment/115/20171008_134603_15074415632699_5596.jpg
             */

            private String img_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
        public static class ReviewBean {
            /**
             * content : nishdah
             * add_time : 2017-08-24
             */

            private String content;
            private String add_time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }
        }
    }
}
