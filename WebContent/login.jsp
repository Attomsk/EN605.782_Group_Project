<%@ include file="master/header.jsp" %>
Build A PC - Log In
<%@ include file="master/navbar.jsp" %> 

<script type="text/javascript">
//Validate Login Form
function validateLoginForm(loginForm){
	var form = document.forms["loginForm"];
	var email = form["email"].value;
	var password = form["password"].value;
	var alertmsg = "";
	if (email==null || email=="")
	{
		alertmsg += "Email\n";
	}
	if (password==null || password=="")
	{
		alertmsg += "Password\n";
	}
	if("" != alertmsg)
	{
		alert("The following fields are required:\n" + alertmsg);
		return false;
	}
	document.forms['loginForm'].submit();
}

//Validate Registration Form
function validateRegistrationForm(){
	var form = document.forms["regForm"];
	var email = form["email"].value;
	var password = form["password"].value;
	var firstName = form["firstName"].value;
	var alertmsg = "";
	if (email==null || email=="")
	{
		alertmsg += "Email\n";
	}
	if (password==null || password=="")
	{
		alertmsg += "Password\n";
	}
	if (firstName==null || firstName=="")
	{
		alertmsg += "First Name\n";
	}
	if("" != alertmsg)
	{
		alert("The following fields are required:\n" + alertmsg);
		return false;
	}
	document.forms['regForm'].submit();
}
</script>
<% String regMessage = (String) request.getAttribute("regMessage"); %>
			<div id="mainWrap">
			<div class="borderDiv">
				<center><%if(null != regMessage){%><h2><%=regMessage%></h2><%}%></center>
				<table style="margin-left:auto;margin-right:auto;width:100%">
				<tr>
					<th><h1>Log In</h1></th>
					<th><h1>Register</h1></th>
				</tr>
				<tr><td>
				<form name="loginForm" action="Login" onsubmit="return validateLoginForm(this)" method="POST">
					<table class="formTable">
						<tr>
							<td>Email</td>
							<td><input type="text" name="email"/></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password"/></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="#" class="squareButton" onClick="validateLoginForm(); return false;">Log In</a></td>
						</tr>
					</table>
				</form>
				</td>
				<td>
				<form name="regForm" action="Login" method="POST">
					<input type="hidden" name="register" value="true"/>
					<table class="formTable" style="border-left:3px solid #3f3f3f">
						<tr>
							<td>Email</td>
							<td><input type="text" name="email"/></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password"/></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" name="firstName"/><td>
							<td>Last Name</td>
							<td><input type="text" name="lastName"/><td>
						</tr>
						<tr>
							<td>Address 1</td>
							<td><input type="text" name="address1"/><td>
							<td>Address 2</td>
							<td><input type="text" name="address2"/><td>
						</tr>
						<tr>
							<td>City</td>
							<td><input type="text" name="city"/><td>
							<td>State</td>
							<td><input type="text" name="state"/><td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><input type="text" name="zip"/><td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a href="#" class="squareButton" onClick="validateRegistrationForm(); return false;">Register</a></td>
						</tr>
					</table>
				</form>
				</td></tr>
				</table>
			</div>
			</div>
<%@ include file="master/footer.jsp" %> 