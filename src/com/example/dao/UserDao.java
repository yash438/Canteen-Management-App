package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.User;

public class UserDao {
    private static UserDao ud=new UserDao();
	private UserDao(){}
	public static UserDao getUserDao(){
		return ud;
	}
	
	public int insert(User u){
		int i=0;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("insert into user values(?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getName());
			ps.setString(3, u.getPass());
			ps.setString(4, u.getMobile());
			ps.setString(5, u.getAddress());
			i=ps.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public User login(String uname,String pass){
		User u=null;
		try{
			PreparedStatement ps=Dao.getCon().prepareStatement("select * from user where username=? and pass=?");
			ps.setString(1,uname);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				u=new User();
				u.setUsername(uname);
				u.setName(rs.getString(2));
				u.setPass(rs.getString(3));
				u.setMobile(rs.getString(4));
				u.setAddress(rs.getString(5));
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}
	
	
}
