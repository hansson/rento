$('#city-form').on('submit', function(event) {

    var link = $(this).attr('action');
    
    $(".ui-autocomplete").hide();

    $.post(link,$(this).serialize(),function(data, status) {
    	$('#apartment-table-body').html(data);
    });

    return false; // dont post it automatically
});