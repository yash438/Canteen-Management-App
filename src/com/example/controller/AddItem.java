package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductDao;
import com.example.model.Product;
import com.google.gson.Gson;

public class AddItem extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter pw=resp.getWriter();
	
	String opt=req.getParameter("opt");
	System.out.println("enter : " + opt);
	if(opt!=null){

		ProductDao pd=new ProductDao();
		
		int choice=Integer.parseInt(opt);
		switch(choice){
		case 1://add
			String c=req.getParameter("category");
			String pro=req.getParameter("name");
			String q=req.getParameter("quantity");
			String pr=req.getParameter("price");
			String nn=req.getParameter("newname");
			Product pp=new Product();
			pp.setId(c);
			pp.setName(nn);
			pp.setPrice(pr);
			pp.setQuantity(q);
			int i1=pd.insert(pp);
			if(i1>0){
				pw.print("success fully add !");
			}else{
				pw.print("failed to add !");
			}
			break;
		case 2://update
			String category1=req.getParameter("category");
			String price=req.getParameter("price");
			String quantity=req.getParameter("quantity");
			String name=req.getParameter("name");
			Product p1=new Product();
			p1.setId(category1);
			p1.setName(name);
			p1.setPrice(price);
			p1.setQuantity(quantity);
			
			int i=pd.update(p1);
			if(i>0){
				pw.print("success update");
			}else{
				pw.print("failed update");
			}
			break;
		case 3://delete
			String category2=req.getParameter("category");
			String name2=req.getParameter("name");
			int ii=pd.delete(name2,category2);
			if(ii>0){
				pw.print("success update");
			}else{
				pw.print("failed update");
			}
			break;
		case 4://return json
			resp.setContentType("application/json");
			String cat=req.getParameter("category");
			if(cat!=null){
			ArrayList<Product> list=pd.getProducts(cat);
			Gson g=new Gson();
			String json=g.toJson(list);
			System.out.println("hit " + json);
			pw.print(json);
			}
			break;
		case 5:
			resp.setContentType("application/json");
			String category=req.getParameter("category");
			String product=req.getParameter("name");
			System.out.println(category+"\t"+product);
			if(category!=null&&product!=null){
			Product p=pd.getProduct(product,category);
			Gson g=new Gson();
			String json=g.toJson(p);
			pw.print(json);
			System.out.println(json);
			}
			break;
		}
		
	}
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
doGet(req, resp);

}
}
