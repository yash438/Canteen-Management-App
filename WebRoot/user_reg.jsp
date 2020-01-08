
<%@page import="com.example.model.User"%>
<%@page import="com.example.dao.UserDao"%><%
String uname=request.getParameter("uname");
String pass=request.getParameter("pass");
String name=request.getParameter("name");
String mobile=request.getParameter("mobile");
String address=request.getParameter("address");

System.out.println(uname+"\t"+pass);

if(uname!=null && pass!=null && name!=null && mobile!=null && address!=null){

User u=new User();
u.setUsername(uname);
u.setPass(pass);
u.setName(name);
u.setMobile(mobile);
u.setAddress(address);

UserDao ud=UserDao.getUserDao();
int i=ud.insert(u);
if(i>0){
out.print("Successfully Registered");
}else{
out.print("Registeration Failed !");
}
}


%>