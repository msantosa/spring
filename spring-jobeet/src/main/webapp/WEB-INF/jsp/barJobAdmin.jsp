<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="job_actions">
	<h3>Admin</h3>
	<ul>
		<c:if test="${trabajo.is_activated}">
			<li><a href="${pageContext.request.contextPath}/newJob">Edit</a></li>
			<li><a href="${pageContext.request.contextPath}/publishJob">Publish</a></li>
		</c:if>
		<li><a href="${pageContext.request.contextPath}/deleteJob">Delete</a></li>
		<c:choose>
			<c:when test="${trabajo.is_activated}">
				<c:choose>
					<c:when test="${trabajo.estaExpirado()}">
						<li>Expired</li>
					</c:when>
					<c:otherwise>
						<li>
							Expires in <strong>${trabajo.obtenerDiasAExpirar()} </strong> days
							<c:if test="${trabajo.obtenerDiasAExpirar()<diasAdvertencia}">
								- <a href="${pageContext.request.contextPath}/extendJob">Extend</a> for another ${diasProrroga} days
							</c:if>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<li>[Bookmark this <a href="${pageContext.request.requestURL}">job</a> to manage this job in the future.]</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>