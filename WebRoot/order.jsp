
<%@page import="com.example.dao.OrderDao"%>
<%@page import="com.example.model.Order"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.JSONObject"%>
<%!static int count=1001; 
boolean status=false;%>
<%
System.out.println("Hello");
String or=request.getParameter("order");
if(or!=null){
if(or.equals("1")){
String product_name=request.getParameter("pname");
String price=request.getParameter("price");
String quantity=request.getParameter("quantity");
String total=request.getParameter("total");
String delivery=request.getParameter("delivery");
String name=request.getParameter("name");
String mobile=request.getParameter("mobile");
String username=request.getParameter("uname");
String mode=request.getParameter("mode");

if(total!=null&&username!=null){
Order order=new Order();
order.setProductname(product_name);
order.setProductprice(price);
order.setProductquantity(quantity);
order.setTotal(total);
order.setDelivery(delivery);
order.setName(name);
order.setMobile(mobile);
order.setUsername(username);
order.setMode(mode);

int i=OrderDao.getOrderDao().insert(order,count);
if(i>0){
out.print(count);
}else{
out.print("order Failed.");
}
}
}else{
String cartdata=request.getParameter("group");
JSONArray ja=new JSONArray(cartdata);
System.out.println(ja);

for(int i=0;i<ja.length();i++){
Gson g=new Gson();
System.out.println();
JSONObject jo=ja.getJSONObject(i);
Order o=new Order();
o.setMode(jo.getString("mode"));
o.setDelivery(jo.getString("delivery"));
o.setTotal(jo.getString("total"));
o.setProductquantity(jo.getString("productquantity"));
o.setMobile(jo.getString("mobile"));
o.setName(jo.getString("name"));
o.setProductname(jo.getString("productname"));
o.setProductprice(jo.getString("productprice"));
o.setUsername(jo.getString("username"));

//Order o=g.fromJson(ja.getJSONObject(i).toString(),Order.class);
int j=OrderDao.getOrderDao().insert(o,count);
if(j>0){
status=true;
}
}
if(status){
out.print(count);
}else{
out.print("order Failed.");
}
}
}
count++;
%>