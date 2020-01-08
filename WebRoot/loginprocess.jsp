<%
String uname=request.getParameter("uname");
String pass=request.getParameter("pass");
if(uname.equals("admin")&&pass.equals("admin")){
session.setAttribute("loginstatus","true");
%>
<jsp:include page="index.jsp">
<jsp:param value="" name="msg"/>
</jsp:include>
<%
}else{
%>
<jsp:include page="index.jsp">
<jsp:param value="Login Failed !" name="msg"/>
</jsp:include>
<%
}
%>