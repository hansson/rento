package com.hansson.rento.apartments.karlskrona;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class LindebergFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private final static String KARLSKRONA = "Karlskrona";
	private final static String LANDLORD = "Lindeberg Fastigheter";
	private final static String BASE_URL = "http://www.lindebergfastigheter.se/";

	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL + "ajax.aspx?content=3");
		if (doc != null) {
			Elements apartments = doc.getElementsByClass("module").get(0).getElementsByAttribute("href");
			for (Element element : apartments) {
				try {
					String link = element.attr("href");
					Pattern p = Pattern.compile("item=\\d+");
					Matcher matcher = p.matcher(link);
					if (matcher.find()) {
						Apartment apartment = new Apartment(LANDLORD);
						apartment.setCity(KARLSKRONA);
						doc = connect(BASE_URL + "ajax.aspx?content=3&" + matcher.group());
						Element header = doc.getElementsByTag("h1").get(0);
						String[] roomsAndAddress = header.text().split(" r o k ");
						apartment.setRooms(Double.valueOf(roomsAndAddress[0]));
						apartment.setAddress(roomsAndAddress[1]);
						apartment.setIdentifier(matcher.group());
						apartment.setRent(Integer.valueOf(doc.getElementsByClass("price").get(0).text().replaceAll("\\D", "")));
						String description = doc.getElementsByClass("description").get(0).text();
						p = Pattern.compile("[\\d\\.,]+ kvm");
						matcher = p.matcher(description);
						matcher.find();
						apartment.setSize(Integer.valueOf(matcher.group().split(" ")[0]));
						apartment.setUrl(BASE_URL + link);
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
