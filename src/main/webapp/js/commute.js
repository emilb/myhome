var Commute = {
	init: function() {
		// do stuff
	},
	
	getStationSuggestions: function(form, event) {
		if (!form.onsubmit) { form.onsubmit = function() { return false; } };
		
		params = {};
        if (event != null) params = event + '&' + $(form).serialize();
        
		$.ajax({
			  type: 'POST',
			  url: form.action,
			  data: params,
			  success: Commute.handleStationSuggestions,
			  dataType: 'json'
		});
	},
	
	handleStationSuggestions: function(response) {
		//alert(response.sites.length);
		
		if (response.sites.length == 0) {
			// Hide the suggestionbox
			$('#suggestions').hide();
			return;
		}
		
		result = '';
		for (i = 0; i < response.sites.length; i++) {
			result += '<li onclick="fill()">' + response.sites[i].name + '</li>';
		}
		$('#suggestions').show();
		$('#autoSuggestionsList').html(result);
		
		//alert(response.sites[0].name);
	}
	
}

$(function(){  
	  
    //attach autocomplete  
    $("#to").autocomplete({  

        //define callback to format results  
        source: function(req, add){  

            //pass request to server  
            $.getJSON("friends.php?callback=?", req, function(data) {  

                //create array for response objects  
                var suggestions = [];  

                //process response  
                $.each(data, function(i, val){  
                suggestions.push(val.name);  
            });  

            //pass array to callback  
            add(suggestions);  
            });  
        },  

	    //define select handler  
	    select: function(e, ui) {  
	
	        //create formatted friend  
	        var friend = ui.item.value,  
	            span = $("<span>").text(friend),  
	            a = $("<a>").addClass("remove").attr({  
	                href: "javascript:",  
	                title: "Remove " + friend  
	            }).text("x").appendTo(span);  
	
	            //add friend to friend div  
	            span.insertBefore("#to");  
	    },  
	
        //define select handler  
        change: function() {  

            //prevent 'to' field being updated and correct position  
            $("#to").val("").css("top", 2);  
        }  
	        
    });  
});  
