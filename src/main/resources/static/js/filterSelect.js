// Filter a select option for the city according to the selected state

$('#state').change(
        function() {
        	
		    var state = $('#state').val();
		    var city = $('#city');
		    city.empty();

		    var cityList = cityMappedByState[state];
		    
		    if (state == 0) {
		    	
	    		$('#city').append($('<option>', {
		    		value: 0,
		    		text: 'Selecione um estado'
		    	}));	 
		    }
		    else if (cityList.length == 0) {
		    	
		    	$('#city').append($('<option>', {
		    		value: 0,
		    		text: 'Cadastre um município'
		    	}));
		    }
		    else {
		    	for (var i = 0; i < cityList.length; i++) {
			    	console.log(cityList[i] + ' ' + cities[cityList[i]])
			    	
			    	$('#city').append($('<option>', {
			    		value: i,
			    		text: cities[cityList[i]]	    		
			    	}));
			    }
		    }	       		    		    
        });