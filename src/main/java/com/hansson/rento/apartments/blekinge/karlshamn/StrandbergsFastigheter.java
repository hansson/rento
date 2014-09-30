package com.hansson.rento.apartments.blekinge.karlshamn;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.ApartmentService;
import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class StrandbergsFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private static final String LANDLORD = "Strandbergs fastigheter AB";
	private static final String BASE_URL = "http://strandbergsfastigheter.se/?page=3";
	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL);
		if (doc != null) {
			Elements apartments = doc.getElementsByTag("tr");
			for (Element element : apartments) {
				try {
					if (element.getElementsByAttributeValue("data-label", "Typ").text().equals("Lägenhet")) {
						Apartment apartment = new Apartment(LANDLORD);
						apartment.setUrl(BASE_URL);
						apartment.setStudent(false);
						apartment.setCity("Karlshamn");
						apartment.setAddress(element.getElementsByAttributeValue("data-label", "Adress").text());
						apartment.setRooms(Double.valueOf(element.getElementsByAttributeValue("data-label", "Antal rum").text()));
						apartment.setSize(Integer.valueOf(element.getElementsByAttributeValue("data-label", "Storlek").text()));
						apartment.setRent(Integer.valueOf(element.getElementsByAttributeValue("data-label", "Hyra").text()));
						apartmentList.add(apartment);
					} 
				} catch (Exception e) {
					mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
					e.printStackTrace();
				}
			}
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
