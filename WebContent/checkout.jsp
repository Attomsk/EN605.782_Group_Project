<%@ page import="Models.Build" %>
<%@ include file="master/header.jsp" %>
Build A PC - Checkout
<%@ include file="master/navbar.jsp" %> 

<script type="text/javascript">
//Validate Registration Form
function validateCheckoutForm(){
	var form = document.forms["checkoutForm"];
	
	var email = form["email"].value;
	var firstName = form["firstName"].value;
	var lastName = form["lastName"].value;
	var address = form["address1"].value;
	var city = form["city"].value;
	var state = form["state"].value;
	var zip = form["zip"].value;
	var ccNum = form["ccNum"].value;
	var ccExp = form["ccExp"].value;
	var ccCvc = form["ccCvc"].value;
	
	var alertmsg = "";
	if (email==null || email=="")
	{
		alertmsg += "Email\n";
	}
	if (firstName==null || firstName=="")
	{
		alertmsg += "First Name\n";
	}
	if (lastName==null || lastName=="")
	{
		alertmsg += "Last Name\n";
	}
	if (address==null || address=="")
	{
		alertmsg += "Address\n";
	}
	if (city==null || city=="")
	{
		alertmsg += "City\n";
	}
	if (state==null || state=="")
	{
		alertmsg += "State\n";
	}
	if (zip==null || zip=="")
	{
		alertmsg += "Zipcode\n";
	}
	if (ccNum==null || ccNum=="")
	{
		alertmsg += "Credit Card Number\n";
	}
	if (ccExp==null || ccExp=="")
	{
		alertmsg += "Expiration Date\n";
	}
	if (ccCvc==null || ccCvc=="")
	{
		alertmsg += "CVC\n";
	}
	
	if("" != alertmsg)
	{
		alert("The following fields are required:\n" + alertmsg);
		return false;
	}
	form.submit();
}
</script>
<% Build build = (Build) request.getSession().getAttribute("build");
if(null == build){ build = new Build();}
if(null == customer){ customer = new Customer(); }
%>
		<div id="checkoutWrapper">
			<div class="clearDiv">
				<div style="float:left">
					<h1>Checkout</h1>
				</div>
				<div style="float:right; text-align:right">
					<h1>Total: $<%=build.getTotalBuildCost() %></h1>
				</div>
				<div style="clear:both"></div>
			</div>
			<div class="borderDiv">
				<form name="checkoutForm" action="Checkout" method="POST">
					<table style="margin-left:auto;margin-right:auto;width:100%">
					<tr>
						<th><h1>User Info</h1></th>
						<th><h1>Credit Card</h1></th>
					</tr>
					<tr><td>
					<table class="formTable">
						<tr>
							<td>Email</td>
							<td><input type="text" name="email"/ value="<%=customer.getEmail()%>"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" name="firstName" value="<%=customer.getFirstName()%>"/><td>
							<td>Last Name</td>
							<td><input type="text" name="lastName" value="<%=customer.getLastName()%>"/><td>
						</tr>
						<tr>
							<td>Address 1</td>
							<td><input type="text" name="address1" value="<%=customer.getAddress1()%>"/><td>
							<td>Address 2</td>
							<td><input type="text" name="address2" value="<%=customer.getAddress2()%>"/><td>
						</tr>
						<tr>
							<td>City</td>
							<td><input type="text" name="city" value="<%=customer.getCity()%>"/><td>
							<td>State</td>
							<td><input type="text" name="state" value="<%=customer.getState()%>"/><td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><input type="text" name="zip" value="<%=customer.getZipcode()%>"/><td>
							<td></td>
							<td></td>
						</tr>
					</table>
					</td>
					<td style="border-left:3px solid #e5e5e5">
					<table class="formTable">
						<tr>
							<td>Credit Card Number </td>
							<td>Type</td>
						</tr>
						<tr>
							<td><input type="text" name="ccNum"/></td>
							<td>
								<select name="ccType">
									<option>Visa</option>
									<option>Master Card</option>
									<option>Discover</option>
									<option>American Express</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Expiration Date </td>
							<td>CVC</td>
						</tr>
						<tr>
							<td><input type="text" name="ccExp"/></td>
							<td><input type="text" name="ccCvc"/></td>
						</tr>
					</table>
					</td></tr>
					<tr>
						<td></td>
						<td style="text-align:right"><a href="#" class="squareButton" onClick="validateCheckoutForm(); return false;">Complete purchase</a></td>
					</tr>
					</table>
				</form>
			</div>
		</div>
<%@ include file="master/footer.jsp" %> 