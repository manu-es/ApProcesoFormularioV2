<%@ page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Informe de errores</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body>
	<%
		String pagOrigenError = request.getParameter("pagOrigen");
	%>
	<P>
		Ha ocurrido un error en la p&aacute;gina:<%=pagOrigenError%></P>
	<P>Descripción de la excepción:</P>
	<P>&nbsp;</P>
	<%=exception.toString()%>
</body>
</html>