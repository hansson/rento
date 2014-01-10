$('#city-form').on('submit', function(event) {
	
	var url = '/resources/json/';
	url += $('input:first').val().toLowerCase();
	url += '.json';
	url = url.replace(' ','_');
	url = url.replace('å','ao');
	url = url.replace('ä','ae');
	url = url.replace('ö','oe');

    $(".ui-autocomplete").hide();

    $.get(url,function(data, status) {
    	l = jQuery.parseJSON(data).landlords;
    	reloadLandlords();
    }).fail(function(){
    	l = [];
    	reloadLandlords();
    });

    return false; // dont post it automatically
});