<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="shortcut icon" href="/favicon.ico" />
<tiles:useAttribute id="list" name="ListCss" classname="java.util.List" />
<c:forEach var="item" items="${list}">
	<link href="${ctx}${item}" rel="stylesheet" type="text/css">
</c:forEach>
<tiles:useAttribute id="listJs" name="ListJS" classname="java.util.List" />
<c:forEach var="item" items="${listJs}">
	<script type="text/javascript" src="${ctx}${item}"></script>
</c:forEach>
</head>
<body>
	<div id="container">
		<div id="header">
			<!--Al usar bootStrap no se puede utilizar h1 directamente
			<h1>-->
				<a href="${pageContext.request.contextPath}"> <img src="${pageContext.request.contextPath}/legacy/images/logo.jpg"	alt="Jobeet Job Board" /></a>
			<!--</h1>-->
		</div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#">Categories</a></li>
			<li><a href="#">Jobs</a></li>
		</ul>
		<div id="content">
			<div class="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>

		<div id="footer">
			<img src="${pageContext.request.contextPath}/legacy/images/jobeet-mini.png" /> powered by 
			<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/legacy/images/symfony.gif" alt="symfony framework" /></a>
		</div>
	</div>
</body>
</html>