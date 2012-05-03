<%@ include file="master/header.jsp" %>
Build A PC - Contact Us
<%@ include file="master/navbar.jsp" %> 

<script type="text/javascript">
//Validate Login Form
function validateEmailForm(emailForm){
	var form = document.forms["emailForm"];
	var fullName = form["fullName"].value;
	var email = form["email"].value;
	var alertmsg = "";
	if (fullName==null || fullName=="")
	{
		alertmsg += "Name\n";
	}
	if (email==null || email=="")
	{
		alertmsg += "Email\n";
	}
	if("" != alertmsg)
	{
		alert("Please fill in the following field(s):\n" + alertmsg);
		return false;
	}
	document.forms['emailForm'].submit();
}
</script>
			<div class="clearDiv"> </div>
			<div id="contactWrap" style="width:100%; height:100%; vertical-align: middle;">
			<div class="borderDiv">
				<div id=mainContent>
				<h1>Contact Us</h1>	
					<p>Please do not hesitate to contact us. Our very qualified Customer Care team
					 is ready and willing to help you. You may contact us by phone or e-mail . 
					</p>
					<h2>By Phone</h2>
					<p>If you need immediate assistance please call<span style="font-weight:bold; font-size:14px; color:#0033CC"> 
					1-888-BUILD-PC (1-888-284-5372)</span> 
					24 hours a day 7 days a week.
					</p>
					<h2>E-Mail</h2>
					<form name="emailForm" action=# method="post" onsubmit="return validateEmailForm(this)">
						<fieldset>
							<table width="300" cellpadding="2" cellspacing="2">
							<tr>
								<td>Name: </td>
								<td><input type="text" name="fullName" size="25"></td>
							</tr>
							<tr>
								<td>Subject: </td>
								<td><input type="text" name="subject" size="25"></td>
							</tr>
							<tr>
								<td>Email: </td>
								<td><input type="text" name="email" size="25"></td>
							</tr>
							<tr>
								<td>Message: </td>
								<td><textarea rows="5" cols="40" name="subject"></textarea></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" name="submit" value="Send Message"></td>
							</tr>
							</table>
						</fieldset>
					
					</form>
				</div>
			</div>
			</div>
<%@ include file="master/footer.jsp" %> 