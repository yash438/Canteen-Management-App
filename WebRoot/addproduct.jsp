<head>
<link href="css/add.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/server.js"></script>
<script type="text/javascript" src="js/add.js"></script>
<script type="text/javascript" src="js/update.js"></script>
<script type="text/javascript" src="js/delete.js"></script>
</head>
<div id="addproduct">

<table >
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
<tr><td>Enter Price</td><td><input id="price" type="text"   placeholder="Price"/></td>
<td>Enter Quantity</td><td><input id="quantity" type="text"   placeholder="Quantity"/></td></tr>
<tr>
<td colspan="2" align="right"><input type="button" value="Update Product" id="update"/></td>
<td colspan="2" align="center"><input type="button" value="Delete Product" id="delete"/></td>
</tr>
</table>
</div>