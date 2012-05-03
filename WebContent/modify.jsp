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
			<div style="float:left">
				<div style="float:left;padding-right:3px"><img src="images/<%=components.get(0).getCategory().trim()%>.jpg"/></div>
				<div style="float:left"><h1>Modify <%=components.get(0).getCategory()%></h1></div>
			</div>
			<div style="float:right; text-align:right">
				<h1>Total: $<%=build.getTotalBuildCost() %></h1>
				<h3><a href="#" onClick="showBuildList(); return false;">View Selected Components</a></h3>
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
					Build URL:
					<input type="text" name="buildurl" value="<%= build.getBuildUrl(request) %>"/>
				</div>
			</div>
		</div>
	</div>
<%@ include file="master/footer.jsp" %>