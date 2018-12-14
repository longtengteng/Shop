package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class AddGoodsBean {

    /**
     * data : [{"cat_id":"194","cat_name_mobile":"裙装","child":[{"cat_id":"205","cat_name_mobile":"包裙","image":"","parent_id":"194"},{"cat_id":"204","cat_name_mobile":"半身裙","image":"","parent_id":"194"},{"cat_id":"203","cat_name_mobile":"短裙","image":"","parent_id":"194"},{"cat_id":"202","cat_name_mobile":"衬衫连衣裙","image":"","parent_id":"194"},{"cat_id":"201","cat_name_mobile":"背心连衣裙","image":"","parent_id":"194"},{"cat_id":"200","cat_name_mobile":"牛仔连衣裙","image":"","parent_id":"194"},{"cat_id":"199","cat_name_mobile":"背带连衣裙","image":"","parent_id":"194"},{"cat_id":"198","cat_name_mobile":"吊带连衣裙","image":"","parent_id":"194"},{"cat_id":"197","cat_name_mobile":"T恤连衣裙","image":"","parent_id":"194"},{"cat_id":"196","cat_name_mobile":"蕾丝连衣裙","image":"","parent_id":"194"},{"cat_id":"195","cat_name_mobile":"雪纺连衣裙","image":"","parent_id":"194"}],"image":"","parent_id":"0"},{"cat_id":"156","cat_name_mobile":"上装","child":[{"cat_id":"165","cat_name_mobile":"背心","image":"","parent_id":"156"},{"cat_id":"164","cat_name_mobile":"马甲","image":"","parent_id":"156"},{"cat_id":"163","cat_name_mobile":"风衣","image":"","parent_id":"156"},{"cat_id":"162","cat_name_mobile":"衬衫","image":"","parent_id":"156"},{"cat_id":"161","cat_name_mobile":"吊带","image":"","parent_id":"156"},{"cat_id":"160","cat_name_mobile":"雪纺衫","image":"","parent_id":"156"},{"cat_id":"159","cat_name_mobile":"牛仔外套","image":"","parent_id":"156"},{"cat_id":"158","cat_name_mobile":"防晒衣","image":"","parent_id":"156"},{"cat_id":"157","cat_name_mobile":"T恤","image":"","parent_id":"156"},{"cat_id":"166","cat_name_mobile":"蕾丝衫","image":"","parent_id":"156"},{"cat_id":"167","cat_name_mobile":"毛衣","image":"","parent_id":"156"},{"cat_id":"168","cat_name_mobile":"卫衣","image":"","parent_id":"156"}],"image":"","parent_id":"0"},{"cat_id":"169","cat_name_mobile":"裤装","child":[{"cat_id":"180","cat_name_mobile":"喇叭裤","image":"","parent_id":"169"},{"cat_id":"179","cat_name_mobile":"裙裤","image":"","parent_id":"169"},{"cat_id":"178","cat_name_mobile":"铅笔裤","image":"","parent_id":"169"},{"cat_id":"177","cat_name_mobile":"哈伦裤","image":"","parent_id":"169"},{"cat_id":"176","cat_name_mobile":"休闲运动裤","image":"","parent_id":"169"},{"cat_id":"175","cat_name_mobile":"背带裤","image":"","parent_id":"169"},{"cat_id":"174","cat_name_mobile":"连体裤","image":"","parent_id":"169"},{"cat_id":"173","cat_name_mobile":"打底裤","image":"","parent_id":"169"},{"cat_id":"172","cat_name_mobile":"阔腿裤","image":"","parent_id":"169"},{"cat_id":"171","cat_name_mobile":"短裤","image":"","parent_id":"169"},{"cat_id":"170","cat_name_mobile":"牛仔裤","image":"","parent_id":"169"}],"image":"","parent_id":"0"},{"cat_id":"153","cat_name_mobile":"男装","child":[{"cat_id":"191","cat_name_mobile":"男士短裤","image":"","parent_id":"153"},{"cat_id":"190","cat_name_mobile":"男士西裤","image":"","parent_id":"153"},{"cat_id":"189","cat_name_mobile":"男士休闲裤","image":"","parent_id":"153"},{"cat_id":"188","cat_name_mobile":"男士牛仔裤","image":"","parent_id":"153"},{"cat_id":"187","cat_name_mobile":"男士风衣","image":"","parent_id":"153"},{"cat_id":"186","cat_name_mobile":"男士西服","image":"","parent_id":"153"},{"cat_id":"185","cat_name_mobile":"运动服","image":"","parent_id":"153"},{"cat_id":"184","cat_name_mobile":"男士背心","image":"","parent_id":"153"},{"cat_id":"183","cat_name_mobile":"牛仔服","image":"","parent_id":"153"},{"cat_id":"182","cat_name_mobile":"POLO衫","image":"","parent_id":"153"},{"cat_id":"181","cat_name_mobile":"男士衬衫","image":"","parent_id":"153"},{"cat_id":"192","cat_name_mobile":"男士束脚裤","image":"","parent_id":"153"},{"cat_id":"193","cat_name_mobile":"运动裤","image":"","parent_id":"153"},{"cat_id":"154","cat_name_mobile":"男装T恤","image":"","parent_id":"153"}],"image":"","parent_id":"0"}]
     * info : 全部分类
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
         * cat_id : 194
         * cat_name_mobile : 裙装
         * child : [{"cat_id":"205","cat_name_mobile":"包裙","image":"","parent_id":"194"},{"cat_id":"204","cat_name_mobile":"半身裙","image":"","parent_id":"194"},{"cat_id":"203","cat_name_mobile":"短裙","image":"","parent_id":"194"},{"cat_id":"202","cat_name_mobile":"衬衫连衣裙","image":"","parent_id":"194"},{"cat_id":"201","cat_name_mobile":"背心连衣裙","image":"","parent_id":"194"},{"cat_id":"200","cat_name_mobile":"牛仔连衣裙","image":"","parent_id":"194"},{"cat_id":"199","cat_name_mobile":"背带连衣裙","image":"","parent_id":"194"},{"cat_id":"198","cat_name_mobile":"吊带连衣裙","image":"","parent_id":"194"},{"cat_id":"197","cat_name_mobile":"T恤连衣裙","image":"","parent_id":"194"},{"cat_id":"196","cat_name_mobile":"蕾丝连衣裙","image":"","parent_id":"194"},{"cat_id":"195","cat_name_mobile":"雪纺连衣裙","image":"","parent_id":"194"}]
         * image :
         * parent_id : 0
         */

        private String cat_id;
        private String cat_name_mobile;
        private String image;
        private String parent_id;
        private List<ChildBean> child;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name_mobile() {
            return cat_name_mobile;
        }

        public void setCat_name_mobile(String cat_name_mobile) {
            this.cat_name_mobile = cat_name_mobile;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * cat_id : 205
             * cat_name_mobile : 包裙
             * image :
             * parent_id : 194
             */

            private String cat_id;
            private String cat_name_mobile;
            private String image;
            private String parent_id;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name_mobile() {
                return cat_name_mobile;
            }

            public void setCat_name_mobile(String cat_name_mobile) {
                this.cat_name_mobile = cat_name_mobile;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
