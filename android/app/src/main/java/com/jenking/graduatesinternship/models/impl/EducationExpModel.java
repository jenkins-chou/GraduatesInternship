package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class EducationExpModel extends BaseTableModel {
    private String education_school;
    private String education_record;
    private String education_major;
    private String education_start_time;
    private String education_end_time;
    private String experience;

    public String getEducation_school() {
        return education_school;
    }

    public void setEducation_school(String education_school) {
        this.education_school = education_school;
    }

    public String getEducation_record() {
        return education_record;
    }

    public void setEducation_record(String education_record) {
        this.education_record = education_record;
    }

    public String getEducation_major() {
        return education_major;
    }

    public void setEducation_major(String education_major) {
        this.education_major = education_major;
    }

    public String getEducation_start_time() {
        return education_start_time;
    }

    public void setEducation_start_time(String education_start_time) {
        this.education_start_time = education_start_time;
    }

    public String getEducation_end_time() {
        return education_end_time;
    }

    public void setEducation_end_time(String education_end_time) {
        this.education_end_time = education_end_time;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "EducationExpModel{" +
                "education_school='" + education_school + '\'' +
                ", education_record='" + education_record + '\'' +
                ", education_major='" + education_major + '\'' +
                ", education_start_time='" + education_start_time + '\'' +
                ", education_end_time='" + education_end_time + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
