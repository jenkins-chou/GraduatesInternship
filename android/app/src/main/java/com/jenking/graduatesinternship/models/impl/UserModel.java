package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class UserModel extends BaseTableModel{
    public String name;
    public String pass;
    public String realname;
    public String avatar;
    public String slogan;
    public String sex;
    public String age;
    public String idnum;
    public String nation;
    public String registered_residence;//户籍
    public String email;
    public String useridentify;
    public String phone;
    public String address;
    public String health;
    public String type;
    public String create_time;
    public String entrance_time;
    public String class_id;
    public String class_name;
    public String college_id;
    public String college_name;
    public String school_id;
    public String school_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRegistered_residence() {
        return registered_residence;
    }

    public void setRegistered_residence(String registered_residence) {
        this.registered_residence = registered_residence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseridentify() {
        return useridentify;
    }

    public void setUseridentify(String useridentify) {
        this.useridentify = useridentify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCreate_time() {
        return create_time;
    }

    @Override
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEntrance_time() {
        return entrance_time;
    }

    public void setEntrance_time(String entrance_time) {
        this.entrance_time = entrance_time;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", realname='" + realname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", slogan='" + slogan + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", idnum='" + idnum + '\'' +
                ", nation='" + nation + '\'' +
                ", registered_residence='" + registered_residence + '\'' +
                ", email='" + email + '\'' +
                ", useridentify='" + useridentify + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", health='" + health + '\'' +
                ", type='" + type + '\'' +
                ", create_time='" + create_time + '\'' +
                ", entrance_time='" + entrance_time + '\'' +
                ", class_id='" + class_id + '\'' +
                ", class_name='" + class_name + '\'' +
                ", college_id='" + college_id + '\'' +
                ", college_name='" + college_name + '\'' +
                ", school_id='" + school_id + '\'' +
                ", school_name='" + school_name + '\'' +
                '}';
    }
}
