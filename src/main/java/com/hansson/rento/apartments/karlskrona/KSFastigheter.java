package com.hansson.rento.apartments.karlskrona;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;
import com.hansson.rento.utils.HtmlUtil;

public class KSFastigheter implements ApartmentsInterface {

	private static final String LANDLORD = "KS Fastigheter";
	private static final String BASE_URL = "http://www.ksfast.se/lediga.htm";
	private static final String CITY = "Karlskrona";

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL).get();
			Elements apartments = doc.getElementsByTag("tbody").get(3).getElementsByTag("tr");
			apartments.remove(0); // Remove non-apartment row
			apartments.remove(0); // Remove header
			for (Element element : apartments) {
				Apartment apartment = new Apartment(LANDLORD);
				Elements informationCells = element.getElementsByTag("td");
				if (!informationCells.get(0).text().equals("Inget ledigt just nu.")) {
					apartment.setAddress(HtmlUtil.textToHtml(informationCells.get(0).text()));
					apartment.setRooms(Double.valueOf(informationCells.get(1).text()));
					apartment.setSize(Integer.valueOf(informationCells.get(2).text()));
					apartment.setRent(Integer.valueOf(informationCells.get(3).text()));
					apartment.setCity(CITY);
					apartment.setIdentifier(apartment.getAddress() + apartment.getCity() + apartment.getRent() + apartment.getSize() + apartment.getRooms());
					apartment.setUrl(BASE_URL);
					apartmentList.add(apartment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
