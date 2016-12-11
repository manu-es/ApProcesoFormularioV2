<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="procesos.*, beans.*, util.*"%>
<%@ page errorPage="WEB-INF/errores.jsp?pagOrigen=controlador.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Solicitud de cursos</title>
</head>
<body>
	<%
		String correo = request.getParameter("correo");		
		String clave = request.getParameter("clave");
		String[] cursos = request.getParameterValues("curso");
		session.setAttribute("clave", clave);
		//Se comprueba si se solicita el registro de un nuevo alumno o la rematriculación		
		if (request.getParameter("accion") != null) {
			if (request.getParameter("accion").equals("registro")) {
			%><jsp:forward page="WEB-INF/registro.jsp"/> <%
			}
			if (request.getParameter("accion").equals("rematricular")) {
			%><jsp:forward page="WEB-INF/rematricular.jsp"/> <%
			}
		}
		session.setAttribute("correo", correo);
		session.setAttribute("cursos", cursos);
		// Se comprueba si el usuario ha entrado directamente por adivinar.jsp, si es así se le envía a index.jsp
		if (correo == null) {
			session.invalidate();
			%> <jsp:forward page="index.jsp" />	<%
		} else {
			Matricular matricular = (Matricular) session.getAttribute("sesionMatricular");
			//Comprueba si el correo tiene un formato válido
			if (Validar.correo(correo)) {
				//Compueba si existe el correo en logins.dat
				if (!matricular.ifExistAlumnoRegistrado(correo)) {
					%><jsp:forward page="index.jsp">
						<jsp:param value="true" name="OpcionRegistrar" />
					</jsp:forward>
					<%
				} else {
					//Comprueba si la clave tiene el formato correcto
					if (!Validar.clave(clave)) {
						%><jsp:forward page="index.jsp">
						<jsp:param value="formatoAcceso" name="ErrorClave" />
						</jsp:forward>
						<%
					}
					//Comprueba si se ha seleccionado almenos un curso
					if (!Validar.cursos(cursos)) {
						%><jsp:forward page="index.jsp">
						<jsp:param value="false" name="ErrorCursos" />
						</jsp:forward>
						<%
						}
					//Comprueba que el correo y el login son correctos para ese alumno
					if (matricular.compruebaClave(correo, clave)) {
						%><jsp:forward page="WEB-INF/matricular.jsp"/> <%
						} else {
							%><jsp:forward page="index.jsp">
							<jsp:param value="true" name="ErrorClave" />
							</jsp:forward>
							<%
							}
				}
			} else {
				%><jsp:forward page="index.jsp">
				<jsp:param value="formato" name="ErrorCorreo" />
				</jsp:forward>
				<%
				}
		}
	%>
</body>
</html>