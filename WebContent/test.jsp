<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Component" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List<Component> memory = (List<Component>) request.getSession().getAttribute("memory");
%>
<ul>
<% for(int i=0; i < memory.size(); i++)
	{%>
	<% Component current = memory.get(i); %>
	<li><%=current.getId()%> : <%=current.getName()%> : <%=current.getPrice()%> </li>
<%} %>
</ul>
SUCCESS!
</body>
</html>