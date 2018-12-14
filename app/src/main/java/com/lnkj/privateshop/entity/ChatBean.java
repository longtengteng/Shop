package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class ChatBean {


    /**
     * status : 1
     * info : 聊天室列表
     * data : [{"id":"3","room_id":"28223760039937","title":"新聊天室","desc":"","max_member":"2000","litpic":"/Uploads/Picture/Article/2017-09-25/59c8a7f06a5d1.jpg","create_time":"09-25"}]
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
         * id : 3
         * room_id : 28223760039937
         * title : 新聊天室
         * desc :
         * max_member : 2000
         * litpic : /Uploads/Picture/Article/2017-09-25/59c8a7f06a5d1.jpg
         * create_time : 09-25
         */

        private String id;
        private String room_id;
        private String title;
        private String desc;
        private String max_member;
        private String litpic;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMax_member() {
            return max_member;
        }

        public void setMax_member(String max_member) {
            this.max_member = max_member;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
