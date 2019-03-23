package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class PersonalSkillModel extends BaseTableModel {
    private String user_id;
    private String skill_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    @Override
    public String toString() {
        return "PersonalSkillModel{" +
                "user_id='" + user_id + '\'' +
                ", skill_name='" + skill_name + '\'' +
                '}';
    }
}
