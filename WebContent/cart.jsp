<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Models.Component" %>
<%@ page import="Models.Build" %>
<%@ page import="Models.Customer" %>
<%@ include file="master/header.jsp" %>
Build A PC - Cart
<%@ include file="master/navbar.jsp" %>
<% Build build = (Build) request.getSession().getAttribute("build");
if(null == build){ build = new Build();}
%>
	<div id="buildWrapper" style="width:100%; height:100%; vertical-align: middle;">
		<div class="clearDiv">
			<div style="float:left">
				<div style="float:left;padding-right:3px"><img src="images/Cart.jpg"/></div>
				<div style="float:left"><h1>Shopping Cart</h1></div>
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
				<% for(int i=0; i < build.getComponents().size(); i++)
				{
					Component current = build.getComponents().get(i); %>
				<tr>
					<td>
						<a href="Modify?category=<%=current.getCategory()%>" class="squareButton" onClick="this.form.onsubmit(); return false;">Modify</a>
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
		<div class="clearDiv">
			<div style="float:left">
				<h3>Build URL:</h3>
			</div>
			<div style="float:left;margin-top:-5px">
				<input type="text" name="buildurl" value="<%= build.getBuildUrl(request) %>"/>
			</div>
			<div style="float:right">
				<h3><a href="checkout.jsp">
				<%if(null == customer)
				{%>
					Checkout as guest
				<%}
				else
				{%>
					Checkout as <%=customer.getFirstName()%>
				<%}%>
				</a></h3>
			</div>
		</div>
	</div>
<%@ include file="master/footer.jsp" %>