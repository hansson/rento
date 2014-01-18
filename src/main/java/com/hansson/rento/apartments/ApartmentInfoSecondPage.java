package com.hansson.rento.apartments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.hansson.rento.entities.Apartment;

public class ApartmentInfoSecondPage {

	public Apartment handle(Document doc, Apartment apartment) {
		
		String description = doc.text();
		if(apartment.getRent() == null) {
			String rent = getRentFromDescription(description);
			apartment.setRent(Integer.valueOf(rent));
		}
		return apartment;

	}

	protected String getRentFromDescription(String description) {
		description = description.toLowerCase();
		Pattern p = Pattern.compile("[\\d ]+:-/mån");
		Matcher matcher = p.matcher(description);
		if(matcher.find()) {
			String rent = matcher.group();
			rent = rent.replaceAll("\\D", "");
			return rent;
		}
		return "0";
	}

}
