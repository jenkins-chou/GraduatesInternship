package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class RecruitModel extends BaseTableModel {
    private String enterprise_id;
    private String enterprise_name;
    private String depertment;
    private String job_name;
    private String job_content;
    private String working_day;
    private String working_start_time;
    private String working_end_time;
    private String working_region;
    private String working_address;
    private String job_abstract;
    private String job_detail;
    private String welfare;
    private String job_requirements;
    private String skill_requirement;
    private String team_detail;
    private String publisher;
    private String salary;

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getDepertment() {
        return depertment;
    }

    public void setDepertment(String depertment) {
        this.depertment = depertment;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_content() {
        return job_content;
    }

    public void setJob_content(String job_content) {
        this.job_content = job_content;
    }

    public String getWorking_day() {
        return working_day;
    }

    public void setWorking_day(String working_day) {
        this.working_day = working_day;
    }

    public String getWorking_start_time() {
        return working_start_time;
    }

    public void setWorking_start_time(String working_start_time) {
        this.working_start_time = working_start_time;
    }

    public String getWorking_end_time() {
        return working_end_time;
    }

    public void setWorking_end_time(String working_end_time) {
        this.working_end_time = working_end_time;
    }

    public String getWorking_region() {
        return working_region;
    }

    public void setWorking_region(String working_region) {
        this.working_region = working_region;
    }

    public String getWorking_address() {
        return working_address;
    }

    public void setWorking_address(String working_address) {
        this.working_address = working_address;
    }

    public String getJob_abstract() {
        return job_abstract;
    }

    public void setJob_abstract(String job_abstract) {
        this.job_abstract = job_abstract;
    }

    public String getJob_detail() {
        return job_detail;
    }

    public void setJob_detail(String job_detail) {
        this.job_detail = job_detail;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getJob_requirements() {
        return job_requirements;
    }

    public void setJob_requirements(String job_requirements) {
        this.job_requirements = job_requirements;
    }

    public String getSkill_requirement() {
        return skill_requirement;
    }

    public void setSkill_requirement(String skill_requirement) {
        this.skill_requirement = skill_requirement;
    }

    public String getTeam_detail() {
        return team_detail;
    }

    public void setTeam_detail(String team_detail) {
        this.team_detail = team_detail;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "RecruitModel{" +
                "enterprise_id='" + enterprise_id + '\'' +
                ", enterprise_name='" + enterprise_name + '\'' +
                ", depertment='" + depertment + '\'' +
                ", job_name='" + job_name + '\'' +
                ", job_content='" + job_content + '\'' +
                ", working_day='" + working_day + '\'' +
                ", working_start_time='" + working_start_time + '\'' +
                ", working_end_time='" + working_end_time + '\'' +
                ", working_region='" + working_region + '\'' +
                ", working_address='" + working_address + '\'' +
                ", job_abstract='" + job_abstract + '\'' +
                ", job_detail='" + job_detail + '\'' +
                ", welfare='" + welfare + '\'' +
                ", job_requirements='" + job_requirements + '\'' +
                ", skill_requirement='" + skill_requirement + '\'' +
                ", team_detail='" + team_detail + '\'' +
                ", publisher='" + publisher + '\'' +
                ", salary='" + salary + '\'' +
                '}'+super.toString();
    }
}
