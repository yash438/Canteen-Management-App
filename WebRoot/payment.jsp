<%@page import="com.example.model.Payment"%>
<%@page import="com.example.dao.PaymentDao"%><%
String status=request.getParameter("status");
String total=request.getParameter("total");
String mobile=request.getParameter("mobile");
String uname=request.getParameter("uname");
String order=request.getParameter("order");
/*System.out.println(status+"\t"+total+"\t"+name+"\t"+mobile+"\t"+uname+"\t"+order);*/
if(order!=null){
Payment p=new Payment();
p.setAmount(total);
p.setMobile(mobile);
p.setOrderid(Integer.parseInt(order));
p.setStatus(status);
p.setUsername(uname);
int i=PaymentDao.getPaymentDao().insert(p);
if(i>0){
out.print("Thanks For using Canteen Automation App. We serve always better.");
}
}
%>