package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.example.model.Product;

public class ProductDao {

	public int insert(Product p){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("insert into product values(?,?,?,?)");
			ps.setString(1, p.getName());
			ps.setString(2, p.getQuantity());
			ps.setString(3, p.getPrice());
			ps.setString(4, p.getId());
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public int delete(String name,String category_id){
		int i=0;
		
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("delete from product where name=? and category_id=?");
			ps.setString(1, name);
			ps.setString(2, category_id);
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public int update(Product p){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("update product set price=? , quantity=?  where name=? and category_id=?");
			ps.setString(1, p.getPrice());
			ps.setString(2, p.getQuantity());
			ps.setString(3, p.getName());
			ps.setString(4, p.getId());
			i=ps.executeUpdate();
			System.out.println(i);
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public Product getProduct(String name,String category_id){
		Product product=null;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from product where name=? and category_id=?");
			ps.setString(1,name);
			ps.setString(2,category_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				product=new Product();
				product.setName(rs.getString(1));
				product.setQuantity(rs.getString(2));
				product.setPrice(rs.getString(3));
				product.setId(rs.getString(4));
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return product;
	}
	
	public ArrayList<Product> getProducts(String category_id){
		ArrayList<Product> list=new ArrayList<Product>(); 
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from product where category_id=?");
			ps.setString(1,category_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setName(rs.getString(1));
				product.setQuantity(rs.getString(2));
				product.setPrice(rs.getString(3));
				product.setId(rs.getString(4));
		        list.add(product);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	
}
