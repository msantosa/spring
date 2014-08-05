function validarFormJob() {
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
	if(document.getElementById('location').value==""){
		alert("Debe rellenarse c\u00f3mo aplicar el trabajo");
		return false;
	}
	if(document.getElementById('how_to_apply').value==""){
		alert("Debe rellenarse c\u00f3mo aplicar el trabajo");
		return false;
	}
	if(document.getElementById('token').value==""){
		alert("Debe rellenarse el token identificador del trabajo");
		return false;
	}
	if(document.getElementById('expires_at').value==""){
		alert("Debe rellenarse la fecha de expiraci\u00f3n del trabajo");
		return false;
	}
}