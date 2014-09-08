<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="jobs">
	<c:if test="${not empty listaCategoriaTrabajo}">
		<!--  <thead>
				<tr>
					<td colspan="3">"Categoria"</td>
				</tr>
			</thead>-->
		<!--<c:forEach items="${listaTrabajosActivos}" var="trabajo" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
					<td class="location" >${trabajo.location}</td>
					<td class="position" ><a href="${pageContext.request.contextPath}/showJob/${trabajo.id}">${trabajo.position}</a></td>
					<td class="company" >${trabajo.company}</td>
				</tr>
			</c:forEach>-->
		<c:forEach items="${listaCategoriaTrabajo}" var="categoria"
			varStatus="loopStatus">
			<c:if test="${categoria.trabajos.size()>0}">
				<div class="category_${categoria.getName()}">
					<div class="category">
						<div class="feed">
							<a href="">Feed</a>
						</div>
						<h1>${categoria.getName()}</h1>
					</div>
					<c:forEach items="${categoria.trabajos}" var="trabajo"
						varStatus="loopStatus">
						<table class="jobs">
							<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
								<td class="location">${trabajo.location}</td>
								<td class="position"><a
									href="${pageContext.request.contextPath}/showJob/${trabajo.id}">${trabajo.position}</a></td>
								<td class="company">${trabajo.company}</td>
							</tr>
						</table>
					</c:forEach>
					<c:if test="${categoriaTrabajosActivos.get(categoria.id).intValue()>0}">
						Hay ${categoriaTrabajosActivos.get(categoria.id).intValue()} mas
					</c:if>
				</div>
			</c:if>
		</c:forEach>

	</c:if>
</div>