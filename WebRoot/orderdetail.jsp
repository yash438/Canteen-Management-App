
<%@page import="com.example.dao.OrderDao"%>
<%@page import="com.example.dao.PaymentDao"%>
<%@page import="com.example.model.Payment"%><%
String order=request.getParameter("order_id");
int order_id=Integer.parseInt(order);
Payment payment=PaymentDao.getPaymentDao().getPaymentDetailByOrderId(order_id);
System.out.println(payment.getUsername()+"\t"+payment.getAmount()+"\t"+payment.getStatus());
%>
<form action="updatestatus.jsp">
<table align="center" cellpadding="5" cellspacing="5">
<tr><td>ORDER ID</td><td><input type="text" value="<%=payment.getOrderid() %>" name="orderid"/></td></tr>
<tr><td>USERNAME</td><td><input type="text" value="<%=payment.getUsername() %>" name="username"/></td></tr>
<tr><td>MOBILE</td><td><input type="text" value="<%=payment.getMobile() %>" name="mobile"/></td></tr>
<tr><td>TOTAL</td><td><input type="text" value="<%=payment.getAmount() %>" name="total"/></td></tr>
<tr><td>PRICE STATUS</td><td><input type="text" value="<%=payment.getStatus()%>" name="status"/>(pending/done)</td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="UPDATE"></td></tr>
</table>
</form>