
<%@page import="com.example.dao.OrderDao"%>
<%@page import="java.util.List"%>
<%@page import="com.example.model.Order"%><%
String status=(String)session.getAttribute("loginstatus");
if(status!=null){
%>
<Table bgcolor="white" border="1">
<tr style="background-color: red;"><td>PRODUCT NAME</td><td>PRODUCT PRICE</td><td>PRODUCT QUANTITY</td><td>USER NAME</td><td>ORDER ID</td><td>TOTAL</td></tr>
<%
List<Order> order=OrderDao.getOrderDao().getAllOrders();

if(order.size()>0){
for(int i=0;i<order.size();i++){
Order o=order.get(i);
%>
<tr><td><%=o.getProductname() %></td><td><%=o.getProductprice() %></td><td><%=o.getProductquantity() %></td><td><%=o.getUsername() %></td><td><a href="orderdetail.jsp?order_id=<%=o.getOrder_id() %>" target="_blank"><%=o.getOrder_id() %></a></td><td><%=o.getTotal() %></td></tr>
<%
}
}else{
%>
<tr><td>There is no order ! </td></tr>
<%
}
}

%>
</Table>