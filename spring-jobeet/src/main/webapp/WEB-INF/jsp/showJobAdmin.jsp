<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="job">
	<h1>${trabajo.company}</h1>
	<h2>${trabajo.location}</h2>
	<h3>
		${trabajo.position}
		<small>${trabajo.type}</small>
	</h3>
	
	<c:if test="${not empty trabajo.logo}">
		<div class="logo">
			<a href="${trabajo.url}">
			<img alt="${trabajo.company}" src="/uploads/jobs/${trabajo.logo}">
			</a>
		</div>
	</c:if>
	
	<div class="description">
		<p>${trabajo.description} </p>
	</div>
	
	<h4>How to apply?</h4>
	
	<p class="how_to_apply">${trabajo.how_to_apply} </p>
	
	<div class="meta">
		<small> posted on ${trabajo.created_at} </small>
	</div>
</div>