<%@ include file="/WEB-INF/jsp/include.jsp"%>
<p>Se ha producido un error, por favor, int�ntelo m�s tarde.</p>
<a href="#" onclick="javascript:blmostrocult('detalleExcepcion'); return false;">Detalle Excepci&oacute;n</a>
<div id="detalleExcepcion" style="display: none;">
	<p>
		<label>Excepci�n: ${exception.getErrorCode()}:${exception.getMessage()} </label>
		<c:forEach items="${exception.stackTrace}" var="element">
			<c:out value="${element}" />
		</c:forEach>
	</p>
</div>
