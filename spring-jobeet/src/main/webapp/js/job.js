$(document).ready(function()
		{
	$("#publicar").click(function(){
		$.ajax({
			url: "/spring-jobeet/publicar/"+$("#idTrabajo").val(),
			type: 'POST', 
			dataType: "text",
			success: function(data) {
				mensaje_accion("Se ha publicado el trabajo de forma correcta","ok");
				$("#editar").hide();
				$("#publicar").hide();
				mostrarExpiracion();
			},
			fail: function(data) {
				mensaje_accion("Se ha producido un error al publicar el trabajo","error");
			}
		});
	});
	
	$("#extender").on("click",extender);
	
	$("#borrar").click(function(){
		$.ajax({
			url: "/spring-jobeet/borrarTrabajo/"+$("#idTrabajo").val(),
			type: 'POST', 
			dataType: "text",
			success: function(data) {
				mensaje_accion("Se ha borrado el trabajo de forma correcta","ok");
				if ($('#job_actions').length) $('#job_actions').hide();
			},
			fail: function(data) {
				mensaje_accion("Se ha producido un error al borrar el trabajo","error");
			}
		});
	});
});

function mostrarExpiracion(){
	//Si se ha dado publicar hay que cambiar el texto del marcador con el de la expiracion
	var texto="Expires in <strong> "+$("#diasExpirar").val()+"</strong> days";
	
	if(parseInt($("#diasExpirar").val())<parseInt($("#diasAdvertencia").val())){
		texto=texto+" - <a id=\"extender\" class=\"extender\" href=\"#\">Extend</a> for another "+$("#diasProrroga").val() +" days";
	}
	
	$("#marcador").html(texto);
	$("#extender").on( "click",extender);
}

function mensaje_accion(mensaje,clase_mensaje){
	$("#mensaje_emergente").html(mensaje);
	$("#mensaje_emergente").attr("class","flash_"+clase_mensaje);
	$("#mensaje_emergente").fadeIn();
}


function extender(){
	$.ajax({
		url: "/spring-jobeet/extender/"+$("#idTrabajo").val(),
		type: 'POST', 
		dataType: "text",
		success: function(data) {
			mensaje_accion("Se ha extendido la fecha de expiraci&oacute;n "+$("#diasProrroga").val()+" d&iacute;as","ok");
			var diasExtension=parseInt($("#diasExpirar").val())+parseInt($("#diasProrroga").val());
			var texto="Expires in <strong> "+diasExtension+"</strong> days";
			if ($('#expiracion').length){
				$("#expiracion").html(texto);
			}
			else if($("#marcador").length){
				$("#marcador").html(texto);
			}
		},
		fail: function(data) {
			mensaje_accion("Se ha producido un error al extender la fecha de expiraci&oacute;n","error");
		}
	});
}