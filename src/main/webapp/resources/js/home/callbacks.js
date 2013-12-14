$('#apartment-table-body').load('/apartments');
		
$('#cityForm').on('submit', function(event) {

    var link = $(this).attr('action');

    $.post(link,$(this).serialize(),function(data, status) {
    	$('#apartment-table-body').html(data);
    });

    return false; // dont post it automatically
});

$('.sortable-header').on('click', function(event) {
	var id = $(this).attr('id');
	var cookie = $.cookie('sorted');
	if(!cookie || cookie != id) {
		sortApartments(id, true);
	} else {
		var asc = $.cookie('sorted-asc');
		if(asc == "true") {
			sortApartments(id, false);
		} else {
			sortApartments(id, true);
		}
	}
});