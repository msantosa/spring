var htmlInicialBusqueda;

$(document).ready(function()
{
  htmlInicialBusqueda=$("#jobs").html();
  $('.search input[type="submit"]').hide();
 
  $('#search_keywords').keyup(function(key)
  {
    if (this.value.length >= 3)
    {
 
    	//alert("Mayor que 3");
      //alert("query:"+ this.value + '*');
      $('#loader').show();
      /*$('#jobs').load(
        $(this).parents('form').attr('action'),
        { query: this.value + '*' },
        	function() { $('#loader').hide(); 
        	alert("vuelta de ajax"); }
      );*/
      $.ajax({
          url: "/spring-jobeet/buscadorTrabajos/"+this.value,
          //force to handle it as text
          type: 'POST', 
          dataType: "text",
          success: function(data) {
              
              //data downloaded so we call parseJSON function 
              //and pass downloaded data
              var json = $.parseJSON(data);
              //now json variable contains data in json format
              //let's display a few items
              pagina=resultadoBusqueda(json);
              $("#jobs").html(pagina);
              $('#loader').hide(); 
          },
	      fail: function(data) {
	          
	          //data downloaded so we call parseJSON function 
	          //and pass downloaded data
	          var json = $.parseJSON(data);
	          //now json variable contains data in json format
	          //let's display a few items
	          pagina=resultadoBusqueda(json);
	          $("#jobs").html(pagina);
	          $('#loader').hide(); 
	      }
      });
    }else if(this.value == ''){
    	$("#jobs").html(htmlInicialBusqueda);
        $('#loader').hide();
    }
  });
});

function resultadoBusqueda(data){
	var html="<div id=\"jobs\">No se han encontrado datos</div>";
	
	if(data.length>0){
		html="<div class=\"category\"><h1><a href=\"#\">Resultado B&uacute;squeda</a></h1>";
		html+="<div id=\"jobs\"><table class=\"jobs\">"
		
		for(i=0;i<data.length;i++){
			if(i%2==0){
				html+="<tr class=\"even\">";
			}else{
				html+="<tr class=\"oden\">";
			}
			
			html+="<td class=\"location\">"+data[i].location+"</td>";
			html+="<td class=\"position\"><a href=\"/spring-jobeet/showJob/"+data[0].id+"\">"+data[i].position+"</a></td>";
			html+="<td class=\"company\">"+data[i].company+"</td>";
			
			html+="</tr>";
		}
		
		html+="</table></div>";
	}
	
	return html;
}