<%
String status=(String)session.getAttribute("loginstatus");
if(status.equals("true")){
session.removeAttribute("loginstatus");
}
%>
<jsp:include page="index.jsp">
<jsp:param value="" name="msg"/>
</jsp:include>