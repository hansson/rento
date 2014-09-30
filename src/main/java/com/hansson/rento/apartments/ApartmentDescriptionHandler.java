package com.hansson.rento.apartments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApartmentDescriptionHandler {
	
	protected String getRentFromDescription(String description) {
		description = description.toLowerCase();
		Pattern p = Pattern.compile("[\\d ]+(?:\\:-|kr)/m\u00e5n");
		Matcher matcher = p.matcher(description);
		if (matcher.find()) {
			String rent = matcher.group();
			rent = rent.replaceAll("\\D", "");
			return rent;
		}
		return "0";
	}
	
	protected String getAddressFromDescription(String description) {
		Pattern p = Pattern.compile("[A-Za-z\u00c5\u00c4\u00d6\u00e5\u00e4\u00f6]{5,} [\\d]+ ");
		Matcher matcher = p.matcher(description);
		if (matcher.find()) {
			return matcher.group().trim();
		}
		return "";
	}
	
	protected String getSizeFromDescription(String description) { 
		description = description.toLowerCase();
		Pattern p = Pattern.compile("[\\d]+m2");
		Matcher matcher = p.matcher(description);
		if (matcher.find()) {
			String size = matcher.group();
			size = size.replaceAll("m2", "");
			size = size.replaceAll("\\D", "");
			return size;
		}
		return "0";
	}
	
	protected String getRoomsFromDescription(String description) {
		description = description.toLowerCase();
		Pattern p = Pattern.compile("[\\d ]+rum och k\u00f6k");
		Matcher matcher = p.matcher(description);
		if (matcher.find()) {
			String rooms = matcher.group();
			rooms = rooms.replaceAll("\\D", "");
			return rooms;
		}
		return "0";
	}


}
