<%@ include file="/WEB-INF/jsp/include.jsp" %>
<script type="text/javascript">
$(document).ready(function()
{
	$("#description").val("${trabajoEditar.description}");
	$("#how_to_apply").val("${trabajoEditar.how_to_apply}");
	$("#type option[value='${trabajoEditar.type}']").attr("selected","selected");
	$("#category option[value='${trabajoEditar.category.id}']").attr("selected","selected");
	

	$("#file").change(function() {
		readURL(this);
	});
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_compania').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

</script>
<!--<div id="mensaje_emergente" class="flash_ok" >Se ha modificado el trabajo de forma correcta</div>-->
<div id="mensaje_emergente" hidden></div>
<div id="job">
	<h1>EDIT JOB</h1>
	<form:form method="post" action="editJob.html" modelAttribute="trabajo" onsubmit="return validarFormJob()">
		<div>
			<form:label path="category.id"><spring:message code="jobform.label.category"/></form:label> 
			<form:select path="category.id" id="category">
				<form:option value=""><spring:message code="select.category"/></form:option>	
			 	<form:options items="${listaCategorias}" itemValue="id" itemLabel="name" />
			</form:select>
			<form:errors path="category.id" class="error_validacion"/> 
		</div>
		<div>
			<form:label path="type"><spring:message code="jobform.label.type"/></form:label>
			<form:select  path="type" id="type">
				<form:option value=""><spring:message code="select.contract"/></form:option>
	  			<form:options items="${tiposContrato}" />
  			</form:select>
  			<form:errors path="type" class="error_validacion"/>
		</div>
		<div>
			<form:label path="company"><spring:message code="jobform.label.company"/></form:label>
			<form:input id="company" path="company" value="${trabajoEditar.company}" />
			<form:errors path="company" class="error_validacion"/>
		</div>
		<div>
			<form:label path="logo"><spring:message code="jobform.label.logo"/></form:label>
			<img style="max-width: 30%; max-height: 30%; display:block;"
				alt="${trabajo.company}" src="/uploads/logos/${trabajoEditar.logo}" id="img_compania">
		</div>
		<div>
			<input type="file" id="file" class="fileupload" name="file" style="margin-top: 0px; margin-left:20%"/>
		</div>
		<div>
			<form:label path="url"><spring:message code="jobform.label.url"/></form:label>
			<form:input id="url" path="url" value="${trabajoEditar.url}" />
			<form:errors path="url" class="error_validacion"/>
		</div>
		<div>
			<form:label path="position"><spring:message code="jobform.label.position"/></form:label>
			<form:input id="position" path="position" value="${trabajoEditar.position}"/>
			<form:errors path="position" class="error_validacion"/>
		</div>
		<div>
			<form:label path="location"><spring:message code="jobform.label.location"/></form:label>
			<form:input id="location" path="location" value="${trabajoEditar.location}"/>
			<form:errors path="location" class="error_validacion"/>
		</div>
		<div>
			<form:label class="description" path="description"><spring:message code="jobform.label.description"/></form:label>
			<form:textarea id="description" path="description" rows="4" cols="50"  />
			<form:errors path="description" class="error_validacion"/>
		</div>
		<div>
			<form:label class="how_to_apply" path="how_to_apply"><spring:message code="jobform.label.HowToApply"/></form:label>
			<form:textarea id="how_to_apply" path="how_to_apply" rows="4" cols="50"/>
			<form:errors path="how_to_apply" class="error_validacion"/>
		</div>
		<div>
			<form:label path="is_public"><spring:message code="jobform.label.isPublic"/></form:label>
			<form:checkbox id="is_public" path="is_public" value="${trabajoEditar.is_public}"/>
			<form:errors path="is_public" class="error_validacion"/>
		</div>
		<div>
			<form:label path="email"><spring:message code="jobform.label.email"/></form:label>
			<form:input id="email" path="email" type="email" value="${trabajoEditar.email}"/>
			<form:errors path="email" class="error_validacion"/>
		</div>
		<div>
			<form:label path="expires_at"><spring:message code="jobform.label.expiresAt"/></form:label>
			<form:input path="expires_at" readonly="readonly" id="expires_at" type="datetime-local" min="2000-10-08T12:05:00" max="2099-10-08T12:23:59" value="${trabajoEditar.expires_at}"/>
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
