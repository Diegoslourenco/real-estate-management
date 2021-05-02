// Filter a select option according to the previous selected one

// BairroCadastro
$('#state').change(
        function() {
        	
		    var state = $('#state').val();
		    var city = $('#city');
		    city.empty();
		    
		    if (state == 0) {
		    	
	    		$('#city').append($('<option>', {
		    		value: 0,
		    		text: 'Selecione um estado'
		    	}));	 
		    }
		    else if (cityMappedByState[state].length == 0) {
		    	
		    	$('#city').append($('<option>', {
		    		value: 0,
		    		text: 'Cadastre um município'
		    	}));
		    }
		    else {
		    	for (key in cityMappedByState) {			    	
			    	if (key == state) {
			    		// Show all the city options by a state for the user in the dropdown Municipio
			    		for (var i = 0; i < cityMappedByState[state].length; i++) {
			    		
					    	$('#city').append($('<option>', {
					    		value: cityMappedByState[state][i],
					    		text: cities[cityMappedByState[state][i]]	    		
					    	}));	
			    		}
			    	}
			    }
		    }	       		    		    
        });

//ImovelCadastro
$('#state-imovel').change(
        function() {
        	
		    var state = $('#state-imovel').val();
		    var city = $('#city-imovel');
		    var district = $('#district-imovel');
		    city.empty();
		    district.empty();
		    
		    if (state == 0) {
		    	
	    		$('#city-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Selecione um estado'
		    	}));	
	    		
	    		$('#district-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Selecione um município'
		    	}));
		    }
		    else if (cityMappedByState[state].length == 0) {
		    	
		    	$('#city-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Cadastre um município'
		    	}));
		    	
	    		$('#district-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Cadastre um município'
		    	}));
		    }
		    else {
		    	for (key in cityMappedByState) {			    	
			    	if (key == state) {
			    		// Show all the city options by state for the user in the dropdown Municipio		    		
			    		for (var i = 0; i < cityMappedByState[state].length; i++) {
			    		
					    	$('#city-imovel').append($('<option>', {
					    		value: cityMappedByState[state][i],
					    		text: cities[cityMappedByState[state][i]]	    		
					    	}));					    	
			    		}
			    		
			    		// Show all the district options for the first city by a state for the user in the dropdown Bairro	    		
			    		var firstCity = cityMappedByState[state][0];
			    		
			    		if (districtMappedByCity[firstCity].length === 0) {
			    			$('#district-imovel').append($('<option>', {
					    		value: 0,
					    		text: 'Cadastre um bairro'
					    	}));
			    		}			    		
			    		
			    		for (var i = 0; i < districtMappedByCity[firstCity].length; i++) {
			    			
				    		$('#district-imovel').append($('<option>', {
					    		value: districtMappedByCity[firstCity][i],
					    		text: districts[districtMappedByCity[firstCity][i]]
					    	}));
			    		}
			    	}
			    }
		    }	       		    		    
        });

$('#city-imovel').change(
        function() {
        	
		    var city = $('#city-imovel').val();
		    var district = $('#district-imovel');
		    district.empty();
		    
		    if (city == 0) {
		    	
	    		$('#district-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Selecione um município'
		    	}));	 
		    }
		    else if (districtMappedByCity[city].length == 0) {
		    	
		    	$('#district-imovel').append($('<option>', {
		    		value: 0,
		    		text: 'Cadastre um bairro'
		    	}));
		    }
		    else {
		    	// Show all the district options by a city for the user in the dropdown Bairro
		    	for (var i = 0; i < districtMappedByCity[city].length; i++) {
			    	
			    	$('#district-imovel').append($('<option>', {
			    		value: districtMappedByCity[city][i],
			    		text: districts[districtMappedByCity[city][i]]	    		
			    	}));
			    }
		    }	       		    		    
        });