<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="procesos.*, util.*"%>
<%@ page errorPage="errores.jsp?pagOrigen=registro.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Registro de Alumno</title>
</head>
<body>
	<%
		String correo = (String) session.getAttribute("correo");
		String clave = (String) session.getAttribute("clave");
		Matricular matricular = (Matricular) session.getAttribute("sesionMatricular");
		//Comprueba si la clave tiene el formato correcto
		if (!Validar.clave(clave)) {
		%><jsp:forward page="../index.jsp">
			<jsp:param value="formatoRegistro" name="ErrorClave" />
			<jsp:param value="true" name="OpcionRegistrar" />
		</jsp:forward>
		<%
		}
		Registrar registrar = new Registrar(matricular.getListaAlumnosRegistrados(), request);
		registrar.RegistraAlumno(correo, clave);
	%><h3>Se ha registrado correctamente.</h3>
	<h3>
		Correo:
		<%=correo%></h3>
	<h3>
		Ahora debe <a href="index.jsp">solicitar</a> los cursos
	</h3>
</body>
</html>