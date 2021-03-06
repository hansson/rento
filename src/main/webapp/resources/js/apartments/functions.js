function reloadApartments() {
	var r = new Array(), j = -1;
	if(a.length > 0) {
		for (var key = 0, size = a.length; key < size; key++) {
			r[++j] = '<tr class="apartment" style="cursor: pointer" data="';
		    r[++j] = a[key].mUrl;
		    r[++j] = '"><td>';
			r[++j] = a[key].mCity;
			r[++j] = '</td><td class="hidden-phone">';
			r[++j] = a[key].mArea;
			r[++j] = '</td><td class="hidden-phone hidden-tablet">';
			r[++j] = a[key].mAddress;
			r[++j] = '</td><td>';
			r[++j] = a[key].mRent;
			r[++j] = ' kr</td><td>';
			r[++j] = a[key].mSize;
			r[++j] = ' kvm</td><td>';
			r[++j] = a[key].mRooms;
			r[++j] = '</td><td class="hidden-phone hidden-tablet">';
			r[++j] = a[key].mLandlord;
			r[++j] = '</td><td>';
			
			var d = new Date(a[key].mAdded);
			
			var date;
			if(d.getDate() < 10) {
				date = "0" + d.getDate();
			} else {
				date = d.getDate();
			}
			
			var month;
			if((d.getMonth() + 1) < 10) {
				month = "0" + (d.getMonth() + 1);
			} else {
				month = (d.getMonth() + 1);
			}
			
			r[++j] = d.getFullYear() + "-" + month + "-" + date;
			r[++j] = '</td></tr>';
			
		}
		$('#apartment-table-body').html(r.join(''));
		$(".apartment").on("click", function() {
			window.open($(this).attr('data'));
			return false;
		});
	} else {
		$('#apartment-table-body').html('<tr><td colspan="8"><h4>Inga tr&auml;ffar!</h4></td><tr>');
	}
	
}
		
function sortApartments(prop, asc) {
	$.cookie('sorted', prop);
	$.cookie('sorted-asc', asc);
    a = a.sort(function(a, b) {
    	var first = a[prop];
    	var second = b[prop];
    	
    	if((!first && asc == true) || (!second && asc == false)) {
    		return 1; 
    	}
    	
    	if((!first && asc == false) || (!second && asc == true)) {
    		return -1; 
    	}
    	
    	try {
	    	if(first.indexOf('&') !== -1) {
	    		first = replaceSpecialCharacters(first);
	    	}
	    	
			if(second.indexOf('&') !== -1) {
				second = replaceSpecialCharacters(second);
	    	}
    	} catch(e) {
    		//Will happen if first/second is a number
    	}
    	
    	if(prop == "mAdded") {
    		first = new Date(first);
    		second = new Date(second);
    	} 
    	
		if (asc) {
			return (first > second) ? 1 : ((first < second) ? -1 : 0);
		} else {
			return (second > first) ? 1 : ((second < first) ? -1 : 0);
		} 
    });
    reloadApartments();
}
		
function replaceSpecialCharacters(replaceString) {
	replaceString = replaceString.replace('&aring;', 'å');
	replaceString = replaceString.replace('&auml;', 'ä');
	replaceString = replaceString.replace('&ouml;', 'ö');
	replaceString = replaceString.replace('&Aring;', 'Å');
	replaceString = replaceString.replace('&Auml;', 'Ä');
	replaceString = replaceString.replace('&Ouml;', 'Ö');
	return replaceString;
}

function updateFilters() {
	a = [];
	
	for (var i = 0 ; i < aOrig.length ; i++) { 
		
		var currentRooms = aOrig[i].mRooms;
		var currentRent = aOrig[i].mRent;
		var currentSize = aOrig[i].mSize;
		
		//Fix high and null values and set to the slider max to be able to decide if they should be in or not
		if(!currentRooms || currentRooms > 6) {
			currentRooms = 6;
		}
		
		if(!currentRent || currentRent > 20000) {
			currentRent = 20000;
		}
		
		if(!currentSize || currentSize > 200) {
			currentSize = 200;
		}
		
		//Decide if an apartment is within the range
		if(currentRooms < $("#room-slider").slider("values", 0) || currentRooms > $("#room-slider").slider("values", 1)) {
			continue;
		}
		
		if(currentRent < $("#rent-slider").slider("values", 0) || currentRent > $("#rent-slider").slider("values", 1)) {
			continue;
		}
		
		if(currentSize < $("#size-slider").slider("values", 0) || currentSize > $("#size-slider").slider("values", 1)) {
			continue;
		}
		
		if(aOrig[i].mStudent && !$("#student-apartment").is(":checked") ) {
			continue;
		}
		
		if(!aOrig[i].mStudent && !$("#non-student-apartment").is(":checked") ) {
			continue;
		}
		
		a.push(aOrig[i]);
	}
	sortApartments($.cookie('sorted'),$.cookie('sorted-asc') == "true");
}