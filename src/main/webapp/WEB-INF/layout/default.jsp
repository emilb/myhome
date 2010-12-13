<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<stripes:layout-definition>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Interchange  
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20100704

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>My home</title>
	
	<link href="${pageContext.request.contextPath}/css/interchange.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${pageContext.request.contextPath}/css/myhome.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${pageContext.request.contextPath}/css/jquery-ui-1.8.6.custom.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${pageContext.request.contextPath}/css/uniform.default.css" rel="stylesheet" type="text/css" media="screen" />
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.6.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.uniform.min.js"></script>
	<!--  
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/stripes.jquery.validation.js"></script>
	-->
	
	<stripes:layout-component name="html_head"/>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="${pageContext.request.contextPath}/">My home</a></h1>
			<p>just some stuff for me!</p>
		</div>
		<!--  
		<div id="search">
			<form method="get" action="">
				<fieldset>
					<input type="text" name="s" id="search-text" size="15" value="enter keywords here..." />
					<input type="submit" id="search-submit" value="GO" />
				</fieldset>
			</form>
		</div>
		-->
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
			<tag:menuitem siteArea="HOME" name="Home" pageRef=""/>
			<tag:menuitem siteArea="COMMUTE" name="Commute" pageRef="commute.html"/>
			<tag:menuitem siteArea="BOOKS" name="Books" pageRef="books.html"/>
			<tag:menuitem siteArea="MOVIES" name="Movies" pageRef="movies.html"/>
			<tag:menuitem siteArea="SHOPPINGLIST" name="Shopping list" pageRef="shoppinglist.html"/>
			<tag:menuitem siteArea="LINKS" name="Links" pageRef="links.html"/>
			<li><a href="http://embr.cygni.se/forme" target="_blank">Blog</a></li>
			<!--  
			<li class="current_page_item"><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/commute.html">Commute</a></li>
			<li><a href="${pageContext.request.contextPath}/books.html">Books</a></li>
			<li><a href="${pageContext.request.contextPath}/movies.html">Movies</a></li>
			<li><a href="${pageContext.request.contextPath}/shoppinglist.html">Shopping list</a></li>
			<li><a href="${pageContext.request.contextPath}/links.html">Links</a></li>
			-->
			
		</ul>
	</div>
	
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					<stripes:layout-component name="contents"/>
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<div id="sidebar">
					<ul>
						<li>
							<h2>About</h2>
							<p>
								This is a small webapp helping me with stuff like
								telling me when a new book of my favorite author is 
								available to buy, when the next subway/bus is leaving 
								or when a new movie that might interest me is available.
							</p>
							<p>
								If you've come here by some mishap, feel free to look
								around but I doubt you will find anything interesting.
							</p>
						</li>
						<li>
							<h2>Technologies</h2>
							<ul>
								<li><a href="#">MongoDB</a></li>
								<li><a href="#">Java</a></li>
								<li><a href="#">Spring</a></li>
								<li><a href="#">Jersey</a></li>
								<li><a href="#">Stripes</a></li>
								<li><a href="#">JQuery</a></li>
								<li><a href="#">Eclipse</a></li>
								<li><a href="#">Maven</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	<p>Copyright (c) 2010 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/"> CSS Templates</a>.</p>
</div>
<!-- end #footer -->
</body>
	
	<stripes:layout-component name="script"/>
	
</html>

</stripes:layout-definition>