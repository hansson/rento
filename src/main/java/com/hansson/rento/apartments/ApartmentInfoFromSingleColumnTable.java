package com.hansson.rento.apartments;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hansson.rento.entities.Apartment;

public class ApartmentInfoFromSingleColumnTable extends ApartmentDescriptionHandler {

	public Set<Apartment> handle(Document doc, String baseUrl, String landlord) {
		Set<Apartment> apartmentList = new HashSet<Apartment>();
		Elements elements = doc.getElementsByTag("tr");
		for(Element element : elements) {
			Apartment apartment = new Apartment(landlord);
			apartment.setUrl(baseUrl);
			String description = element.text();
			apartment.setRent(Integer.valueOf(getRentFromDescription(description)));
			apartment.setAddress(getAddressFromDescription(description));
			apartment.setSize(Integer.valueOf(getSizeFromDescription(description)));
			apartment.setRooms(Double.valueOf(getRoomsFromDescription(description)));
			if(!apartment.getRent().equals(new Integer(0)) && !apartment.getSize().equals(new Integer(0)) && !apartment.getSize().equals(new Double(0.0))) {
				apartmentList.add(apartment);
			}
		}
		return apartmentList;
	}

}
