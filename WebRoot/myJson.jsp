
<%@page import="com.example.dao.ProductDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.example.model.Product"%><%
ProductDao pd=new ProductDao();
HashMap<String,List<Product>> hm=new HashMap<String,List<Product>>();
hm.put("cake",pd.getProducts("cake"));
hm.put("pastry",pd.getProducts("pastry"));
hm.put("snack",pd.getProducts("snack"));

Gson g=new Gson();
String data=g.toJson(hm);
 out.print(data);
 System.out.println(data);

%>