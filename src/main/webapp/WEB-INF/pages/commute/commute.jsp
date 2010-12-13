<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/WEB-INF/layout/default.jsp">
    
    
    <stripes:layout-component name="html_head">
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/commute.js"></script>
    </stripes:layout-component>
    
    <stripes:layout-component name="contents">
        
		<div class="post">
			<h2 class="title">Add a commute watch</h2>
			<p class="meta">meta info</p>
			<div class="entry">
				<stripes:form action="/commute.html">
				<p>
					<stripes:errors/>
					Station/site: <br> 
					<stripes:text name="publicTransportSetting.name" id="name"/> <br>
					
					Station identifier: <br> 
					<stripes:text name="publicTransportSetting.stationId" id="stationId"/> <br>
					
					Line: <br>
					<stripes:text name="publicTransportSetting.line" id="publicTransportSetting.line"/> <br>
					
					Filter: <br> 
	    			<stripes:text name="publicTransportSetting.filter" id="publicTransportSetting.filter"/> <br>
	    			
	    			
				</p>
				<p>
					<stripes:submit name="save" value="Save" />
				</p>
				
				</stripes:form>
			</div>
		</div>
		
    </stripes:layout-component>

	<stripes:layout-component name="script">
    	<script type="text/javascript">
    	
    	$(document).ready(function() {
			$("select, input").uniform();
			
			
			//attach autocomplete  
		    $("input#name").autocomplete({  
		
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
			    	$("input#name").val(ui.item.value);
					$("input#stationId").val(ui.item.id);
			    },  
			
		        //define change handler  
		        change: function() {  
					
		        }
		    });

			$("input#name").autocomplete("option", "minLength", 2);
			$("input#name").autocomplete("option", "delay", 10);
		 });
    	</script>
    </stripes:layout-component>
</stripes:layout-render>