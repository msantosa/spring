<%@ include file="/WEB-INF/jsp/include.jsp" %>
<div id="job">
	<h1>NEW JOB</h1>
	<form:form method="post" action="addJob.html" commandName="trabajo">
		<div>
			<form:label path="category">Category Id</form:label> 
			<form:select path="category">
				<option value="" selected="selected"></option>
				<c:forEach var="categoria" items="${listaCategorias}" >
					<option value="${categoria}" >${categoria.name}</option>
				</c:forEach>	
			</form:select> 
		</div>
		<div>
			<form:label path="type">Type</form:label>
			<form:input path="type"/>
		</div>
		<div>
			<form:label path="company">Company</form:label>
			<form:input path="company" />
		</div>
		<div>
			<form:label path="logo">Logo</form:label>
			<form:input path="logo"/>
		</div>
		<div>
			<form:label path="url">Url</form:label>
			<form:input path="url" />
		</div>
		<div>
			<form:label path="position">Position</form:label>
			<form:input path="position"/>
		</div>
		<div>
			<form:label path="location">Location</form:label>
			<form:input path="location" />
		</div>
		<div>
			<form:label path="description">Description</form:label>
			<form:textarea path="description" rows="4" cols="50"/>
		</div>
		<div>
			<form:label path="how_to_apply">How to apply</form:label>
			<form:textarea path="how_to_apply" rows="4" cols="50"/>
		</div>
		<div>
			<form:label path="token">Token</form:label>
			<form:input path="token"/>
		</div>
		<div>
			<form:label path="is_public">Is public</form:label>
			<form:checkbox path="is_public" />
		</div>
		<div>
			<form:label path="is_activated">Is public</form:label>
			<form:checkbox path="is_activated" />
		</div>
		<div>
			<form:label path="email">Email</form:label>
			<form:input path="email" type="email"/>
		</div>
		<div>
			<form:label path="expires_at">Expires_at</form:label>
			<form:input type="datetime-local" value="01/01/2000T00:00" path="expires_at" name="type-datetime" id="type-datetime" />
		</div>
		<div>
			<input type="submit" value="Añadir">
		</div>
	</form:form>
</div>
