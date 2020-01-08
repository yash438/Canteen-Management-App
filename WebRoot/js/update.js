$(document).ready(function(){
    $("#update").click(function(){
        var product=$("#product").find("option:selected").text();
        var cat=$("#category").val();
        var price=$("#price").val();
        var quantity=$("#quantity").val();
        
       if(price!=null && quantity!=null){

    	   $.post("add",
        {
          category: cat,
          name: product,
          price:price,
          quantity:quantity,
          opt:2,
        },
        function(data,status){
        	alert(status);
        	$('#product').html("<option>select product</option>");
        	$('#quantity').val("");
        	$('#price').val("");
        });
    	   
       }
       else{
    	   alert("please fill all data !");
       }
    });
});