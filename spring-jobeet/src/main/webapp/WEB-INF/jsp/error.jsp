<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:choose>
	<c:when test="${empty exception}">
		<p>${mensaje}</p>
		<c:if test="${not empty listaErrores}">
			<c:forEach items="${listaErrores}" var="error">
				<li>${error.getDefaultMessage()}</li>
			</c:forEach>
		</c:if>
	</c:when>
	<c:otherwise>
		<p>Se ha producido un error, por favor, inténtelo más tarde.</p>
	</c:otherwise>
</c:choose>

<c:if test="${not empty exception}">
	<!--<a href="#" onclick="javascript:blmostrocult('detalleExcepcion'); return false;">Detalle Excepci&oacute;n</a>-->
	<input type="checkbox" id="read_more" role="button">
	<label for="read_more" onclick=""><span>Detalle
			Excepci&oacute;n</span><span>Ocultar Detalle</span></label>
	<div id="detalleExcepcion">
		<p>
			<c:choose>
				<c:when
					test="${exception.getClass().getName()}==java.sql.SQLException">
					<label>Excepción:
						${exception.getErrorCode()}:${exception.getMessage()} </label>
				</c:when>
				<c:otherwise>
					<label>Excepción:
						${exception.hashCode()}:${exception.getMessage()} </label>
				</c:otherwise>
			</c:choose>
			<c:forEach items="${exception.stackTrace}" var="element">
				<c:out value="${element}" />
			</c:forEach>
		</p>
	</div>
</c:if>

