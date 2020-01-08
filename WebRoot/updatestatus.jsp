
<%@page import="com.example.model.Payment"%>
<%@page import="com.example.dao.PaymentDao"%><%
String order=request.getParameter("orderid");
String username=request.getParameter("username");
String mobile=request.getParameter("mobile");
String status=request.getParameter("status");
String total=request.getParameter("total");

if(order!=null&&username!=null&&mobile!=null&&status!=null&&total!=null){
Payment p=new Payment();
p.setAmount(total);
p.setMobile(mobile);
p.setOrderid(Integer.parseInt(order));
p.setStatus(status);
p.setUsername(username);

int i=PaymentDao.getPaymentDao().updatePayment(p);
if(i>0){
out.print("<center><h1>Successfully Update</h1></center>");
 out.println ("<script>"); 
        out.println ("window.close()"); 
        out.println ("</script>"); 
}
}

%>