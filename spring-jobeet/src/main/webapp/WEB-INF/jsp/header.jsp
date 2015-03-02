<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="header">
	<div class="content">
    	<h1><a href="${ctx}">
        	<img src="${ctx}/legacy/images/logo.jpg" alt="Jobeet Job Board" />
        </a></h1>
 
       	<div id="sub_header">
        	<div class="post">
            	<h2>Ask for people</h2>
            	<div>
                	<a href="${ctx}/newJob">Post a Job /></a>
              	</div>
            </div>
 
            <div class="search">
              <h2><spring:message code="label.askForJob"/><span>>></span></h2>
              <form action="${ctx}/buscadorTrabajos/1" method="get">
                <input type="text" name="patroBusqueda"
                  id="search_keywords" />
                <input type="submit" value="search" />
                <img id="loader" src="${ctx}/legacy/images/loader.gif" style="vertical-align: middle; display: none" />
                <div class="help">
                  <spring:message code="label.searchPattern"/>
                </div>
              </form>
            </div>
    	</div>
	</div>
</div>
