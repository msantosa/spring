$(document).ready(function()
		{
	$("#publicar").click(function(){
		$("#publicar").attr("href","#");
		$.ajax({
			url: "/spring-jobeet/publicar/"+$("#idTrabajo").val(),
			type: 'POST', 
			dataType: "text",
			success: function(data) {
				mensaje_accion_ok("Se ha publicado el trabajo de forma correcta");
			},
			fail: function(data) {
				mensaje_accion_error("Se ha producido un error al publicar el trabajo");
			}
		});
	});
});

function mensaje_accion_ok(mensaje){
	$("#mensaje_emergente").html(mensaje);
	$("#mensaje_emergente").attr("class","flash_ok");
	$("#editar").hide();
	$("#publicar").hide();
	$("#mensaje_emergente").show();
}

function mensaje_accion_error(mensaje){
	$("#mensaje_emergente").html(mensaje);
	$("#mensaje_emergente").attr("class","flash_error");
	$("#mensaje_emergente").show();
}