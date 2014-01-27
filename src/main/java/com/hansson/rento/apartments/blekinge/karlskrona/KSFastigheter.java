package com.hansson.rento.apartments.blekinge.karlskrona;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class KSFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private static final String LANDLORD = "KS Fastigheter";
	private static final String BASE_URL = "http://www.ksfast.se/lediga.htm";
	private static final String CITY = "Karlskrona";

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL);
		if (doc != null) {
			Elements apartments = doc.getElementsByTag("tbody").get(3).getElementsByTag("tr");
			apartments.remove(0); // Remove non-apartment row
			apartments.remove(0); // Remove header
			for (Element element : apartments) {
				try {
					Apartment apartment = new Apartment(LANDLORD);
					Elements informationCells = element.getElementsByTag("td");
					if (!informationCells.get(0).text().contains("Inget ledigt just nu")) {
						apartment.setAddress(informationCells.get(0).text());
						apartment.setRooms(Double.valueOf(informationCells.get(1).text()));
						apartment.setSize(Integer.valueOf(informationCells.get(2).text()));
						apartment.setRent(Integer.valueOf(informationCells.get(3).text()));
						apartment.setCity(CITY);
						apartment.setIdentifier(apartment.getAddress() + apartment.getCity() + apartment.getRent() + apartment.getSize() + apartment.getRooms());
						apartment.setUrl(BASE_URL);
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
