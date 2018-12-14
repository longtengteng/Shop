package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class BankCardBean {

    /**
     * status : 1
     * info : 银行卡列表
     * data : [{"id":"4","user_id":"84","bank_name":"a1","bank_num":"iada","cardholder":"23sdfa","open_bank":"asdf"}]
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
         * id : 4
         * user_id : 84
         * bank_name : a1
         * bank_num : iada
         * cardholder : 23sdfa
         * open_bank : asdf
         */

        private String id;
        private String user_id;
        private String bank_name;
        private String bank_num;
        private String cardholder;
        private String open_bank;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public String getCardholder() {
            return cardholder;
        }

        public void setCardholder(String cardholder) {
            this.cardholder = cardholder;
        }

        public String getOpen_bank() {
            return open_bank;
        }

        public void setOpen_bank(String open_bank) {
            this.open_bank = open_bank;
        }
    }
}
