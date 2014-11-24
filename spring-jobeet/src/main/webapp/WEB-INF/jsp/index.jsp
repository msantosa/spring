<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="jobs">
	<c:if test="${not empty listaCategoriaTrabajo}">
		<c:forEach items="${listaCategoriaTrabajo}" var="categoria"
			varStatus="loopStatus">
			<c:if test="${categoria.trabajos.size()>0}">
				<div class="category_${categoria.getName()}">
					<div class="category">
						<div class="feed">
							<a href="#">Feed</a>
						</div>
						<h1><a href="${pageContext.request.contextPath}/showCategory/${categoria.id}/1">${categoria.getName()}</a></h1>
					</div>
					<table class="jobs">
					<c:forEach items="${categoria.trabajos}" var="trabajo"
						varStatus="loopStatus">
						
							<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
								<td class="location">${trabajo.location}</td>
								<td class="position"><a
									href="${pageContext.request.contextPath}/showJob/${trabajo.id}">${trabajo.position}</a></td>
								<td class="company">${trabajo.company}</td>
							</tr>
						
					</c:forEach>
					</table>
					<c:if test="${categoriaTrabajosActivos.get(categoria.id).intValue()>0}">
						<div class="more_jobs">
							and <a href="${pageContext.request.contextPath}/showCategory/${categoria.id}/1">${categoriaTrabajosActivos.get(categoria.id).intValue()}</a> more...
						</div>
					</c:if>
				</div>
			</c:if>
		</c:forEach>

	</c:if>
</div>