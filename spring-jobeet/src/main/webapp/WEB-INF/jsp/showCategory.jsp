<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="firstUrl" value="/showCategory/${categoria.id}/1" />
<c:url var="lastUrl" value="/showCategory/${categoria.id}/${endIndex}" />
<c:url var="prevUrl"
	value="/showCategory/${categoria.id}/${currentIndex - 1}" />
<c:url var="nextUrl"
	value="/showCategory/${categoria.id}/${currentIndex + 1}" />

<div id="jobs">

	<div class="category_${categoria.getName()}">
		<div class="category">
			<div class="feed">
				<a href="">Feed</a>
			</div>
			<h1>${categoria.getName()}</h1>
		</div>
		<c:forEach items="${listaTrabajos}" var="trabajo"
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
		<div class="pagination">
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<a href="#"> <img src="${pageContext.request.contextPath}/legacy/images/first.png"
						alt="First page" title="First page" />
					</a>
					<a href="#"> <img src="${pageContext.request.contextPath}/legacy/images/previous.png"
						alt="Previous page" title="Previous page" />
					</a>
				</c:when>
				<c:otherwise>
					<a href="${firstUrl}"> <img src="${pageContext.request.contextPath}/legacy/images/first.png"
						alt="First page" title="First page" />
					</a>
					<a href="${prevUrl}"> <img src="${pageContext.request.contextPath}/legacy/images/previous.png"
						alt="Previous page" title="Previous page" />
					</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="/showCategory/${categoria.id}/${i}" />
				<c:choose>
					<c:when test="${i == currentIndex}">
						<c:out value="${i}" />
					</c:when>
					<c:otherwise>
						<a href="${pageUrl}"><c:out value="${i}" /></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentIndex == endIndex}">
					<a href="#"> <img src="${pageContext.request.contextPath}/legacy/images/next.png" alt="Next page"
						title="Next page" />
					</a>
					<a href="#"> <img src="${pageContext.request.contextPath}/legacy/images/last.png" alt="Last page"
						title="Last page" />
					</a>
				</c:when>
				<c:otherwise>
					<a href="${nextUrl}"> <img src="${pageContext.request.contextPath}/legacy/images/next.png"
						alt="Next page" title="Next page" />
					</a>
					<a href="${lastUrl}"> <img src="${pageContext.request.contextPath}/legacy/images/last.png"
						alt="Last page" title="Last page" />
					</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="pagination_desc">
			<strong>
				${numTrabajos}
			</strong> jobs in this category 

			<c:if test="${endIndex>1}">
				- page ${currentIndex}/${endIndex}
			</c:if>
		</div>
	</div>
</div>