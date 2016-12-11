<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="procesos.*, beans.*"%>
<%@ page errorPage="errores.jsp?pagOrigen=rematricular.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Rematricularse</title>
</head>
<body>
	<jsp:useBean id="alumno" scope="session" class="beans.Alumno" />
	<%
		Matricular matricular = (Matricular) session.getAttribute("sesionMatricular");
		String correo = (String) session.getAttribute("correo");
		String[] cursos = (String[]) session.getAttribute("cursos");
		matricular.guardaAlumnoMatriculado(alumno, cursos);
		alumno = matricular.getAlumnoMatriculado(correo);
	%>
	<h3>Alumno: <jsp:getProperty name="alumno" property="correo" /></h3>
	<h3>Cursos solicitados: <jsp:getProperty name="alumno" property="stringCursos" /></h3>
	<form method="post" action="index.jsp">
		<input type="hidden" name="salir" value="true" /> <input name="salir"
			type="submit" value="Salir">
	</form>
</body>
</html>