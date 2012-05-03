</title>
</head>
<%@ page import="Models.Customer" %>
<% Customer customer = (Customer) request.getSession().getAttribute("customer");%>
<body onload="setContentHeight()">
	<div id="wrapper">
		<div id="siteHeader">
			<a style="float:left;margin:0px; padding:0px; border-style: none" href="index.jsp"><img src="images/bapc_logo.jpg"/></a>
			<div class="customerWelcome">
				<%if(null != customer)
				{%>
					Welcome back, <%=customer.getFirstName()%>! <br/>
				<%}%>
			</div>
			<div class="headerText">
				<%if(null != customer)
				{%>
				<a href="Login?logout=true">Log out</a>
				<%} 
				else
				{%>
					<a href="login.jsp">Log in / Register</a>
				<%} %>
			</div>
		</div>
		<div id="navBar">
			<div class="navItem" style="float:left"><a href="Build">Build</a></div>
			<div class="navItem" style="float:left"><a href="products.jsp">Products</a></div>
			<div class="navItem" style="float:left"><a href="about.jsp">About</a></div>
			<div class="navItem" style="float:left"><a href="contactUs.jsp">Contact</a></div>
			<div class="navItem" style="float:right"><a href="cart.jsp">Shopping Cart</a></div>
		</div>
		<div id="mainContent">