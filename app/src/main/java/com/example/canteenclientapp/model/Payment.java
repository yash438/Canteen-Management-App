package com.example.canteenclientapp.model;

import java.io.Serializable;

/**
 * Created by DEEP on 06-03-2018.
 */

public class Payment implements Serializable {
    private String amount;
    private String status;
    private int orderid;
    private String username;
    private String mobile;
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getOrderid() {
        return orderid;
    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return getOrderid() + "\t \t " + getAmount() + " \t \t " + getUsername();
    }
}
