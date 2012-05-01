<%@ include file="master/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Models.Component" %>

<%@ page import="Models.Build" %>
Build A PC - Build
<%@ include file="master/navbar.jsp" %>
<% 
List<Component> components = (List<Component>) request.getSession().getAttribute("newComponents");
Build build = (Build) request.getSession().getAttribute("build");
%>
<script type="text/javascript">
//Show the build list Div
function showBuildList(){
	var ele = document.getElementById('popup');
	ele.style.display = "block";
}
//Hide the build list Div
function hideBuildList(){
	var ele = document.getElementById('popup');
	ele.style.display = "none";
}
</script>
	<div id="buildWrapper" style="width:100%; height:100%; vertical-align: middle;">
		<div class="clearDiv">
			<div style="float:left"><h1>Select <%=build.getStateString() %></h1></div>
			<div style="float:right; text-align:right">
				<h3>Current Build Total: $<%=build.getTotalBuildCost() %></h3><br/>
				<h3><a href="#" onClick="showBuildList(); return false;">View Selected Components</a></h3>
			</div>
			<div style="clear:both"></div>
		</div>
		<div class="borderDiv" style="height:300px; overflow-y:auto">
			<form action="Build" method="GET">
				<table class="buildTable">
				<tr>
					<th> </th>
					<th>Brand</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				<% for(int i=0; i < components.size(); i++)
				{
					Component current = components.get(i); %>
				<tr>
					<td>
						<button name="<%=build.getStateString()%>" onClick="this.form.onsubmit(); return false;" value="<%=current.getId()%>">Select</button>
					</td>
					<td><%=current.getBrand() %>
					<td><%=current.getName()%></td>
					<td>$<%=current.getPrice()%></td>
				</tr>
				<%} %>
				</table>
			</form>
		</div>
		<!-- Selected items popup window -->
		<div class="holder">     
			<div id="popup" class="popup" style="display: none;" >            
				<div class="content">
					<div style="float:right; margin-top:-20px; margin-right:-15px">
						<a href="#" onClick="hideBuildList(); return false;">x</a>
					</div>
					<h3>Selected Components</h3>
					<table class="selectedTable">
					<%
					for(int i=0; i < build.getComponents().size(); i++)
					{
						Component current =  build.getComponents().get(i);%>
					<tr>
						<td><%=current.getCategory() %></td>
						<td><%=current.getBrand() %></td>
						<td><%=current.getName()%></td>
						<td>$<%=current.getPrice()%></td>
					</tr>
					<%}%>
					</table>
				</div>
			</div>
		</div>
	</div>
<%@ include file="master/footer.jsp" %> 