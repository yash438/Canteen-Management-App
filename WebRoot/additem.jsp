
<head>
<link href="css/add.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/server.js"></script>
<script type="text/javascript" src="js/add.js"></script>
<script type="text/javascript" src="js/additem.js"></script>
</head>
<div id="addproduct">

<table align="center">
<!--Category section start-->
<tr><td align="center" colspan="2">Select Category :</td>
<td align="center" colspan="2"><select name="category" style="width: 200px;" id="category">
<option value="select category">Select Category</option>
<option value="cake">Cakes</option>
<option value="pastry">Pastry</option>
<option value="snack">Snacks</option>
</select></td></tr>
<!--Category section end-->
<!--Product section start-->
<tr><td align="center" colspan="2">Select Product</td><td colspan="2" align="center">
<select id="product" name="product" style="width: 200px;">
<option>Select Product</option>
</select>
</td></tr>
<!--Product section end-->
<tr><td align="center" colspan="2">Enter Price</td><td><input id="price" type="text" placeholder="Product Price" required/></td></tr>
<tr><td align="center" colspan="2">Enter Name</td><td><input id="name" type="text" placeholder="Product Name" required/></td></tr>
<tr><td align="center" colspan="2">Enter Quantity</td><td><input id="quantity" type="text" placeholder="Product Quantity" required/></td></tr>
<tr>
<td colspan="4" align="center"><input type="button" value="Add Product" id="add"/></td>
</tr>
</table>
</div>