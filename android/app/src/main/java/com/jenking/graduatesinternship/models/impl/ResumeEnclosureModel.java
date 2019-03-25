package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class ResumeEnclosureModel extends BaseTableModel {
    private String user_id;
    private String file_url;
    private String status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResumeEnclosureModel{" +
                "user_id='" + user_id + '\'' +
                ", file_url='" + file_url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
