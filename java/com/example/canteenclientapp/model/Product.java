package com.example.canteenclientapp.model;

import java.io.Serializable;

/**
 * Created by DEEP on 11/7/2017.
 */

public class Product implements Serializable {
    private String name;
    private String price;
    private String quantity;
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
