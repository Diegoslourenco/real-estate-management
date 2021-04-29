// Filter a select option according to the previous one

$('#state').on('change', function(e) {
      let selector = $(this).val();
      $("#city > option").hide();
      $("#city > option").filter(function(){return $(this).data('choice') == selector}).show();
});