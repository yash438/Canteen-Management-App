package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Order;
import com.example.model.Payment;


public class OrderDao {
	
	private static OrderDao od=new OrderDao();
	private OrderDao(){}
	
	public static OrderDao getOrderDao(){
		return od;
	}
	
	public int insert(Order p,int order_id){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, p.getProductname());
			ps.setString(2, p.getProductprice());
			ps.setString(3, p.getProductquantity());
			ps.setString(4, p.getTotal());
			ps.setString(5, p.getDelivery());
			ps.setString(6, p.getName());
			ps.setString(7, p.getMobile());
			ps.setString(8, p.getUsername());
			ps.setString(9, p.getMode());
			ps.setInt(10, order_id);
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public List<Order> getAllOrders(){
		List<Order>list=new ArrayList<Order>();
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from orders");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Order p=new Order();
				p.setProductname(rs.getString(1));
				p.setProductprice(rs.getString(2));
				p.setProductquantity(rs.getString(3));
				p.setTotal(rs.getString(4));
				p.setDelivery(rs.getString(5));
				p.setName(rs.getString(6));
				p.setMobile(rs.getString(7));
				p.setUsername(rs.getString(8));
				p.setMode(rs.getString(9));
				p.setOrder_id(rs.getInt(10));
				list.add(p);
			}
		}catch (Exception e) {
		System.out.println(e);
		}
		return list;
	}
	
}
