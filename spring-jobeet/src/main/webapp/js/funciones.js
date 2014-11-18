function validarFormJob() {
	if(document.getElementById('type').selectedIndex==0){
		alert("Debe seleccionar el tipo de contrato del trabajo");
		return false;
	}
	if(document.getElementById('company').value==""){
		alert("Debe informarse el nombre de la compa\u00f1\u00eda");
		return false;
	}
	if(document.getElementById('position').value==""){
		alert("Debe informarse la posici\u00f3n del trabajo");
		return false;
	}
	if(document.getElementById('location').value==""){
		alert("Debe informarse la localizaci\u00f3n del trabajo");
		return false;
	}
	if(document.getElementById('description').value==""){
		alert("Debe rellenarse la descripci\u00f3n del trabajo");
		return false;
	}
	if(document.getElementById('how_to_apply').value==""){
		alert("Debe rellenarse c\u00f3mo aplicar el trabajo");
		return false;
	}
	if(document.getElementById('expires_at').value==""){
		alert("Debe rellenarse la fecha de expiraci\u00f3n del trabajo");
		return false;
	}
	else{
		var fechaActual=new Date();
		var sfecha_expiracion= document.getElementById('expires_at').value;
		var dfechaExpiracion=new Date(sfecha_expiracion);
		
		if(dfechaExpiracion<fechaActual){
			alert("La fecha de expiraci\u00f3n debe ser mayor que la fecha actual");
			return false;
		}	
	}
}

function blmostrocult(detalleExc){
	var detalle=document.getElementById('detalleExcepcion');
	
	if(detalle.style.display=='none'){
		detalle.style.display='inline';
	}else{
		detalle.style.display='none';
	}
}