<head>
<link href="css/style.css" rel="stylesheet">
</head>
<div id="head">
<div id="logo"><h1>Canteen App</h1></div>
<div id="nav"><ul>
<%
String status=(String)session.getAttribute("loginstatus");

if(status!=null){
%>
<li><a href="index.jsp?opt=5">Logout</a></li>
<li><a href="index.jsp?opt=6">View Product</a></li>
<li><a href="index.jsp?opt=7">Add Product</a></li>
<li><a href="index.jsp?opt=8">List Orders</a></li>
<%
}else{
%>
<li><a href="index.jsp?opt=4">Contact Us</a></li>
<li><a href="index.jsp?opt=3">Services</a></li>
<li><a href="index.jsp?opt=2">About Us</a></li>
<li><a href="index.jsp?opt=1">Login</a></li>
<li><a href="index.jsp">Home</a></li>
<%
}
%>
</ul></div>
</div>