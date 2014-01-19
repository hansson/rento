package com.hansson.rento.apartments;

import org.jsoup.nodes.Document;

import com.hansson.rento.entities.Apartment;

public class ApartmentInfoSecondPage extends ApartmentDescriptionHandler {

	public Apartment handle(Document doc, Apartment apartment) {

		String description = doc.text();
		if (apartment.getRent() == null) {
			String rent = getRentFromDescription(description);
			apartment.setRent(Integer.valueOf(rent));
		}
		return apartment;

	}

}
