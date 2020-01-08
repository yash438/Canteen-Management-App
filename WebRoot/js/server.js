$(document).ready(function(){
    $("#category").change(function(){
        var cat=$("#category").val();
       if(cat!="select category"){
    	   $("#product").html("<option>Select Product</option>");
    	   var menu = $('#product');
    	   $.post("add",
        {
          category: cat,
          opt:4,
        },
        function(data,status){
        	for(var i=0;i<data.length;i++){
        menu.append("<option value="+data[i].name+">"+data[i].name+"</option>");
        }
        });
    	   
       }else{
    	   $("#product").html("<option>Select Product</option>");
       }
    });
});
