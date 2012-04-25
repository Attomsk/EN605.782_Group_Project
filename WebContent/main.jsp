<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

window.onresize = setContentHeight;

// Sets the height of the mainContent DIV
function setContentHeight(){
	// Combined Height of all the other GUI elements
	var offset = 203;
	// Height that the footer should be
	var footerHeight = 80;
	// Minimum Size of the content DIV
	var minContentSize = 200;
	// Get the current window height
	var userHeight = window.innerHeight;
	// Get the mainContent DIV
	var elem = document.getElementById('mainContent');
	// Calculate new height
	var newHeight  = (userHeight - (offset + footerHeight));
	// Clamp height t minContentSize
	if(newHeight < minContentSize){
		newHeight = minContentSize;
	}
	elem.style.height = newHeight + "px";
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL="StyleSheet" href="styles.css" type="text/css" media="screen">
<title>Build A PC</title>
</head>
<body onload="setContentHeight()">
	<div id="wrapper">
		<div id="siteHeader">
			<img src="images/bapc_logo.jpg"/>
		</div>
		<div id="navBar">
			<div class="navItem"><a href="#">Build</a></div>
			<div class="navItem"><a href="#">Products</a></div>
			<div class="navItem"><a href="#">About</a></div>
			<div class="navItem"><a href="#">Contact</a></div>
		</div>
		<div id="mainContent">
		</div>
		<div id="baseBar"></div>
	</div>
	<div id="footer"></div>
</body>
</html>