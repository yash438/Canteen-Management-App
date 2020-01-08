package com.example.model;

public class Order {
private String productname;
private String productprice;
private String productquantity;
private String total;
private String delivery;
private String name;
private String mobile;
private String username;
private String mode;
private int order_id;


public int getOrder_id() {
	return order_id;
}
public void setOrder_id(int orderId) {
	order_id = orderId;
}
public String getMode() {
	return mode;
}
public void setMode(String mode) {
	this.mode = mode;
}
public String getDelivery() {
	return delivery;
}
public void setDelivery(String delivery) {
	this.delivery = delivery;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public String getProductprice() {
	return productprice;
}
public void setProductprice(String productprice) {
	this.productprice = productprice;
}
public String getProductquantity() {
	return productquantity;
}
public void setProductquantity(String productquantity) {
	this.productquantity = productquantity;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
@Override
public String toString() {
    return productname+"\t"+productprice+"\t"+productquantity+"\t"+total+"\t"+delivery+"\t"+name+"\t"+mobile+"\t"+username+"\t"+mode;
}
}
