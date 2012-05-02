<%@ include file="master/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Models.Component" %>

<%@ page import="Models.Build" %>
Build A PC - Modify
<%@ include file="master/navbar.jsp" %>
<%
List<Component> components = (List<Component>) request.getSession().getAttribute("modifyComponents");
Build build = (Build) request.getSession().getAttribute("build");
%>
	<div id="buildWrapper" style="width:100%; height:100%; vertical-align: middle;">
		<div class="clearDiv">
			<div style="float:left">
				<h1>Modify Build Selection</h1>
			</div>
			<div style="float:right; text-align:right">
				<h1>Total: $<%=build.getTotalBuildCost() %></h1>
			</div>
			<div style="clear:both"></div>
		</div>
		<div class="borderDiv" style="height:300px; overflow-y:auto">
			<form action="Build" method="GET">
				<table class="buildTable">
				<tr>
					<th> </th>
					<th>Category</th>
					<th>Brand</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				<% for(int i=0; i < components.size(); i++)
				{
					Component current = components.get(i); %>
				<tr>
					<td>
						<a href="Modify?category=<%=current.getCategory()%>&newItem=<%=current.getId()%>" class="squareButton" onClick="this.form.onsubmit(); return false;">Select</a>
					</td>
					<td><%=current.getCategory() %></td>
					<td><%=current.getBrand() %></td>
					<td><%=current.getName()%></td>
					<td>$<%=current.getPrice()%></td>
				</tr>
				<%} %>
				</table>
			</form>
		</div>
	</div>
<%@ include file="master/footer.jsp" %>