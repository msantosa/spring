<%@ include file="/WEB-INF/jsp/include.jsp" %>
<div id="job">
	<h1>NEW JOB</h1>
	<form:form method="post" action="addJob.html" commandName="trabajo" onsubmit="return validarFormJob()" enctype="multipart/form-data">
		<div>
			<form:label path="category.id">Category Id</form:label> 
			<form:select path="category.id" items="${listaCategorias}" itemValue="id" itemLabel="name">
			</form:select> 
		</div>
		<div>
			<form:label path="type">Type</form:label>
			<form:select  path="type">
				<form:option value="" label="--- Seleccione tipo contrato ---"/>
	  			<form:options items="${tiposContrato}" />
  			</form:select>
		</div>
		<!--<div>
			<form:label path="type">Type</form:label>
			<form:input id="type" path="type"/>
		</div>-->
		<div>
			<form:label path="company">Company</form:label>
			<form:input id="company" path="company" />
		</div>
		<div>
			<form:label path="logo">Logo</form:label>
			<!--<form:input id="logo" path="logo"/>-->
			<input type="file" id="file" class="file" name="file"/>
		</div>
		<div>
			<form:label path="url">Url</form:label>
			<form:input id="url" path="url" />
		</div>
		<div>
			<form:label path="position">Position</form:label>
			<form:input id="position" path="position"/>
		</div>
		<div>
			<form:label path="location">Location</form:label>
			<form:input id="location" path="location" />
		</div>
		<div>
			<form:label class="description" path="description">Description</form:label>
			<form:textarea id="description" path="description" rows="4" cols="50"/>
		</div>
		<div>
			<form:label class="how_to_apply" path="how_to_apply">How to apply</form:label>
			<form:textarea id="how_to_apply" path="how_to_apply" rows="4" cols="50"/>
		</div>
		<!--<div>
			<form:label path="token">Token</form:label>
			<form:input id="token" path="token"/>
		</div>-->
		<div>
			<form:label path="is_public">Is public</form:label>
			<form:checkbox id="is_public" path="is_public" />
		</div>
		<!--<div>
			<form:label path="is_activated">Is activated</form:label>
			<form:checkbox id="is_activated" path="is_activated" />
		</div>-->
		<div>
			<form:label path="email">Email</form:label>
			<form:input id="email" path="email" type="email"/>
		</div>
		<div>
			<form:label path="expires_at">Expires_at</form:label>
			<form:input path="expires_at" readonly="readonly" id="expires_at" type="datetime-local" value="" min="2000-10-08T12:05:00" max="2099-10-08T12:23:59"/>
			<input type="button" value="Cal" onclick="displayCalendar(document.getElementById('expires_at'),'dd/mm/yyyy hh:ii',this,true)">
		</div>
		<div>
			<input type="submit" value="Añadir">
		</div>
	</form:form>
</div>
