package com.jenking.graduatesinternship.models.impl;

import com.jenking.graduatesinternship.models.base.BaseTableModel;

public class MessageModel extends BaseTableModel {
    private String send_user_id;
    private String send_user_type;
    private String send_user_name;
    private String send_user_contract;
    private String receive_user_id;
    private String receive_user_type;
    private String message;

    public String getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(String send_user_id) {
        this.send_user_id = send_user_id;
    }

    public String getSend_user_type() {
        return send_user_type;
    }

    public void setSend_user_type(String send_user_type) {
        this.send_user_type = send_user_type;
    }

    public String getSend_user_name() {
        return send_user_name;
    }

    public void setSend_user_name(String send_user_name) {
        this.send_user_name = send_user_name;
    }

    public String getSend_user_contract() {
        return send_user_contract;
    }

    public void setSend_user_contract(String send_user_contract) {
        this.send_user_contract = send_user_contract;
    }

    public String getReceive_user_id() {
        return receive_user_id;
    }

    public void setReceive_user_id(String receive_user_id) {
        this.receive_user_id = receive_user_id;
    }

    public String getReceive_user_type() {
        return receive_user_type;
    }

    public void setReceive_user_type(String receive_user_type) {
        this.receive_user_type = receive_user_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "send_user_id='" + send_user_id + '\'' +
                ", send_user_type='" + send_user_type + '\'' +
                ", send_user_name='" + send_user_name + '\'' +
                ", send_user_contract='" + send_user_contract + '\'' +
                ", receive_user_id='" + receive_user_id + '\'' +
                ", receive_user_type='" + receive_user_type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
