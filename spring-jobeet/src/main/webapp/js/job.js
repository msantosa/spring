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
	
	$("#extender").click(function(){
		$.ajax({
			url: "/spring-jobeet/extender/"+$("#idTrabajo").val(),
			type: 'POST', 
			dataType: "text",
			success: function(data) {
				mensaje_accion("Se ha extendido la fecha de expiraci&oacute;n "+$("#diasProrroga").val()+" d&iacute;as","ok");

				var texto="Expires in <strong> "+parseInt($("#diasExpirar").val())+parseInt($("#diasProrroga").val())+"</strong> days";
				$("#expiracion").html(texto);
			},
			fail: function(data) {
				mensaje_accion("Se ha producido un error al extender la fecha de expiraci&oacute;n","error");
			}
		});
	});
	
	$("#borrar").click(function(){
		$.ajax({
			url: "/spring-jobeet/borrarTrabajo/"+$("#idTrabajo").val(),
			type: 'POST', 
			dataType: "text",
			success: function(data) {
				mensaje_accion("Se ha borrado el trabajo de forma correcta","ok");
				$("#expiracion").hide();
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
	
	if($("#diasExpirar").val()<$("#diasAdvertencia").val()){
		texto=texto+" - <a id=\"extender\" href=\""+$("#contextoAplic").val()+"/extendJob\">Extend</a> for another "+$("#diasProrroga").val() +" days";
	}
	
	$("#marcador").html(texto);
}

function mensaje_accion(mensaje,clase_mensaje){
	$("#mensaje_emergente").html(mensaje);
	$("#mensaje_emergente").attr("class","flash_"+clase_mensaje);
	$("#mensaje_emergente").fadeIn();
}
