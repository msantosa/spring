<div id="header">
	<div class="content">
    	<h1><a href="${pageContext.request.contextPath}">
        	<img src="${pageContext.request.contextPath}/legacy/images/logo.jpg" alt="Jobeet Job Board" />
        </a></h1>
 
       	<div id="sub_header">
        	<div class="post">
            	<h2>Ask for people</h2>
            	<div>
                	<a href="${pageContext.request.contextPath}/newJob">Post a Job /></a>
              	</div>
            </div>
 
            <div class="search">
              <h2>Ask for a job</h2>
              <form action="/busquedaAjax.htm" method="get">
                <input type="text" name="keywords"
                  id="search_keywords" />
                <input type="submit" value="search" />
                <img id="loader" src="${pageContext.request.contextPath}/legacy/images/loader.gif" style="vertical-align: middle; display: none" />
                <div class="help">
                  Enter some keywords (city, country, position, ...)
                </div>
              </form>
            </div>
    	</div>
	</div>
</div>
