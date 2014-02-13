<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<html:rewrite page='/css/cupertino/jquery-ui-1.8.custom.css'/>"
	type="text/css">
	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UtilFachada.js'/>"></script>
	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UtilSIIFFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UtilSIIMFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UtilSIIGFachada.js'/>"></script>
		
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>
<script type="text/javascript"	
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>

<script>
	function errh(msg, exc) {
	  $("#divCargando").hide();
	  $("#Aceptar").removeAttr("disabled");
	  alert("Error message is: " + msg + " - Error Details: " + dwr.util.toDescriptiveString(exc, 2));
	  $("#exitoGrabado").html("");
	}

	dwr.engine.setErrorHandler(errh);
	
	function ejecutarScript(){
		if($("#script").val().trim()!=""){
			$("#divCargando").show();
			$("#Aceptar").attr("disabled",true);
			//alert($("#sistema").val() + "|" + $("#script").val())
			if($("#sistema").val()=="ADMIN"){
				UtilFachada.execute($("#script").val(),ejecutarScriptCallback );
			}else{
				eval("Util" + $("#sistema").val() + "Fachada.execute('" + escape($("#script").val()) + "',ejecutarScriptCallback );");
			}

		}else{
			alert("Escriba el script a ejecutar.");
			$("#script").focus();
		}
	}
	
	function ejecutarScriptCallback(str){
		$("#divCargando").hide();
		$("#Aceptar").removeAttr("disabled");
		if(str.length<5){//es un update/delete
			$("#exitoGrabado").html(str + " filas Afectadas.");
		}else{
			$("#exitoGrabado").html(str);
		}
	}
	
	function verEntidades(){
		if($("#sistema").val()=="ADMIN"){
			UtilFachada.getAllMappedClasses(verEntidadesCallback );
		}else{
			eval("Util" + $("#sistema").val() + "Fachada.getAllMappedClasses(verEntidadesCallback );");
		}
		/*if($("#sistema").val()=="ADMIN"){
			UtilFachada.getAllMappedClasses(verEntidadesCallback );
		}
		if($("#sistema").val()=="SIIF"){
			UtilSIIFFachada.getAllMappedClasses(verEntidadesCallback );
		}
		if($("#sistema").val()=="SIIM"){
			UtilSIIMFachada.getAllMappedClasses(verEntidadesCallback );
		}*/
	}

	function setearValor(valor){
		$('#script').val("");
		$('#script').val("select * from " + valor);
	}

	function verEntidadesCallback(entidades){
		$("#entidades").html("");

		for(var i=0;i<entidades.length;i++){
			$("#entidades").append("<a href='javascript:" + "setearValor(\"" + entidades[i] + "\")'>" + entidades[i] + "</a><br>");
		}
		//alert($('#titulo').offset().left + "/" + $('#titulo').offset().top)
		$("#entidades").dialog();//{position:[$('#titulo').offset().left,$('#titulo').offset().top]}
		$("#script").focus();
	}

</script>


<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>
<input type="hidden" id="sistema" value="${sistema}">

<div id="entidades"></div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td id="titulo" class="azulAjustado">${sistema}</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<textarea rows="20" cols="100" id="script">
			</textarea>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<input id="Aceptar" type="button" onclick="javascript:ejecutarScript();" name="Aceptar" value="Aceptar"/>
			<input id="VerEntidades" type="button" onclick="javascript:verEntidades(event);" name="verEntidades" value="Ver Entidades/Tablas"/>
			<td id="divCargando" style="display: none">
				<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
			</td> 
		</td>
	</tr>	
	<tr>
		<td height="10"></td>
	</tr>	
</table>

<script>
	verEntidades();
	$("#script").focus();
</script>