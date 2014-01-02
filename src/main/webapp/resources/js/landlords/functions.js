function replaceSpecialCharacters(replaceString) {
	replaceString = replaceString.replace('&aring;', 'å');
	replaceString = replaceString.replace('&auml;', 'ä');
	replaceString = replaceString.replace('&ouml;', 'ö');
	replaceString = replaceString.replace('&Aring;', 'Å');
	replaceString = replaceString.replace('&Auml;', 'Ä');
	replaceString = replaceString.replace('&Ouml;', 'Ö');
	return replaceString;
}
