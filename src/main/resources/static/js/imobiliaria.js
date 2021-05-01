// Function that gets the element id to be deleted and show the description in the modal confirmation
$('#confirmarExclusaoModal').on('show.bs.modal', function(event) {
	
	// Receives the button that started the event in the html file
	var button = $(event.relatedTarget);
	
	// Saves the objects's field
	var id = button.data('id');
	var description = button.data('description');
	var url = button.data('url');
	
	// Defines the variable modal in order to use the methods
	var modal = $(this);
	
	// Finds the fields action and form inside the html file
	var form = modal.find('form');
	var action = form.data('url-base');
	
	// Add the url and a slash to complete the action
	action += url + '/';
	
	// Adds to the action string the id
	form.attr('action', action + id);
	
	// Adds to the message the description to be deleted
	if (url === 'estados') {
		modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + description + '</strong> e os municípios e bairros associados?');
	}
	else if (url === 'municipios') {
		modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + description + '</strong> e os bairros associados?');
	}
	else {
		modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + description + '</strong>?');
	}
});


// Everytime the page refreshes it looks for the class and calls the function
$(function() {
	$('[rel="tooltip"]').tooltip();
	
	$('.js-currency').maskMoney({decimal: ',', thousands: '.'});
});

// Sorting the table on click in the fields
var table = $('table');
    
$('#id-header, #name-header, #state-header, #city-header, #district-header, #business-header, #category-header, #bedroom-header')
    .wrapInner('<span title="sort this column"/>')
    .each( function () {
        
        var th = $(this),
            thIndex = th.index(),
            inverse = false;
        
        th.click(function() {
            
            table.find('td').filter( function() {
                
                return $(this).index() === thIndex;
                
            }).sortElements(function(a, b){
                
                return $.text([a]) > $.text([b]) ?
                    inverse ? -1 : 1
                    : inverse ? 1 : -1;
                
            }, function() {
                
                // parentNode is the element to move
                return this.parentNode; 
                
            });
            
            inverse = !inverse;
                
        });
            
    });
