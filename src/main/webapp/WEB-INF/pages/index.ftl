[#ftl]
[#assign s=JspTaglibs["http://stripes.sourceforge.net/stripes.tld"]]

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
	
	<link href="${actionBean.contextPath}/css/interchange.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${actionBean.contextPath}/css/myhome.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${actionBean.contextPath}/css/jquery-ui-1.8.6.custom.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${actionBean.contextPath}/css/uniform.default.css" rel="stylesheet" type="text/css" media="screen" />
		
	<script type="text/javascript" src="${actionBean.contextPath}/js/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="${actionBean.contextPath}/js/jquery-ui-1.8.6.custom.min.js"></script>
	<script type="text/javascript" src="${actionBean.contextPath}/js/jquery.uniform.min.js"></script>
	
	<script type="text/javascript" src="${actionBean.contextPath}/js/commute.js"></script>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="${actionBean.contextPath}/">My home -  now by Freemarker</a></h1>
			<p>just some stuff for me! - now in jetty 7</p>
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
			<li><a href="${actionBean.contextPath}/commute.html">Commute</a></li>
			<li><a href="${actionBean.contextPath}/books.html">Books</a></li>
			<li><a href="${actionBean.contextPath}/movies.html">Movies</a></li>
			<li><a href="${actionBean.contextPath}/shoppinglist.html">Shopping list</a></li>
			<li><a href="${actionBean.contextPath}/links.html">Links</a></li>
			<li><a href="http://embr.cygni.se/forme" target="_blank">Blog</a></li>
		</ul>
	</div>
	
	<!-- end #menu -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					<div class="post">
						<h2 class="title">jquery stuff</h2>
						<p class="meta">meta info</p>
						<div class="entry">
						
							[@s.form action="/index.html"]
							<p>
        						[@s.errors/]
        						Search station: [@s.text name="stationSearch" id="stationSearch"/] 
        						
        						<br>
        						
        						Station identifier: [@s.text name="stationIdentifier" id="stationIdentifier"/]
        						
							</p>
							<p>[@s.submit name="suggestStation" value="Search"
								onclick="Commute.getStationSuggestions(this.form, this.name);"/]</p>
							[/@]
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
			
			
			//attach autocomplete  
		    $("input#stationSearch").autocomplete({  
		
		        //define callback to format results  
		        source: function(req, responseFn){  
					
					params = {};
			        params = 'suggestStation&' + $(this.element).closest("form").serialize();
					$.ajax({
						  type: 'POST',
						  url: $(this.element).closest("form").action,
						  data: params,
						  success: function(data) {
						  	var suggestions = [];
						  	for (i = 0; i < data.sites.length; i++) {
								suggestions.push({label: data.sites[i].name + '/' + data.sites[i].area, value: data.sites[i].name, id: data.sites[i].identifier});
							}
							responseFn(suggestions);
						  },
						  dataType: 'json'
					});
		        },
		        
		        //define select handler  
			    select: function(e, ui) {  
					$("input#stationIdentifier").val(ui.item.id);
			    },  
			
		        //define change handler  
		        change: function() {  
					
		        }
		    });

			$("input#stationSearch").autocomplete("option", "minLength", 2);
			$("input#stationSearch").autocomplete("option", "delay", 250);
		 });
		
	</script>	
</html>
