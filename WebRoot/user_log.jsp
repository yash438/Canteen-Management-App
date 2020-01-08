
<%@page import="com.example.model.User"%>
<%@page import="com.example.dao.UserDao"%>
<%@page import="com.google.gson.Gson"%><%
String uname=request.getParameter("uname");
String pass=request.getParameter("pass");
System.out.println(uname+"\t"+pass);

if(uname!=null && pass!=null){

UserDao ud=UserDao.getUserDao();
User u=ud.login(uname,pass);
if(u!=null){
Gson g=new Gson();
String data=g.toJson(u);
System.out.println(data);
out.print(data);
}
}


%>