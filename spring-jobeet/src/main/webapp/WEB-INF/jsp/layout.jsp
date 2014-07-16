<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
  </head>
  <body>
    <div id="container">
      <tiles:insertAttribute name="header" />
      
      <div id="content">
      	<div class="content">
      		<tiles:insertAttribute name="body" />
      	</div>
      </div>
      
 	  <tiles:insertAttribute name="footer" />
    </div>
  </body>
</html>