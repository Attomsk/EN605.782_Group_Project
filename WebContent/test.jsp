<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Models.Component" %>
<%@ page import="Models.Build" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
List<Component> components = (List<Component>) request.getSession().getAttribute("newComponents");
Build build = (Build) request.getSession().getAttribute("build");
%>
<h1><%=build.getStateString() %></h1>
<form action="Build" method="GET">
	<ul>
	<% for(int i=0; i < components.size(); i++)
		{%>
		<% Component current = components.get(i); %>
		<li>
			<button name="<%=build.getStateString()%>" onClick="this.form.onsubmit(); return false;" value="<%=current.getId()%>">Select</button>
			<%=current.getId()%> : <%=current.getName()%> : <%=current.getPrice()%>
		</li>
	<%} %>
	</ul>
</form>
<h1>Selected Components:</h1>
<%
Iterator<Component> itr = build.getComponents().iterator();
while (itr.hasNext()) {
	Component cmp = itr.next();%>
	<%=cmp.getBrand()%> : <%=cmp.getName()%> : <%=cmp.getPrice()%> <br/>
<%}%>
</body>
</html>