$(document).ready(function(){
    $("#product").change(function(){
        var product=$("#product").find("option:selected").text();
        var cat=$("#category").val();
       if(product!="select product"){

    	   $.post("add",
        {
          category: cat,
          name: product,
          opt:5,
        },
        function(data,status){
        	$('#price').val(data.price);
        	$('#quantity').val(data.quantity);
        });
    	   
       }
    });
});