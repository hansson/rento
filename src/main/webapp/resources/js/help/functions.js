function replaceSpecialCharacters(replaceString) {
	replaceString = replaceString.replace('&aring;', 'å');
	replaceString = replaceString.replace('&auml;', 'ä');
	replaceString = replaceString.replace('&ouml;', 'ö');
	replaceString = replaceString.replace('&Aring;', 'Å');
	replaceString = replaceString.replace('&Auml;', 'Ä');
	replaceString = replaceString.replace('&Ouml;', 'Ö');
	return replaceString;
}

function reloadLandlords() {
	var r = new Array(), j = -1;
	if(l.length > 0) {
		for (var key = 0, size = l.length; key < size; key++) {
			r[++j] = '<li><a href="';
			r[++j] = l[key].url;
			r[++j] = '" target="_blank">';
			r[++j] = l[key].name;
			r[++j] = '</a></li>';
		}
		$('#landlords').html(r.join(''));
	} else {
		$('#landlords').html('<li><h4>Inga tr&auml;ffar!</h4></li>');
	}
	
}
