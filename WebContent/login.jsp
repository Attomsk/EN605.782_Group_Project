<%@ include file="master/header.jsp" %>
Build A PC - Log In
<%@ include file="master/navbar.jsp" %> 
			<div id="mainWrap">
			<div class="borderDiv">
				<form action="login">
				<table class="formTable">
					<tr>
						<td>User Name</td>
						<td><input type="text" name="username"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password"/></td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit">Log In</button></td>
					</tr>
				</table>
				</form>
			</div>
			</div>
<%@ include file="master/footer.jsp" %> 