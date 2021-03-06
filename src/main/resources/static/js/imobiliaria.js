// Everytime the page refreshes it looks for the class and calls the function
$(function() {
	$('[rel="tooltip"]').tooltip();
});

// Sorting the table on click in the fields
var table = $('table');
    
$('#id-header, #name-header, #state-header, #city-header, #district-header, #business-header, #category-header, #bedroom-header, #address-header')
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
