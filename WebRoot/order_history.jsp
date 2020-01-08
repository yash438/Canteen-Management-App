
<%@page import="com.example.dao.PaymentDao"%>
<%@page import="java.util.List"%>
<%@page import="com.example.model.Payment"%>
<%@page import="com.google.gson.Gson"%><%
String mobile=request.getParameter("mobile");
String uname=request.getParameter("uname");

List<Payment>list=PaymentDao.getPaymentDao().getAllOrders(uname,mobile);
if(list.size()>0){
Gson g=new Gson();
String data=g.toJson(list);
out.print(data);
System.out.println(data);
}
%>