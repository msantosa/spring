<%@ include file="/WEB-INF/jsp/include.jsp" %>
<script type="text/javascript">
$(document).ready(function()
{
	$("#description").val("${trabajoEditar.description}");
	$("#how_to_apply").val("${trabajoEditar.how_to_apply}");
	$("#type option[value='${trabajoEditar.type}']").attr("selected","selected");
});

</script>
<!--<div id="mensaje_emergente" class="flash_ok" >Se ha modificado el trabajo de forma correcta</div>-->
<div id="mensaje_emergente" hidden></div>
<div id="job">
	<h1>EDIT JOB</h1>
	<form:form method="post" action="editJob.html" commandName="trabajo" onsubmit="return validarFormJob()">
		<div>
			<form:label path="category.id">Category Id</form:label> 
			<form:select path="category.id" items="${listaCategorias}" itemValue="id" itemLabel="name">
			</form:select> 
		</div>
		<div>
			<form:label path="type">Type</form:label>
			<form:select  path="type" id="type">
				<form:option value="" label="--- Seleccione tipo contrato ---"/>
	  			<form:options items="${tiposContrato}" />
  			</form:select>
		</div>
		<div>
			<form:label path="company">Company</form:label>
			<form:input id="company" path="company" value="${trabajoEditar.company}" />
		</div>
		<div>
			<form:label path="logo">Logo</form:label>
			<input type="file" id="file" class="fileupload" name="fileupload"  onchange="javascript:cambiarLogo(this.value)" style="margin-top: 0px"/>
		</div>
		<div>
			<form:label path="url">Url</form:label>
			<form:input id="url" path="url" value="${trabajoEditar.url}" />
		</div>
		<div>
			<form:label path="position">Position</form:label>
			<form:input id="position" path="position" value="${trabajoEditar.position}"/>
		</div>
		<div>
			<form:label path="location">Location</form:label>
			<form:input id="location" path="location" value="${trabajoEditar.location}"/>
		</div>
		<div>
			<form:label class="description" path="description">Description</form:label>
			<form:textarea id="description" path="description" rows="4" cols="50"  />
		</div>
		<div>
			<form:label class="how_to_apply" path="how_to_apply">How to apply</form:label>
			<form:textarea id="how_to_apply" path="how_to_apply" rows="4" cols="50"/>
		</div>
		<div>
			<form:label path="is_public">Is public</form:label>
			<form:checkbox id="is_public" path="is_public" value="${trabajoEditar.is_public}"/>
		</div>
		<div>
			<form:label path="email">Email</form:label>
			<form:input id="email" path="email" type="email" value="${trabajoEditar.email}"/>
		</div>
		<div>
			<form:label path="expires_at">Expires_at</form:label>
			<form:input path="expires_at" readonly="readonly" id="expires_at" type="datetime-local" min="2000-10-08T12:05:00" max="2099-10-08T12:23:59" value="${trabajoEditar.expires_at}"/>
			<input type="button" value="Cal" onclick="displayCalendar(document.getElementById('expires_at'),'dd/mm/yyyy hh:ii',this,true)">
		</div>
		<div>
			<button type="button" class="btn" onclick="history.back()">Volver</button>
			<button type="reset" class="btn">Reset</button>
			<button type="submit" class="btn">Guardar</button>
		</div>
	</form:form>
</div>
