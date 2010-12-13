<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My home</title>
		
		<link href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.8.6.custom.css" rel="stylesheet" type="text/css" media="screen"  />
	 	<link href="${pageContext.request.contextPath}/css/uniform.default.css" rel="stylesheet" type="text/css" media="screen" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.6.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.uniform.min.js"></script>
		 
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/commute.js"></script>
	</head>

	<body>
		<h2>My home</h2>
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
	</body>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("select, input").uniform();
			loadCommutes();
		 });
		
	</script>
</html>
