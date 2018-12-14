package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class ClassGoodsBean {

    /**
     * status : 1
     * info : 全部分类
     * data : [{"cat_id":"205","cat_name":"包裙"},{"cat_id":"204","cat_name":"半身裙"},{"cat_id":"203","cat_name":"短裙"},{"cat_id":"202","cat_name":"衬衫连衣裙"},{"cat_id":"201","cat_name":"背心连衣裙"},{"cat_id":"200","cat_name":"牛仔连衣裙"},{"cat_id":"199","cat_name":"背带连衣裙"},{"cat_id":"198","cat_name":"吊带连衣裙"},{"cat_id":"197","cat_name":"T恤连衣裙"},{"cat_id":"196","cat_name":"蕾丝连衣裙"},{"cat_id":"195","cat_name":"雪纺连衣裙"},{"cat_id":"165","cat_name":"背心"},{"cat_id":"164","cat_name":"马甲"},{"cat_id":"163","cat_name":"风衣"},{"cat_id":"162","cat_name":"衬衫"},{"cat_id":"161","cat_name":"吊带"},{"cat_id":"160","cat_name":"雪纺衫"},{"cat_id":"159","cat_name":"牛仔外套"},{"cat_id":"158","cat_name":"防晒衣"},{"cat_id":"157","cat_name":"T恤"},{"cat_id":"166","cat_name":"蕾丝衫"},{"cat_id":"167","cat_name":"毛衣"},{"cat_id":"168","cat_name":"卫衣"},{"cat_id":"180","cat_name":"喇叭裤"},{"cat_id":"179","cat_name":"裙裤"},{"cat_id":"178","cat_name":"铅笔裤"},{"cat_id":"177","cat_name":"哈伦裤"},{"cat_id":"176","cat_name":"休闲运动裤"},{"cat_id":"175","cat_name":"背带裤"},{"cat_id":"174","cat_name":"连体裤"},{"cat_id":"173","cat_name":"打底裤"},{"cat_id":"172","cat_name":"阔腿裤"},{"cat_id":"171","cat_name":"短裤"},{"cat_id":"170","cat_name":"牛仔裤"},{"cat_id":"191","cat_name":"男士短裤"},{"cat_id":"190","cat_name":"男士西裤"},{"cat_id":"189","cat_name":"男士休闲裤"},{"cat_id":"188","cat_name":"男士牛仔裤"},{"cat_id":"187","cat_name":"男士风衣"},{"cat_id":"186","cat_name":"男士西服"},{"cat_id":"185","cat_name":"运动服"},{"cat_id":"184","cat_name":"男士背心"},{"cat_id":"183","cat_name":"牛仔服"},{"cat_id":"182","cat_name":"POLO衫"},{"cat_id":"181","cat_name":"男士衬衫"},{"cat_id":"192","cat_name":"男士束脚裤"},{"cat_id":"193","cat_name":"运动裤"},{"cat_id":"154","cat_name":"男装T恤"}]
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
         * cat_id : 205
         * cat_name : 包裙
         */

        private String cat_id;
        private String cat_name;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }
    }
}
