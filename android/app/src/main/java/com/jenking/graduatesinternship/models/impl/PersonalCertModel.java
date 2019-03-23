package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class PersonalCertModel extends BaseTableModel {
    private String user_id;
    private String cert_name;
    private String cert_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCert_name() {
        return cert_name;
    }

    public void setCert_name(String cert_name) {
        this.cert_name = cert_name;
    }

    public String getCert_time() {
        return cert_time;
    }

    public void setCert_time(String cert_time) {
        this.cert_time = cert_time;
    }

    @Override
    public String toString() {
        return "PersonalCertModel{" +
                "user_id='" + user_id + '\'' +
                ", cert_name='" + cert_name + '\'' +
                ", cert_time='" + cert_time + '\'' +
                '}';
    }
}
