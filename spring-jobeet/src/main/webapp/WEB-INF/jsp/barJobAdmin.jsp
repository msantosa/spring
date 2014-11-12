<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="job_actions">
	<h3>Admin</h3>
	<ul>
		<c:if test="${trabajo.is_activated}">
			<li>Edit</li>
			<li>Publish</li>
		</c:if>
		<li>
			Delete
		</li>
		<c:choose>
			<c:when test="${trabajo.is_activated}">
				<li>
					Expires in <strong> 5 </strong> days
				</li>
			</c:when>
			<c:otherwise>
				<li>[Bookmark this job to manage this job in the future.]</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>