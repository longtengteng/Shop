package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class ExpressBean {

    /**
     * status : 1
     * info : 物流追踪
     * data : {"EBusinessID":"1303394","ShipperCode":"YTO","Success":true,"LogisticCode":"710411594140","State":"3","Traces":[{"AcceptTime":"2017-09-05 23:23:34","AcceptStation":"【广东省广州市嘉和公司】 取件人: 张茂康 已收件"},{"AcceptTime":"2017-09-06 02:02:56","AcceptStation":"【广东省广州市嘉和公司】 已打包"},{"AcceptTime":"2017-09-06 02:02:56","AcceptStation":"【广东省广州市嘉和公司】 已收件"},{"AcceptTime":"2017-09-06 03:53:13","AcceptStation":"【广东省广州市嘉和公司】 已发出 下一站 【广州转运中心】"},{"AcceptTime":"2017-09-06 06:19:41","AcceptStation":"【广州转运中心】 已收入"},{"AcceptTime":"2017-09-06 06:23:25","AcceptStation":"【广州转运中心】 已发出 下一站 【济南转运中心】"},{"AcceptTime":"2017-09-07 15:13:12","AcceptStation":"【济南转运中心】 已收入"},{"AcceptTime":"2017-09-07 15:15:27","AcceptStation":"【济南转运中心】 已发出 下一站 【临沂转运中心】"},{"AcceptTime":"2017-09-08 04:14:39","AcceptStation":"【临沂转运中心】 已收入"},{"AcceptTime":"2017-09-08 04:16:23","AcceptStation":"【临沂转运中心】 已发出 下一站 【山东省临沂市公司】"},{"AcceptTime":"2017-09-08 04:25:19","AcceptStation":"【山东省临沂市公司】 已收入"},{"AcceptTime":"2017-09-08 06:31:15","AcceptStation":"【山东省临沂市公司】 派件人: 张文件 派件中 派件员电话15554883790"},{"AcceptTime":"2017-09-08 10:03:43","AcceptStation":"快件已被中印软件产业园速递易【自提柜】代收，请及时取件。有问题请联系派件员15554883790"},{"AcceptTime":"2017-09-08 10:53:37","AcceptStation":"客户 签收人: 葛 已签收 感谢使用圆通速递，期待再次为您服务"}],"express_name":"圆通","express_code":"710411594140","goods_counts":"2"}
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
         * EBusinessID : 1303394
         * ShipperCode : YTO
         * Success : true
         * LogisticCode : 710411594140
         * State : 3
         * Traces : [{"AcceptTime":"2017-09-05 23:23:34","AcceptStation":"【广东省广州市嘉和公司】 取件人: 张茂康 已收件"},{"AcceptTime":"2017-09-06 02:02:56","AcceptStation":"【广东省广州市嘉和公司】 已打包"},{"AcceptTime":"2017-09-06 02:02:56","AcceptStation":"【广东省广州市嘉和公司】 已收件"},{"AcceptTime":"2017-09-06 03:53:13","AcceptStation":"【广东省广州市嘉和公司】 已发出 下一站 【广州转运中心】"},{"AcceptTime":"2017-09-06 06:19:41","AcceptStation":"【广州转运中心】 已收入"},{"AcceptTime":"2017-09-06 06:23:25","AcceptStation":"【广州转运中心】 已发出 下一站 【济南转运中心】"},{"AcceptTime":"2017-09-07 15:13:12","AcceptStation":"【济南转运中心】 已收入"},{"AcceptTime":"2017-09-07 15:15:27","AcceptStation":"【济南转运中心】 已发出 下一站 【临沂转运中心】"},{"AcceptTime":"2017-09-08 04:14:39","AcceptStation":"【临沂转运中心】 已收入"},{"AcceptTime":"2017-09-08 04:16:23","AcceptStation":"【临沂转运中心】 已发出 下一站 【山东省临沂市公司】"},{"AcceptTime":"2017-09-08 04:25:19","AcceptStation":"【山东省临沂市公司】 已收入"},{"AcceptTime":"2017-09-08 06:31:15","AcceptStation":"【山东省临沂市公司】 派件人: 张文件 派件中 派件员电话15554883790"},{"AcceptTime":"2017-09-08 10:03:43","AcceptStation":"快件已被中印软件产业园速递易【自提柜】代收，请及时取件。有问题请联系派件员15554883790"},{"AcceptTime":"2017-09-08 10:53:37","AcceptStation":"客户 签收人: 葛 已签收 感谢使用圆通速递，期待再次为您服务"}]
         * express_name : 圆通
         * express_code : 710411594140
         * goods_counts : 2
         */

        private String EBusinessID;
        private String ShipperCode;
        private boolean Success;
        private String LogisticCode;
        private String State;
        private String express_name;
        private String express_code;
        private String goods_counts;
        private List<TracesBean> Traces;

        public String getEBusinessID() {
            return EBusinessID;
        }

        public void setEBusinessID(String EBusinessID) {
            this.EBusinessID = EBusinessID;
        }

        public String getShipperCode() {
            return ShipperCode;
        }

        public void setShipperCode(String ShipperCode) {
            this.ShipperCode = ShipperCode;
        }

        public boolean isSuccess() {
            return Success;
        }

        public void setSuccess(boolean Success) {
            this.Success = Success;
        }

        public String getLogisticCode() {
            return LogisticCode;
        }

        public void setLogisticCode(String LogisticCode) {
            this.LogisticCode = LogisticCode;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getExpress_code() {
            return express_code;
        }

        public void setExpress_code(String express_code) {
            this.express_code = express_code;
        }

        public String getGoods_counts() {
            return goods_counts;
        }

        public void setGoods_counts(String goods_counts) {
            this.goods_counts = goods_counts;
        }

        public List<TracesBean> getTraces() {
            return Traces;
        }

        public void setTraces(List<TracesBean> Traces) {
            this.Traces = Traces;
        }

        public static class TracesBean {
            /**
             * AcceptTime : 2017-09-05 23:23:34
             * AcceptStation : 【广东省广州市嘉和公司】 取件人: 张茂康 已收件
             */

            private String AcceptTime;
            private String AcceptStation;

            public String getAcceptTime() {
                return AcceptTime;
            }

            public void setAcceptTime(String AcceptTime) {
                this.AcceptTime = AcceptTime;
            }

            public String getAcceptStation() {
                return AcceptStation;
            }

            public void setAcceptStation(String AcceptStation) {
                this.AcceptStation = AcceptStation;
            }
        }
    }
}
