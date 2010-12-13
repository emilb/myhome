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
	<link href="${pageContext.request.contextPath}/css/uniform.default.css" rel="stylesheet" type="text/css" media="screen" />
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.uniform.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/commute.js"></script>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="${pageContext.request.contextPath}/">My home -jsp</a></h1>
			<p>just some stuff for me</p>
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
			<li class="current_page_item"><a href="#">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/commute">Commute</a></li>
			<li><a href="${pageContext.request.contextPath}/books">Books</a></li>
			<li><a href="${pageContext.request.contextPath}/movies">Movies</a></li>
			<li><a href="${pageContext.request.contextPath}/shoppinglist">Shopping list</a></li>
			<li><a href="${pageContext.request.contextPath}/links">Links</a></li>
			<li><a href="http://embr.cygni.se/forme" target="_blank">Blog</a></li>
		</ul>
	</div>
	
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					<div class="post">
						<h2 class="title"><a href="#">Welcome to Interchange </a></h2>
						<p class="meta">Posted by <a href="#">Someone</a> on March 10, 2008
							&nbsp;&bull;&nbsp; <a href="#" class="comments">Comments (64)</a> &nbsp;&bull;&nbsp; <a href="#" class="permalink">Full article</a></p>
						<div class="entry">
							<p><img src="images/img05.jpg" width="143" height="143" alt="" class="alignleft border" />This is <strong>Interchange </strong>, a free, fully standards-compliant CSS template designed by FreeCssTemplates<a href="http://www.nodethirtythree.com/"></a> for <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.  The picture in this template is from <a href="#">PDPhoto.org</a>. This free template is released under a <a href="http://creativecommons.org/licenses/by/2.5/">Creative Commons Attributions 2.5</a> license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)</p>
							<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.</p>
						</div>
					</div>
					
					<div class="post">
						<h2 class="title">Form test</h2>
						<div class="entry">
							<form> 
					            <div class='select-radio'> 
					              <label>Select Dropdown</label> 
					              <select> 
					                <option value='option1'>Option 1</option> 
					                <option value='option2'>Option 2</option> 
					                <option value='option3'>Option 3</option> 
					              </select> 
					              <input type="text"></input>
					              <label> 
					                <input name='rgroup' type='radio' value='radio1' /> 
					                Radio 1
					              </label> 
					              <label> 
					                <input checked='checked' name='rgroup' type='radio' value='radio2' /> 
					                Radio 2
					              </label> 
					              <label> 
					                <input disabled='disabled' name='rgroup' type='radio' value='radio3' /> 
					                Radio 3
					              </label> 
					            </div> 
					            <div class='file-checkbox'> 
					              <label>File Upload</label> 
					              <input class='file' type='file' /> 
					              <label> 
					                <input type='checkbox' value='check1' /> 
					                Checkbox 1
					              </label> 
					              <label> 
					                <input checked='checked' type='checkbox' value='check2' /> 
					                Checkbox 2
					              </label> 
					              <label> 
					                <input disabled='disabled' type='checkbox' value='check3' /> 
					                Checkbox 3
					              </label> 
					            </div> 
					          </form>
						</div>
					</div>
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
							<h2>Categories</h2>
							<ul>
								<li><a href="#">Aliquam libero</a></li>
								<li><a href="#">Consectetuer adipiscing elit</a></li>
								<li><a href="#">Metus aliquam pellentesque</a></li>
								<li><a href="#">Suspendisse iaculis mauris</a></li>
								<li><a href="#">Urnanet non molestie semper</a></li>
								<li><a href="#">Proin gravida orci porttitor</a></li>
							</ul>
						</li>
						<li>
							<h2>Blogroll</h2>
							<ul>
								<li><a href="#">Aliquam libero</a></li>
								<li><a href="#">Consectetuer adipiscing elit</a></li>
								<li><a href="#">Metus aliquam pellentesque</a></li>
								<li><a href="#">Suspendisse iaculis mauris</a></li>
								<li><a href="#">Urnanet non molestie semper</a></li>
								<li><a href="#">Proin gravida orci porttitor</a></li>
							</ul>
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

	<script type="text/javascript">
		$(document).ready(function() {
			$("select, input").uniform();
			
			loadCommutes();
		 });
		
	</script>	
</html>
