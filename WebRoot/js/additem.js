$(document).ready(function(){
    $("#add").click(function(){
        var product=$("#product").find("option:selected").text();
        var cat=$("#category").val();
        var price=$("#price").val();
        var quantity=$("#quantity").val();
        var newname=$("#name").val();
        alert(newname);
        if(newname!=product){
       if(price!="" && quantity!="" && newname!=""){
    	   $.post("add",
        {
          category: cat,
          name: product,
          price:price,
          quantity:quantity,
          newname:newname,
          opt:1,
        },
        function(data,status){
        	alert(status);
        	$('#product').html("<option>select product</option>");
        	$('#quantity').val("");
        	$('#price').val("");
        	$('#name').val("");
        });
      }else{
    	  alert("please fill all field");
      }
       }
      
    });
    
});