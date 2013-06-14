<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.7.2.min.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
	
<script type="text/javascript">
	function enviar(){
		parent.location=contextRoot() + '<c:out value="${accion}" escapeXml="false"/>' + '&sistema=' + $("#sistema").val();
	}
</script> 



<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td class="azulAjustado">${titulo}</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<select id="sistema">
				<c:forEach items="${aplicaciones}" var="aplicacion">
					<option value="${aplicacion}">${aplicacion}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td height="20">
			<input type="button" value="Aceptar" onclick="javascript:enviar();">
		</td>
	</tr>
</table>


