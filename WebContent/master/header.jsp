<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
window.onresize = setContentHeight;

// Sets the height of the mainContent DIV
function setContentHeight(){
	// Combined Height of all the other GUI elements
	var offset = 203;
	// Height that the footer should be
	var footerHeight = 80;
	// Minimum Size of the content DIV
	var minContentSize = 400;
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
	
	//resize the main wrapper for centering the main content
	var wrap = document.getElementById('mainWrap');
	if(wrap){
		var wrapPadding = (newHeight-370)/2;
		wrap.style.paddingTop = wrapPadding + "px";
	}
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL="StyleSheet" href="styles.css" type="text/css" media="screen">
<title>