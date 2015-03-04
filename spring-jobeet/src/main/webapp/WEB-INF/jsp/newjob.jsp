<%@ include file="/WEB-INF/jsp/include.jsp" %>
<script language="JavaScript" type="text/javascript">

	function HandleBrowseClick() {
		var fileinput = document.getElementById("file");
		fileinput.click();
	}
	function Handlechange() {
		var fileinput = document.getElementById("file");
		var textinput = document.getElementById("filename");
		textinput.value = fileinput.value;
	}
</script>
<spring:message code="jobform.button.file" var="textButton" />

<div id="job">
	<h1>NEW JOB</h1>
	<form:form method="post" commandName="trabajo" enctype="multipart/form-data">
		<div>
			<form:label path="category.id"><spring:message code="jobform.label.category"/></form:label> 
			<form:select path="category.id">
				<form:option value=""><spring:message code="select.category"/></form:option>	
			 	<form:options items="${listaCategorias}" itemValue="id" itemLabel="name" />
			</form:select> 
			<form:errors path="category.id" class="error_validacion"/>
		</div>
		<div>
			<form:label path="type"><spring:message code="jobform.label.type"/></form:label>
			<form:select  path="type">
				<form:option value=""><spring:message code="select.contract"/></form:option>
	  			<form:options items="${tiposContrato}" />
  			</form:select>
  			<form:errors path="type" class="error_validacion"/>
		</div>
		<div>
			<form:label path="company"><spring:message code="jobform.label.company"/></form:label>
			<form:input id="company" path="company" />
			<form:errors path="company" class="error_validacion"/>
		</div>
		<div>
			<form:label path="logo" for="file"><spring:message code="jobform.label.logo"/></form:label>
			<!--<input path="logo" type="text" id="filename" readonly="true" disabled="disabled"/>
			<input type="button" value="${textButton}" id="fakeBrowse" onclick="HandleBrowseClick();"/>-->
			<input type="file" id="file" class="file" name="file"/>
			<!-- <input type="file" id="file" class="file" name="file" style="display: none" onChange="Handlechange();"/>-->
		</div>
		<div>
			<form:label path="url"><spring:message code="jobform.label.url"/></form:label>
			<form:input id="url" path="url" />
			<form:errors path="url" class="error_validacion"/>
		</div>
		<div>
			<form:label path="position"><spring:message code="jobform.label.position"/></form:label>
			<form:input id="position" path="position"/>
			<form:errors path="position" class="error_validacion"/>
		</div>
		<div>
			<form:label path="location"><spring:message code="jobform.label.location"/></form:label>
			<form:input id="location" path="location" />
			<form:errors path="location" class="error_validacion"/>
		</div>
		<div>
			<form:label class="description" path="description"><spring:message code="jobform.label.description"/></form:label>
			<form:textarea id="description" path="description" rows="4" cols="50"/>
			<form:errors path="description" class="error_validacion"/>
		</div>
		<div>
			<form:label class="how_to_apply" path="how_to_apply"><spring:message code="jobform.label.HowToApply"/></form:label>
			<form:textarea id="how_to_apply" path="how_to_apply" rows="4" cols="50"/>
			<form:errors path="how_to_apply" class="error_validacion"/>
		</div>
		<div>
			<form:label path="is_public"><spring:message code="jobform.label.isPublic"/></form:label>
			<form:checkbox id="is_public" path="is_public" />
		</div>
		<div>
			<form:label path="email"><spring:message code="jobform.label.email"/></form:label>
			<form:input id="email" path="email" type="email"/>
		</div>
		<div>
			<form:label path="expires_at"><spring:message code="jobform.label.expiresAt"/></form:label>
			<form:input path="expires_at" readonly="readonly" id="expires_at" type="datetime-local" value="" min="2000-10-08T12:05:00" max="2099-10-08T12:23:59"/>
			<input type="button" value="Cal" onclick="displayCalendar(document.getElementById('expires_at'),'dd/mm/yyyy hh:ii',this,true)">
			<form:errors path="expires_at" class="error_validacion"/>
		</div>
		<div>
			<button type="button" class="btn" onclick="history.back()"><spring:message code="jobform.button.back"/></button>
			<button type="reset" class="btn"><spring:message code="jobform.button.reset"/></button>
			<button type="submit" class="btn"><spring:message code="jobform.button.save"/></button>
		</div>
	</form:form>
</div>