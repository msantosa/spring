<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="jobs">
	<c:if test="${not empty listaTrabajosActivos}">
		<table class="jobs">
			<!--  <thead>
				<tr>
					<td colspan="3">"Categoria"</td>
				</tr>
			</thead>-->
			<c:forEach items="${listaTrabajosActivos}" var="trabajo" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
					<td class="location" >${trabajo.location}</td>
					<td class="position" >${trabajo.position}</td>
					<td class="company" >${trabajo.company}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>