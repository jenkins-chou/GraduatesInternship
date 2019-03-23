package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.models.base.BaseTableModel;

import butterknife.BindView;

public class InternshipExperienceModel extends BaseTableModel {

    private String user_id;

    private String  internship_job;
   
    private String  internship_start_time;
   
    private String  internship_end_time;
    
    private String  internship_enterprise;
    
    private String  internship_department;
    
    private String  internship_job_content;
    
    private String  internship_result;
    
    private String  internship_harvest;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInternship_job() {
        return internship_job;
    }

    public void setInternship_job(String internship_job) {
        this.internship_job = internship_job;
    }

    public String getInternship_start_time() {
        return internship_start_time;
    }

    public void setInternship_start_time(String internship_start_time) {
        this.internship_start_time = internship_start_time;
    }

    public String getInternship_end_time() {
        return internship_end_time;
    }

    public void setInternship_end_time(String internship_end_time) {
        this.internship_end_time = internship_end_time;
    }

    public String getInternship_enterprise() {
        return internship_enterprise;
    }

    public void setInternship_enterprise(String internship_enterprise) {
        this.internship_enterprise = internship_enterprise;
    }

    public String getInternship_department() {
        return internship_department;
    }

    public void setInternship_department(String internship_department) {
        this.internship_department = internship_department;
    }

    public String getInternship_job_content() {
        return internship_job_content;
    }

    public void setInternship_job_content(String internship_job_content) {
        this.internship_job_content = internship_job_content;
    }

    public String getInternship_result() {
        return internship_result;
    }

    public void setInternship_result(String internship_result) {
        this.internship_result = internship_result;
    }

    public String getInternship_harvest() {
        return internship_harvest;
    }

    public void setInternship_harvest(String internship_harvest) {
        this.internship_harvest = internship_harvest;
    }

    @Override
    public String toString() {
        return "InternshipExperienceModel{" +
                "user_id='" + user_id + '\'' +
                ", internship_job='" + internship_job + '\'' +
                ", internship_start_time='" + internship_start_time + '\'' +
                ", internship_end_time='" + internship_end_time + '\'' +
                ", internship_enterprise='" + internship_enterprise + '\'' +
                ", internship_department='" + internship_department + '\'' +
                ", internship_job_content='" + internship_job_content + '\'' +
                ", internship_result='" + internship_result + '\'' +
                ", internship_harvest='" + internship_harvest + '\'' +
                ", id='" + id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
