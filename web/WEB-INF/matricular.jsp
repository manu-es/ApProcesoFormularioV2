<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="procesos.*, beans.*"%>
<%@ page errorPage="errores.jsp?pagOrigen=matricular.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Matricular</title>
</head>
<body>
	<%
	String correo = (String) session.getAttribute("correo");
	String[] cursos = (String[]) session.getAttribute("cursos");
	Matricular matricular = (Matricular) session.getAttribute("sesionMatricular");
	%>
	<jsp:useBean id="alumno" scope="session" class="beans.Alumno" />
	<jsp:setProperty name="alumno" property="correo" value='<%= correo%>'/>
	<%
	//Comprueba si el alumno ya estaba matriculado anteriormente en otros cursos
	if(!matricular.ifExistAlumnoMatriculado(correo)){
		matricular.guardaAlumnoMatriculado(alumno, cursos);
	%>
	<h3>Alumno: <jsp:getProperty name="alumno" property="correo" /></h3>
	<h3>Cursos solicitados: <jsp:getProperty name="alumno" property="stringCursos" /></h3>
	<form method="post" action="index.jsp">
		<input type="hidden" name="salir" value="true" /> <input name="salir" type="submit" value="Salir">
	</form>
	<%
	} else {
		%><h3>Ya está matriculado en otros cursos. que desea hacer:</h3>
		<form method="post" action="controlador.jsp">
			<input type="hidden" name="accion" value="rematricular" />
			<input name="rematricular" type="submit" value="Eliminar los anteriores y matricularse de los nuevos">
		</form>
		<form method="post" action="index.jsp">
			<input type="hidden" name="salir" value="true" /> <input name="salir" type="submit" value="Salir">
		</form>
		<%
			}
		%>
</body>
</html>