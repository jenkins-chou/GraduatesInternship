package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class RecruitCollectionModel extends BaseTableModel {
    private String user_id;
    private String recruit_id;
    private String status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecruitDeliveryModel{" +
                "user_id='" + user_id + '\'' +
                ", recruit_id='" + recruit_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
