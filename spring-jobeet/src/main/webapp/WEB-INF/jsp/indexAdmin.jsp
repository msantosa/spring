<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<div class="col-sm-6 col-sm-6-1">
		<div id="titulo">
			<h1>CATEGORY LIST</h1>
			<a class="add_icon"><img src="${pageContext.request.contextPath}/legacy/images/add.png"><p class="texto_enlace">Add</p></a>
			
		</div>

		<c:if test="${not empty categorias}">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>id</th>
							<th>Name</th>
							<th class="hidden-xs hidden-sm">Slug</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="categoria" items="${categorias}">
							<tr>
								<td>${categoria.id}</td>
								<td>${categoria.name}</td>
								<td class="hidden-xs hidden-sm">${categoria.name}</td>
								<td class="acciones">
									<a class="edit_img" ><img src="${pageContext.request.contextPath}/legacy/images/pencil.png"><p class="texto_enlace">Edit</p></a>
									<a class="edit_img" > <img src="${pageContext.request.contextPath}/legacy/images/trash.png"><p class="texto_enlace">Delete</p></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<span>${fn:length(categorias)} results</span>
		</c:if>
	</div>
	<div class="col-sm-6 col-sm-6-1">
		<form role="form" >
			<div class="form-group">
				<label for="usr">Name:</label> <input type="text"
					class="form-control" id="usr">
			
				<label for="pwd">Slug:</label> <input type="password"
					class="form-control" id="pwd">
				<br>
				<button class="btn">Filter</button>
			</div>
		</form>
	</div>
</div>