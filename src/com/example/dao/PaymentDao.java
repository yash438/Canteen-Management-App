package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Order;
import com.example.model.Payment;

public class PaymentDao {

	private static PaymentDao od=new PaymentDao();
	private PaymentDao(){}
	
	public static PaymentDao getPaymentDao(){
		return od;
	}
	
	public int insert(Payment p){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("insert into payment values(?,?,?,?,?)");
			ps.setString(1, p.getUsername());
			ps.setString(2, p.getMobile());
			ps.setString(3, p.getAmount());
			ps.setString(4, p.getStatus());
			ps.setInt(5, p.getOrderid());
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public List<Payment> getAllOrders(String uname,String mobile){
		List<Payment>list=new ArrayList<Payment>();
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from payment where username=? and mobile=?");
			ps.setString(1,uname);
			ps.setString(2,mobile);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Payment p=new Payment();
				p.setUsername(rs.getString(1));
				p.setMobile(rs.getString(2));
				p.setAmount(rs.getString(3));
				p.setStatus(rs.getString(4));
				p.setOrderid(rs.getInt(5));
				list.add(p);
			}
		}catch (Exception e) {
		System.out.println(e);
		}
		return list;
	}
	
	public Payment getPaymentDetailByOrderId(int order){
		Payment p=null;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from payment where order_id=?");
			ps.setInt(1, order);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				p=new Payment();
				p.setUsername(rs.getString(1));
				p.setMobile(rs.getString(2));
				p.setAmount(rs.getString(3));
				p.setStatus(rs.getString(4));
				p.setOrderid(rs.getInt(5));
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return p;
	}
	
	public int updatePayment(Payment p){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("update payment set username=? , mobile=? ,amount=? , status=? where order_id=?");
			ps.setString(1, p.getUsername());
			ps.setString(2, p.getMobile());
			ps.setString(3, p.getAmount());
			ps.setString(4, p.getStatus());
			ps.setInt(5, p.getOrderid());
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
}
