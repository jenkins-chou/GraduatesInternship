package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class ResumeModel extends BaseTableModel {
    private String resume_name;
    private String resume_expected_salary;
    private String resume_work_life;
    private String resume_education;
    private String resume_age;
    private String resume_website;
    private String resume_wechat;
    private String resume_phone;
    private String resume_email;
    private String resume_qq;
    private String resume_address;
    private String resume_intention_job;
    private String resume_status;

    public String getResume_name() {
        return resume_name;
    }

    public void setResume_name(String resume_name) {
        this.resume_name = resume_name;
    }

    public String getResume_expected_salary() {
        return resume_expected_salary;
    }

    public void setResume_expected_salary(String resume_expected_salary) {
        this.resume_expected_salary = resume_expected_salary;
    }

    public String getResume_work_life() {
        return resume_work_life;
    }

    public void setResume_work_life(String resume_work_life) {
        this.resume_work_life = resume_work_life;
    }

    public String getResume_education() {
        return resume_education;
    }

    public void setResume_education(String resume_education) {
        this.resume_education = resume_education;
    }

    public String getResume_age() {
        return resume_age;
    }

    public void setResume_age(String resume_age) {
        this.resume_age = resume_age;
    }

    public String getResume_website() {
        return resume_website;
    }

    public void setResume_website(String resume_website) {
        this.resume_website = resume_website;
    }

    public String getResume_wechat() {
        return resume_wechat;
    }

    public void setResume_wechat(String resume_wechat) {
        this.resume_wechat = resume_wechat;
    }

    public String getResume_phone() {
        return resume_phone;
    }

    public void setResume_phone(String resume_phone) {
        this.resume_phone = resume_phone;
    }

    public String getResume_email() {
        return resume_email;
    }

    public void setResume_email(String resume_email) {
        this.resume_email = resume_email;
    }

    public String getResume_qq() {
        return resume_qq;
    }

    public void setResume_qq(String resume_qq) {
        this.resume_qq = resume_qq;
    }

    public String getResume_address() {
        return resume_address;
    }

    public void setResume_address(String resume_address) {
        this.resume_address = resume_address;
    }

    public String getResume_intention_job() {
        return resume_intention_job;
    }

    public void setResume_intention_job(String resume_intention_job) {
        this.resume_intention_job = resume_intention_job;
    }

    public String getResume_status() {
        return resume_status;
    }

    public void setResume_status(String resume_status) {
        this.resume_status = resume_status;
    }

    @Override
    public String toString() {
        return "ResumeModel{" +
                "resume_name='" + resume_name + '\'' +
                ", resume_expected_salary='" + resume_expected_salary + '\'' +
                ", resume_work_life='" + resume_work_life + '\'' +
                ", resume_education='" + resume_education + '\'' +
                ", resume_age='" + resume_age + '\'' +
                ", resume_website='" + resume_website + '\'' +
                ", resume_wechat='" + resume_wechat + '\'' +
                ", resume_phone='" + resume_phone + '\'' +
                ", resume_email='" + resume_email + '\'' +
                ", resume_qq='" + resume_qq + '\'' +
                ", resume_address='" + resume_address + '\'' +
                ", resume_intention_job='" + resume_intention_job + '\'' +
                ", resume_status='" + resume_status + '\'' +
                '}';
    }
}
