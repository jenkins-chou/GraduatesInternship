package com.jenking.graduatesinternship.models.impl;

public class StudentTutorModel {
    private String user_id;
    private String teacher_useridentify;
    private String teacher_name;
    private String status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTeacher_useridentify() {
        return teacher_useridentify;
    }

    public void setTeacher_useridentify(String teacher_useridentify) {
        this.teacher_useridentify = teacher_useridentify;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentTutorModel{" +
                "user_id='" + user_id + '\'' +
                ", teacher_useridentify='" + teacher_useridentify + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
