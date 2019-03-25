package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class EnterpriseModel extends BaseTableModel {
    private String enterprise_name;
    private String enterprise_email;
    private String enterprise_phone;
    private String enterprise_token;
    private String enterprise_build_time;
    private String enterprise_abstract;
    private String enterprise_detail;
    private String enterprise_type;
    private String enterprise_business;
    private String enterprise_leader;
    private String enterprise_address;
    private String enterprise_staff;
    private String enterprise_status;
    private String enterprise_image;
    private String enterprise_website;
    private String enterprise_credit_code;
    private String enterprise_work_time;

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getEnterprise_email() {
        return enterprise_email;
    }

    public void setEnterprise_email(String enterprise_email) {
        this.enterprise_email = enterprise_email;
    }

    public String getEnterprise_phone() {
        return enterprise_phone;
    }

    public void setEnterprise_phone(String enterprise_phone) {
        this.enterprise_phone = enterprise_phone;
    }

    public String getEnterprise_token() {
        return enterprise_token;
    }

    public void setEnterprise_token(String enterprise_token) {
        this.enterprise_token = enterprise_token;
    }

    public String getEnterprise_build_time() {
        return enterprise_build_time;
    }

    public void setEnterprise_build_time(String enterprise_build_time) {
        this.enterprise_build_time = enterprise_build_time;
    }

    public String getEnterprise_abstract() {
        return enterprise_abstract;
    }

    public void setEnterprise_abstract(String enterprise_abstract) {
        this.enterprise_abstract = enterprise_abstract;
    }

    public String getEnterprise_detail() {
        return enterprise_detail;
    }

    public void setEnterprise_detail(String enterprise_detail) {
        this.enterprise_detail = enterprise_detail;
    }

    public String getEnterprise_type() {
        return enterprise_type;
    }

    public void setEnterprise_type(String enterprise_type) {
        this.enterprise_type = enterprise_type;
    }

    public String getEnterprise_business() {
        return enterprise_business;
    }

    public void setEnterprise_business(String enterprise_business) {
        this.enterprise_business = enterprise_business;
    }

    public String getEnterprise_leader() {
        return enterprise_leader;
    }

    public void setEnterprise_leader(String enterprise_leader) {
        this.enterprise_leader = enterprise_leader;
    }

    public String getEnterprise_address() {
        return enterprise_address;
    }

    public void setEnterprise_address(String enterprise_address) {
        this.enterprise_address = enterprise_address;
    }

    public String getEnterprise_staff() {
        return enterprise_staff;
    }

    public void setEnterprise_staff(String enterprise_staff) {
        this.enterprise_staff = enterprise_staff;
    }

    public String getEnterprise_status() {
        return enterprise_status;
    }

    public void setEnterprise_status(String enterprise_status) {
        this.enterprise_status = enterprise_status;
    }

    public String getEnterprise_image() {
        return enterprise_image;
    }

    public void setEnterprise_image(String enterprise_image) {
        this.enterprise_image = enterprise_image;
    }

    public String getEnterprise_website() {
        return enterprise_website;
    }

    public void setEnterprise_website(String enterprise_website) {
        this.enterprise_website = enterprise_website;
    }

    public String getEnterprise_credit_code() {
        return enterprise_credit_code;
    }

    public void setEnterprise_credit_code(String enterprise_credit_code) {
        this.enterprise_credit_code = enterprise_credit_code;
    }

    public String getEnterprise_work_time() {
        return enterprise_work_time;
    }

    public void setEnterprise_work_time(String enterprise_work_time) {
        this.enterprise_work_time = enterprise_work_time;
    }

    @Override
    public String toString() {
        return "EnterpriseModel{" +
                "enterprise_name='" + enterprise_name + '\'' +
                ", enterprise_email='" + enterprise_email + '\'' +
                ", enterprise_phone='" + enterprise_phone + '\'' +
                ", enterprise_token='" + enterprise_token + '\'' +
                ", enterprise_build_time='" + enterprise_build_time + '\'' +
                ", enterprise_abstract='" + enterprise_abstract + '\'' +
                ", enterprise_detail='" + enterprise_detail + '\'' +
                ", enterprise_type='" + enterprise_type + '\'' +
                ", enterprise_business='" + enterprise_business + '\'' +
                ", enterprise_leader='" + enterprise_leader + '\'' +
                ", enterprise_address='" + enterprise_address + '\'' +
                ", enterprise_staff='" + enterprise_staff + '\'' +
                ", enterprise_status='" + enterprise_status + '\'' +
                ", enterprise_image='" + enterprise_image + '\'' +
                ", enterprise_website='" + enterprise_website + '\'' +
                ", enterprise_credit_code='" + enterprise_credit_code + '\'' +
                ", enterprise_work_time='" + enterprise_work_time + '\'' +
                '}';
    }
}
