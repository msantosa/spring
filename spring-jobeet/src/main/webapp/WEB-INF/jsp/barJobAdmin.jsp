<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="job_actions">
	<input id="contextoAplic" type="hidden" value="${pageContext.request.contextPath}">
	<input id="idTrabajo" type="hidden" value="${trabajo.id}">
	<input id="diasExpirar" type="hidden" value="${trabajo.obtenerDiasAExpirar()}">
	<input id="diasAdvertencia" type="hidden" value="${diasAdvertencia}">
	<input id="diasProrroga" type="hidden" value="${diasProrroga}">
	<h3>Admin</h3>
	<ul id="accionesTrabajo">
		<c:if test="${not trabajo.is_activated}">
			<li><a id="editar" href="${pageContext.request.contextPath}/editJob/${trabajo.id}">Edit</a></li>
			<li><a id="publicar" href="#">Publish</a></li>
		</c:if>
		<li><a id="borrar" href="#">Delete</a></li>
		<c:choose>
			<c:when test="${trabajo.is_activated}">
				<li id="expiracion">
				<c:choose>
					<c:when test="${trabajo.estaExpirado()}">
						Expired
					</c:when>
					<c:otherwise>
							Expires in <strong>${trabajo.obtenerDiasAExpirar()} </strong> days
							<c:if test="${trabajo.obtenerDiasAExpirar()<diasAdvertencia}">
								- <a id="extender" href="#">Extend</a> for another ${diasProrroga} days
							</c:if>
					</c:otherwise>
				</c:choose>
				</li>
			</c:when>
			<c:otherwise>
				<li id="marcador">[Bookmark this <a href="${requestScope['javax.servlet.forward.query_string']}">job</a> to manage this job in the future.]</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>