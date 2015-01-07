<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/spring-jobeet/favicon.ico">
<title>Signin Template for Bootstrap</title>
<!-- Bootstrap core CSS -->
<link type="text/css" rel="stylesheet"
	href="/spring-jobeet/css/bootstrap.css">
<link type="text/css" rel="stylesheet"
	href="/spring-jobeet/css/login.css">
</head>

<body>
	<div class="container">

		<form class="form-signin" method="post" name="loginForm"
			action="<c:url value='/j_spring_security_check' />">
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${not empty error}">
				<font color="red"> Authentication was not successful, try
					again.<br /> Cause:
					 ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					 <br /> <%-- ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}<br/> --%>
					<%-- ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}<br/> --%>
				</font>
				<br />
			</c:if>
			<label for="inputEmail" class="sr-only">User</label> <input
				type="text" id="inputEmail" class="form-control" name="j_username"
				placeholder="User" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" class="form-control"
				name="j_password" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>
	<!-- /container -->
</body>
</html>
