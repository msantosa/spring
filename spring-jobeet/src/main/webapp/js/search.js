var htmlInicialBusqueda;

$(document).ready(function()
{
  htmlInicialBusqueda=$("#jobs").html();
  $('.search input[type="submit"]').hide();
 
  $('#search_keywords').keyup(function(key)
  {
    if (this.value.length >= 3)
    {
      $('#loader').show();

      $.ajax({
          url: "/spring-jobeet/buscadorTrabajosAjax/"+this.value,
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
	    	  var pagina="<div id=\"jobs\">No se han encontrado datos</div>";
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
	
	if(data.listaTrabajos.length>0){
		html="<div class=\"category\"><h1><a href=\"#\">Resultado B&uacute;squeda</a></h1>";
		html+="<div id=\"jobs\"><table class=\"jobs\">"
		
		for(i=0;i<data.listaTrabajos.length;i++){
			if(i%2==0){
				html+="<tr class=\"even\">";
			}else{
				html+="<tr class=\"oden\">";
			}
			
			html+="<td class=\"location\">"+data.listaTrabajos[i].location+"</td>";
			html+="<td class=\"position\"><a href=\"/spring-jobeet/showJob/"+data.listaTrabajos[i].id+"\">"+data.listaTrabajos[i].position+"</a></td>";
			html+="<td class=\"company\">"+data.listaTrabajos[i].company+"</td>";
			
			html+="</tr>";
		}
		
		html+="</table></div>";
		
		//Verificamos si hay que paginar
		if(data.numTrabajosRestantes>0){
			html+="<div class=\"more_jobs\">";
			//Redirigimos a la ventana de búsqueda para mostrar más resultados
			html+="and <a href=\"/spring-jobeet/buscadorTrabajos/2/?patroBusqueda="+$('#search_keywords').val()+"\">"+data.numTrabajosRestantes+"</a> more...";
			html+="</div>";
		}
	}
	
	return html;
}