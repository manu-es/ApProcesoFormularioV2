<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="procesos.*"%>
<%@ page errorPage="WEB-INF/errores.jsp?pagOrigen=index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de cursos</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />
<%
	if (request.getParameter("ErrorClave") != null) {
		//Comprueba si la clave no coincide
		if (request.getParameter("ErrorClave").equals("true")) {
%>
<style type="text/css">
.clave {
	display: inline;
}
</style>
<%
	}
		//Comprueba si la clave no tiene el formato correcto
		if (request.getParameter("ErrorClave").equals("formatoAcceso")) {
%>
<style type="text/css">
.clave2 {
	display: inline;
}
</style>
<%
	}
	//Comprueba si la clave no tiene el formato correcto
		if (request.getParameter("ErrorClave").equals("formatoRegistro")) {
%>
<style type="text/css">
.clave3 {
	display: inline;
}
</style>
<%
	}
	}
	if (request.getParameter("ErrorCorreo") != null) {
		//Comprueba si elcorreo tiene un formato válido
		if (request.getParameter("ErrorCorreo").equals("formato")) {
%>
<style type="text/css">
.correo {
	display: inline;
}
</style>
<%
	}
	}
	if (request.getParameter("ErrorCursos") != null) {
		//Comprueba si no ha sido seleccionado ningún curso
		if (request.getParameter("ErrorCursos").equals("false")) {
%>
<style type="text/css">
.cursos {
	display: inline;
}
</style>
<%
	}
	}
	//Comprueba si se ha pulsado la opción de registrar un nuevo alumno
	if (request.getParameter("OpcionRegistrar") != null) {
%>
<style type="text/css">
.registro {
	display: inline;
}
</style>
<%
	}
%>
</head>
<%
	String correo = "";
	String clave = "";
	Matricular matricular = null;
	if (session.isNew()) {
		matricular = new Matricular(request);
		session.setAttribute("sesionMatricular", matricular);
	} else {
		// Se comprueba si el usuario ha hecho clic en salir, si es así se le envía a index.jsp
		if (request.getParameter("salir") != null) {
			session.invalidate();
			%>
			<jsp:forward page="index.jsp" />
			<%
		} else {
			matricular = (Matricular) session.getAttribute("sesionMatricular");
			//Se obtiene el correo intruducido anteriormente
			if (session.getAttribute("correo") != null) {
				correo = (String) session.getAttribute("correo");
			}
			if (session.getAttribute("clave") != null) {
				clave = (String) session.getAttribute("clave");
			}
		}
	}
%>
<body>
	<fieldset>
		<p>Ingrese los datos para solicitar un curso:</p>
		<form method="post" action="controlador.jsp">
			<input type="hidden" name="accion" value="matricular" />
			<label for="correo">Correo: </label><input id="correo" name="correo"
				value="<%=correo%>"><span class="correo">Formato
				erroneo</span><br /> <label>Seleccione el curso: </label><select multiple
				name="curso">
				<option value="jardineria">Jardineria</option>
				<option value="ofimatica">ofimática</option>
				<option value="pintura">Pintura</option>
				<option value="electricidad">Electricidad</option>
			</select><span class="cursos">Debe seleccionar al menos uno</span><br /> <label
				for="clave">Clave: </label><input id="clave" name="clave"><span
				class="clave">Clave erronea</span><span class="clave2">Formato
				erroneo</span><br /> <input name="solicitar" type="submit"
				value="Solicitar">
		</form>
	</fieldset>
	<!-- Formulario de registro -->
	<div class="registro">
		<fieldset>
			<h3>Correo no existente, ¿desea registrarse?:</h3>
			<form method="post" action="controlador.jsp">
				<input type="hidden" name="accion" value="registro" />
				<label for="correo">Correo: </label><input id="correo" name="correo"
					value="<%=correo%>" readonly><br /> <label for="clave">Clave:
				</label><input id="clave" name="clave" ><span class="clave3">Formato
				erroneo</span><br /> <input
					name="solicitar" type="submit" value="Registrarse">
			</form>
		</fieldset>
	</div>
	</form>
	<!-- Formulario para salir de la aplicación -->
	<form method="post" action="index.jsp">
		<input type="hidden" name="salir" value="true" /> <input name="salir"
			type="submit" value="Salir">
	</form>
</body>
</html>