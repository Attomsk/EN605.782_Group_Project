<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Models.Component" %>
<%@ page import="Models.Build" %>
<%@ page import="Models.Customer" %>
<%@ page import="Models.CreditCard" %>
<%@ include file="master/header.jsp" %>
Build A PC - Order Confirmation
<%@ include file="master/navbar.jsp" %>
<% Build build = (Build) request.getSession().getAttribute("build");
if(null == build){ build = new Build();}
Customer orderCust = (Customer) request.getAttribute("checkout_customer");
if(null == orderCust){ orderCust = new Customer();}
CreditCard creditCard = (CreditCard) request.getAttribute("credit_card");
if(null == creditCard){ creditCard = new CreditCard();}
%>
	<div id="buildWrapper" style="width:100%; height:100%; vertical-align: middle;">
		<div class="clearDiv">
			<div style="float:left">
				<h1>Thank you for your purchase!</h1>
			</div>
			<div style="float:right; text-align:right">
				<h1>Total: $<%=build.getTotalBuildCost() %></h1>
			</div>
			<div style="clear:both"></div>
		</div>
		<div class="borderDiv" style="height:300px; overflow-y:auto">
				<table class="buildTable">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				<tr>
					<td><%=orderCust.getFirstName()%></td>
					<td><%=orderCust.getLastName()%></td>
					<td><%=orderCust.getEmail()%></td>
				</tr>
				</table>
				<hr/>
				<table class="buildTable">
				<tr>
					<th>Credit Card</th>
					<th>Expiration Date</th>
				</tr>
				<tr>
					<td><%=creditCard.getType()%></td>
					<td><%=creditCard.getExpiration()%></td>
				</tr>
				</table>
				<hr/>
				<table class="buildTable">
				<tr>
					<th>Category</th>
					<th>Brand</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				<% for(int i=0; i < build.getComponents().size(); i++)
				{
					Component current = build.getComponents().get(i); %>
				<tr>
					<td><%=current.getCategory() %></td>
					<td><%=current.getBrand() %></td>
					<td><%=current.getName()%></td>
					<td>$<%=current.getPrice()%></td>
				</tr>
				<%} %>
				</table>
		</div>
	</div>
<%@ include file="master/footer.jsp" %>