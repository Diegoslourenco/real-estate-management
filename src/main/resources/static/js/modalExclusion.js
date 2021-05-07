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